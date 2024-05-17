package io.github.sculkpowered.server.container.equipment;

public enum EquipmentSlot {

  MAIN_HAND {
    @Override
    public boolean isHand() {
      return true;
    }
  },
  OFF_HAND {
    @Override
    public boolean isHand() {
      return true;
    }
  },
  FEET {
    @Override
    public boolean isArmor() {
      return true;
    }
  },
  LEGS {
    @Override
    public boolean isArmor() {
      return true;
    }
  },
  CHEST {
    @Override
    public boolean isArmor() {
      return true;
    }
  },
  HEAD {
    @Override
    public boolean isArmor() {
      return true;
    }
  },
  BODY;

  public boolean isHand() {
    return false;
  }

  public boolean isArmor() {
    return false;
  }
}
