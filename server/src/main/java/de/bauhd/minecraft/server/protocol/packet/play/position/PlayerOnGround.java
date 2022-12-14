package de.bauhd.minecraft.server.protocol.packet.play.position;

import de.bauhd.minecraft.server.protocol.Buffer;
import de.bauhd.minecraft.server.protocol.Protocol;
import de.bauhd.minecraft.server.protocol.packet.Packet;

public final class PlayerOnGround implements Packet {

    private boolean onGround;

    @Override
    public void decode(Buffer buf, Protocol.Version version) {
        this.onGround = buf.readBoolean();
    }

    @Override
    public String toString() {
        return "PlayerOnGround{" +
                "onGround=" + this.onGround +
                '}';
    }
}
