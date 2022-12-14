package de.bauhd.minecraft.server.protocol.packet.play;

import de.bauhd.minecraft.server.protocol.Buffer;
import de.bauhd.minecraft.server.protocol.Connection;
import de.bauhd.minecraft.server.protocol.Protocol;
import de.bauhd.minecraft.server.protocol.packet.Packet;

public final class ClientCommand implements Packet {

    private int actionId;

    @Override
    public void decode(Buffer buf, Protocol.Version version) {
        this.actionId = buf.readVarInt();
    }

    @Override
    public boolean handle(Connection connection) {
        if (this.actionId == 1) {
            connection.send(new AwardStatistics());
        }
        return false;
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
