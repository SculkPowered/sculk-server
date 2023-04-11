package de.bauhd.minecraft.server.protocol.packet.play;

import de.bauhd.minecraft.server.protocol.Buffer;
import de.bauhd.minecraft.server.protocol.packet.Packet;
import de.bauhd.minecraft.server.protocol.packet.PacketHandler;

public final class SwingArm implements Packet {

    private int hand;

    @Override
    public void decode(Buffer buf) {
        this.hand = buf.readVarInt();
    }

    @Override
    public boolean handle(PacketHandler handler) {
        return handler.handle(this);
    }

    @Override
    public int minLength() {
        return 1;
    }

    @Override
    public int maxLength() {
        return 5;
    }

    @Override
    public String toString() {
        return "SwingArm{" +
                "hand=" + this.hand +
                '}';
    }

    public int hand() {
        return this.hand;
    }
}
