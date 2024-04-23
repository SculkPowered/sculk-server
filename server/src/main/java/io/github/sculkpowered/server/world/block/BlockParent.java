package io.github.sculkpowered.server.world.block;

import java.util.Map;
import net.kyori.adventure.key.Key;

public final class BlockParent {

  private final Key key;
  private final float destroyTime;
  private SculkBlockState[] states;

  BlockParent(final Key key, final float destroyTime) {
    this.key = key;
    this.destroyTime = destroyTime;
  }

  public Key key() {
    return this.key;
  }

  public float destroyTime() {
    return this.destroyTime;
  }

  void setStates(SculkBlockState[] states) {
    this.states = states;
  }

  public BlockState state(Map<String, String> properties) {
    for (final var state : this.states) {
      if (state.properties().equals(properties)) {
        return state;
      }
    }
    return null;
  }

  @Override
  public String toString() {
    return "BlockParent{" +
        "name=" + this.key +
        '}';
  }
}
