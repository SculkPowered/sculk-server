package de.bauhd.minecraft.server.protocol.packet.play;

import de.bauhd.minecraft.server.protocol.Protocol;
import de.bauhd.minecraft.server.protocol.packet.Packet;
import io.netty5.buffer.Buffer;

public final class GameEvent implements Packet {

    private final int event;
    private final float value;

    public GameEvent(final int event, final float value) {
        this.event = event;
        this.value = value;
    }

    @Override
    public void encode(Buffer buf, Protocol.Version version) {
        buf
                .writeUnsignedByte(this.event)
                .writeFloat(this.value);
    }
}