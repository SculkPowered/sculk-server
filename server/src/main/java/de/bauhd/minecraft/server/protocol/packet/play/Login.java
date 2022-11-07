package de.bauhd.minecraft.server.protocol.packet.play;

import de.bauhd.minecraft.server.api.world.biome.Biome;
import de.bauhd.minecraft.server.api.world.dimension.Dimension;
import de.bauhd.minecraft.server.protocol.Protocol;
import de.bauhd.minecraft.server.protocol.packet.Packet;
import io.netty5.buffer.Buffer;
import net.kyori.adventure.nbt.CompoundBinaryTag;
import net.kyori.adventure.nbt.ListBinaryTag;
import net.kyori.adventure.nbt.TagStringIO;

import java.io.IOException;
import java.util.List;

import static de.bauhd.minecraft.server.protocol.packet.PacketUtils.*;

public final class Login implements Packet {

    // TODO

    private static final CompoundBinaryTag CHAT_REGISTRY;

    static {
        try {
            CHAT_REGISTRY = TagStringIO.get().asCompound("""
                    {
                        "value": [
                          {
                            "name": "minecraft:chat",
                            "id": 0,
                            "element": {
                              "chat": {
                                "decoration": {
                                  "parameters": [
                                    "sender",
                                    "content"
                                  ],
                                  "translation_key": "chat.type.text",
                                  "style": {}
                                }
                              },
                              "narration": {
                                "decoration": {
                                  "parameters": [
                                    "sender",
                                    "content"
                                  ],
                                  "translation_key": "chat.type.text.narrate",
                                  "style": {}
                                },
                                "priority": "chat"
                              }
                            }
                          },
                          {
                            "element": {
                              "narration": {
                                "priority": "system"
                              },
                              "chat": {}
                            },
                            "name": "minecraft:system",
                            "id": 1
                          },
                          {
                            "id": 2,
                            "name": "minecraft:game_info",
                            "element": {
                              "overlay": {}
                            }
                          },
                          {
                            "element": {
                              "narration": {
                                "priority": "chat",
                                "decoration": {
                                  "style": {},
                                  "parameters": [
                                    "sender",
                                    "content"
                                  ],
                                  "translation_key": "chat.type.text.narrate"
                                }
                              },
                              "chat": {
                                "decoration": {
                                  "style": {},
                                  "parameters": [
                                    "sender",
                                    "content"
                                  ],
                                  "translation_key": "chat.type.announcement"
                                }
                              }
                            },
                            "id": 3,
                            "name": "minecraft:say_command"
                          },
                          {
                            "id": 4,
                            "element": {
                              "chat": {
                                "decoration": {
                                  "translation_key": "commands.message.display.incoming",
                                  "style": {
                                    "italic": 1,
                                    "color": "gray"
                                  },
                                  "parameters": [
                                    "sender",
                                    "content"
                                  ]
                                }
                              },
                              "narration": {
                                "priority": "chat",
                                "decoration": {
                                  "translation_key": "chat.type.text.narrate",
                                  "style": {},
                                  "parameters": [
                                    "sender",
                                    "content"
                                  ]
                                }
                              }
                            },
                            "name": "minecraft:msg_command"
                          },
                          {
                            "name": "minecraft:team_msg_command",
                            "id": 5,
                            "element": {
                              "narration": {
                                "priority": "chat",
                                "decoration": {
                                  "style": {},
                                  "parameters": [
                                    "sender",
                                    "content"
                                  ],
                                  "translation_key": "chat.type.text.narrate"
                                }
                              },
                              "chat": {
                                "decoration": {
                                  "translation_key": "chat.type.team.text",
                                  "style": {},
                                  "parameters": [
                                    "team_name",
                                    "sender",
                                    "content"
                                  ]
                                }
                              }
                            }
                          },
                          {
                            "id": 6,
                            "element": {
                              "chat": {
                                "decoration": {
                                  "translation_key": "chat.type.emote",
                                  "style": {},
                                  "parameters": [
                                    "sender",
                                    "content"
                                  ]
                                }
                              },
                              "narration": {
                                "priority": "chat",
                                "decoration": {
                                  "style": {},
                                  "translation_key": "chat.type.emote",
                                  "parameters": [
                                    "sender",
                                    "content"
                                  ]
                                }
                              }
                            },
                            "name": "minecraft:emote_command"
                          },
                          {
                            "element": {
                              "chat": {},
                              "narration": {
                                "priority": "chat"
                              }
                            },
                            "id": 7,
                            "name": "minecraft:tellraw_command"
                          }
                        ],
                        "type": "minecraft:chat_type"
                      }""");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static final Dimension OVERWORLD = Dimension.builder("minecraft:overworld")
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

    private static final Biome THE_VOID = Biome.builder("minecraft:plains")
            .precipitation("none")
            .temperature(0.5F)
            .downfall(0.5F)
            .effects(Biome.Effects.builder()
                    .skyColor(8103167)
                    .waterFogColor(329011)
                    .fogColor(12638463)
                    .waterColor(4159204)
            )
            .build();

    @Override
    public void decode(Buffer buf, Protocol.Version version) {

    }

    @Override
    public void encode(Buffer buf, Protocol.Version version) {
        buf.writeInt(0); // Entity Id
        buf.writeBoolean(false); // Hardcode
        buf.writeByte((byte) 1); // GameMode
        buf.writeByte((byte) -3); // Previous GameMode
        writeVarInt(buf, 1); // Dimensions
        writeString(buf, "minecraft:world"); // Dimensions

/*        PacketUtils.writeCompoundTag(buf, CompoundBinaryTag.builder()
                .put("minecraft:dimension_type", CompoundBinaryTag.builder()
                        .putString("type", "minecraft:dimension_type")
                        .put("value", ListBinaryTag.builder()
                                .add(CompoundBinaryTag.builder()
                                        .putString("name", "minecraft:overworld")
                                        .putInt("id", 0)
                                        .put("element", CompoundBinaryTag.builder()
                                                .putInt("ultrawarm", 0)
                                                .putInt("logical_height", 384)
                                                .putString("infiniburn", "#minecraft:infiniburn_overworld")
                                                .putInt("piglin_safe", 0)
                                                .putDouble("ambient_light", 0.0)
                                                .putInt("has_skylight", 1)
                                                .putString("effects", "minecraft:overworld")
                                                .putInt("has_raids", 1)
                                                .putInt("monster_spawn_block_light_limit", 0)
                                                .putInt("respawn_anchor_works", 0)
                                                .putInt("height", 384)
                                                .putInt("has_ceiling", 0)
                                                .put("monster_spawn_light_level", CompoundBinaryTag.builder()
                                                        .put("value", CompoundBinaryTag.builder()
                                                                .putInt("max_inclusive", 7)
                                                                .putInt("min_inclusive", 0)
                                                                .build())
                                                        .putString("type", "minecraft:uniform")
                                                        .build())
                                                .putInt("natural", 1)
                                                .putInt("min_y", -64)
                                                .putDouble("coordinate_scale", 1.0)
                                                .putInt("bed_works", 1)
                                                .build())
                                        .build())
                                .build())
                        .build())
                .put("minecraft:worldgen/biome", CompoundBinaryTag.builder()
                        .putString("type", "minecraft:worldgen/biome")
                        .put("value", ListBinaryTag.builder()
                                .add(CompoundBinaryTag.builder()
                                        .putInt("id", 0)
                                        .build())
                                .build())
                        .build())
                .put("minecraft:chat_type", CompoundBinaryTag.builder()
                        .putString("type", "minecraft:chat_type")
                        .put("value", ListBinaryTag.builder()
                                .build())
                        .build())
                .build());*/

        final var tag = CompoundBinaryTag.builder()
                .put("minecraft:worldgen/biome", CompoundBinaryTag.builder()
                        .putString("type", "minecraft:worldgen/biome")
                        .put("value", ListBinaryTag.from(List.of(THE_VOID.nbt())))
                        .build())
                .put("minecraft:dimension_type", CompoundBinaryTag.builder()
                        .putString("type", "minecraft:dimension_type")
                        .put("value", ListBinaryTag.from(List.of(OVERWORLD.nbt())))
                        .build())
                .put("minecraft:chat_type", CHAT_REGISTRY)
                .build();
        try {
            System.out.println(TagStringIO.get().asString(tag));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        writeCompoundTag(buf, tag);

        writeString(buf, "minecraft:overworld"); // Dimension Type
        writeString(buf, "minecraft:overworld"); // Dimension Name
        buf.writeLong(34723587329438L); // Hashed Seed
        writeVarInt(buf, 20); // Max Players
        writeVarInt(buf, 18); // View Distance
        writeVarInt(buf, 10); // Simulation Distance
        buf.writeBoolean(false); // Reduced Debug Info
        buf.writeBoolean(false); // Enable respawn screen
        buf.writeBoolean(false); // Debug
        buf.writeBoolean(true); // Flat
        buf.writeBoolean(false); // Death Location
    }

}
