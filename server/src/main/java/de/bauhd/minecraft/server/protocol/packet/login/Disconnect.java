package de.bauhd.minecraft.server.protocol.packet.login;

import de.bauhd.minecraft.server.DefaultMinecraftServer;
import de.bauhd.minecraft.server.protocol.Protocol;
import de.bauhd.minecraft.server.protocol.packet.Packet;
import io.netty5.buffer.Buffer;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;

import static de.bauhd.minecraft.server.protocol.packet.PacketUtils.writeString;

public final class Disconnect implements Packet {

    @Override
    public void decode(Buffer buf, Protocol.Version version) {

    }

    @Override
    public void encode(Buffer buf, Protocol.Version version) {
        writeString(buf, DefaultMinecraftServer.getGsonSerializer(version).serialize(Component.text("hjewaipweahehwea").color(NamedTextColor.RED)));
    }
}
