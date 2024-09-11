package eu.sculkpowered.server.command.argument;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.kyori.adventure.text.format.NamedTextColor;

public final class ColorArgumentType implements ArgumentType<NamedTextColor> {

  private static final ColorArgumentType COLOR_ARGUMENT_TYPE = new ColorArgumentType();

  private ColorArgumentType() {}

  public static ColorArgumentType color() {
    return COLOR_ARGUMENT_TYPE;
  }

  @Override
  public NamedTextColor parse(StringReader reader) throws CommandSyntaxException {
      return NamedTextColor.NAMES.value(reader.readString());
  }
}
