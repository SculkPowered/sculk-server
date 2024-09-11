package eu.sculkpowered.server.container.item.data;

public enum Rarity {

  COMMON("common"),
  UNCOMMON("uncommon"),
  RARE("rare"),
  EPIC("epic");

  private final String key;

  Rarity(final String key) {
    this.key = key;
  }

  public String key() {
    return this.key;
  }
}
