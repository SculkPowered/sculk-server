package de.bauhd.minecraft.server.protocol.packet.play;

import de.bauhd.minecraft.server.protocol.Buffer;
import de.bauhd.minecraft.server.protocol.Protocol;
import de.bauhd.minecraft.server.protocol.packet.Packet;
import it.unimi.dsi.fastutil.Pair;

import java.util.Map;

public final class EntityMetadata implements Packet {

    private final int entityId;
    private final Map<Integer, Pair<Integer, Object>> map;

    public EntityMetadata(final int entityId, final Map<Integer, Pair<Integer, Object>> map) {
        this.entityId = entityId;
        this.map = map;
    }

    @Override
    public void encode(Buffer buf, Protocol.Version version) {
        buf.writeVarInt(this.entityId);
        this.map.forEach((i, pair) -> {
            buf
                    .writeUnsignedByte(i)
                    .writeVarInt(pair.left());
            final var object = pair.right();
            final var clazz = object.getClass();

        });
        buf.writeUnsignedByte(0xFF);
    }
}
