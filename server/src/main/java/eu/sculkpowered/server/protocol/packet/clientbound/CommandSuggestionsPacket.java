package eu.sculkpowered.server.protocol.packet.clientbound;

import com.mojang.brigadier.suggestion.Suggestion;
import eu.sculkpowered.server.protocol.Buffer;
import eu.sculkpowered.server.protocol.packet.ClientboundPacket;
import java.util.List;
import net.kyori.adventure.text.Component;

public final class CommandSuggestionsPacket implements ClientboundPacket {

  private final int transactionId;
  private final int start;
  private final int length;
  private final List<Suggestion> matches;

  public CommandSuggestionsPacket(final int transactionId, final int start, final int length,
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
    return "CommandSuggestionsPacket{" +
        "transactionId=" + this.transactionId +
        ", start=" + this.start +
        ", length=" + this.length +
        ", matches=" + this.matches +
        '}';
  }
}
