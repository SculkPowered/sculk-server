package eu.sculkpowered.server.attribute;

import eu.sculkpowered.server.registry.Registry.Entry;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.nbt.CompoundBinaryTag;
import org.intellij.lang.annotations.Subst;
import org.jetbrains.annotations.NotNull;

public record Attribute(
    @NotNull Key key,
    int id,
    float def,
    float min,
    float max
) implements Entry {

  public Attribute(@Subst("value") String key, int id, float def, float min, float max) {
    this(Key.key(Key.MINECRAFT_NAMESPACE, key), id, def, min, max);
  }

  @Override
  public @NotNull CompoundBinaryTag asNBT() {
    return CompoundBinaryTag.empty();
  }
}
