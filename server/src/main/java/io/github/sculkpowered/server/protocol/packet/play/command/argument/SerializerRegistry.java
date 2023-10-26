package io.github.sculkpowered.server.protocol.packet.play.command.argument;

import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.arguments.DoubleArgumentType;
import com.mojang.brigadier.arguments.FloatArgumentType;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.arguments.LongArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import io.github.sculkpowered.server.command.argument.ColorArgumentType;
import io.github.sculkpowered.server.command.argument.TeamArgumentType;
import io.github.sculkpowered.server.protocol.Buffer;
import java.util.Map;

public final class SerializerRegistry {

  private static final Map<Class<? extends ArgumentType<?>>, ArgumentSerializer> SERIALIZER =
      Map.of(
          BoolArgumentType.class, new ArgumentSerializer(0),
          FloatArgumentType.class, new FloatArgumentSerializer(1),
          DoubleArgumentType.class, new DoubleArgumentSerializer(2),
          IntegerArgumentType.class, new IntegerArgumentSerializer(3),
          LongArgumentType.class, new LongArgumentSerializer(4),
          StringArgumentType.class, new StringArgumentSerializer(5),
          ColorArgumentType.class, new ArgumentSerializer(16),
          TeamArgumentType.class, new ArgumentSerializer(31)
      );

  public static void serialize(final ArgumentType<?> argumentType, final Buffer buf) {
    SERIALIZER.get(argumentType.getClass()).serialize(argumentType, buf);
  }
}
