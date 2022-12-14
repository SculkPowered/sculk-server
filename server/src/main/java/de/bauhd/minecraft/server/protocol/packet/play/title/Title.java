package de.bauhd.minecraft.server.protocol.packet.play.title;

import de.bauhd.minecraft.server.protocol.Buffer;
import de.bauhd.minecraft.server.protocol.Protocol;
import de.bauhd.minecraft.server.protocol.packet.Packet;
import net.kyori.adventure.text.Component;

public final class Title implements Packet {

    private final Component text;

    public Title(final Component text) {
        this.text = text;
    }

    @Override
    public void encode(Buffer buf, Protocol.Version version) {
        buf.writeComponent(this.text, version);
    }

    @Override
    public String toString() {
        return "Title{" +
                "text=" + this.text +
                '}';
    }
}
