package de.bauhd.sculk.protocol.packet.play.position;

import de.bauhd.sculk.protocol.Buffer;
import de.bauhd.sculk.protocol.packet.Packet;
import de.bauhd.sculk.protocol.packet.PacketHandler;

public final class PlayerOnGround implements Packet {

    private boolean onGround;

    @Override
    public void decode(Buffer buf) {
        this.onGround = buf.readBoolean();
    }

    @Override
    public boolean handle(PacketHandler handler) {
        return handler.handle(this);
    }

    @Override
    public String toString() {
        return "PlayerOnGround{" +
                "onGround=" + this.onGround +
                '}';
    }
}
