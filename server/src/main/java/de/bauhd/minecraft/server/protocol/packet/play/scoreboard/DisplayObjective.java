package de.bauhd.minecraft.server.protocol.packet.play.scoreboard;

import de.bauhd.minecraft.server.protocol.Buffer;
import de.bauhd.minecraft.server.protocol.packet.Packet;

public final class DisplayObjective implements Packet {

    private final byte position;
    private final String scoreName;

    public DisplayObjective(final byte position, final String scoreName) {
        this.position = position;
        this.scoreName = scoreName;
    }

    @Override
    public void encode(Buffer buf) {
        buf
                .writeByte(this.position)
                .writeString(this.scoreName);
    }
}
