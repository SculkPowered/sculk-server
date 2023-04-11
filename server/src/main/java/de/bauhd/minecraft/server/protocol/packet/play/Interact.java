package de.bauhd.minecraft.server.protocol.packet.play;

import de.bauhd.minecraft.server.protocol.Buffer;
import de.bauhd.minecraft.server.protocol.packet.Packet;
import de.bauhd.minecraft.server.protocol.packet.PacketHandler;

public final class Interact implements Packet {

    private int entityId;
    private int type;
    private float x;
    private float y;
    private float z;
    private int hand;
    private boolean sneaking;

    @Override
    public void decode(Buffer buf) {
        this.entityId = buf.readVarInt();
        this.type = buf.readVarInt();
        if (this.type == 2) {
            this.x = buf.readFloat();
            this.y = buf.readFloat();
            this.z = buf.readFloat();
            this.hand = buf.readVarInt();
        } else if (this.type == 0) {
            this.hand = buf.readVarInt();
        }
        this.sneaking = buf.readBoolean();
    }

    @Override
    public boolean handle(PacketHandler handler) {
        return handler.handle(this);
    }

    @Override
    public int minLength() {
        return 3;
    }

    @Override
    public int maxLength() {
        return 28;
    }

    @Override
    public String toString() {
        return "Interact{" +
                "entityId=" + this.entityId +
                ", type=" + this.type +
                ", x=" + this.x +
                ", y=" + this.y +
                ", z=" + this.z +
                ", hand=" + this.hand +
                ", sneaking=" + this.sneaking +
                '}';
    }
}
