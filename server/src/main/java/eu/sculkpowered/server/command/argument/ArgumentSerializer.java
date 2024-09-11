package eu.sculkpowered.server.command.argument;

import com.mojang.brigadier.arguments.ArgumentType;
import eu.sculkpowered.server.protocol.Buffer;

class ArgumentSerializer {

  private final int id;

  protected ArgumentSerializer(int id) {
    this.id = id;
  }

  public void serialize(final ArgumentType<?> argumentType, final Buffer buf) {
    buf.writeVarInt(this.id);
  }
}
