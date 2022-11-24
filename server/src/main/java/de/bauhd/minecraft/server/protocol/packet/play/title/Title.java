package de.bauhd.minecraft.server.protocol.packet.play.title;

import de.bauhd.minecraft.server.AdvancedMinecraftServer;
import de.bauhd.minecraft.server.protocol.Protocol;
import de.bauhd.minecraft.server.protocol.packet.Packet;
import io.netty5.buffer.Buffer;
import net.kyori.adventure.text.Component;

import static de.bauhd.minecraft.server.protocol.packet.PacketUtils.writeString;

public final class Title implements Packet {

    private final Component text;

    public Title(final Component text) {
        this.text = text;
    }

    @Override
    public void encode(Buffer buf, Protocol.Version version) {
        writeString(buf, AdvancedMinecraftServer.getGsonSerializer(version).serialize(this.text));
    }

    @Override
    public String toString() {
        return "Title{" +
                "text=" + this.text +
                '}';
    }
}
