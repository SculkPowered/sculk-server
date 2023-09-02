package de.bauhd.sculk.world.biome;

import de.bauhd.sculk.registry.Registry;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.nbt.CompoundBinaryTag;
import org.jetbrains.annotations.NotNull;

/**
 * Represents a biome.
 */
public final class Biome implements Registry.Entry {

    public static final Biome PLAINS = builder(Key.key(Key.MINECRAFT_NAMESPACE, "plains"))
            .precipitation(true)
            .temperature(0.5F)
            .downfall(0.5F)
            .effects(Effects.builder()
                    .skyColor(8103167)
                    .waterFogColor(329011)
                    .fogColor(12638463)
                    .waterColor(4159204)
            )
            .build();

    private static int CURRENT_ID = 0;

    private final Key key;
    private final CompoundBinaryTag nbt;

    private Biome(final Key key, final CompoundBinaryTag nbt) {
        this.key = key;
        this.nbt = nbt;
    }

    public static @NotNull Builder builder(@NotNull Key key) {
        return new Builder(key);
    }

    public @NotNull Builder toBuilder(final Key newKey) {
        return new Builder(newKey, CompoundBinaryTag.builder().put(this.nbt.getCompound("element")));
    }

    @Override
    public @NotNull Key key() {
        return this.key;
    }

    @Override
    public int id() {
        return this.nbt.getInt("id");
    }

    @Override
    public @NotNull CompoundBinaryTag asNBT() {
        return this.nbt;
    }

    public static final class Builder {

        private final Key key;
        private final int id;
        private final CompoundBinaryTag.Builder builder;

        private Builder(final Key key) {
            this(key, CompoundBinaryTag.builder());
        }

        private Builder(final Key key, final CompoundBinaryTag.Builder builder) {
            this.key = key;
            this.id = CURRENT_ID++;
            this.builder = builder;
        }

        public @NotNull Builder precipitation(boolean precipitation) {
            this.builder.putBoolean("has_precipitation", precipitation);
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
            return new Biome(this.key, CompoundBinaryTag.builder()
                    .putString("name", this.key.asString())
                    .putInt("id", this.id)
                    .put("element", this.builder.build())
                    .build());
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

        public @NotNull Effects foliageColor(int foliageColor) {
            this.builder.putInt("foliage_color", foliageColor);
            return this;
        }

        public @NotNull Effects grassColor(int grassColor) {
            this.builder.putInt("grass_color", grassColor);
            return this;
        }

        public @NotNull Effects grassColorModifier(String grassColorModifier) {
            this.builder.putString("grass_color_modifier", grassColorModifier);
            return this;
        }

        public @NotNull Effects music(CompoundBinaryTag binaryTag) {
            this.builder.put("music", binaryTag);
            return this;
        }

        public @NotNull Effects ambientSound(String ambientSound) {
            this.builder.putString("ambient_sound", ambientSound);
            return this;
        }

        public @NotNull Effects additionsSound(CompoundBinaryTag binaryTag) {
            this.builder.put("additions_sound", binaryTag);
            return this;
        }

        public @NotNull Effects moodSound(CompoundBinaryTag binaryTag) {
            this.builder.put("mood_sound", binaryTag);
            return this;
        }

        public @NotNull Effects particle(CompoundBinaryTag binaryTag) {
            this.builder.put("particle", binaryTag);
            return this;
        }

        public CompoundBinaryTag build() {
            return this.builder.build();
        }
    }

    public static class Music {

        private final CompoundBinaryTag.Builder builder = CompoundBinaryTag.builder();

        public @NotNull Music replaceCurrentMusic(final byte b) {
            this.builder.putByte("replace_current_music", b);
            return this;
        }

        public @NotNull Music sound(final String sound) {
            this.builder.putString("sound", sound);
            return this;
        }

        public @NotNull Music maxDelay(final int maxDelay) {
            this.builder.putInt("max_delay", maxDelay);
            return this;
        }

        public @NotNull Music minDelay(final int minDelay) {
            this.builder.putInt("min_delay", minDelay);
            return this;
        }

        public CompoundBinaryTag build() {
            return this.builder.build();
        }
    }
}
