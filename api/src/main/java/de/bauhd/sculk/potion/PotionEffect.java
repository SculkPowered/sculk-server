package de.bauhd.sculk.potion;

/**
 * An enum of all supported potion effects.
 */
@SuppressWarnings("unused")
public enum PotionEffect {

    // START
    AWKWARD("minecraft:awkward", 4),
    EMPTY("minecraft:empty", 0),
    FIRE_RESISTANCE("minecraft:fire_resistance", 12),
    HARMING("minecraft:harming", 27),
    HEALING("minecraft:healing", 25),
    INVISIBILITY("minecraft:invisibility", 7),
    LEAPING("minecraft:leaping", 9),
    LONG_FIRE_RESISTANCE("minecraft:long_fire_resistance", 13),
    LONG_INVISIBILITY("minecraft:long_invisibility", 8),
    LONG_LEAPING("minecraft:long_leaping", 10),
    LONG_NIGHT_VISION("minecraft:long_night_vision", 6),
    LONG_POISON("minecraft:long_poison", 30),
    LONG_REGENERATION("minecraft:long_regeneration", 33),
    LONG_SLOW_FALLING("minecraft:long_slow_falling", 42),
    LONG_SLOWNESS("minecraft:long_slowness", 18),
    LONG_STRENGTH("minecraft:long_strength", 36),
    LONG_SWIFTNESS("minecraft:long_swiftness", 15),
    LONG_TURTLE_MASTER("minecraft:long_turtle_master", 21),
    LONG_WATER_BREATHING("minecraft:long_water_breathing", 24),
    LONG_WEAKNESS("minecraft:long_weakness", 39),
    LUCK("minecraft:luck", 40),
    MUNDANE("minecraft:mundane", 2),
    NIGHT_VISION("minecraft:night_vision", 5),
    POISON("minecraft:poison", 29),
    REGENERATION("minecraft:regeneration", 32),
    SLOW_FALLING("minecraft:slow_falling", 41),
    SLOWNESS("minecraft:slowness", 17),
    STRENGTH("minecraft:strength", 35),
    STRONG_HARMING("minecraft:strong_harming", 28),
    STRONG_HEALING("minecraft:strong_healing", 26),
    STRONG_LEAPING("minecraft:strong_leaping", 11),
    STRONG_POISON("minecraft:strong_poison", 31),
    STRONG_REGENERATION("minecraft:strong_regeneration", 34),
    STRONG_SLOWNESS("minecraft:strong_slowness", 19),
    STRONG_STRENGTH("minecraft:strong_strength", 37),
    STRONG_SWIFTNESS("minecraft:strong_swiftness", 16),
    STRONG_TURTLE_MASTER("minecraft:strong_turtle_master", 22),
    SWIFTNESS("minecraft:swiftness", 14),
    THICK("minecraft:thick", 3),
    TURTLE_MASTER("minecraft:turtle_master", 20),
    WATER("minecraft:water", 1),
    WATER_BREATHING("minecraft:water_breathing", 23),
    WEAKNESS("minecraft:weakness", 38),
    // END
    ;

    private final String key;
    private final int protocolId;

    PotionEffect(final String key, final int protocolId) {
        this.key = key;
        this.protocolId = protocolId;
    }

    public String key() {
        return this.key;
    }

    public int protocolId() {
        return this.protocolId;
    }
}
