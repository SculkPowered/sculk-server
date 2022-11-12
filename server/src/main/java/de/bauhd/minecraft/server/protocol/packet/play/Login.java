package de.bauhd.minecraft.server.protocol.packet.play;

import de.bauhd.minecraft.server.protocol.Protocol;
import de.bauhd.minecraft.server.protocol.packet.Packet;
import io.netty5.buffer.Buffer;
import net.kyori.adventure.nbt.CompoundBinaryTag;
import net.kyori.adventure.nbt.ListBinaryTag;
import net.kyori.adventure.nbt.TagStringIO;

import java.io.IOException;
import java.util.List;

import static de.bauhd.minecraft.server.api.world.biome.Biome.PLAINS;
import static de.bauhd.minecraft.server.api.world.dimension.Dimension.OVERWORLD;
import static de.bauhd.minecraft.server.protocol.packet.PacketUtils.*;

public final class Login implements Packet {

    private static final CompoundBinaryTag CHAT_REGISTRY;

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
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private final int entityId;

    public Login(final int entityId) {
        this.entityId = entityId;
    }

    @Override
    public void encode(Buffer buf, Protocol.Version version) {
        buf.writeInt(this.entityId); // Entity Id
        buf.writeBoolean(false); // Hardcode
        buf.writeByte((byte) 1); // GameMode
        buf.writeByte((byte) -3); // Previous GameMode
        writeVarInt(buf, 1); // Dimensions
        writeString(buf, "minecraft:world"); // Dimensions
        writeCompoundTag(buf, CompoundBinaryTag.builder()
                .put("minecraft:worldgen/biome", CompoundBinaryTag.builder()
                        .putString("type", "minecraft:worldgen/biome")
                        .put("value", ListBinaryTag.from(List.of(PLAINS.nbt())))
                        .build())
                .put("minecraft:dimension_type", CompoundBinaryTag.builder()
                        .putString("type", "minecraft:dimension_type")
                        .put("value", ListBinaryTag.from(List.of(OVERWORLD.nbt())))
                        .build())
                .put("minecraft:chat_type", CHAT_REGISTRY)
                .build());
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
