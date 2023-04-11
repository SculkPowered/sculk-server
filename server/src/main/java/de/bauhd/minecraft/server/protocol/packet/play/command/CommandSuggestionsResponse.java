package de.bauhd.minecraft.server.protocol.packet.play.command;

import de.bauhd.minecraft.server.protocol.Buffer;
import de.bauhd.minecraft.server.protocol.packet.Packet;
import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public final class CommandSuggestionsResponse implements Packet {

    private final int id;
    private final int start;
    private final int length;
    private final List<Match> matches;

    public CommandSuggestionsResponse(final int id, final int start, final int length, final List<Match> matches) {
        this.id = id;
        this.start = start;
        this.length = length;
        this.matches = matches;
    }

    @Override
    public void encode(Buffer buf) {
        buf
                .writeVarInt(this.id)
                .writeVarInt(this.start)
                .writeVarInt(this.length)
                .writeVarInt(this.matches.size());
        for (final var match : this.matches) {
            buf.writeString(match.match);
            if (match.toolTip != null) {
                buf
                        .writeBoolean(true)
                        .writeComponent(match.toolTip);
            } else {
                buf.writeBoolean(false);
            }
        }
    }

    @Override
    public String toString() {
        return "CommandSuggestionsResponse{" +
                "id=" + this.id +
                ", start=" + this.start +
                ", length=" + this.length +
                ", matches=" + this.matches +
                '}';
    }

    public record Match(
            @NotNull String match,
            @Nullable Component toolTip) {}

}
