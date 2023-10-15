package de.bauhd.sculk.world.block;

import java.util.HashMap;
import java.util.Map;
import org.jetbrains.annotations.NotNull;

public interface Bed extends Block.Entity<Bed>, BlockState.Facing<Bed> {

  /**
   * Checks if the state is occupied.
   *
   * @return whether the state is occupied
   * @since 1.0.0
   */
  boolean occupied();

  /**
   * Sets whether the state should be occupied.
   *
   * @param occupied occupied or not
   * @return the state
   * @since 1.0.0
   */
  @NotNull Bed occupied(boolean occupied);

  /**
   * Gets the part.
   *
   * @return the part
   * @since 1.0.0
   */
  @NotNull Part part();

  /**
   * Sets the part.
   *
   * @param part the part to set
   * @return the state
   * @since 1.0.0
   */
  @NotNull Bed part(@NotNull Part part);

  /**
   * @since 1.0.0
   */
  final class Part extends Block.PropertyValue<Part> {

    private static final Map<String, Part> INDEX = new HashMap<>(2);

    public static final Part HEAD = new Part("head");
    public static final Part FOOT = new Part("foot");

    private Part(String value) {
      super(value, INDEX);
    }

    public static Part get(@NotNull String value) {
      return INDEX.get(value);
    }
  }
}
