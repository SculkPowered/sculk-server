package io.github.sculkpowered.server.command.argument;

import com.mojang.brigadier.arguments.DoubleArgumentType;
import io.github.sculkpowered.server.protocol.Buffer;

final class DoubleArgumentSerializer extends AdvancedArgumentSerializer<DoubleArgumentType> {

  public DoubleArgumentSerializer(final int id) {
    super(id);
  }

  @Override
  public void write(DoubleArgumentType argument, Buffer buf) {
    final var hasMinimum = argument.getMinimum() != -Double.MAX_VALUE;
    final var hasMaximum = argument.getMaximum() != Double.MAX_VALUE;

    byte argumentFlags = 0;
    if (hasMinimum) {
      argumentFlags |= 0x01;
    }
    if (hasMaximum) {
      argumentFlags |= 0x02;
    }

    buf.writeByte(argumentFlags);
    if (hasMinimum) {
      buf.writeDouble(argument.getMinimum());
    }
    if (hasMaximum) {
      buf.writeDouble(argument.getMaximum());
    }
  }
}
