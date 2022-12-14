package de.bauhd.minecraft.server.protocol.packet.play.scoreboard;

import de.bauhd.minecraft.server.protocol.Buffer;
import de.bauhd.minecraft.server.protocol.Protocol;
import de.bauhd.minecraft.server.protocol.packet.Packet;

public final class DisplayObjective implements Packet {

    @Override
    public void encode(Buffer buf, Protocol.Version version) {
        buf
                .writeByte((byte) 1)
                .writeString("board");
    }
}
