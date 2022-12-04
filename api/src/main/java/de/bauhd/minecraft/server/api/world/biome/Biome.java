package de.bauhd.minecraft.server.api.world.biome;

import net.kyori.adventure.nbt.CompoundBinaryTag;
import org.jetbrains.annotations.NotNull;

public final class Biome {

    private static int CURRENT_ID = 0;

    public static final Biome PLAINS = Biome.builder("minecraft:plains")
            .precipitation("none")
            .temperature(0.5F)
            .downfall(0.5F)
            .effects(Effects.builder()
                    .skyColor(8103167)
                    .waterFogColor(329011)
                    .fogColor(12638463)
                    .waterColor(4159204)
            )
            .build();

    private final CompoundBinaryTag nbt;

    private Biome(final CompoundBinaryTag nbt) {
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

        public @NotNull Builder precipitation(@NotNull String precipitation) {
            this.builder.putString("precipitation", precipitation);
            return this;
        }

        public @NotNull Builder depth(float depth) {
            this.builder.putFloat("depth", depth);
            return this;
        }

        public @NotNull Builder temperature(float temperature) {
            this.builder.putFloat("temperature", temperature);
            return this;
        }

        public @NotNull Builder scale(float scale) {
            this.builder.putFloat("scale", scale);
            return this;
        }

        public @NotNull Builder downfall(float downfall) {
            this.builder.putFloat("downfall", downfall);
            return this;
        }

        public @NotNull Builder category(@NotNull String category) {
            this.builder.putString("category", category);
            return this;
        }

        public @NotNull Builder temperatureModifer(@NotNull String temperatureModifer) {
            this.builder.putString("temperature_modifer", temperatureModifer);
            return this;
        }

        public @NotNull Builder effects(@NotNull Effects effects) {
            this.builder.put("effects", effects.build());
            return this;
        }

        public @NotNull Biome build() {
            return new Biome(CompoundBinaryTag.builder()
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
