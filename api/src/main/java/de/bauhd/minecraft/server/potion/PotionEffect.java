package de.bauhd.minecraft.server.potion;

public enum PotionEffect {

    // START
    AWKWARD(4),
    EMPTY(0),
    FIRE_RESISTANCE(12),
    HARMING(27),
    HEALING(25),
    INVISIBILITY(7),
    LEAPING(9),
    LONG_FIRE_RESISTANCE(13),
    LONG_INVISIBILITY(8),
    LONG_LEAPING(10),
    LONG_NIGHT_VISION(6),
    LONG_POISON(30),
    LONG_REGENERATION(33),
    LONG_SLOW_FALLING(42),
    LONG_SLOWNESS(18),
    LONG_STRENGTH(36),
    LONG_SWIFTNESS(15),
    LONG_TURTLE_MASTER(21),
    LONG_WATER_BREATHING(24),
    LONG_WEAKNESS(39),
    LUCK(40),
    MUNDANE(2),
    NIGHT_VISION(5),
    POISON(29),
    REGENERATION(32),
    SLOW_FALLING(41),
    SLOWNESS(17),
    STRENGTH(35),
    STRONG_HARMING(28),
    STRONG_HEALING(26),
    STRONG_LEAPING(11),
    STRONG_POISON(31),
    STRONG_REGENERATION(34),
    STRONG_SLOWNESS(19),
    STRONG_STRENGTH(37),
    STRONG_SWIFTNESS(16),
    STRONG_TURTLE_MASTER(22),
    SWIFTNESS(14),
    THICK(3),
    TURTLE_MASTER(20),
    WATER(1),
    WATER_BREATHING(23),
    WEAKNESS(38),
    // END
    ;

    private final int protocolId;

    PotionEffect(final int protocolId) {
        this.protocolId = protocolId;
    }

    public int protocolId() {
        return this.protocolId;
    }
}
