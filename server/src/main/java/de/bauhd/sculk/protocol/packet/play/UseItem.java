package de.bauhd.sculk.protocol.packet.play;

import de.bauhd.sculk.protocol.Buffer;
import de.bauhd.sculk.protocol.packet.Packet;
import de.bauhd.sculk.protocol.packet.PacketHandler;

public final class UseItem implements Packet {

    private int hand;
    private int sequence;

    @Override
    public void decode(Buffer buf) {
        this.hand = buf.readVarInt();
        this.sequence = buf.readVarInt();
    }

    @Override
    public boolean handle(PacketHandler handler) {
        return handler.handle(this);
    }

    @Override
    public String toString() {
        return "UseItem{" +
                "hand=" + this.hand +
                ", sequence=" + this.sequence +
                '}';
    }

    public int hand() {
        return this.hand;
    }

    public int sequence() {
        return this.sequence;
    }
}
