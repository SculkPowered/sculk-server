package io.github.sculkpowered.server.world.block;

import java.util.HashMap;
import java.util.Map;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.nbt.CompoundBinaryTag;
import net.kyori.adventure.nbt.StringBinaryTag;
import org.jetbrains.annotations.NotNull;

class SculkBlockState implements BlockState {

  protected final BlockParent block;
  protected final int id;
  protected final Map<String, String> properties;

  SculkBlockState(final BlockParent block, final int id, final Map<String, String> properties) {
    this.block = block;
    this.id = id;
    this.properties = properties;
  }

  @Override
  public @NotNull Key key() {
    return this.block.key();
  }

  @Override
  public int id() {
    return this.id;
  }

  @Override
  public float destroyTime() {
    return this.block.destroyTime();
  }

  @Override
  public @NotNull CompoundBinaryTag asNBT() {
    final var properties = CompoundBinaryTag.builder();
    for (final var entry : this.properties().entrySet()) {
      properties.put(entry.getKey(), StringBinaryTag.stringBinaryTag(entry.getValue()));
    }
    return CompoundBinaryTag.builder()
        .putString("Name", this.name())
        .put("Properties", properties.build())
        .build();
  }

  @Override
  public @NotNull Map<String, String> properties() {
    return this.properties;
  }

  @Override
  public boolean hasProperty(@NotNull String key) {
    return this.properties.containsKey(key);
  }

  @Override
  public BlockState property(@NotNull String key, @NotNull String value) {
    final var properties = new HashMap<>(this.properties);
    properties.put(key, value);
    return this.block.state(properties);
  }

  @Override
  public BlockState property(@NotNull String key, boolean value) {
    return this.property(key, String.valueOf(value));
  }

  @Override
  public BlockState property(@NotNull String key, int value) {
    return this.property(key, String.valueOf(value));
  }

  @Override
  public String property(@NotNull String key) {
    return this.properties.get(key);
  }

  @Override
  public BlockState properties(@NotNull Map<String, String> properties) {
    final var newProperties = new HashMap<>(this.properties);
    newProperties.putAll(properties);
    return this.block.state(newProperties);
  }

  @Override
  public String toString() {
    return "SculkBlockState{" +
        "block=" + this.block +
        ", id=" + this.id +
        ", properties=" + this.properties +
        '}';
  }

  public abstract static class Entity<B extends Block.Entity<B>> extends SculkBlockState implements
      Block.Entity<B> {

    protected final int entityId;
    protected final CompoundBinaryTag nbt;

    Entity(BlockParent block, int id, Map<String, String> properties, int entityId) {
      this(block, id, properties, entityId, CompoundBinaryTag.empty());
    }

    Entity(BlockParent block, int id, Map<String, String> properties, int entityId,
        CompoundBinaryTag nbt) {
      super(block, id, properties);
      this.entityId = entityId;
      this.nbt = nbt;
    }

    @Override
    public int getEntityId() {
      return this.entityId;
    }

    @Override
    public @NotNull CompoundBinaryTag nbt() {
      return this.nbt;
    }

    @SuppressWarnings("unchecked")
    @Override
    public B property(@NotNull String key, @NotNull String value) {
      return ((B) super.property(key, value)).nbt(this.nbt);
    }

    @SuppressWarnings("unchecked")
    @Override
    public B properties(@NotNull Map<String, String> properties) {
      return ((B) super.properties(properties)).nbt(this.nbt);
    }
  }
}
