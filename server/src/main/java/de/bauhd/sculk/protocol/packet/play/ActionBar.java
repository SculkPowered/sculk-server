package de.bauhd.sculk.protocol.packet.play;

import de.bauhd.sculk.protocol.Buffer;
import de.bauhd.sculk.protocol.packet.Packet;
import net.kyori.adventure.text.Component;

public final class ActionBar implements Packet {

    private final Component text;

    public ActionBar(final Component actionBar) {
        this.text = actionBar;
    }

    @Override
    public void encode(Buffer buf) {
        buf.writeComponent(this.text);
    }

    @Override
    public String toString() {
        return "ActionBar{" +
                "text=" + this.text +
                '}';
    }
}