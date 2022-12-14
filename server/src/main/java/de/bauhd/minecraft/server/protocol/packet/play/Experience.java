package de.bauhd.minecraft.server.protocol.packet.play;

import de.bauhd.minecraft.server.protocol.Buffer;
import de.bauhd.minecraft.server.protocol.Protocol;
import de.bauhd.minecraft.server.protocol.packet.Packet;

public final class Experience implements Packet {

    @Override
    public void encode(Buffer buf, Protocol.Version version) {
        buf
                .writeFloat(0)
                .writeVarInt(0)
                .writeVarInt(0);
    }
}
