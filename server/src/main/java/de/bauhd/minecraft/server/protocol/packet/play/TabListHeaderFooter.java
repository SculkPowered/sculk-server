package de.bauhd.minecraft.server.protocol.packet.play;

import de.bauhd.minecraft.server.AdvancedMinecraftServer;
import de.bauhd.minecraft.server.protocol.Protocol;
import de.bauhd.minecraft.server.protocol.packet.Packet;
import io.netty5.buffer.Buffer;
import net.kyori.adventure.text.Component;

import static de.bauhd.minecraft.server.protocol.packet.PacketUtils.writeString;

public final class TabListHeaderFooter implements Packet {

    private final Component header;
    private final Component footer;

    public TabListHeaderFooter(final Component header, final Component footer) {
        this.header = header;
        this.footer = footer;
    }

    @Override
    public void encode(Buffer buf, Protocol.Version version) {
        final var serializer = AdvancedMinecraftServer.getGsonSerializer(version);

        writeString(buf, serializer.serialize(this.header));
        writeString(buf, serializer.serialize(this.footer));
    }

    @Override
    public String toString() {
        return "TabListHeaderFooter{" +
                "header=" + this.header +
                ", footer=" + this.footer +
                '}';
    }
}
