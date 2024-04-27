package io.github.sculkpowered.server.container.item.data;

import io.github.sculkpowered.server.protocol.Buffer;
import io.github.sculkpowered.server.registry.Registry;
import io.github.sculkpowered.server.registry.SimpleRegistry;
import net.kyori.adventure.nbt.BinaryTag;
import net.kyori.adventure.nbt.CompoundBinaryTag;
import net.kyori.adventure.nbt.IntBinaryTag;
import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;

public final class DataComponentTypeRegistry {

  public static Registry<DataComponentType<?>> get() {
    final var registry = new SimpleRegistry<DataComponentType<?>>("minecraft:data_component_type");
    registry.register(new BinaryTagComponent("custom_data", 0));
    registry.register(new BinaryTagComponent("entity_data", 37));
    registry.register(new BinaryTagComponent("bucket_entity_data", 38));
    registry.register(new BinaryTagComponent("block_entity_data", 39));
    registry.register(new VarIntComponent("max_stack_size", 1));
    registry.register(new VarIntComponent("max_damage", 2));
    registry.register(new VarIntComponent("damage", 3));
    registry.register(new VarIntComponent("repair_cost", 16));
    registry.register(new VarIntComponent("ominous_bottle_amplifier", 41));
    registry.register(new ComponentComponent("custom_name", 5));
    registry.register(new ComponentComponent("item_name", 6));
    registry.register(new VoidComponent("hide_additional_tooltip", 14));
    registry.register(new VoidComponent("hide_tooltip", 15));
    registry.register(new VoidComponent("creative_slot_lock", 17));
    return registry;
  }

  private static final class BinaryTagComponent extends SculkDataComponentType<CompoundBinaryTag> {

    public BinaryTagComponent(final String keyValue, final int id) {
      super(keyValue, id);
    }

    @Override
    public void write(Buffer buf, CompoundBinaryTag value) {
      buf.writeBinaryTag(value);
    }

    @Override
    public CompoundBinaryTag read(Buffer buf) {
      return buf.readCompoundTag();
    }

    @Override
    public @NotNull BinaryTag valueToBinary(@NotNull CompoundBinaryTag value) {
      return value;
    }
  }

  private static final class VarIntComponent extends SculkDataComponentType<Integer> {

    public VarIntComponent(final String keyValue, final int id) {
      super(keyValue, id);
    }

    @Override
    public void write(Buffer buf, Integer value) {
      buf.writeVarInt(value);
    }

    @Override
    public Integer read(Buffer buf) {
      return buf.readVarInt();
    }

    @Override
    public @NotNull BinaryTag valueToBinary(@NotNull Integer value) {
      return IntBinaryTag.intBinaryTag(value);
    }
  }

  private static final class ComponentComponent extends SculkDataComponentType<Component> {

    public ComponentComponent(final String keyValue, final int id) {
      super(keyValue, id);
    }

    @Override
    public void write(Buffer buf, Component value) {
      buf.writeComponent(value);
    }

    @Override
    public Component read(Buffer buf) {
      throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public @NotNull BinaryTag valueToBinary(@NotNull Component value) {
      throw new UnsupportedOperationException("Not supported yet.");
    }
  }

  private static final class VoidComponent extends SculkDataComponentType<Void> {

    public VoidComponent(final String keyValue, final int id) {
      super(keyValue, id);
    }

    @Override
    public void write(Buffer buf, Void value) {
    }

    @Override
    public Void read(Buffer buf) {
      return null;
    }

    @Override
    public @NotNull BinaryTag valueToBinary(@NotNull Void value) {
      return CompoundBinaryTag.empty();
    }
  }
}
