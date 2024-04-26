package io.github.sculkpowered.server.scoreboard;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.Style;
import net.kyori.adventure.text.format.StyleBuilderApplicable;
import org.jetbrains.annotations.NotNull;

public sealed interface NumberFormat {

  static @NotNull NumberFormat blank() {
    return Blank.INSTANCE;
  }

  static @NotNull NumberFormat styled(@NotNull Style style) {
    return new NumberFormat.Styled(style);
  }

  static @NotNull NumberFormat styled(@NotNull StyleBuilderApplicable... applicable) {
    return styled(Style.style(applicable));
  }

  static @NotNull NumberFormat fixed(@NotNull Component component) {
    return new NumberFormat.Fixed(component);
  }

  record Blank() implements NumberFormat {

    private static final Blank INSTANCE = new Blank();
  }

  record Styled(@NotNull Style style) implements NumberFormat, StyleBuilderApplicable {

    @Override
    public void styleApply(Style.Builder style) {
      style.merge(this.style);
    }
  }

  record Fixed(@NotNull Component component) implements NumberFormat {

  }
}
