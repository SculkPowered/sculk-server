package de.bauhd.minecraft.server.protocol.packet.play.block;

import de.bauhd.minecraft.server.protocol.Buffer;
import de.bauhd.minecraft.server.protocol.Protocol;
import de.bauhd.minecraft.server.protocol.packet.Packet;

public final class BlockChange implements Packet {

    private final int sequenceId;

    public BlockChange(final int sequenceId) {
        this.sequenceId = sequenceId;
    }

    @Override
    public void encode(Buffer buf, Protocol.Version version) {
        buf.writeVarInt(this.sequenceId);
    }

    @Override
    public String toString() {
        return "BlockChange{" +
                "sequenceId=" + this.sequenceId +
                '}';
    }
}
