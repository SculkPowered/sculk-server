package de.bauhd.sculk.entity;

import de.bauhd.sculk.container.item.ItemStack;
import de.bauhd.sculk.protocol.Buffer;
import de.bauhd.sculk.world.Position;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import java.util.UUID;
import net.kyori.adventure.nbt.CompoundBinaryTag;
import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.Nullable;

public final class Metadata {

  private static final int BYTE_TYPE = 0;
  private static final int VAR_INT_TYPE = 1;
  private static final int VAR_LONG_TYPE = 2;
  private static final int FLOAT_TYPE = 3;
  private static final int STRING_TYPE = 4;
  private static final int CHAT_TYPE = 5;
  private static final int OPT_CHAT_TYPE = 6;
  private static final int SLOT_TYPE = 7;
  private static final int BOOLEAN_TYPE = 8;
  private static final int ROTATION_TYPE = 9;
  private static final int POSITION_TYPE = 10;
  private static final int OPT_POSITION_TYPE = 11;
  private static final int DIRECTION_TYPE = 12;
  private static final int OPT_UUID_TYPE = 13;
  private static final int BLOCK_TYPE = 14;
  private static final int OPT_BLOCK_TYPE = 15;
  private static final int NBT_TYPE = 16;
  private static final int PARTICLE_TYPE = 17;
  private static final int VILLAGER_DATA_TYPE = 18;
  private static final int OPT_VAR_INT_TYPE = 19;
  private static final int POSE_TYPE = 20;
  private static final int CAT_VARIANT_TYPE = 21;
  private static final int FROG_VARIANT_TYPE = 22;
  private static final int OPT_GLOBAL_POS_TYPE = 23;
  private static final int PAINTING_VARIANT_TYPE = 24;
  private static final int SNIFFER_STATE_TYPE = 25;
  private static final int VECTOR3_TYPE = 26;
  private static final int QUATERNION_TYPE = 27;

  private final Int2ObjectMap<Entry<?>> entries = new Int2ObjectOpenHashMap<>();
  private Int2ObjectMap<Entry<?>> changes = new Int2ObjectOpenHashMap<>();

  public void set(final int index, final Entry<?> entry) {
    this.entries.put(index, entry);
    this.changes.put(index, entry);
  }

  @SuppressWarnings("unchecked")
  public <T> T get(final int index, final T def) {
    final var entry = this.entries.get(index);
    if (entry != null) {
      return (T) entry.t;
    }
    return def;
  }

  public void setByte(final int index, final byte value) {
    this.set(index, new Entry<>(BYTE_TYPE, value, Buffer::writeByte));
  }

  public byte getByte(final int index, final byte def) {
    return this.get(index, def);
  }

  public void setVarInt(final int index, final int value) {
    this.set(index, new Entry<>(VAR_INT_TYPE, value, Buffer::writeVarInt));
  }

  public int getVarInt(final int index, final int def) {
    return this.get(index, def);
  }

  public void setVarLong(final int index, final long value) {
    this.set(index, new Entry<>(VAR_LONG_TYPE, value, Buffer::writeVarLong));
  }

  public long getVarLong(final int index, final long def) {
    return this.get(index, def);
  }

  public void setFloat(final int index, final float value) {
    this.set(index, new Entry<>(FLOAT_TYPE, value, Buffer::writeFloat));
  }

  public float getFloat(final int index, final float def) {
    return this.get(index, def);
  }

  public void setString(final int index, final String value) {
    this.set(index, new Entry<>(STRING_TYPE, value, Buffer::writeString));
  }

  public String getString(final int index, final String def) {
    return this.get(index, def);
  }

  public void setComponent(final int index, final Component value) {
    this.set(index, new Entry<>(CHAT_TYPE, value, Buffer::writeComponent));
  }

  public Component getComponent(final int index, final Component def) {
    return this.get(index, def);
  }

  public void setOptComponent(final int index, final Component value) {
    this.set(index, new Entry<>(OPT_CHAT_TYPE, value, (buf, component) -> {
      if (buf.writeOptional(value)) {
        buf.writeComponent(component);
      }
    }));
  }

  public void setItem(final int index, final ItemStack value) {
    this.set(index, new Entry<>(SLOT_TYPE, value, Buffer::writeItem));
  }

  public ItemStack getItem(final int index, final ItemStack def) {
    return this.get(index, def);
  }

  public void setBoolean(final int index, final boolean value) {
    this.set(index, new Entry<>(BOOLEAN_TYPE, value, Buffer::writeBoolean));
  }

  public boolean getBoolean(final int index, final boolean def) {
    return this.get(index, def);
  }

  public void setPosition(final int index, final Position value) {
    this.set(index, new Entry<>(POSITION_TYPE, value, Buffer::writePosition));
  }

  public Position getPosition(final int index, final Position def) {
    return this.get(index, def);
  }

  public void setOptPosition(final int index, final Position value) {
    this.set(index, new Entry<>(OPT_POSITION_TYPE, value, (buf, position) -> {
      if (buf.writeOptional(position)) {
        buf.writePosition(position);
      }
    }));
  }

  public void setOptUUID(final int index, final UUID value) {
    this.set(index, new Entry<>(OPT_UUID_TYPE, value, (buf, uuid) -> {
      if (buf.writeOptional(uuid)) {
        buf.writeUniqueId(uuid);
      }
    }));
  }

  public @Nullable UUID getOptUUID(final int index, final UUID def) {
    return this.get(index, def);
  }

  public void setBlockId(final int index, final int value) {
    this.set(index, new Entry<>(BLOCK_TYPE, value, Buffer::writeVarInt));
  }

  public void setNbt(final int index, final CompoundBinaryTag value) {
    this.set(index, new Entry<>(NBT_TYPE, value, Buffer::writeCompoundTag));
  }

  public CompoundBinaryTag getNbt(final int index, final CompoundBinaryTag def) {
    return this.get(index, def);
  }

  public void setPose(final int index, final Entity.Pose value) {
    this.set(index,
        new Entry<>(POSE_TYPE, value, (buffer, pose) -> buffer.writeVarInt(pose.ordinal())));
  }

  public Entity.Pose getPose(final int index, final Entity.Pose def) {
    return this.get(index, def);
  }

  public boolean inMask(final int index, final int value) {
    return (this.getByte(index, (byte) 0) & value) == value;
  }

  public void setMask(final int index, final int i, final boolean value) {
    var mask = this.getByte(index, (byte) 0);
    if (((mask & i) == i) == value) {
      return;
    }
    if (value) {
      mask |= 0x20;
    } else {
      mask &= ~0x20;
    }
    this.setByte(index, mask);
  }

  @SuppressWarnings("unchecked")
  public <T extends Enum<T>> T getEnum(final int index, final T def) {
    final var entry = this.entries.get(index);
    if (entry != null) {
      return (T) def.getClass().getEnumConstants()[(int) entry.t];
    }
    return def;
  }

  public Int2ObjectMap<Entry<?>> entries() {
    return this.entries;
  }

  public Int2ObjectMap<Entry<?>> changes() {
    return this.changes;
  }

  public void reset() {
    this.changes = new Int2ObjectOpenHashMap<>();
  }

  public record Entry<T>(int type, T t, Applier<T> applier) {

    public void write(final Buffer buffer) {
      this.applier.apply(buffer, this.t);
    }
  }

  @FunctionalInterface
  private interface Applier<T> {

    void apply(final Buffer buffer, final T t);
  }
}
