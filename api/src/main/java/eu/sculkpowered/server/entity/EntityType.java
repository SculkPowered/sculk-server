package eu.sculkpowered.server.entity;

import eu.sculkpowered.server.registry.Registry;
import java.util.function.Function;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.nbt.CompoundBinaryTag;
import org.intellij.lang.annotations.Subst;
import org.jetbrains.annotations.NotNull;

/**
 * All entity types possible.
 */
public final class EntityType<E extends Entity<?>> implements Registry.Entry {

  private final Key key;
  private final int id;
  private final Function<EntityType<E>, E> creator;

  EntityType(@Subst("key") final String key, final int id, Function<EntityType<E>, E> creator) {
    this.key = Key.key(key);
    this.id = id;
    this.creator = creator;
  }

  @Override
  public @NotNull Key key() {
    return this.key;
  }

  public int id() {
    return this.id;
  }

  @Override
  public @NotNull CompoundBinaryTag asNBT() {
    return CompoundBinaryTag.empty();
  }

  public E create() {
    return this.creator.apply(this);
  }
}
