package io.github.sculkpowered.server.attribute;

public enum AttributeSlot {

  ANY("any"),
  MAIN_HAND("mainhand"),
  OFF_HAND("offhand"),
  FEET("feet"),
  LEGS("legs"),
  CHEST("chest"),
  HEAD("head"),
  ARMOR("armor"),
  BODY("body");

  private final String key;

  AttributeSlot(final String key) {
    this.key = key;
  }

  public String key() {
    return this.key;
  }
}
