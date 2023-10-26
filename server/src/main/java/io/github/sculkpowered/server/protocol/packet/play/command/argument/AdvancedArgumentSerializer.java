package io.github.sculkpowered.server.protocol.packet.play.command.argument;

import com.mojang.brigadier.arguments.ArgumentType;
import io.github.sculkpowered.server.protocol.Buffer;

abstract class AdvancedArgumentSerializer<T extends ArgumentType<?>> extends ArgumentSerializer {

  protected AdvancedArgumentSerializer(int id) {
    super(id);
  }

  @SuppressWarnings("unchecked")
  @Override
  public void serialize(final ArgumentType<?> argumentType, final Buffer buf) {
    super.serialize(argumentType, buf);
    this.write((T) argumentType, buf);
  }

  public abstract void write(final T t, final Buffer buf);
}
