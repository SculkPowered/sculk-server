package eu.sculkpowered.server.scoreboard;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.Style;
import net.kyori.adventure.text.format.StyleBuilderApplicable;
import org.jetbrains.annotations.NotNull;

/**
 * The number format of scoreboards.
 */
public sealed interface NumberFormat {

  /**
   * Gets the blank number format instance.
   *
   * @return the blank instance
   * @since 1.0.0
   */
  static @NotNull NumberFormat blank() {
    return Blank.INSTANCE;
  }

  /**
   * Creates a new styled number format.
   *
   * @param style the style of the format
   * @return the newly created number format
   * @since 1.0.0
   */
  static @NotNull NumberFormat styled(@NotNull Style style) {
    return new NumberFormat.Styled(style);
  }

  /**
   * Creates a new styled number format.
   *
   * @param applicable the style of the format
   * @return the newly created number format
   * @since 1.0.0
   */
  static @NotNull NumberFormat styled(@NotNull StyleBuilderApplicable... applicable) {
    return styled(Style.style(applicable));
  }

  /**
   * Creates a new fixed number format.
   *
   * @param component the fixed component
   * @return the newly created number format
   * @since 1.0.0
   */
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
