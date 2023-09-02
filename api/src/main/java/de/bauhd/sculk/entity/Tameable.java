package de.bauhd.sculk.entity;

import org.jetbrains.annotations.Nullable;

import java.util.UUID;

/**
 * Represents an entity that is tameable.
 */
public interface Tameable {

    /**
     * Gets whether the entity sits or not.
     * @return {@code true} if its sits
     */
    boolean isSitting();

    /**
     * Sets the entity into the sit mode or not.
     * @param sitting whether it should sit or not
     */
    void setSitting(boolean sitting);

    /**
     * Gets whether the entity is tamed or not.
     * @return {@code true} if its tamed
     */
    boolean isTamed();

    /**
     * Gets the unique id of the owner of the entity.
     * @return the unique id of the owner of this entity
     */
    @Nullable UUID getOwner();

}
