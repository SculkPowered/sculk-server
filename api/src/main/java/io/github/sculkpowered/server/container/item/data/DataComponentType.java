package io.github.sculkpowered.server.container.item.data;

import io.github.sculkpowered.server.registry.Registry;
import net.kyori.adventure.nbt.BinaryTag;
import org.jetbrains.annotations.NotNull;

public interface DataComponentType<T> extends Registry.Entry {

  boolean serializable();

  BinaryTag valueToBinary(@NotNull T value);

  T binaryToValue(@NotNull BinaryTag binaryTag);
}
