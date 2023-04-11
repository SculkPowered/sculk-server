package de.bauhd.minecraft.server.protocol.packet.play.position;

import de.bauhd.minecraft.server.protocol.Buffer;
import de.bauhd.minecraft.server.protocol.packet.Packet;
import de.bauhd.minecraft.server.protocol.packet.PacketHandler;

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
