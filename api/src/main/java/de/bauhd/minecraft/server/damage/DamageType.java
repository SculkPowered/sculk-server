package de.bauhd.minecraft.server.damage;

import de.bauhd.minecraft.server.registry.Registry;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.nbt.CompoundBinaryTag;
import org.jetbrains.annotations.NotNull;

public final class DamageType implements Registry.Entry {

    // START
    public static final DamageType ARROW = builder(Key.key("arrow")).exhaustion(0.1).messageId("arrow").scaling("when_caused_by_living_non_player").build();
    public static final DamageType BAD_RESPAWN_POINT = builder(Key.key("bad_respawn_point")).exhaustion(0.1).messageId("badRespawnPoint").scaling("always").deathMessageType("intentional_game_design").build();
    public static final DamageType CACTUS = builder(Key.key("cactus")).exhaustion(0.1).messageId("cactus").scaling("when_caused_by_living_non_player").build();
    public static final DamageType CRAMMING = builder(Key.key("cramming")).exhaustion(0.0).messageId("cramming").scaling("when_caused_by_living_non_player").build();
    public static final DamageType DRAGON_BREATH = builder(Key.key("dragon_breath")).exhaustion(0.0).messageId("dragonBreath").scaling("when_caused_by_living_non_player").build();
    public static final DamageType DROWN = builder(Key.key("drown")).exhaustion(0.0).effects("drowning").messageId("drown").scaling("when_caused_by_living_non_player").build();
    public static final DamageType DRY_OUT = builder(Key.key("dry_out")).exhaustion(0.1).messageId("dryout").scaling("when_caused_by_living_non_player").build();
    public static final DamageType EXPLOSION = builder(Key.key("explosion")).exhaustion(0.1).messageId("explosion").scaling("always").build();
    public static final DamageType FALL = builder(Key.key("fall")).exhaustion(0.0).messageId("fall").scaling("when_caused_by_living_non_player").deathMessageType("fall_variants").build();
    public static final DamageType FALLING_ANVIL = builder(Key.key("falling_anvil")).exhaustion(0.1).messageId("anvil").scaling("when_caused_by_living_non_player").build();
    public static final DamageType FALLING_BLOCK = builder(Key.key("falling_block")).exhaustion(0.1).messageId("fallingBlock").scaling("when_caused_by_living_non_player").build();
    public static final DamageType FALLING_STALACTITE = builder(Key.key("falling_stalactite")).exhaustion(0.1).messageId("fallingStalactite").scaling("when_caused_by_living_non_player").build();
    public static final DamageType FIREBALL = builder(Key.key("fireball")).exhaustion(0.1).effects("burning").messageId("fireball").scaling("when_caused_by_living_non_player").build();
    public static final DamageType FIREWORKS = builder(Key.key("fireworks")).exhaustion(0.1).messageId("fireworks").scaling("when_caused_by_living_non_player").build();
    public static final DamageType FLY_INTO_WALL = builder(Key.key("fly_into_wall")).exhaustion(0.0).messageId("flyIntoWall").scaling("when_caused_by_living_non_player").build();
    public static final DamageType FREEZE = builder(Key.key("freeze")).exhaustion(0.0).effects("freezing").messageId("freeze").scaling("when_caused_by_living_non_player").build();
    public static final DamageType GENERIC = builder(Key.key("generic")).exhaustion(0.0).messageId("generic").scaling("when_caused_by_living_non_player").build();
    public static final DamageType GENERIC_KILL = builder(Key.key("generic_kill")).exhaustion(0.0).messageId("genericKill").scaling("when_caused_by_living_non_player").build();
    public static final DamageType HOT_FLOOR = builder(Key.key("hot_floor")).exhaustion(0.1).effects("burning").messageId("hotFloor").scaling("when_caused_by_living_non_player").build();
    public static final DamageType INDIRECT_MAGIC = builder(Key.key("indirect_magic")).exhaustion(0.0).messageId("indirectMagic").scaling("when_caused_by_living_non_player").build();
    public static final DamageType IN_FIRE = builder(Key.key("in_fire")).exhaustion(0.1).effects("burning").messageId("inFire").scaling("when_caused_by_living_non_player").build();
    public static final DamageType IN_WALL = builder(Key.key("in_wall")).exhaustion(0.0).messageId("inWall").scaling("when_caused_by_living_non_player").build();
    public static final DamageType LAVA = builder(Key.key("lava")).exhaustion(0.1).effects("burning").messageId("lava").scaling("when_caused_by_living_non_player").build();
    public static final DamageType LIGHTNING_BOLT = builder(Key.key("lightning_bolt")).exhaustion(0.1).messageId("lightningBolt").scaling("when_caused_by_living_non_player").build();
    public static final DamageType MAGIC = builder(Key.key("magic")).exhaustion(0.0).messageId("magic").scaling("when_caused_by_living_non_player").build();
    public static final DamageType MOB_ATTACK = builder(Key.key("mob_attack")).exhaustion(0.1).messageId("mob").scaling("when_caused_by_living_non_player").build();
    public static final DamageType MOB_ATTACK_NO_AGGRO = builder(Key.key("mob_attack_no_aggro")).exhaustion(0.1).messageId("mob").scaling("when_caused_by_living_non_player").build();
    public static final DamageType MOB_PROJECTILE = builder(Key.key("mob_projectile")).exhaustion(0.1).messageId("mob").scaling("when_caused_by_living_non_player").build();
    public static final DamageType ON_FIRE = builder(Key.key("on_fire")).exhaustion(0.0).effects("burning").messageId("onFire").scaling("when_caused_by_living_non_player").build();
    public static final DamageType OUTSIDE_BORDER = builder(Key.key("outside_border")).exhaustion(0.0).messageId("outsideBorder").scaling("when_caused_by_living_non_player").build();
    public static final DamageType OUT_OF_WORLD = builder(Key.key("out_of_world")).exhaustion(0.0).messageId("outOfWorld").scaling("when_caused_by_living_non_player").build();
    public static final DamageType PLAYER_ATTACK = builder(Key.key("player_attack")).exhaustion(0.1).messageId("player").scaling("when_caused_by_living_non_player").build();
    public static final DamageType PLAYER_EXPLOSION = builder(Key.key("player_explosion")).exhaustion(0.1).messageId("explosion.player").scaling("always").build();
    public static final DamageType SONIC_BOOM = builder(Key.key("sonic_boom")).exhaustion(0.0).messageId("sonic_boom").scaling("always").build();
    public static final DamageType STALAGMITE = builder(Key.key("stalagmite")).exhaustion(0.0).messageId("stalagmite").scaling("when_caused_by_living_non_player").build();
    public static final DamageType STARVE = builder(Key.key("starve")).exhaustion(0.0).messageId("starve").scaling("when_caused_by_living_non_player").build();
    public static final DamageType STING = builder(Key.key("sting")).exhaustion(0.1).messageId("sting").scaling("when_caused_by_living_non_player").build();
    public static final DamageType SWEET_BERRY_BUSH = builder(Key.key("sweet_berry_bush")).exhaustion(0.1).effects("poking").messageId("sweetBerryBush").scaling("when_caused_by_living_non_player").build();
    public static final DamageType THORNS = builder(Key.key("thorns")).exhaustion(0.1).effects("thorns").messageId("thorns").scaling("when_caused_by_living_non_player").build();
    public static final DamageType THROWN = builder(Key.key("thrown")).exhaustion(0.1).messageId("thrown").scaling("when_caused_by_living_non_player").build();
    public static final DamageType TRIDENT = builder(Key.key("trident")).exhaustion(0.1).messageId("trident").scaling("when_caused_by_living_non_player").build();
    public static final DamageType UNATTRIBUTED_FIREBALL = builder(Key.key("unattributed_fireball")).exhaustion(0.1).effects("burning").messageId("onFire").scaling("when_caused_by_living_non_player").build();
    public static final DamageType WITHER = builder(Key.key("wither")).exhaustion(0.0).messageId("wither").scaling("when_caused_by_living_non_player").build();
    public static final DamageType WITHER_SKULL = builder(Key.key("wither_skull")).exhaustion(0.1).messageId("witherSkull").scaling("when_caused_by_living_non_player").build();
    // END

    private static int CURRENT_ID = 0;

    private final Key key;
    private final CompoundBinaryTag nbt;

    public DamageType(final Key key, final CompoundBinaryTag nbt) {
        this.key = key;
        this.nbt = nbt;
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

    public static @NotNull Builder builder(final @NotNull Key key) {
        return new Builder(key);
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

        public @NotNull Builder effects(@NotNull String effects) {
            this.builder.putString("effects", effects);
            return this;
        }

        public @NotNull Builder exhaustion(double exhaustion) {
            this.builder.putDouble("exhaustion", exhaustion);
            return this;
        }

        public @NotNull Builder messageId(@NotNull String messageId) {
            this.builder.putString("message_id", messageId);
            return this;
        }

        public @NotNull Builder scaling(@NotNull String scaling) {
            this.builder.putString("scaling", scaling);
            return this;
        }

        public @NotNull Builder deathMessageType(@NotNull String deathMessageType) {
            this.builder.putString("death_message_type", deathMessageType);
            return this;
        }

        public @NotNull DamageType build() {
            return new DamageType(this.key, CompoundBinaryTag.builder()
                    .putString("name", this.key.asString())
                    .putInt("id", this.id)
                    .put("element", this.builder.build())
                    .build());
        }
    }
}
