package de.bauhd.minecraft.server.protocol.packet.play.block;

import de.bauhd.minecraft.server.api.world.Position;
import de.bauhd.minecraft.server.protocol.Buffer;
import de.bauhd.minecraft.server.protocol.Protocol;
import de.bauhd.minecraft.server.protocol.packet.Packet;

public final class BlockAction implements Packet {

    private final Position position;
    private final byte action;
    private final byte actionParameter;
    private final int blockType;

    public BlockAction(final Position position, final byte action, final byte actionParameter, final int blockType) {
        this.position = position;
        this.action = action;
        this.actionParameter = actionParameter;
        this.blockType = blockType;
    }

    @Override
    public void encode(Buffer buf, Protocol.Version version) {
        buf
                .writePosition(this.position)
                .writeUnsignedByte(this.action)
                .writeUnsignedByte(this.actionParameter)
                .writeVarInt(this.blockType);
    }

    @Override
    public String toString() {
        return "BlockAction{" +
                "position=" + this.position +
                ", action=" + this.action +
                ", actionParameter=" + this.actionParameter +
                ", blockType=" + this.blockType +
                '}';
    }
}
