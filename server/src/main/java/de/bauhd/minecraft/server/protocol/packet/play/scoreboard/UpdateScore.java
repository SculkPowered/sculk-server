package de.bauhd.minecraft.server.protocol.packet.play.scoreboard;

import de.bauhd.minecraft.server.protocol.Buffer;
import de.bauhd.minecraft.server.protocol.Protocol;
import de.bauhd.minecraft.server.protocol.packet.Packet;

public final class UpdateScore implements Packet {

    private final String name;
    private final int score;

    public UpdateScore(final String name, final int score) {
        this.name = name;
        this.score = score;
    }

    @Override
    public void encode(Buffer buf, Protocol.Version version) {
        buf.writeString(this.name)
                .writeVarInt(0)
                .writeString("board")
                .writeVarInt(this.score);
    }
}
