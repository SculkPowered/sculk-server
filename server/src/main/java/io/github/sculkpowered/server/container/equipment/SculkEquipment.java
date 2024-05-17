package io.github.sculkpowered.server.container.equipment;

import io.github.sculkpowered.server.container.item.ItemStack;
import io.github.sculkpowered.server.container.item.data.DataComponent;
import io.github.sculkpowered.server.entity.AbstractLivingEntity;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import org.jetbrains.annotations.NotNull;

public final class SculkEquipment implements EntityEquipment{

  private final AbstractLivingEntity entity;
  private final Map<EquipmentSlot, ItemStack> equipment;

  public SculkEquipment(final AbstractLivingEntity entity) {
    this.entity = entity;
    this.equipment = new HashMap<>();
  }

  @Override
  public @NotNull ItemStack get(@NotNull EquipmentSlot slot) {
    return this.equipment.getOrDefault(slot, ItemStack.empty());
  }

  @Override
  public @NotNull ItemStack set(@NotNull EquipmentSlot slot, @NotNull ItemStack equipment) {
    var previous = this.equipment.put(slot, equipment);
    if (previous == null) {
      previous = ItemStack.empty();
    }
    // remove previous attributes
    var attributes = Objects.requireNonNull(previous
        .get(DataComponent.ATTRIBUTE_MODIFIERS)).attributes();
    for (final var attribute : attributes) {
      if (attribute.slot().is(slot)) {
        this.entity.attribute(attribute.attribute()).removeModifier(attribute.modifier());
      }
    }
    // add new attributes
    attributes = Objects.requireNonNull(equipment
        .get(DataComponent.ATTRIBUTE_MODIFIERS)).attributes();
    for (final var attribute : attributes) {
      if (attribute.slot().is(slot)) {
        this.entity.attribute(attribute.attribute()).addModifier(attribute.modifier());
      }
    }
    return previous;
  }

  @Override
  public Map<EquipmentSlot, ItemStack> asMap() {
    return this.equipment;
  }

  @Override
  public String toString() {
    return "SculkEquipment{" +
        "entity=" + this.entity +
        ", equipment=" + this.equipment +
        '}';
  }
}
