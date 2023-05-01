package de.bauhd.minecraft.server.protocol.packet.play;

import de.bauhd.minecraft.server.world.Position;
import de.bauhd.minecraft.server.world.block.Block;
import de.bauhd.minecraft.server.protocol.Buffer;
import de.bauhd.minecraft.server.protocol.packet.Packet;
import de.bauhd.minecraft.server.protocol.packet.PacketHandler;

public final class UseItemOn implements Packet {

    private int hand;
    private Position position;
    private Block.Face face;
    private float x;
    private float y;
    private float z;
    private boolean insideBlock;
    private int sequence;

    @Override
    public void decode(Buffer buf) {
        this.hand = buf.readVarInt();
        this.position = buf.readPosition();
        this.face = Block.Face.class.getEnumConstants()[buf.readVarInt()];
        this.x = buf.readFloat();
        this.y = buf.readFloat();
        this.z = buf.readFloat();
        this.insideBlock = buf.readBoolean();
        this.sequence = buf.readVarInt();
    }

    @Override
    public boolean handle(PacketHandler handler) {
        return handler.handle(this);
    }

    @Override
    public String toString() {
        return "UseItemOn{" +
                "hand=" + this.hand +
                ", position=" + this.position +
                ", face=" + this.face +
                ", x=" + this.x +
                ", y=" + this.y +
                ", z=" + this.z +
                ", insideBlock=" + this.insideBlock +
                ", sequence=" + this.sequence +
                '}';
    }

    public int hand() {
        return this.hand;
    }

    public Position position() {
        return this.position;
    }

    public Block.Face face() {
        return this.face;
    }

    public float x() {
        return this.x;
    }

    public float y() {
        return this.y;
    }

    public float z() {
        return this.z;
    }

    public boolean insideBlock() {
        return this.insideBlock;
    }

    public int sequence() {
        return this.sequence;
    }
}
