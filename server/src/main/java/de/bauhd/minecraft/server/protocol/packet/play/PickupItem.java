package de.bauhd.minecraft.server.protocol.packet.play;

import de.bauhd.minecraft.server.protocol.Buffer;
import de.bauhd.minecraft.server.protocol.Protocol;
import de.bauhd.minecraft.server.protocol.packet.Packet;

public final class PickupItem implements Packet {

    private final int collectedEntity;
    private final int collector;
    private final int itemCount;

    public PickupItem(final int collectedEntity, final int collector, final int itemCount) {
        this.collectedEntity = collectedEntity;
        this.collector = collector;
        this.itemCount = itemCount;
    }

    @Override
    public void encode(Buffer buf, Protocol.Version version) {
        buf
                .writeVarInt(this.collectedEntity)
                .writeVarInt(this.collector)
                .writeVarInt(this.itemCount);
    }

    @Override
    public String toString() {
        return "PickupItem{" +
                "collectedEntity=" + this.collectedEntity +
                ", collector=" + this.collector +
                ", itemCount=" + this.itemCount +
                '}';
    }
}
