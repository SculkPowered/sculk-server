package de.bauhd.minecraft.server.protocol.packet.play;

import de.bauhd.minecraft.server.protocol.Connection;
import de.bauhd.minecraft.server.protocol.Protocol;
import de.bauhd.minecraft.server.protocol.packet.Packet;
import io.netty5.buffer.Buffer;

import static de.bauhd.minecraft.server.protocol.packet.PacketUtils.readVarInt;

public final class Interact implements Packet {

    private int entityId;
    private int type;
    private float x;
    private float y;
    private float z;
    private int hand;
    private boolean sneaking;

    @Override
    public void decode(Buffer buf, Protocol.Version version) {
        this.entityId = readVarInt(buf);
        this.type = readVarInt(buf);
        if (this.type == 2) {
            this.x = buf.readFloat();
            this.y = buf.readFloat();
            this.z = buf.readFloat();
            this.hand = readVarInt(buf);
        }
        if (this.type == 0) {
            this.hand = readVarInt(buf);
        }
        this.sneaking = buf.readBoolean();
    }

    @Override
    public boolean handle(Connection connection) {
        return false;
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
