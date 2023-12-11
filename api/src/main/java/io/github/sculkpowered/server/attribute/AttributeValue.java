package io.github.sculkpowered.server.attribute;

import org.jetbrains.annotations.NotNull;

public interface AttributeValue {

  /**
   * Sets the base value.
   *
   * @param value the new base value
   * @since 1.0.0
   */
  void baseValue(double value);

  /**
   * Gets the base value.
   *
   * @return the base value
   * @since 1.0.0
   */
  double baseValue();

  /**
   * @since 1.0.0
   */
  void addModifier(@NotNull AttributeModifier modifier);

  /**
   * @since 1.0.0
   */
  void removeModifier(@NotNull AttributeModifier modifier);

  /**
   * @return the calculated value
   * @since 1.0.0
   */
  double calculatedValue();
}
