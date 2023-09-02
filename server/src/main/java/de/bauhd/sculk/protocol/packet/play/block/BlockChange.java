package de.bauhd.sculk.protocol.packet.play.block;

import de.bauhd.sculk.protocol.Buffer;
import de.bauhd.sculk.protocol.packet.Packet;

public final class BlockChange implements Packet {

    private final int sequenceId;

    public BlockChange(final int sequenceId) {
        this.sequenceId = sequenceId;
    }

    @Override
    public void encode(Buffer buf) {
        buf.writeVarInt(this.sequenceId);
    }

    @Override
    public String toString() {
        return "BlockChange{" +
                "sequenceId=" + this.sequenceId +
                '}';
    }
}
