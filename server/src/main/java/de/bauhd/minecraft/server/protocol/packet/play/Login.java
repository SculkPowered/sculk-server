package de.bauhd.minecraft.server.protocol.packet.play;

import de.bauhd.minecraft.server.protocol.Buffer;
import de.bauhd.minecraft.server.protocol.packet.Packet;
import net.kyori.adventure.nbt.CompoundBinaryTag;
import net.kyori.adventure.nbt.TagStringIO;

import java.io.IOException;

public final class Login implements Packet {

    private static final CompoundBinaryTag CHAT_REGISTRY;
    private static final CompoundBinaryTag DAMAGE_TYPE_REGISTRY;

    // not forever
    static {
        try {
            CHAT_REGISTRY = TagStringIO.get().asCompound("""
                    {
                                                    "type": "minecraft:chat_type",
                                                    "value": [
                                                         {
                                                            "name":"minecraft:chat",
                                                            "id":1,
                                                            "element":{
                                                               "chat":{
                                                                  "translation_key":"chat.type.text",
                                                                  "parameters":[
                                                                     "sender",
                                                                     "content"
                                                                  ]
                                                               },
                                                               "narration":{
                                                                  "translation_key":"chat.type.text.narrate",
                                                                  "parameters":[
                                                                     "sender",
                                                                     "content"
                                                                  ]
                                                               }
                                                            }
                                                         }    ]
                                                }""");
            DAMAGE_TYPE_REGISTRY = TagStringIO.get().asCompound("{\"type\":\"minecraft:damage_type\",\"value\":[{\"name\":\"minecraft:arrow\",\"id\":44,\"element\":{\"exhaustion\":0.1,\"message_id\":\"arrow\",\"scaling\":\"when_caused_by_living_non_player\"}},{\"name\":\"minecraft:bad_respawn_point\",\"id\":44,\"element\":{\"death_message_type\":\"intentional_game_design\",\"exhaustion\":0.1,\"message_id\":\"badRespawnPoint\",\"scaling\":\"always\"}},{\"name\":\"minecraft:cactus\",\"id\":44,\"element\":{\"exhaustion\":0.1,\"message_id\":\"cactus\",\"scaling\":\"when_caused_by_living_non_player\"}},{\"name\":\"minecraft:cramming\",\"id\":44,\"element\":{\"exhaustion\":0.0,\"message_id\":\"cramming\",\"scaling\":\"when_caused_by_living_non_player\"}},{\"name\":\"minecraft:dragon_breath\",\"id\":44,\"element\":{\"exhaustion\":0.0,\"message_id\":\"dragonBreath\",\"scaling\":\"when_caused_by_living_non_player\"}},{\"name\":\"minecraft:drown\",\"id\":44,\"element\":{\"effects\":\"drowning\",\"exhaustion\":0.0,\"message_id\":\"drown\",\"scaling\":\"when_caused_by_living_non_player\"}},{\"name\":\"minecraft:dry_out\",\"id\":44,\"element\":{\"exhaustion\":0.1,\"message_id\":\"dryout\",\"scaling\":\"when_caused_by_living_non_player\"}},{\"name\":\"minecraft:explosion\",\"id\":44,\"element\":{\"exhaustion\":0.1,\"message_id\":\"explosion\",\"scaling\":\"always\"}},{\"name\":\"minecraft:fall\",\"id\":44,\"element\":{\"death_message_type\":\"fall_variants\",\"exhaustion\":0.0,\"message_id\":\"fall\",\"scaling\":\"when_caused_by_living_non_player\"}},{\"name\":\"minecraft:falling_anvil\",\"id\":44,\"element\":{\"exhaustion\":0.1,\"message_id\":\"anvil\",\"scaling\":\"when_caused_by_living_non_player\"}},{\"name\":\"minecraft:falling_block\",\"id\":44,\"element\":{\"exhaustion\":0.1,\"message_id\":\"fallingBlock\",\"scaling\":\"when_caused_by_living_non_player\"}},{\"name\":\"minecraft:falling_stalactite\",\"id\":44,\"element\":{\"exhaustion\":0.1,\"message_id\":\"fallingStalactite\",\"scaling\":\"when_caused_by_living_non_player\"}},{\"name\":\"minecraft:fireball\",\"id\":44,\"element\":{\"effects\":\"burning\",\"exhaustion\":0.1,\"message_id\":\"fireball\",\"scaling\":\"when_caused_by_living_non_player\"}},{\"name\":\"minecraft:fireworks\",\"id\":44,\"element\":{\"exhaustion\":0.1,\"message_id\":\"fireworks\",\"scaling\":\"when_caused_by_living_non_player\"}},{\"name\":\"minecraft:fly_into_wall\",\"id\":44,\"element\":{\"exhaustion\":0.0,\"message_id\":\"flyIntoWall\",\"scaling\":\"when_caused_by_living_non_player\"}},{\"name\":\"minecraft:freeze\",\"id\":44,\"element\":{\"effects\":\"freezing\",\"exhaustion\":0.0,\"message_id\":\"freeze\",\"scaling\":\"when_caused_by_living_non_player\"}},{\"name\":\"minecraft:generic\",\"id\":44,\"element\":{\"exhaustion\":0.0,\"message_id\":\"generic\",\"scaling\":\"when_caused_by_living_non_player\"}},{\"name\":\"minecraft:generic_kill\",\"id\":44,\"element\":{\"exhaustion\":0.0,\"message_id\":\"genericKill\",\"scaling\":\"when_caused_by_living_non_player\"}},{\"name\":\"minecraft:hot_floor\",\"id\":44,\"element\":{\"effects\":\"burning\",\"exhaustion\":0.1,\"message_id\":\"hotFloor\",\"scaling\":\"when_caused_by_living_non_player\"}},{\"name\":\"minecraft:indirect_magic\",\"id\":44,\"element\":{\"exhaustion\":0.0,\"message_id\":\"indirectMagic\",\"scaling\":\"when_caused_by_living_non_player\"}},{\"name\":\"minecraft:in_fire\",\"id\":44,\"element\":{\"effects\":\"burning\",\"exhaustion\":0.1,\"message_id\":\"inFire\",\"scaling\":\"when_caused_by_living_non_player\"}},{\"name\":\"minecraft:in_wall\",\"id\":44,\"element\":{\"exhaustion\":0.0,\"message_id\":\"inWall\",\"scaling\":\"when_caused_by_living_non_player\"}},{\"name\":\"minecraft:lava\",\"id\":44,\"element\":{\"effects\":\"burning\",\"exhaustion\":0.1,\"message_id\":\"lava\",\"scaling\":\"when_caused_by_living_non_player\"}},{\"name\":\"minecraft:lightning_bolt\",\"id\":44,\"element\":{\"exhaustion\":0.1,\"message_id\":\"lightningBolt\",\"scaling\":\"when_caused_by_living_non_player\"}},{\"name\":\"minecraft:magic\",\"id\":44,\"element\":{\"exhaustion\":0.0,\"message_id\":\"magic\",\"scaling\":\"when_caused_by_living_non_player\"}},{\"name\":\"minecraft:mob_attack\",\"id\":44,\"element\":{\"exhaustion\":0.1,\"message_id\":\"mob\",\"scaling\":\"when_caused_by_living_non_player\"}},{\"name\":\"minecraft:mob_attack_no_aggro\",\"id\":44,\"element\":{\"exhaustion\":0.1,\"message_id\":\"mob\",\"scaling\":\"when_caused_by_living_non_player\"}},{\"name\":\"minecraft:mob_projectile\",\"id\":44,\"element\":{\"exhaustion\":0.1,\"message_id\":\"mob\",\"scaling\":\"when_caused_by_living_non_player\"}},{\"name\":\"minecraft:on_fire\",\"id\":44,\"element\":{\"effects\":\"burning\",\"exhaustion\":0.0,\"message_id\":\"onFire\",\"scaling\":\"when_caused_by_living_non_player\"}},{\"name\":\"minecraft:outside_border\",\"id\":44,\"element\":{\"exhaustion\":0.0,\"message_id\":\"outsideBorder\",\"scaling\":\"when_caused_by_living_non_player\"}},{\"name\":\"minecraft:out_of_world\",\"id\":44,\"element\":{\"exhaustion\":0.0,\"message_id\":\"outOfWorld\",\"scaling\":\"when_caused_by_living_non_player\"}},{\"name\":\"minecraft:player_attack\",\"id\":44,\"element\":{\"exhaustion\":0.1,\"message_id\":\"player\",\"scaling\":\"when_caused_by_living_non_player\"}},{\"name\":\"minecraft:player_explosion\",\"id\":44,\"element\":{\"exhaustion\":0.1,\"message_id\":\"explosion.player\",\"scaling\":\"always\"}},{\"name\":\"minecraft:sonic_boom\",\"id\":44,\"element\":{\"exhaustion\":0.0,\"message_id\":\"sonic_boom\",\"scaling\":\"always\"}},{\"name\":\"minecraft:stalagmite\",\"id\":44,\"element\":{\"exhaustion\":0.0,\"message_id\":\"stalagmite\",\"scaling\":\"when_caused_by_living_non_player\"}},{\"name\":\"minecraft:starve\",\"id\":44,\"element\":{\"exhaustion\":0.0,\"message_id\":\"starve\",\"scaling\":\"when_caused_by_living_non_player\"}},{\"name\":\"minecraft:sting\",\"id\":44,\"element\":{\"exhaustion\":0.1,\"message_id\":\"sting\",\"scaling\":\"when_caused_by_living_non_player\"}},{\"name\":\"minecraft:sweet_berry_bush\",\"id\":44,\"element\":{\"effects\":\"poking\",\"exhaustion\":0.1,\"message_id\":\"sweetBerryBush\",\"scaling\":\"when_caused_by_living_non_player\"}},{\"name\":\"minecraft:thorns\",\"id\":44,\"element\":{\"effects\":\"thorns\",\"exhaustion\":0.1,\"message_id\":\"thorns\",\"scaling\":\"when_caused_by_living_non_player\"}},{\"name\":\"minecraft:thrown\",\"id\":44,\"element\":{\"exhaustion\":0.1,\"message_id\":\"thrown\",\"scaling\":\"when_caused_by_living_non_player\"}},{\"name\":\"minecraft:trident\",\"id\":44,\"element\":{\"exhaustion\":0.1,\"message_id\":\"trident\",\"scaling\":\"when_caused_by_living_non_player\"}},{\"name\":\"minecraft:unattributed_fireball\",\"id\":44,\"element\":{\"effects\":\"burning\",\"exhaustion\":0.1,\"message_id\":\"onFire\",\"scaling\":\"when_caused_by_living_non_player\"}},{\"name\":\"minecraft:wither\",\"id\":44,\"element\":{\"exhaustion\":0.0,\"message_id\":\"wither\",\"scaling\":\"when_caused_by_living_non_player\"}},{\"name\":\"minecraft:wither_skull\",\"id\":44,\"element\":{\"exhaustion\":0.1,\"message_id\":\"witherSkull\",\"scaling\":\"when_caused_by_living_non_player\"}}]}");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private final int entityId;
    private final byte gameMode;
    private final CompoundBinaryTag biomeRegistry;
    private final CompoundBinaryTag dimensionRegistry;
    private final String dimensionType;

    public Login(final int entityId, final byte gameMode, final CompoundBinaryTag biomeRegistry,
                 final CompoundBinaryTag dimensionRegistry, final String dimensionType) {
        this.entityId = entityId;
        this.biomeRegistry = biomeRegistry;
        this.dimensionRegistry = dimensionRegistry;
        this.gameMode = gameMode;
        this.dimensionType = dimensionType;
    }

    @Override
    public void encode(Buffer buf) {
        buf
                .writeInt(this.entityId) // Entity id
                .writeBoolean(false) // Hardcode
                .writeByte(this.gameMode) // GameMode
                .writeByte((byte) -1) // Previous GameMode
                .writeVarInt(1) // Dimensions
                .writeString("minecraft:world") // Dimensions
                .writeCompoundTag(CompoundBinaryTag.builder()
                        .put("minecraft:worldgen/biome", this.biomeRegistry)
                        .put("minecraft:dimension_type", this.dimensionRegistry)
                        .put("minecraft:chat_type", CHAT_REGISTRY)
                        .put("minecraft:damage_type", DAMAGE_TYPE_REGISTRY)
                        .build())
                .writeString(this.dimensionType) // Dimension Type
                .writeString("minecraft:overworld") // Dimension Name
                .writeLong(0) // Hashed Seed
                .writeVarInt(20) // Max Players
                .writeVarInt(18) // View Distance
                .writeVarInt(10) // Simulation Distance
                .writeBoolean(false) // Reduced Debug Info
                .writeBoolean(false) // Enable respawn screen
                .writeBoolean(false) // Debug
                .writeBoolean(true) // Flat
                .writeBoolean(false) // Death Location
                .writeVarInt(0); // Portal Cooldown
    }

    @Override
    public String toString() {
        return "Login{" +
                "entityId=" + this.entityId +
                '}';
    }
}
