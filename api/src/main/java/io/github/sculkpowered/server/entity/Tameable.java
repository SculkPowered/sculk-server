package io.github.sculkpowered.server.entity;

import java.util.UUID;
import org.jetbrains.annotations.Nullable;

/**
 * Represents an entity that is tameable.
 */
public interface Tameable {

  /**
   * Gets whether the entity sits or not.
   *
   * @return {@code true} if its sits
   * @since 1.0.0
   */
  boolean isSitting();

  /**
   * Sets the entity into the sit mode or not.
   *
   * @param sitting whether it should sit or not
   * @since 1.0.0
   */
  void setSitting(boolean sitting);

  /**
   * Gets whether the entity is tamed or not.
   *
   * @return {@code true} if its tamed
   * @since 1.0.0
   */
  boolean isTamed();

  /**
   * Gets the unique id of the owner of the entity.
   *
   * @return the unique id of the owner of this entity
   * @since 1.0.0
   */
  @Nullable UUID getOwner();

}
