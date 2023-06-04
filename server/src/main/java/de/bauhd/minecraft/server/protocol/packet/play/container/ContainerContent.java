package de.bauhd.minecraft.server.protocol.packet.play.container;

import de.bauhd.minecraft.server.protocol.Buffer;
import de.bauhd.minecraft.server.protocol.packet.Packet;
import de.bauhd.minecraft.server.util.ItemList;

public final class ContainerContent implements Packet {

    private final byte windowId;
    private final int stateId;
    private final ItemList items;

    public ContainerContent(final byte windowId, final int stateId, final ItemList items) {
        this.windowId = windowId;
        this.stateId = stateId;
        this.items = items;
    }

    @Override
    public void encode(Buffer buf) {
        buf
                .writeUnsignedByte(this.windowId)
                .writeVarInt(this.stateId)
                .writeVarInt(this.items.size());
        for (final var item : this.items) {
            buf.writeItem(item);
        }
        buf.writeBoolean(false); // no carried item
    }
}
