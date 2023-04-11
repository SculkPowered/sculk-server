package de.bauhd.minecraft.server.protocol.packet.play;

import de.bauhd.minecraft.server.protocol.Buffer;
import de.bauhd.minecraft.server.protocol.packet.Packet;
import net.kyori.adventure.nbt.CompoundBinaryTag;
import net.kyori.adventure.nbt.ListBinaryTag;
import net.kyori.adventure.nbt.TagStringIO;

import java.io.IOException;
import java.util.List;

import static de.bauhd.minecraft.server.api.world.biome.Biome.PLAINS;
import static de.bauhd.minecraft.server.api.world.dimension.Dimension.OVERWORLD;

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
            DAMAGE_TYPE_REGISTRY = TagStringIO.get().asCompound("{\"type\":\"minecraft:damage_type\",\"value\":[{\"name\":\"arrow\",\"id\":42,\"element\":{\"exhaustion\":0.1,\"message_id\":\"arrow\",\"scaling\":\"when_caused_by_living_non_player\"}},{\"name\":\"bad_respawn_point\",\"id\":42,\"element\":{\"death_message_type\":\"intentional_game_design\",\"exhaustion\":0.1,\"message_id\":\"badRespawnPoint\",\"scaling\":\"always\"}},{\"name\":\"cactus\",\"id\":42,\"element\":{\"exhaustion\":0.1,\"message_id\":\"cactus\",\"scaling\":\"when_caused_by_living_non_player\"}},{\"name\":\"cramming\",\"id\":42,\"element\":{\"exhaustion\":0.0,\"message_id\":\"cramming\",\"scaling\":\"when_caused_by_living_non_player\"}},{\"name\":\"dragon_breath\",\"id\":42,\"element\":{\"exhaustion\":0.0,\"message_id\":\"dragonBreath\",\"scaling\":\"when_caused_by_living_non_player\"}},{\"name\":\"drown\",\"id\":42,\"element\":{\"effects\":\"drowning\",\"exhaustion\":0.0,\"message_id\":\"drown\",\"scaling\":\"when_caused_by_living_non_player\"}},{\"name\":\"dry_out\",\"id\":42,\"element\":{\"exhaustion\":0.1,\"message_id\":\"dryout\",\"scaling\":\"when_caused_by_living_non_player\"}},{\"name\":\"explosion\",\"id\":42,\"element\":{\"exhaustion\":0.1,\"message_id\":\"explosion\",\"scaling\":\"always\"}},{\"name\":\"fall\",\"id\":42,\"element\":{\"death_message_type\":\"fall_variants\",\"exhaustion\":0.0,\"message_id\":\"fall\",\"scaling\":\"when_caused_by_living_non_player\"}},{\"name\":\"falling_anvil\",\"id\":42,\"element\":{\"exhaustion\":0.1,\"message_id\":\"anvil\",\"scaling\":\"when_caused_by_living_non_player\"}},{\"name\":\"falling_block\",\"id\":42,\"element\":{\"exhaustion\":0.1,\"message_id\":\"fallingBlock\",\"scaling\":\"when_caused_by_living_non_player\"}},{\"name\":\"falling_stalactite\",\"id\":42,\"element\":{\"exhaustion\":0.1,\"message_id\":\"fallingStalactite\",\"scaling\":\"when_caused_by_living_non_player\"}},{\"name\":\"fireball\",\"id\":42,\"element\":{\"effects\":\"burning\",\"exhaustion\":0.1,\"message_id\":\"fireball\",\"scaling\":\"when_caused_by_living_non_player\"}},{\"name\":\"fireworks\",\"id\":42,\"element\":{\"exhaustion\":0.1,\"message_id\":\"fireworks\",\"scaling\":\"when_caused_by_living_non_player\"}},{\"name\":\"fly_into_wall\",\"id\":42,\"element\":{\"exhaustion\":0.0,\"message_id\":\"flyIntoWall\",\"scaling\":\"when_caused_by_living_non_player\"}},{\"name\":\"freeze\",\"id\":42,\"element\":{\"effects\":\"freezing\",\"exhaustion\":0.0,\"message_id\":\"freeze\",\"scaling\":\"when_caused_by_living_non_player\"}},{\"name\":\"generic\",\"id\":42,\"element\":{\"exhaustion\":0.0,\"message_id\":\"generic\",\"scaling\":\"when_caused_by_living_non_player\"}},{\"name\":\"hot_floor\",\"id\":42,\"element\":{\"effects\":\"burning\",\"exhaustion\":0.1,\"message_id\":\"hotFloor\",\"scaling\":\"when_caused_by_living_non_player\"}},{\"name\":\"indirect_magic\",\"id\":42,\"element\":{\"exhaustion\":0.0,\"message_id\":\"indirectMagic\",\"scaling\":\"when_caused_by_living_non_player\"}},{\"name\":\"in_fire\",\"id\":42,\"element\":{\"effects\":\"burning\",\"exhaustion\":0.1,\"message_id\":\"inFire\",\"scaling\":\"when_caused_by_living_non_player\"}},{\"name\":\"in_wall\",\"id\":42,\"element\":{\"exhaustion\":0.0,\"message_id\":\"inWall\",\"scaling\":\"when_caused_by_living_non_player\"}},{\"name\":\"lava\",\"id\":42,\"element\":{\"effects\":\"burning\",\"exhaustion\":0.1,\"message_id\":\"lava\",\"scaling\":\"when_caused_by_living_non_player\"}},{\"name\":\"lightning_bolt\",\"id\":42,\"element\":{\"exhaustion\":0.1,\"message_id\":\"lightningBolt\",\"scaling\":\"when_caused_by_living_non_player\"}},{\"name\":\"magic\",\"id\":42,\"element\":{\"exhaustion\":0.0,\"message_id\":\"magic\",\"scaling\":\"when_caused_by_living_non_player\"}},{\"name\":\"mob_attack\",\"id\":42,\"element\":{\"exhaustion\":0.1,\"message_id\":\"mob\",\"scaling\":\"when_caused_by_living_non_player\"}},{\"name\":\"mob_attack_no_aggro\",\"id\":42,\"element\":{\"exhaustion\":0.1,\"message_id\":\"mob\",\"scaling\":\"when_caused_by_living_non_player\"}},{\"name\":\"mob_projectile\",\"id\":42,\"element\":{\"exhaustion\":0.1,\"message_id\":\"mob\",\"scaling\":\"when_caused_by_living_non_player\"}},{\"name\":\"on_fire\",\"id\":42,\"element\":{\"effects\":\"burning\",\"exhaustion\":0.0,\"message_id\":\"onFire\",\"scaling\":\"when_caused_by_living_non_player\"}},{\"name\":\"out_of_world\",\"id\":42,\"element\":{\"exhaustion\":0.0,\"message_id\":\"outOfWorld\",\"scaling\":\"when_caused_by_living_non_player\"}},{\"name\":\"player_attack\",\"id\":42,\"element\":{\"exhaustion\":0.1,\"message_id\":\"player\",\"scaling\":\"when_caused_by_living_non_player\"}},{\"name\":\"player_explosion\",\"id\":42,\"element\":{\"exhaustion\":0.1,\"message_id\":\"explosion.player\",\"scaling\":\"always\"}},{\"name\":\"sonic_boom\",\"id\":42,\"element\":{\"exhaustion\":0.0,\"message_id\":\"sonic_boom\",\"scaling\":\"always\"}},{\"name\":\"stalagmite\",\"id\":42,\"element\":{\"exhaustion\":0.0,\"message_id\":\"stalagmite\",\"scaling\":\"when_caused_by_living_non_player\"}},{\"name\":\"starve\",\"id\":42,\"element\":{\"exhaustion\":0.0,\"message_id\":\"starve\",\"scaling\":\"when_caused_by_living_non_player\"}},{\"name\":\"sting\",\"id\":42,\"element\":{\"exhaustion\":0.1,\"message_id\":\"sting\",\"scaling\":\"when_caused_by_living_non_player\"}},{\"name\":\"sweet_berry_bush\",\"id\":42,\"element\":{\"effects\":\"poking\",\"exhaustion\":0.1,\"message_id\":\"sweetBerryBush\",\"scaling\":\"when_caused_by_living_non_player\"}},{\"name\":\"thorns\",\"id\":42,\"element\":{\"effects\":\"thorns\",\"exhaustion\":0.1,\"message_id\":\"thorns\",\"scaling\":\"when_caused_by_living_non_player\"}},{\"name\":\"thrown\",\"id\":42,\"element\":{\"exhaustion\":0.1,\"message_id\":\"thrown\",\"scaling\":\"when_caused_by_living_non_player\"}},{\"name\":\"trident\",\"id\":42,\"element\":{\"exhaustion\":0.1,\"message_id\":\"trident\",\"scaling\":\"when_caused_by_living_non_player\"}},{\"name\":\"unattributed_fireball\",\"id\":42,\"element\":{\"effects\":\"burning\",\"exhaustion\":0.1,\"message_id\":\"onFire\",\"scaling\":\"when_caused_by_living_non_player\"}},{\"name\":\"wither\",\"id\":42,\"element\":{\"exhaustion\":0.0,\"message_id\":\"wither\",\"scaling\":\"when_caused_by_living_non_player\"}},{\"name\":\"wither_skull\",\"id\":42,\"element\":{\"exhaustion\":0.1,\"message_id\":\"witherSkull\",\"scaling\":\"when_caused_by_living_non_player\"}}]}");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private final int entityId;

    public Login(final int entityId) {
        this.entityId = entityId;
    }

    @Override
    public void encode(Buffer buf) {
        buf.writeInt(this.entityId); // Entity id
        buf.writeBoolean(false); // Hardcode
        buf.writeByte((byte) 1); // GameMode
        buf.writeByte((byte) -1); // Previous GameMode
        buf.writeVarInt(1); // Dimensions
        buf.writeString("minecraft:world"); // Dimensions
        final var registryCodec = CompoundBinaryTag.builder()
                .put("minecraft:worldgen/biome", CompoundBinaryTag.builder()
                        .putString("type", "minecraft:worldgen/biome")
                        .put("value", ListBinaryTag.from(List.of(PLAINS.nbt())))
                        .build())
                .put("minecraft:dimension_type", CompoundBinaryTag.builder()
                        .putString("type", "minecraft:dimension_type")
                        .put("value", ListBinaryTag.from(List.of(OVERWORLD.nbt())))
                        .build())
                .put("minecraft:chat_type", CHAT_REGISTRY);
        registryCodec.put("minecraft:damage_type", DAMAGE_TYPE_REGISTRY);
        buf.writeCompoundTag(registryCodec.build());
        buf.writeString("minecraft:overworld"); // Dimension Type
        buf.writeString("minecraft:overworld"); // Dimension Name
        buf.writeLong(34723587329438L); // Hashed Seed
        buf.writeVarInt(20); // Max Players
        buf.writeVarInt(18); // View Distance
        buf.writeVarInt(10); // Simulation Distance
        buf.writeBoolean(false); // Reduced Debug Info
        buf.writeBoolean(false); // Enable respawn screen
        buf.writeBoolean(false); // Debug
        buf.writeBoolean(true); // Flat
        buf.writeBoolean(false); // Death Location
    }

    @Override
    public String toString() {
        return "Login{" +
                "entityId=" + this.entityId +
                '}';
    }
}
