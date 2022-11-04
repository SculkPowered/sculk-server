package de.bauhd.minecraft.server.protocol.packet.play;

import de.bauhd.minecraft.server.MinecraftServer;
import de.bauhd.minecraft.server.protocol.Protocol;
import de.bauhd.minecraft.server.protocol.packet.Packet;
import de.bauhd.minecraft.server.protocol.packet.PacketUtils;
import io.netty5.buffer.Buffer;
import net.kyori.adventure.nbt.TagStringIO;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import static de.bauhd.minecraft.server.protocol.packet.PacketUtils.writeString;
import static de.bauhd.minecraft.server.protocol.packet.PacketUtils.writeVarInt;

public final class Login implements Packet {

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

        try {
            final InputStream resourceStream;
            if (version == Protocol.Version.MINECRAFT_1_19) {
                resourceStream = MinecraftServer.class.getClassLoader().getResourceAsStream("codec-1-19.json");
            } else if (version == Protocol.Version.MINECRAFT_1_19_1) {
                resourceStream = MinecraftServer.class.getClassLoader().getResourceAsStream("codec-1-19-1.json");
            } else {
                System.out.println("oh no");
                return;
            }
            assert resourceStream != null;
            final var s = new String(resourceStream.readAllBytes(), StandardCharsets.UTF_8);
            PacketUtils.writeCompoundTag(buf, TagStringIO.get().asCompound(s));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

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
