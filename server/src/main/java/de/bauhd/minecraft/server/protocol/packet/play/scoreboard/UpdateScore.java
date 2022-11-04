package de.bauhd.minecraft.server.protocol.packet.play.scoreboard;

import de.bauhd.minecraft.server.protocol.Protocol;
import de.bauhd.minecraft.server.protocol.packet.Packet;
import io.netty5.buffer.Buffer;

import static de.bauhd.minecraft.server.protocol.packet.PacketUtils.writeString;
import static de.bauhd.minecraft.server.protocol.packet.PacketUtils.writeVarInt;

public final class UpdateScore implements Packet {

    private String name;
    private int score;

    public UpdateScore(final String name, final int score) {
        this.name = name;
        this.score = score;
    }

    public UpdateScore() {
    }

    @Override
    public void decode(Buffer buf, Protocol.Version version) {

    }

    @Override
    public void encode(Buffer buf, Protocol.Version version) {
        writeString(buf, this.name);
        writeVarInt(buf, 0);
        writeString(buf, "board");
        writeVarInt(buf, this.score);
    }
}
