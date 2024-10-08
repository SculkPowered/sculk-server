package eu.sculkpowered.server.command.argument;

import com.mojang.brigadier.arguments.StringArgumentType;
import eu.sculkpowered.server.protocol.Buffer;

final class StringArgumentSerializer extends AdvancedArgumentSerializer<StringArgumentType> {

  public StringArgumentSerializer(final int id) {
    super(id);
  }

  @Override
  public void write(StringArgumentType argument, Buffer buf) {
    buf.writeVarInt(argument.getType().ordinal());
  }
}
