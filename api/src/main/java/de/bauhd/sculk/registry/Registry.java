package de.bauhd.sculk.registry;

import net.kyori.adventure.key.Key;
import net.kyori.adventure.key.Keyed;
import net.kyori.adventure.nbt.CompoundBinaryTag;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;

public interface Registry<E extends Registry.Entry> {

    /**
     * Gets the type key of the registry.
     * @return the type key of the registry
     */
    @NotNull String type();

    /**
     * Registers a new entry into the registry.
     * @param entry the entry to register
     */
    void register(@NotNull E entry);

    /**
     * Gets an entry by its key.
     * @param key the key of the entry
     * @return the entry or a default value
     */
    @NotNull E get(@NotNull String key);

    /**
     * Gets an entry by its key.
     * @param key the key of the entry
     * @return the entry or a default value
     */
    default @NotNull E get(@NotNull Key key) {
        return this.get(key.asString());
    }

    /**
     * Gets a collection of all entries.
     * @return the collection of all entries
     */
    @NotNull Collection<E> entries();

    /**
     * Gets the registry as a {@link CompoundBinaryTag}.
     * @return the registry as a {@link CompoundBinaryTag}
     */
    @NotNull CompoundBinaryTag asNBT();

    interface Entry extends Keyed {

        /**
         * Gets the name of the entry.
         * @return the name of the entry
         */
        default @NotNull String name() {
            return this.key().asString();
        }

        /**
         * Gets the id of the entry
         * @return the id of the entry
         */
        int id();

        /**
         * Gets the entry as a {@link CompoundBinaryTag}.
         * @return the entry as a {@link CompoundBinaryTag}
         */
        @NotNull CompoundBinaryTag asNBT();
    }
}
