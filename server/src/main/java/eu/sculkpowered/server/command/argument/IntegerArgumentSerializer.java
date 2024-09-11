package eu.sculkpowered.server.command.argument;

import com.mojang.brigadier.arguments.IntegerArgumentType;
import eu.sculkpowered.server.protocol.Buffer;

final class IntegerArgumentSerializer extends AdvancedArgumentSerializer<IntegerArgumentType> {

  public IntegerArgumentSerializer(final int id) {
    super(id);
  }

  @Override
  public void write(IntegerArgumentType argument, Buffer buf) {
    final var hasMinimum = argument.getMinimum() != Integer.MIN_VALUE;
    final var hasMaximum = argument.getMaximum() != Integer.MAX_VALUE;

    byte argumentFlags = 0;
    if (hasMinimum) {
      argumentFlags |= 0x01;
    }
    if (hasMaximum) {
      argumentFlags |= 0x02;
    }

    buf.writeByte(argumentFlags);
    if (hasMinimum) {
      buf.writeInt(argument.getMinimum());
    }
    if (hasMaximum) {
      buf.writeInt(argument.getMaximum());
    }
  }
}
