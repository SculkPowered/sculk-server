package de.bauhd.minecraft.server.protocol.packet.play.scoreboard;

import de.bauhd.minecraft.server.MinecraftServer;
import de.bauhd.minecraft.server.protocol.Protocol;
import de.bauhd.minecraft.server.protocol.packet.Packet;
import io.netty5.buffer.Buffer;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;

import static de.bauhd.minecraft.server.protocol.packet.PacketUtils.writeString;
import static de.bauhd.minecraft.server.protocol.packet.PacketUtils.writeVarInt;

public final class UpdateObjectives implements Packet {

    @Override
    public void decode(Buffer buf, Protocol.Version version) {

    }

    @Override
    public void encode(Buffer buf, Protocol.Version version) {
        writeString(buf, "board");
        buf.writeByte((byte) 0);
        writeString(buf, MinecraftServer.getGsonSerializer(version).serialize(Component.text("Title", NamedTextColor.RED)));
        writeVarInt(buf, 0);
    }
}
