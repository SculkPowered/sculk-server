package eu.sculkpowered.server.command.argument;

import com.mojang.brigadier.arguments.LongArgumentType;
import eu.sculkpowered.server.protocol.Buffer;

final class LongArgumentSerializer extends AdvancedArgumentSerializer<LongArgumentType> {

  public LongArgumentSerializer(final int id) {
    super(id);
  }

  @Override
  public void write(LongArgumentType argument, Buffer buf) {
    final var hasMinimum = argument.getMinimum() != Long.MIN_VALUE;
    final var hasMaximum = argument.getMaximum() != Long.MAX_VALUE;

    byte argumentFlags = 0;
    if (hasMinimum) {
      argumentFlags |= 0x01;
    }
    if (hasMaximum) {
      argumentFlags |= 0x02;
    }

    buf.writeByte(argumentFlags);
    if (hasMinimum) {
      buf.writeLong(argument.getMinimum());
    }
    if (hasMaximum) {
      buf.writeLong(argument.getMaximum());
    }
  }
}
