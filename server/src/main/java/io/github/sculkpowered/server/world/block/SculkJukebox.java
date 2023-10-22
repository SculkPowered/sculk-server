package io.github.sculkpowered.server.world.block;

import net.kyori.adventure.nbt.CompoundBinaryTag;
import org.jetbrains.annotations.NotNull;
import java.util.Map;

final class SculkJukebox extends SculkBlockState.Entity<Jukebox> implements Jukebox {

  SculkJukebox(BlockParent block, int id, Map<String, String> properties) {
    super(block, id, properties, 4);
  }

  public SculkJukebox(BlockParent block, int id, Map<String, String> properties, int entityId, CompoundBinaryTag nbt) {
    super(block, id, properties, entityId, nbt);
  }

  @Override
  public @NotNull Jukebox nbt(@NotNull CompoundBinaryTag nbt) {
     return new SculkJukebox(this.block, this.id, this.properties, this.entityId, nbt);
  }
}