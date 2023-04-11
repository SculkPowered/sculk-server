package de.bauhd.minecraft.server.protocol.packet.play;

import de.bauhd.minecraft.server.protocol.Buffer;
import de.bauhd.minecraft.server.protocol.packet.Packet;
import net.kyori.adventure.text.Component;

public final class TabListHeaderFooter implements Packet {

    private final Component header;
    private final Component footer;

    public TabListHeaderFooter(final Component header, final Component footer) {
        this.header = header;
        this.footer = footer;
    }

    @Override
    public void encode(Buffer buf) {
        buf
                .writeComponent(this.header)
                .writeComponent(this.footer);
    }

    @Override
    public String toString() {
        return "TabListHeaderFooter{" +
                "header=" + this.header +
                ", footer=" + this.footer +
                '}';
    }
}
