package de.bauhd.sculk.protocol.packet.play;

import de.bauhd.sculk.protocol.Buffer;
import de.bauhd.sculk.protocol.packet.Packet;
import de.bauhd.sculk.protocol.packet.PacketHandler;

public final class ClientCommand implements Packet {

    private int actionId;

    @Override
    public void decode(Buffer buf) {
        this.actionId = buf.readVarInt();
    }

    @Override
    public boolean handle(PacketHandler handler) {
        return handler.handle(this);
    }

    public int actionId() {
        return this.actionId;
    }

    @Override
    public int minLength() {
        return 1; // VarInt can only be 0 or 1
    }

    @Override
    public int maxLength() {
        return this.minLength();
    }
}
