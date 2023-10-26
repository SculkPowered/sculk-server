package io.github.sculkpowered.server.protocol.packet.play.command.argument;

import com.mojang.brigadier.arguments.FloatArgumentType;
import io.github.sculkpowered.server.protocol.Buffer;

final class FloatArgumentSerializer extends AdvancedArgumentSerializer<FloatArgumentType> {

  public FloatArgumentSerializer(final int id) {
    super(id);
  }

  @Override
  public void write(FloatArgumentType argument, Buffer buf) {
    final var hasMinimum = argument.getMinimum() != -Float.MAX_VALUE;
    final var hasMaximum = argument.getMaximum() != Float.MAX_VALUE;

    byte argumentFlags = 0;
    if (hasMinimum) {
      argumentFlags |= 0x01;
    }
    if (hasMaximum) {
      argumentFlags |= 0x02;
    }

    buf.writeByte(argumentFlags);
    if (hasMinimum) {
      buf.writeFloat(argument.getMinimum());
    }
    if (hasMaximum) {
      buf.writeFloat(argument.getMaximum());
    }
  }
}
