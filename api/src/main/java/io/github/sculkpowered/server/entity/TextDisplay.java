package io.github.sculkpowered.server.entity;

import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;

/**
 * Represents a text display entity.
 */
public interface TextDisplay extends Display {

  /**
   * @since 1.0.0
   */
  @NotNull Component getText();

  /**
   * @since 1.0.0
   */
  void setText(@NotNull Component text);

  /**
   * @since 1.0.0
   */
  int getLineWidth();

  /**
   * @since 1.0.0
   */
  void setLineWidth(int lineWidth);

  /**
   * @since 1.0.0
   */
  int getBackgroundColor();

  /**
   * @since 1.0.0
   */
  void setBackgroundColor(int backgroundColor);

  /**
   * @since 1.0.0
   */
  byte getTextOpacity();

  /**
   * @since 1.0.0
   */
  void setTextOpacity(byte opacity);

  /**
   * @since 1.0.0
   */
  boolean hasShadow();

  /**
   * @since 1.0.0
   */
  void setShadow(boolean shadow);

  /**
   * @since 1.0.0
   */
  boolean isSeeThrough();

  /**
   * @since 1.0.0
   */
  void setSeeThrough(boolean seeThrough);
}