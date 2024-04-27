package io.github.sculkpowered.server.container.item.data;

import io.github.sculkpowered.server.registry.Registry;
import net.kyori.adventure.nbt.BinaryTag;
import org.jetbrains.annotations.NotNull;

public interface DataComponentType<T> extends Registry.Entry {

  @NotNull BinaryTag valueToBinary(@NotNull T value);
}
