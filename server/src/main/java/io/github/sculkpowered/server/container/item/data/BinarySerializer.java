package io.github.sculkpowered.server.container.item.data;

import net.kyori.adventure.nbt.BinaryTag;

public interface BinarySerializer<T> {

  BinaryTag serialize(T t);

  T deserialize(BinaryTag binaryTag);
}
