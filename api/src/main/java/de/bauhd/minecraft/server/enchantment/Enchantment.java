package de.bauhd.minecraft.server.enchantment;

public enum Enchantment {

    // START
    AQUA_AFFINITY(6),
    BANE_OF_ARTHROPODS(15),
    BINDING_CURSE(10),
    BLAST_PROTECTION(3),
    CHANNELING(33),
    DEPTH_STRIDER(8),
    EFFICIENCY(20),
    FEATHER_FALLING(2),
    FIRE_ASPECT(17),
    FIRE_PROTECTION(1),
    FLAME(26),
    FORTUNE(23),
    FROST_WALKER(9),
    IMPALING(31),
    INFINITY(27),
    KNOCKBACK(16),
    LOOTING(18),
    LOYALTY(30),
    LUCK_OF_THE_SEA(28),
    LURE(29),
    MENDING(37),
    MULTISHOT(34),
    PIERCING(36),
    POWER(24),
    PROJECTILE_PROTECTION(4),
    PROTECTION(0),
    PUNCH(25),
    QUICK_CHARGE(35),
    RESPIRATION(5),
    RIPTIDE(32),
    SHARPNESS(13),
    SILK_TOUCH(21),
    SMITE(14),
    SOUL_SPEED(11),
    SWEEPING(19),
    SWIFT_SNEAK(12),
    THORNS(7),
    UNBREAKING(22),
    VANISHING_CURSE(38),
    ;
    // END

    private final int protocolId;

    Enchantment(final int protocolId) {
        this.protocolId = protocolId;
    }

    public int protocolId() {
        return this.protocolId;
    }
}
