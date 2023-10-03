package de.bauhd.sculk.protocol.packet.play;

import de.bauhd.sculk.entity.Metadata;
import de.bauhd.sculk.protocol.Buffer;
import de.bauhd.sculk.protocol.packet.Packet;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;

public final class EntityMetadata implements Packet {

    private final int entityId;
    private final Int2ObjectMap<Metadata.Entry<?>> metadata;

    public EntityMetadata(final int entityId, final Int2ObjectMap<Metadata.Entry<?>> metadata) {
        this.entityId = entityId;
        this.metadata = metadata;
    }

    @Override
    public void encode(Buffer buf) {
        buf.writeVarInt(this.entityId);
        for (final var entry : this.metadata.int2ObjectEntrySet()) {
            buf
                    .writeUnsignedByte(entry.getIntKey())
                    .writeVarInt(entry.getValue().type());
            entry.getValue().write(buf);
        }
        buf.writeUnsignedByte(0xFF);
    }
}
