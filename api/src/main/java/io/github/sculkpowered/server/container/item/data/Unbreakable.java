package io.github.sculkpowered.server.container.item.data;

import org.jetbrains.annotations.NotNull;

public final class Unbreakable {

  private static final Unbreakable TOOLTIP = new Unbreakable(true);
  private static final Unbreakable NO_TOOLTIP = new Unbreakable(false);

  private final boolean showInTooltip;

  private Unbreakable(final boolean showInTooltip) {
    this.showInTooltip = showInTooltip;
  }

  public boolean showInTooltip() {
    return this.showInTooltip;
  }

  public static @NotNull Unbreakable showInTooltip(final boolean showTooltip) {
    return showTooltip ? TOOLTIP : NO_TOOLTIP;
  }
}
