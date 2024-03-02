package io.github.sculkpowered.server.protocol.packet.play;

import io.github.sculkpowered.server.protocol.Buffer;
import io.github.sculkpowered.server.protocol.packet.Packet;

import java.util.Arrays;

public final class ChatSuggestions implements Packet {

  private final int action;
  private final String[] suggestions;

  public ChatSuggestions(final int action, final String[] suggestions) {
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
