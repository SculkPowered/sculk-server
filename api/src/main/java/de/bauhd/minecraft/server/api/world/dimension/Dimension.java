package de.bauhd.minecraft.server.api.world.dimension;

import net.kyori.adventure.nbt.CompoundBinaryTag;
import org.jetbrains.annotations.NotNull;

public final class Dimension {

    private static int CURRENT_ID = 0;

    private final CompoundBinaryTag nbt;

    private Dimension(final CompoundBinaryTag nbt) {
        this.nbt = nbt;
    }

    public static @NotNull Builder builder(@NotNull String name) {
        return new Builder(name);
    }

    public @NotNull CompoundBinaryTag nbt() {
        return this.nbt;
    }

    public static class Builder {

        private final String name;
        private final int id;
        private final CompoundBinaryTag.Builder builder;

        private Builder(final String name) {
            this.name = name;
            this.id = CURRENT_ID++;
            this.builder = CompoundBinaryTag.builder();
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
            return new Dimension(CompoundBinaryTag.builder()
                    .putString("name", this.name)
                    .putInt("id", this.id)
                    .put("element", this.builder.build())
                    .build());
        }

    }

    public static class Effects {

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

    public class Music {

    }

}
