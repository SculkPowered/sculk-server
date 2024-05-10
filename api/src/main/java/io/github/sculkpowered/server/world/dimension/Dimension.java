package io.github.sculkpowered.server.world.dimension;

import io.github.sculkpowered.server.registry.Registry;
import io.github.sculkpowered.server.world.chunk.Chunk;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.nbt.CompoundBinaryTag;
import org.jetbrains.annotations.NotNull;

/**
 * Represents a dimension.
 */
public final class Dimension implements Registry.Entry {

  public static final Dimension OVERWORLD = builder(Key.key(Key.MINECRAFT_NAMESPACE, "overworld"))
      .piglinSafe(false)
      .hasRaids(true)
      .monsterSpawnLightLevel(CompoundBinaryTag.builder()
          .putString("type", "minecraft:uniform")
          .putInt("max_inclusive", 7)
          .putInt("min_inclusive", 0)
          .build())
      .monsterSpawnBlockLightLimit(0)
      .natural(true)
      .ambientLight(0F)
      .infiniburn("#minecraft:infiniburn_overworld")
      .respawnAnchorWorks(false)
      .hasSkylight(true)
      .bedWorks(true)
      .effects("minecraft:overworld")
      .minY(-64)
      .height(384)
      .logicalHeight(384)
      .coordinateScale(1D)
      .ultrawarm(false)
      .hasCeiling(false)
      .build();

  private static int CURRENT_ID = 0;

  private final Key key;
  private final int id;
  private final CompoundBinaryTag nbt;
  private final int minimumSections;
  private final int maximumSections;

  private Dimension(final Key key, final int id, final CompoundBinaryTag nbt) {
    this.key = key;
    this.id = id;
    this.nbt = nbt;
    final var dimensionHeight = this.nbt.getInt("height");
    final var minY = this.nbt.getInt("min_y");
    this.minimumSections = minY / Chunk.CHUNK_SECTION_SIZE;
    this.maximumSections = (minY + dimensionHeight) / Chunk.CHUNK_SECTION_SIZE;
  }

  public static @NotNull Builder builder(final @NotNull Key key) {
    return new Builder(key);
  }

  public @NotNull Builder toBuilder(final @NotNull Key newKey) {
    return new Builder(newKey, CompoundBinaryTag.builder().put(this.nbt.getCompound("element")));
  }

  @Override
  public @NotNull Key key() {
    return this.key;
  }

  public @NotNull String name() {
    return this.key.asString();
  }

  @Override
  public int id() {
    return this.id;
  }

  @Override
  public @NotNull CompoundBinaryTag asNBT() {
    return this.nbt;
  }

  public int minimumSections() {
    return this.minimumSections;
  }

  public int maximumSections() {
    return this.maximumSections;
  }

  public static final class Builder {

    private final Key key;
    private final int id;
    private final CompoundBinaryTag.Builder builder;

    public Builder(final Key key) {
      this(key, CompoundBinaryTag.builder());
    }

    private Builder(final Key key, final CompoundBinaryTag.Builder builder) {
      this.key = key;
      this.id = CURRENT_ID++;
      this.builder = builder;
    }

    public @NotNull Builder piglinSafe(boolean piglinSafe) {
      this.builder.putBoolean("piglin_safe", piglinSafe);
      return this;
    }

    public @NotNull Builder hasRaids(boolean hasRaids) {
      this.builder.putBoolean("has_raids", hasRaids);
      return this;
    }

    public @NotNull Builder monsterSpawnLightLevel(int monsterSpawnLightLevel) {
      this.builder.putFloat("monster_spawn_light_level", monsterSpawnLightLevel);
      return this;
    }

    public @NotNull Builder monsterSpawnLightLevel(CompoundBinaryTag compoundBinaryTag) {
      this.builder.put("monster_spawn_light_level", compoundBinaryTag);
      return this;
    }

    public @NotNull Builder monsterSpawnBlockLightLimit(int monsterSpawnBlockLightLimit) {
      this.builder.putFloat("monster_spawn_block_light_limit", monsterSpawnBlockLightLimit);
      return this;
    }

    public @NotNull Builder natural(boolean natural) {
      this.builder.putBoolean("natural", natural);
      return this;
    }

    public @NotNull Builder ambientLight(float ambientLight) {
      this.builder.putFloat("ambient_light", ambientLight);
      return this;
    }

    public @NotNull Builder fixedTime(long fixedTime) {
      this.builder.putLong("fixed_time", fixedTime);
      return this;
    }

    public @NotNull Builder infiniburn(@NotNull String infiniburn) {
      this.builder.putString("infiniburn", infiniburn);
      return this;
    }

    public @NotNull Builder respawnAnchorWorks(boolean respawnAnchorWorks) {
      this.builder.putBoolean("respawn_anchor_works", respawnAnchorWorks);
      return this;
    }

    public @NotNull Builder hasSkylight(boolean hasSkylight) {
      this.builder.putBoolean("has_skylight", hasSkylight);
      return this;
    }

    public @NotNull Builder bedWorks(boolean bedWorks) {
      this.builder.putBoolean("bed_works", bedWorks);
      return this;
    }

    public @NotNull Builder effects(@NotNull String effects) {
      this.builder.putString("effects", effects);
      return this;
    }

    public @NotNull Builder minY(int minY) {
      this.builder.putInt("min_y", minY);
      return this;
    }

    /**
     * Height must be dividable by 16
     */
    public @NotNull Builder height(int height) {
      this.builder.putInt("height", height);
      return this;
    }

    public @NotNull Builder logicalHeight(int logicalHeight) {
      this.builder.putInt("logical_height", logicalHeight);
      return this;
    }

    public @NotNull Builder coordinateScale(double coordinateScale) {
      this.builder.putDouble("coordinate_scale", coordinateScale);
      return this;
    }

    public @NotNull Builder ultrawarm(boolean ultrawarm) {
      this.builder.putBoolean("ultrawarm", ultrawarm);
      return this;
    }

    public @NotNull Builder hasCeiling(boolean hasCeiling) {
      this.builder.putBoolean("has_ceiling", hasCeiling);
      return this;
    }

    public @NotNull Dimension build() {
      return new Dimension(this.key, this.id, this.builder.build());
    }

  }

  public static final class Effects {

    private final CompoundBinaryTag.Builder builder = CompoundBinaryTag.builder();

    public static Effects builder() {
      return new Effects();
    }

    public @NotNull Effects skyColor(int skyColor) {
      this.builder.putInt("sky_color", skyColor);
      return this;
    }

    public @NotNull Effects waterFogColor(int waterFogColor) {
      this.builder.putInt("water_fog_color", waterFogColor);
      return this;
    }

    public @NotNull Effects fogColor(int fogColor) {
      this.builder.putInt("fog_color", fogColor);
      return this;
    }

    public @NotNull Effects waterColor(int waterColor) {
      this.builder.putInt("water_color", waterColor);
      return this;
    }

    public CompoundBinaryTag build() {
      return this.builder.build();
    }
  }
}
