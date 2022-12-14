package de.bauhd.minecraft.server.protocol.packet.play;

import de.bauhd.minecraft.server.protocol.Buffer;
import de.bauhd.minecraft.server.protocol.Protocol;
import de.bauhd.minecraft.server.protocol.packet.Packet;
import net.kyori.adventure.text.Component;

public final class ActionBar implements Packet {

    private final Component text;

    public ActionBar(final Component actionBar) {
        this.text = actionBar;
    }

    @Override
    public void encode(Buffer buf, Protocol.Version version) {
        buf.writeComponent(this.text, version);
    }

    @Override
    public String toString() {
        return "ActionBar{" +
                "text=" + this.text +
                '}';
    }
}
