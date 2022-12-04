package de.bauhd.minecraft.server.protocol.packet.play.container;

import de.bauhd.minecraft.server.protocol.Protocol;
import de.bauhd.minecraft.server.protocol.packet.Packet;
import io.netty5.buffer.Buffer;

public final class ClickContainerButton implements Packet {

    private byte windowId;
    private byte buttonId;

    @Override
    public void decode(Buffer buf, Protocol.Version version) {
        this.windowId = buf.readByte();
        this.buttonId = buf.readByte();
    }

    @Override
    public int minLength() {
        return 2;
    }

    @Override
    public int maxLength() {
        return this.minLength();
    }

    @Override
    public String toString() {
        return "ClickContainerButton{" +
                "windowId=" + this.windowId +
                ", buttonId=" + this.buttonId +
                '}';
    }
}
