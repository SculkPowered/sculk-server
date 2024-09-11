package eu.sculkpowered.server.protocol.packet.clientbound;

import eu.sculkpowered.server.protocol.Buffer;
import eu.sculkpowered.server.protocol.packet.ClientboundPacket;

import java.util.Arrays;

public final class CustomChatCompletionsPacket implements ClientboundPacket {

  private final int action;
  private final String[] suggestions;

  public CustomChatCompletionsPacket(final int action, final String[] suggestions) {
    this.action = action;
    this.suggestions = suggestions;
  }

  @Override
  public void encode(Buffer buf) {
    buf
        .writeVarInt(this.action)
        .writeVarInt(this.suggestions.length);
      for (final var entry : this.suggestions) {
          buf.writeString(entry);
      }
  }

  @Override
  public String toString() {
    return "ChatSuggestions{" +
        "action=" + this.action +
        ", entries=" + Arrays.toString(this.suggestions) +
        '}';
  }
}
