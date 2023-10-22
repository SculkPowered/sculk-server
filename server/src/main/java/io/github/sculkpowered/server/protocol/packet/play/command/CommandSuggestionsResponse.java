package io.github.sculkpowered.server.protocol.packet.play.command;

import com.mojang.brigadier.suggestion.Suggestion;
import io.github.sculkpowered.server.protocol.Buffer;
import io.github.sculkpowered.server.protocol.packet.Packet;
import java.util.List;
import net.kyori.adventure.text.Component;

public final class CommandSuggestionsResponse implements Packet {

  private final int transactionId;
  private final int start;
  private final int length;
  private final List<Suggestion> matches;

  public CommandSuggestionsResponse(final int transactionId, final int start, final int length,
      final List<Suggestion> matches) {
    this.transactionId = transactionId;
    this.start = start;
    this.length = length;
    this.matches = matches;
  }

  @Override
  public void encode(Buffer buf) {
    buf
        .writeVarInt(this.transactionId)
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
        "transactionId=" + this.transactionId +
        ", start=" + this.start +
        ", length=" + this.length +
        ", matches=" + this.matches +
        '}';
  }
}
