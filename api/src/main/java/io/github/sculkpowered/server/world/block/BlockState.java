package io.github.sculkpowered.server.world.block;

import io.github.sculkpowered.server.registry.Registry.Entry;
import java.util.Map;
import net.kyori.adventure.key.Key;
import org.jetbrains.annotations.NotNull;

/**
 * Represents a state of a block.
 */
public interface BlockState extends Entry {

  /**
   * Gets the key of the state.
   *
   * @return the state's id
   * @since 1.0.0
   */
  @NotNull Key key();

  /**
   * Gets the id of the state.
   *
   * @return the state's id
   * @since 1.0.0
   */
  int id();

  /**
   * Gets the destroy time of the block.
   *
   * @return the destroy time of the block
   * @since 1.0.0
   */
  float destroyTime();

  /**
   * Gets the properties of the state.
   *
   * @return the state's properties
   * @since 1.0.0
   */
  @NotNull Map<String, String> properties();

  /**
   * Checks if the state has the property with the specified key.
   *
   * @param key the key of the property
   * @return whether the state has the key or not
   * @since 1.0.0
   */
  boolean hasProperty(@NotNull String key);

  /**
   * Sets the property with the specified key to the specified value.
   *
   * @param key   the key of the property
   * @param value the value the porperty should be set to
   * @return the state
   * @since 1.0.0
   */
  BlockState property(@NotNull String key, @NotNull String value);

  /**
   * Sets the property with the specified key to the specified value.
   *
   * @param key   the key of the property
   * @param value the value the property should be set to
   * @return the state
   * @since 1.0.0
   */
  BlockState property(@NotNull String key, boolean value);

  /**
   * Sets the property with the specified key to the specified value.
   *
   * @param key   the key of the property
   * @param value the value the property should be set to
   * @return the state
   * @since 1.0.0
   */
  BlockState property(@NotNull String key, int value);

  /**
   * Gets the property of the specified key.
   *
   * @param key the key of the property
   * @return the property
   * @since 1.0.0
   */
  String property(@NotNull String key);

  /**
   * Sets the properties.
   *
   * @param properties the properties to set
   * @return the state
   * @since 1.0.0
   */
  BlockState properties(@NotNull Map<String, String> properties);
}
