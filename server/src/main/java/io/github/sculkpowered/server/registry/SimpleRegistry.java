package io.github.sculkpowered.server.registry;

import io.github.sculkpowered.server.container.item.Material;
import io.github.sculkpowered.server.damage.DamageTypeRegistry;
import io.github.sculkpowered.server.world.biome.Biome;
import io.github.sculkpowered.server.world.block.BlockRegistry;
import io.github.sculkpowered.server.world.dimension.Dimension;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import net.kyori.adventure.nbt.CompoundBinaryTag;
import net.kyori.adventure.nbt.ListBinaryTag;
import org.jetbrains.annotations.NotNull;

public class SimpleRegistry<E extends Registry.Entry> implements Registry<E> {

  static {
    Registries.DIMENSIONS = new SimpleRegistry<>("minecraft:dimension_type", Dimension.OVERWORLD);
    Registries.BIOMES = new SimpleRegistry<>("minecraft:worldgen/biome", Biome.PLAINS);
    Registries.DAMAGE_TYPES = DamageTypeRegistry.get();
    Registries.BLOCKS = BlockRegistry.addBlocks();
    Registries.MATERIALS = new SimpleRegistry<>("minecraft:item", Material.AIR);
  }

  protected final Map<String, E> byKey = new HashMap<>();
  protected final Int2ObjectMap<E> byId = new Int2ObjectOpenHashMap<>();
  protected final String type;
  protected E def;

  public SimpleRegistry(@NotNull String type) {
    this.type = type;
  }

  public SimpleRegistry(@NotNull String type, @NotNull E def) {
    this.type = type;
    this.def = def;
    this.register(def);
  }

  @Override
  public @NotNull String type() {
    return this.type;
  }

  @Override
  public void register(@NotNull E entry) {
    this.byKey.put(entry.name(), entry);
    this.byId.put(entry.id(), entry);
  }

  @Override
  public E get(@NotNull String key, E def) {
    return this.byKey.getOrDefault(key, def);
  }

  @Override
  public @NotNull E get(int id) {
    return this.byId.get(id);
  }

  @Override
  public @NotNull Collection<E> entries() {
    return this.byKey.values();
  }

  @Override
  public @NotNull E defaultValue() {
    return this.def;
  }

  @Override
  public @NotNull CompoundBinaryTag asNBT() {
    final var list = ListBinaryTag.builder();
    for (final var value : this.entries()) {
      list.add(value.asNBT());
    }
    return CompoundBinaryTag.builder()
        .putString("type", this.type)
        .put("value", list.build())
        .build();
  }

  @Override
  public @NotNull Iterator<E> iterator() {
    return this.entries().iterator();
  }
}
