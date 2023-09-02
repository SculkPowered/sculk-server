package de.bauhd.sculk.protocol.packet.play;

import de.bauhd.sculk.protocol.Buffer;
import de.bauhd.sculk.protocol.packet.Packet;

public final class GameEvent implements Packet {

    private final int event;
    private final float value;

    public GameEvent(final int event, final float value) {
        this.event = event;
        this.value = value;
    }

    @Override
    public void encode(Buffer buf) {
        buf
                .writeUnsignedByte(this.event)
                .writeFloat(this.value);
    }

    @Override
    public String toString() {
        return "GameEvent{" +
                "event=" + this.event +
                ", value=" + this.value +
                '}';
    }
}
