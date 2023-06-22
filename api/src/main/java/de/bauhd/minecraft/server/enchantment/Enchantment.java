package de.bauhd.minecraft.server.enchantment;

/**
 * An enum of all enchantments supported.
 */
@SuppressWarnings("unused")
public enum Enchantment {

    // START
    AQUA_AFFINITY("minecraft:aqua_affinity", 6),
    BANE_OF_ARTHROPODS("minecraft:bane_of_arthropods", 15),
    BINDING_CURSE("minecraft:binding_curse", 10),
    BLAST_PROTECTION("minecraft:blast_protection", 3),
    CHANNELING("minecraft:channeling", 33),
    DEPTH_STRIDER("minecraft:depth_strider", 8),
    EFFICIENCY("minecraft:efficiency", 20),
    FEATHER_FALLING("minecraft:feather_falling", 2),
    FIRE_ASPECT("minecraft:fire_aspect", 17),
    FIRE_PROTECTION("minecraft:fire_protection", 1),
    FLAME("minecraft:flame", 26),
    FORTUNE("minecraft:fortune", 23),
    FROST_WALKER("minecraft:frost_walker", 9),
    IMPALING("minecraft:impaling", 31),
    INFINITY("minecraft:infinity", 27),
    KNOCKBACK("minecraft:knockback", 16),
    LOOTING("minecraft:looting", 18),
    LOYALTY("minecraft:loyalty", 30),
    LUCK_OF_THE_SEA("minecraft:luck_of_the_sea", 28),
    LURE("minecraft:lure", 29),
    MENDING("minecraft:mending", 37),
    MULTISHOT("minecraft:multishot", 34),
    PIERCING("minecraft:piercing", 36),
    POWER("minecraft:power", 24),
    PROJECTILE_PROTECTION("minecraft:projectile_protection", 4),
    PROTECTION("minecraft:protection", 0),
    PUNCH("minecraft:punch", 25),
    QUICK_CHARGE("minecraft:quick_charge", 35),
    RESPIRATION("minecraft:respiration", 5),
    RIPTIDE("minecraft:riptide", 32),
    SHARPNESS("minecraft:sharpness", 13),
    SILK_TOUCH("minecraft:silk_touch", 21),
    SMITE("minecraft:smite", 14),
    SOUL_SPEED("minecraft:soul_speed", 11),
    SWEEPING("minecraft:sweeping", 19),
    SWIFT_SNEAK("minecraft:swift_sneak", 12),
    THORNS("minecraft:thorns", 7),
    UNBREAKING("minecraft:unbreaking", 22),
    VANISHING_CURSE("minecraft:vanishing_curse", 38),
    // END
    ;

    private final String key;
    private final int protocolId;

    Enchantment(final String key, final int protocolId) {
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
