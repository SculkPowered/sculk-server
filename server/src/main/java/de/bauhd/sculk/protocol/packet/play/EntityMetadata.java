package de.bauhd.sculk.protocol.packet.play;

import de.bauhd.sculk.entity.Metadata;
import de.bauhd.sculk.protocol.Buffer;
import de.bauhd.sculk.protocol.packet.Packet;

import java.util.Map;

public final class EntityMetadata implements Packet {

    private final int entityId;
    private final Map<Integer, Metadata.Entry<?>> metadata;

    public EntityMetadata(final int entityId, final Map<Integer, Metadata.Entry<?>> metadata) {
        this.entityId = entityId;
        this.metadata = metadata;
    }

    @Override
    public void encode(Buffer buf) {
        buf.writeVarInt(this.entityId);
        this.metadata.forEach((index, entry) -> {
            buf
                    .writeUnsignedByte(index)
                    .writeVarInt(entry.type());
            entry.write(buf);
        });
        buf.writeUnsignedByte(0xFF);
    }
}
