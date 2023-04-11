package de.bauhd.minecraft.server.protocol.packet.play.scoreboard;

import de.bauhd.minecraft.server.protocol.Buffer;
import de.bauhd.minecraft.server.protocol.packet.Packet;

public final class UpdateScore implements Packet {

    private final String name;
    private final int action;
    private final String objective;
    private final int score;

    public UpdateScore(final String name, final int action, final String objective, final int score) {
        this.name = name;
        this.action = action;
        this.objective = objective;
        this.score = score;
    }

    @Override
    public void encode(Buffer buf) {
        buf.writeString(this.name)
                .writeVarInt(this.action)
                .writeString(this.objective)
                .writeVarInt(this.score);
    }
}
