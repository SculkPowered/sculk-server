package de.bauhd.minecraft.server.protocol.packet.play.command;

import com.mojang.brigadier.suggestion.Suggestion;
import de.bauhd.minecraft.server.protocol.Buffer;
import de.bauhd.minecraft.server.protocol.packet.Packet;
import net.kyori.adventure.text.Component;

import java.util.List;

public final class CommandSuggestionsResponse implements Packet {

    private final int id;
    private final int start;
    private final int length;
    private final List<Suggestion> matches;

    public CommandSuggestionsResponse(final int id, final int start, final int length, final List<Suggestion> matches) {
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
            buf.writeString(match.getText());
            if (match.getTooltip() != null) {
                buf
                        .writeBoolean(true)
                        .writeComponent(Component.text(match.getTooltip().getString()));
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
}
