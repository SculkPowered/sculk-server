package io.github.sculkpowered.server.world.chunk.loader;

import io.github.sculkpowered.server.SculkServer;
import io.github.sculkpowered.server.util.CoordinateUtil;
import io.github.sculkpowered.server.world.SculkWorld;
import io.github.sculkpowered.server.world.WorldLoader;
import io.github.sculkpowered.server.world.block.Block;
import io.github.sculkpowered.server.world.chunk.ChunkGenerator;
import io.github.sculkpowered.server.world.chunk.SculkChunk;
import io.github.sculkpowered.server.world.section.Palette;
import io.github.sculkpowered.server.world.section.PaletteHolder;
import io.github.sculkpowered.server.world.section.Section;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import net.kyori.adventure.nbt.BinaryTagIO;
import net.kyori.adventure.nbt.CompoundBinaryTag;
import net.kyori.adventure.nbt.ListBinaryTag;
import net.kyori.adventure.nbt.StringBinaryTag;
import org.jetbrains.annotations.NotNull;

public final class AnvilLoader extends DefaultChunkLoader {

  private static final int SECTOR_SIZE = 4096;

  private final SculkServer server;
  private final WorldLoader loader;
  private final Path regionPath;
  private final Map<String, RegionFile> regionCache;

  public AnvilLoader(final SculkServer server, final ChunkGenerator generator,
      final WorldLoader.Anvil loader) {
    super(generator);
    this.server = server;
    this.loader = loader;
    this.regionPath = loader.path().resolve("region");
    this.regionCache = new HashMap<>();
  }

  @Override
  public @NotNull SculkChunk loadChunk(final SculkWorld world, final int x, final int z) {
    final var fileName =
        "r." + CoordinateUtil.regionCoordinate(x) + "." + CoordinateUtil.regionCoordinate(z)
            + ".mca";
    try {
      SculkChunk chunk;
      if (!this.regionCache.containsKey(fileName)) {
        final var file = this.regionPath.resolve(fileName);
        if (Files.exists(file)) {
          this.regionCache.put(fileName, new RegionFile(file));
        } else {
          return super.loadChunk(world, x, z);
        }
      }
      chunk = this.regionCache.get(fileName).getChunk(this.server, world, x, z);
      if (chunk == null) {
        chunk = super.loadChunk(world, x, z);
      }
      return chunk;
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  private final class RegionFile {

    private final RandomAccessFile accessFile;

    private final int[] locations = new int[1024];

    private RegionFile(final Path path) throws IOException {
      this.accessFile = new RandomAccessFile(path.toFile(), "r");
      this.accessFile.seek(0);

      for (var i = 0; i < this.locations.length; i++) {
        final var location = this.accessFile.readInt();
        this.locations[i] = location;
      }
    }

    private SculkChunk getChunk(final SculkServer server, final SculkWorld world, final int chunkX,
        final int chunkZ) throws IOException {
      final var offset =
          this.sectorOffset(this.locations[(chunkX & 31) + (chunkZ & 31) * 32]) * SECTOR_SIZE;
      var buf = ByteBuffer.allocate(5);
      this.accessFile.getChannel().read(buf, offset);
      final var length = buf.getInt(0);
      if (length < 0) {
        return null;
      }
      final var compressionScheme = buf.get(4);
      if (compressionScheme < 1) {
        return null;
      }
      buf = ByteBuffer.allocate(length);
      this.accessFile.getChannel().read(buf, offset + 5);
      buf.flip();

      final var nbt = BinaryTagIO.reader(Integer.MAX_VALUE)
          .read(new ByteArrayInputStream(buf.array(), buf.position(), length - buf.position()),
              switch (compressionScheme) {
                case 1 -> BinaryTagIO.Compression.GZIP;
                case 2 -> BinaryTagIO.Compression.ZLIB;
                case 3 -> BinaryTagIO.Compression.NONE;
                default -> throw new IllegalStateException(
                    "Unexpected compression scheme: " + compressionScheme);
              });
      final var sectionList = nbt.getList("sections");
      if (sectionList.size() == 0) {
        return null;
      }
      final var sections = new Section[sectionList.size()];
      for (var i = 0; i < sectionList.size(); i++) {
        final var compound = (CompoundBinaryTag) sectionList.get(i);
        final var states = compound.getCompound("block_states");
        final var blockPalette = states.getList("palette");
        if (blockPalette.equals(ListBinaryTag.empty())) {
          sections[i] = new Section();
          continue;
        }
        final var section = new Section(compound.getByteArray("SkyLight"),
            compound.getByteArray("BlockLight"));

        loadBlocks(section, blockPalette, states);
        loadBiomes(server, section, compound.getCompound("biomes"));

        sections[i] = section;
      }
      final var chunk = new SculkChunk(world, chunkX, chunkZ, sections,
          nbt.getCompound("Heightmaps", world.dimension().heightmaps()));
      if (AnvilLoader.this.loader.blockEntities()) {
        for (final var blockEntity : nbt.getList("block_entities")) {
          loadBlockEntity(chunk, (CompoundBinaryTag) blockEntity);
        }
      }
      return chunk;
    }

    private long sectorOffset(final int location) {
      return location >>> 8;
    }

  }

  public static void loadBlocks(
      final Section section,
      final ListBinaryTag blockPalette,
      final CompoundBinaryTag states
  ) {
    final var blocks = (PaletteHolder) section.blocks();
    final var palette = new int[blockPalette.size()];
    for (var k = 0; k < palette.length; k++) {
      final var entry = blockPalette.getCompound(k);
      final var block = entry.getString("Name");
      final var properties = entry.getCompound("Properties");
      if (!properties.equals(CompoundBinaryTag.empty())) {
        final var map = new HashMap<String, String>(
            properties.keySet().size()); // TODO: change if #size is available
        for (final var property : properties) {
          map.put(property.getKey(), ((StringBinaryTag) property.getValue()).value());
        }
        palette[k] = Block.get(block).properties(map).id();
      } else {
        palette[k] = Block.get(block).id();
      }
    }
    if (palette.length == 1) {
      blocks.fill(palette[0]);
      return;
    }
    blocks.setIndirectPalette(palette, states.getLongArray("data"));
  }

  public static void loadBiomes(
      final SculkServer server,
      final Section section,
      final CompoundBinaryTag biomeData
  ) {
    final var biomes = (PaletteHolder) section.biomes();
    final var biomePalette = biomeData.getList("palette");
    final var palette = new int[biomePalette.size()];
    final var registry = server.biomeRegistry();
    var unknownBiome = false;
    for (var k = 0; k < palette.length; k++) {
      final var value = biomePalette.getCompound(k).getString("value");
      final var biome = registry.get(value, null);
      if (biome != null) {
        palette[k] = k;
      } else {
        unknownBiome = true;
        palette[k] = -1;
        break;
      }
    }
    if (palette.length == 1 || unknownBiome) {
      var biome = palette[0];
      if (biome == -1) {
        biome = registry.defaultValue().id();
      }
      biomes.fill(biome);
      return;
    }

    biomes.setIndirectPalette(palette, biomeData.getLongArray("data"));
  }

  public static void loadBlockEntity(SculkChunk chunk, CompoundBinaryTag compound) {
    final var x = compound.getInt("x");
    final var y = compound.getInt("y");
    final var z = compound.getInt("z");
    final var entity = (Block.Entity<?>) chunk.block(x, y, z);
    chunk.block(x, y, z, entity.nbt(compound.remove("id")
        .remove("x").remove("y").remove("z").remove("keepPacked")));
  }

  public static CompoundBinaryTag blockStatesToNbt(final Palette palette) {
    final var paletteToValue = palette.paletteToValue();
    final var blocks = ListBinaryTag.builder();
    for (final var value : paletteToValue) {
      final var block = Block.get(value);
      final var properties = CompoundBinaryTag.builder();
      for (final var entry : block.properties().entrySet()) {
        properties.put(entry.getKey(), StringBinaryTag.stringBinaryTag(entry.getValue()));
      }
      blocks.add(CompoundBinaryTag.builder()
          .putString("Name", block.key())
          .put("Properties", properties.build())
          .build());
    }
    final var nbt = CompoundBinaryTag.builder().put("palette", blocks.build());
    if (paletteToValue.length != 1) {
      nbt.putLongArray("data", palette.values());
    }
    return nbt.build();
  }

  public static CompoundBinaryTag biomesToNbt(final SculkServer server, final Palette palette) {
    final var paletteToValue = palette.paletteToValue();
    final var biomes = ListBinaryTag.builder();
    for (final var value : paletteToValue) {
      biomes.add(CompoundBinaryTag.builder()
          .putString("value", server.biomeRegistry().get(value).name())
          .build());
    }

    final var nbt = CompoundBinaryTag.builder().put("palette", biomes.build());
    if (paletteToValue.length != 1) {
      nbt.putLongArray("data", palette.values());
    }
    return nbt.build();
  }
}
