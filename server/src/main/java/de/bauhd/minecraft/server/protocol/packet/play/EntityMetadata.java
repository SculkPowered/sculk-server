package de.bauhd.minecraft.server.protocol.packet.play;

import de.bauhd.minecraft.server.protocol.Buffer;
import de.bauhd.minecraft.server.protocol.Protocol;
import de.bauhd.minecraft.server.protocol.packet.Packet;
import it.unimi.dsi.fastutil.Pair;

import java.util.Map;
import java.util.function.Consumer;

public final class EntityMetadata implements Packet {

    private final int entityId;
    private final Consumer<Buffer> bufferConsumer;

    public EntityMetadata(final int entityId, final Consumer<Buffer> bufferConsumer) {
        this.entityId = entityId;
        this.bufferConsumer = bufferConsumer;
    }

    @Override
    public void encode(Buffer buf, Protocol.Version version) {
        buf.writeVarInt(this.entityId);
        this.bufferConsumer.accept(buf);
        buf.writeUnsignedByte(0xFF);
    }
}
