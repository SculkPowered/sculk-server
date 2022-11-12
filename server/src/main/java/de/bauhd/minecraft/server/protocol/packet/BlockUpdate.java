package de.bauhd.minecraft.server.protocol.packet;

import de.bauhd.minecraft.server.api.world.Position;
import de.bauhd.minecraft.server.protocol.Protocol;
import io.netty5.buffer.Buffer;

import static de.bauhd.minecraft.server.protocol.packet.PacketUtils.writePosition;
import static de.bauhd.minecraft.server.protocol.packet.PacketUtils.writeVarInt;

public final class BlockUpdate implements Packet {

    private final Position position;
    private final int blockId;

    public BlockUpdate(final Position position, final int blockId) {
        this.position = position;
        this.blockId = blockId;
    }

    @Override
    public void encode(Buffer buf, Protocol.Version version) {
        writePosition(buf, this.position);
        writeVarInt(buf, this.blockId);
    }
}
