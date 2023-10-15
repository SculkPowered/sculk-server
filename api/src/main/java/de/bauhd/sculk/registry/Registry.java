package de.bauhd.sculk.registry;

import java.util.Collection;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.key.Keyed;
import net.kyori.adventure.nbt.CompoundBinaryTag;
import org.jetbrains.annotations.NotNull;

public interface Registry<E extends Registry.Entry> {

  /**
   * Gets the type key of the registry.
   *
   * @return the type key of the registry
   * @since 1.0.0
   */
  @NotNull String type();

  /**
   * Registers a new entry into the registry.
   *
   * @param entry the entry to register
   * @since 1.0.0
   */
  void register(@NotNull E entry);

  /**
   * Gets an entry by its key.
   *
   * @param key the key of the entry
   * @return the entry or a default value
   * @since 1.0.0
   */
  @NotNull E get(@NotNull String key);

  /**
   * Gets an entry by its key.
   *
   * @param key the key of the entry
   * @return the entry or a default value
   * @since 1.0.0
   */
  default @NotNull E get(@NotNull Key key) {
    return this.get(key.asString());
  }

  /**
   * Gets an entry by its id.
   *
   * @param id the id of the entry
   * @return the entry or a default value
   * @since 1.0.0
   */
  @NotNull E get(int id);

  /**
   * Gets a collection of all entries.
   *
   * @return the collection of all entries
   * @since 1.0.0
   */
  @NotNull Collection<E> entries();

  /**
   * Gets the registry as a {@link CompoundBinaryTag}.
   *
   * @return the registry as a {@link CompoundBinaryTag}
   * @since 1.0.0
   */
  @NotNull CompoundBinaryTag asNBT();

  interface Entry extends Keyed {

    /**
     * Gets the name of the entry.
     *
     * @return the name of the entry
     * @since 1.0.0
     */
    default @NotNull String name() {
      return this.key().asString();
    }

    /**
     * Gets the id of the entry
     *
     * @return the id of the entry
     * @since 1.0.0
     */
    int id();

    /**
     * Gets the entry as a {@link CompoundBinaryTag}.
     *
     * @return the entry as a {@link CompoundBinaryTag}
     * @since 1.0.0
     */
    @NotNull CompoundBinaryTag asNBT();
  }
}
