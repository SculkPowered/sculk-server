package io.github.sculkpowered.server.world.block;

import io.github.sculkpowered.server.registry.Registry.Entry;
import io.github.sculkpowered.server.world.Direction;
import java.util.Map;
import net.kyori.adventure.key.Key;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Range;

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
   * Sets the properties.
   *
   * @param properties the properties to set
   * @return the state
   * @since 1.0.0
   */
  BlockState properties(@NotNull Map<String, String> properties);

  interface Waterloggable<B> extends BlockState {

    /**
     * Checks if the state is waterlogged.
     *
     * @return whether the state is waterlogged
     * @since 1.0.0
     */
    default boolean isWaterlogged() {
      return this.properties().get("waterlogged").equals("true");
    }

    /**
     * Sets the state waterlogged or not.
     *
     * @param waterlogged waterlogged or not
     * @return the state
     * @since 1.0.0
     */
    @SuppressWarnings("unchecked")
    default @NotNull B waterlogged(boolean waterlogged) {
      return (B) this.property("waterlogged", waterlogged);
    }
  }

  interface Powerable<B extends BlockState> extends BlockState {

    /**
     * Checks if the state is powered.
     *
     * @return whether the state is powered
     * @since 1.0.0
     */
    default boolean isPowered() {
      return this.properties().get("powered").equals("true");
    }

    /**
     * Sets whether the state should be powered.
     *
     * @param powered powered or not
     * @return the state
     * @since 1.0.0
     */
    @SuppressWarnings("unchecked")
    default @NotNull B powered(boolean powered) {
      return (B) this.property("powered", powered);
    }
  }

  interface Snowy<B extends BlockState> extends BlockState {

    /**
     * Checks if the state is snowy.
     *
     * @return whether the state is snowy
     * @since 1.0.0
     */
    default boolean isSnowy() {
      return this.properties().get("snowy").equals("true");
    }

    /**
     * Sets whether the state should be snowy.
     *
     * @param snowy snowy or not
     * @return the state
     * @since 1.0.0
     */
    @SuppressWarnings("unchecked")
    default @NotNull B powered(boolean snowy) {
      return (B) this.property("snowy", snowy);
    }
  }

  interface Face<B extends BlockState> extends BlockState {

    /**
     * Gets the face.
     *
     * @return the face
     * @since 1.0.0
     */
    default @NotNull Block.Face face() {
      return Block.Face.get(this.properties().get("face"));
    }

    /**
     * Sets the face.
     *
     * @param face the face to set
     * @return the state
     * @since 1.0.0
     */
    @SuppressWarnings("unchecked")
    default @NotNull B face(@NotNull Block.Face face) {
      return (B) this.property("face", face.value());
    }
  }

  interface Facing<B extends BlockState> extends BlockState {

    /**
     * Gets the facing.
     *
     * @return the facing
     * @since 1.0.0
     */
    default @NotNull Block.Facing facing() {
      return Block.Facing.get(this.properties().get("facing"));
    }

    /**
     * Sets the facing.
     *
     * @param facing the facing to set
     * @return the state
     * @since 1.0.0
     */
    @SuppressWarnings("unchecked")
    default @NotNull B facing(@NotNull Block.Facing facing) {
      return (B) this.property("facing", facing.value());
    }
  }

  interface Half<B extends BlockState> extends BlockState {

    /**
     * Gets the half.
     *
     * @return the half
     * @since 1.0.0
     */
    default @NotNull Block.Half half() {
      return Block.Half.get(this.properties().get("half"));
    }

    /**
     * Sets the half.
     *
     * @param half the half to set
     * @return the state
     * @since 1.0.0
     */
    @SuppressWarnings("unchecked")
    default @NotNull B half(@NotNull Block.Half half) {
      return (B) this.property("half", half.value());
    }
  }

  interface Ageable<B extends BlockState> extends BlockState {

    /**
     * Gets the age.
     *
     * @return the age
     * @since 1.0.0
     */
    default int age() {
      return Integer.parseInt(this.properties().get("age"));
    }

    /**
     * Sets the age.
     *
     * @param age the age to set
     * @return the state
     * @since 1.0.0
     */
    @SuppressWarnings("unchecked")
    default @NotNull B age(int age) {
      return (B) this.property("age", age);
    }
  }

  interface Rotationable<B extends BlockState> extends BlockState {

    /**
     * Gets the rotation.
     *
     * @return the rotation
     * @since 1.0.0
     */
    default int rotation() {
      return Integer.parseInt(this.properties().get("rotation"));
    }

    /**
     * Sets the rotation.
     *
     * @param rotation the rotation to set
     * @return the state
     * @since 1.0.0
     */
    @SuppressWarnings("unchecked")
    default @NotNull B rotation(@Range(from = 0, to = 15) int rotation) {
      return (B) this.property("rotation", rotation);
    }
  }

  interface Axis<B extends BlockState> extends BlockState {

    /**
     * Gets the axis.
     *
     * @return the axis
     * @since 1.0.0
     */
    default @NotNull Direction.Axis axis() {
      return Direction.Axis.valueOf(this.properties().get("axis"));
    }

    /**
     * Sets the axis.
     *
     * @param axis the axis to set
     * @return the state
     * @since 1.0.0
     */
    @SuppressWarnings("unchecked")
    default @NotNull B axis(@NotNull Direction.Axis axis) {
      return (B) this.property("axis", axis.name().toLowerCase());
    }
  }
}
