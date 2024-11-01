package eu.sculkpowered.server.entity;

import eu.sculkpowered.server.attribute.Attribute;
import eu.sculkpowered.server.attribute.AttributeValue;
import eu.sculkpowered.server.container.equipment.EntityEquipment;
import eu.sculkpowered.server.entity.meta.EntityMeta;
import org.jetbrains.annotations.NotNull;

public interface LivingEntity<M extends EntityMeta> extends Entity<M> {

  /**
   * Gets the attribute value by the attribute.
   *
   * @param attribute the attribute
   * @return the value
   * @since 1.0.0
   */
  @NotNull AttributeValue attribute(@NotNull Attribute attribute);

  /**
   * Gets the equipment from the entity.
   * @return the equipment
   */
  @NotNull EntityEquipment equipment();
}
