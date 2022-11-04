package de.bauhd.minecraft.server.protocol.packet.play;

import de.bauhd.minecraft.server.MinecraftServer;
import de.bauhd.minecraft.server.protocol.Protocol;
import de.bauhd.minecraft.server.protocol.packet.Packet;
import io.netty5.buffer.Buffer;
import net.kyori.adventure.text.Component;

import static de.bauhd.minecraft.server.protocol.packet.PacketUtils.writeString;

public final class TabListHeaderFooter implements Packet {

    private Component header;
    private Component footer;

    public TabListHeaderFooter(final Component header, final Component footer) {
        this.header = header;
        this.footer = footer;
    }

    public TabListHeaderFooter() {}

    @Override
    public void decode(Buffer buf, Protocol.Version version) {

    }

    @Override
    public void encode(Buffer buf, Protocol.Version version) {
        final var serializer = MinecraftServer.getGsonSerializer(version);

        writeString(buf, serializer.serialize(this.header));
        writeString(buf, serializer.serialize(this.footer));
    }
}
