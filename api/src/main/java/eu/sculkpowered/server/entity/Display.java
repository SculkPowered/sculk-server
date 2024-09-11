package eu.sculkpowered.server.entity;

import org.jetbrains.annotations.NotNull;

/**
 * Represents a display entity.
 */
public interface Display extends Entity {

  /**
   * @since 1.0.0
   */
  int getInterpolationDelay();

  /**
   * @since 1.0.0
   */
  void setInterpolationDelay(int interpolationDelay);

  /**
   * @since 1.0.0
   */
  int getInterpolationDuration();

  /**
   * @since 1.0.0
   */
  void setInterpolationDuration(int interpolationDuration);

  /**
   * @since 1.0.0
   */
  @NotNull Constraint getConstraint();

  /**
   * @since 1.0.0
   */
  void setConstraint(@NotNull Constraint constraint);

  /**
   * @since 1.0.0
   */
  float getViewRange();

  /**
   * @since 1.0.0
   */
  void setViewRange(float viewRange);

  /**
   * @since 1.0.0
   */
  float getShadowRange();

  /**
   * @since 1.0.0
   */
  void setShadowRange(float shadowRange);

  /**
   * @since 1.0.0
   */
  float getShadowStrength();

  /**
   * @since 1.0.0
   */
  void setShadowStrength(float shadowStrength);

  /**
   * @since 1.0.0
   */
  float getWidth();

  /**
   * @since 1.0.0
   */
  void setWidth(float width);

  /**
   * @since 1.0.0
   */
  float getHeight();

  /**
   * @since 1.0.0
   */
  void setHeight(float height);

  enum Constraint {
    FIXED,
    VERTICAL,
    HORIZONTAL,
    CENTER
  }
}
