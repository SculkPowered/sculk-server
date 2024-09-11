package eu.sculkpowered.server.attribute;

import eu.sculkpowered.server.container.equipment.EquipmentSlot;

public enum AttributeSlot {

  ANY("any", null),
  MAIN_HAND("mainhand", EquipmentSlot.MAIN_HAND),
  OFF_HAND("offhand", EquipmentSlot.OFF_HAND),
  HAND("hand", null),
  FEET("feet", EquipmentSlot.FEET),
  LEGS("legs", EquipmentSlot.LEGS),
  CHEST("chest", EquipmentSlot.CHEST),
  HEAD("head", null),
  ARMOR("armor", null),
  BODY("body", null);

  private final String key;
  private final EquipmentSlot equipmentSlot;

  AttributeSlot(final String key, final EquipmentSlot equipmentSlot) {
    this.key = key;
    this.equipmentSlot = equipmentSlot;
  }

  public String key() {
    return this.key;
  }

  public EquipmentSlot equipmentSlot() {
    return this.equipmentSlot;
  }

  public boolean is(final EquipmentSlot slot) {
    return (this == AttributeSlot.ANY ||
        (this == AttributeSlot.ARMOR && slot.isArmor()) ||
        (this == AttributeSlot.HAND && slot.isHand()) ||
        this.equipmentSlot() == slot);
  }
}
