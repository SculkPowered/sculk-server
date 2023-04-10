package de.bauhd.minecraft.server.protocol.packet.play;

import de.bauhd.minecraft.server.protocol.Buffer;
import de.bauhd.minecraft.server.protocol.Protocol;
import de.bauhd.minecraft.server.protocol.packet.Packet;
import de.bauhd.minecraft.server.protocol.packet.PacketHandler;

public final class ClientCommand implements Packet {

    private int actionId;

    @Override
    public void decode(Buffer buf, Protocol.Version version) {
        this.actionId = buf.readVarInt();
    }

    @Override
    public boolean handle(PacketHandler handler) {
        return handler.handle(this);
    }

    public int actionId() {
        return this.actionId;
    }

    @Override
    public int minLength() {
        return 1; // VarInt can only be 0 or 1
    }

    @Override
    public int maxLength() {
        return this.minLength();
    }
}
