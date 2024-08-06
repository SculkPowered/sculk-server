package io.github.sculkpowered.server.enchantment;

import io.github.sculkpowered.server.registry.Registry;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.nbt.CompoundBinaryTag;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

/**
 * An enum of all enchantments supported.
 */
@SuppressWarnings("unused")
@ApiStatus.Experimental // TODO
public enum Enchantment implements Registry.Entry {

  // START,
  // END
  ;

  private final Key key;

  Enchantment(final String key) {
    this.key = Key.key(Key.MINECRAFT_NAMESPACE, key);
  }

  @Override
  public @NotNull Key key() {
    return this.key;
  }

  @Override
  public int id() {
    return this.ordinal();
  }

  @Override
  public @NotNull CompoundBinaryTag asNBT() {
    return CompoundBinaryTag.empty();
  }
}
