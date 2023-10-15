package de.bauhd.sculk.world.block;

import java.util.Map;
import net.kyori.adventure.nbt.CompoundBinaryTag;
import org.jetbrains.annotations.NotNull;

final class SculkBed extends SculkBlockState.Entity<Bed> implements Bed {

  SculkBed(BlockParent block, int id, Map<String, String> properties) {
    super(block, id, properties, 24);
  }

  public SculkBed(BlockParent block, int id, Map<String, String> properties, int entityId,
      CompoundBinaryTag nbt) {
    super(block, id, properties, entityId, nbt);
  }

  @Override
  public boolean occupied() {
    return this.properties.get("occupied").equals("true");
  }

  @Override
  public @NotNull Bed occupied(boolean occupied) {
    return (Bed) this.property("occupied", occupied);
  }

  @Override
  public @NotNull Part part() {
    return Part.get(this.properties.get("part"));
  }

  @Override
  public @NotNull Bed part(@NotNull Part part) {
    return this.property("part", part.getValue());
  }

  @Override
  public @NotNull Bed nbt(@NotNull CompoundBinaryTag nbt) {
    return new SculkBed(this.block, this.id, this.properties, this.entityId, nbt);
  }
}
