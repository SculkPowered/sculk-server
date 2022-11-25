package de.bauhd.minecraft.server.api.world.dimension;

import net.kyori.adventure.nbt.CompoundBinaryTag;
import org.jetbrains.annotations.NotNull;

public final class Dimension {

    private static int CURRENT_ID = 0;

    private static final int[] MAGIC = {
            -1, -1, 0, Integer.MIN_VALUE, 0, 0, 1431655765, 1431655765, 0, Integer.MIN_VALUE,
            0, 1, 858993459, 858993459, 0, 715827882, 715827882, 0, 613566756, 613566756,
            0, Integer.MIN_VALUE, 0, 2, 477218588, 477218588, 0, 429496729, 429496729, 0,
            390451572, 390451572, 0, 357913941, 357913941, 0, 330382099, 330382099, 0, 306783378,
            306783378, 0, 286331153, 286331153, 0, Integer.MIN_VALUE, 0, 3, 252645135, 252645135,
            0, 238609294, 238609294, 0, 226050910, 226050910, 0, 214748364, 214748364, 0,
            204522252, 204522252, 0, 195225786, 195225786, 0, 186737708, 186737708, 0, 178956970,
            178956970, 0, 171798691, 171798691, 0, 165191049, 165191049, 0, 159072862, 159072862,
            0, 153391689, 153391689, 0, 148102320, 148102320, 0, 143165576, 143165576, 0,
            138547332, 138547332, 0, Integer.MIN_VALUE, 0, 4, 130150524, 130150524, 0, 126322567,
            126322567, 0, 122713351, 122713351, 0, 119304647, 119304647, 0, 116080197, 116080197,
            0, 113025455, 113025455, 0, 110127366, 110127366, 0, 107374182, 107374182, 0,
            104755299, 104755299, 0, 102261126, 102261126, 0, 99882960, 99882960, 0, 97612893,
            97612893, 0, 95443717, 95443717, 0, 93368854, 93368854, 0, 91382282, 91382282,
            0, 89478485, 89478485, 0, 87652393, 87652393, 0, 85899345, 85899345, 0,
            84215045, 84215045, 0, 82595524, 82595524, 0, 81037118, 81037118, 0, 79536431,
            79536431, 0, 78090314, 78090314, 0, 76695844, 76695844, 0, 75350303, 75350303,
            0, 74051160, 74051160, 0, 72796055, 72796055, 0, 71582788, 71582788, 0,
            70409299, 70409299, 0, 69273666, 69273666, 0, 68174084, 68174084, 0, Integer.MIN_VALUE,
            0, 5
    };

    private static final int[] MOTION_BLOCKING = {
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0
    };

    public static final Dimension OVERWORLD = Dimension.builder("minecraft:overworld")
            .piglinSafe(false)
            .hasRaids(true)
            .monsterSpawnLightLevel(CompoundBinaryTag.builder()
                    .putString("type", "minecraft:uniform")
                    .put("value", CompoundBinaryTag.builder()
                            .putInt("max_inclusive", 7)
                            .putInt("min_inclusive", 0)
                            .build())
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


    private final CompoundBinaryTag nbt;
    private final CompoundBinaryTag heightmaps;

    private Dimension(final CompoundBinaryTag nbt) {
        this.nbt = nbt;
        final var dimensionHeight = this.nbt.getCompound("element").getInt("height");
        final var worldSurface = new int[256];
        for (var x = 0; x < 16; x++) {
            for (var z = 0; z < 16; z++) {
                worldSurface[x + z * 16] = dimensionHeight - 1;
            }
        }
        final var bitsForHeight = Integer.SIZE - Integer.numberOfLeadingZeros(dimensionHeight);
        this.heightmaps = CompoundBinaryTag.builder()
                .putLongArray("MOTION_BLOCKING", encodeBlocks(MOTION_BLOCKING, bitsForHeight))
                .putLongArray("WORLD_SURFACE", encodeBlocks(worldSurface, bitsForHeight))
                .build();
    }

    public static @NotNull Builder builder(@NotNull String name) {
        return new Builder(name);
    }

    public @NotNull CompoundBinaryTag nbt() {
        return this.nbt;
    }

    public @NotNull CompoundBinaryTag heightmaps() {
        return this.heightmaps;
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

    private static long[] encodeBlocks(final int[] blocks, final int bitsPerEntry) {
        final var maxEntryValue = (1L << bitsPerEntry) - 1;
        final var valuesPerLong = (char) (64 / bitsPerEntry);
        final var magicIndex = 3 * (valuesPerLong - 1);
        final var divideMul = Integer.toUnsignedLong(MAGIC[magicIndex]);
        final var divideAdd = Integer.toUnsignedLong(MAGIC[magicIndex + 1]);
        final var divideShift = MAGIC[magicIndex + 2];
        final var size = (blocks.length + valuesPerLong - 1) / valuesPerLong;

        var data = new long[size];

        for (var i = 0; i < blocks.length; i++) {
            final var value = blocks[i];
            final var cellIndex = (int) (i * divideMul + divideAdd >> 32L >> divideShift);
            final var bitIndex = (i - cellIndex * valuesPerLong) * bitsPerEntry;
            data[cellIndex] = data[cellIndex] & ~(maxEntryValue << bitIndex) | (value & maxEntryValue) << bitIndex;
        }

        return data;
    }

}
