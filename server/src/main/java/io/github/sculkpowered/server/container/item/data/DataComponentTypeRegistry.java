package io.github.sculkpowered.server.container.item.data;

import io.github.sculkpowered.server.enchantment.Enchantment;
import io.github.sculkpowered.server.protocol.Buffer;
import io.github.sculkpowered.server.protocol.ComponentSerializer;
import io.github.sculkpowered.server.registry.Registries;
import io.github.sculkpowered.server.registry.Registry;
import io.github.sculkpowered.server.registry.SimpleRegistry;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import net.kyori.adventure.nbt.BinaryTag;
import net.kyori.adventure.nbt.BinaryTagTypes;
import net.kyori.adventure.nbt.ByteBinaryTag;
import net.kyori.adventure.nbt.CompoundBinaryTag;
import net.kyori.adventure.nbt.IntBinaryTag;
import net.kyori.adventure.nbt.ListBinaryTag;
import net.kyori.adventure.nbt.StringBinaryTag;
import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;

public final class DataComponentTypeRegistry {

  public static Registry<DataComponentType<?>> get() {
    final var registry = new SimpleRegistry<DataComponentType<?>>("minecraft:data_component_type",
        new BinaryTagComponent("custom_data", 0));
    registry.register(new VarIntComponent("max_stack_size", 1));
    registry.register(new VarIntComponent("max_damage", 2));
    registry.register(new VarIntComponent("damage", 3));
    registry.register(new BooleanComponent("unbreakable", 4));
    registry.register(new ComponentComponent("custom_name", 5));
    registry.register(new ComponentComponent("item_name", 6));
    registry.register(new SculkDataComponentType<List<Component>>("lore", 7) {
      @Override
      public void write(Buffer buf, List<Component> value) {
        buf.writeVarInt(value.size());
        for (final var component : value) {
          buf.writeComponent(component);
        }
      }

      @Override
      public List<Component> read(Buffer buf) {
        final var size = buf.readVarInt();
        final var list = new ArrayList<Component>(size);
        for (var i = 0; i < size; i++) {
          list.add(ComponentSerializer.deserialize(buf.readBinaryTag()));
        }
        return List.copyOf(list);
      }

      @Override
      public @NotNull BinaryTag valueToBinary(@NotNull List<Component> value) {
        final var list = new ArrayList<BinaryTag>(value.size());
        for (final var component : value) {
          list.add(ComponentSerializer.serializeToNbt(component));
        }
        return ListBinaryTag.listBinaryTag(BinaryTagTypes.COMPOUND, list);
      }
    });
    registry.register(new SculkDataComponentType<Rarity>("rarity", 8) {
      @Override
      public void write(Buffer buf, Rarity value) {
        buf.writeVarInt(value.ordinal());
      }

      @Override
      public Rarity read(Buffer buf) {
        return buf.readEnum(Rarity.class);
      }

      @Override
      public @NotNull BinaryTag valueToBinary(@NotNull Rarity value) {
        return StringBinaryTag.stringBinaryTag(value.key());
      }
    });
    registry.register(new ItemEnchantmentsComponent("enchantments", 9));
    registry.register(new VarIntComponent("custom_model_data", 13));
    registry.register(new VoidComponent("hide_additional_tooltip", 14));
    registry.register(new VoidComponent("hide_tooltip", 15));
    registry.register(new VarIntComponent("repair_cost", 16));
    registry.register(new VoidComponent("creative_slot_lock", 17));
    registry.register(new BooleanComponent("enchantment_glint_override", 18));
    registry.register(new VoidComponent("intangible_projectile", 19));
    registry.register(new VoidComponent("fire_resistant", 21));
    registry.register(new ItemEnchantmentsComponent("stored_enchantments", 23));
    registry.register(new BinaryTagComponent("entity_data", 37));
    registry.register(new BinaryTagComponent("bucket_entity_data", 38));
    registry.register(new BinaryTagComponent("block_entity_data", 39));
    registry.register(new VarIntComponent("ominous_bottle_amplifier", 41));
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
      return ComponentSerializer.deserialize(buf.readBinaryTag());
    }

    @Override
    public @NotNull BinaryTag valueToBinary(@NotNull Component value) {
      return ComponentSerializer.serializeToNbt(value);
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

  private static final class BooleanComponent extends SculkDataComponentType<Boolean> {

    public BooleanComponent(final String keyValue, final int id) {
      super(keyValue, id);
    }

    @Override
    public void write(Buffer buf, Boolean value) {
      buf.writeBoolean(value);
    }

    @Override
    public Boolean read(Buffer buf) {
      return buf.readBoolean();
    }

    @Override
    public @NotNull BinaryTag valueToBinary(@NotNull Boolean value) {
      return value ? ByteBinaryTag.ONE : ByteBinaryTag.ZERO;
    }
  }

  private static final class ItemEnchantmentsComponent extends
      SculkDataComponentType<ItemEnchantments> {

    public ItemEnchantmentsComponent(final String keyValue, final int id) {
      super(keyValue, id);
    }

    @Override
    public void write(Buffer buf, ItemEnchantments value) {
      buf.writeVarInt(value.enchantments().size());
      for (final var entry : value.enchantments().entrySet()) {
        buf
            .writeVarInt(entry.getKey().id())
            .writeVarInt(entry.getValue());
      }
      buf.writeBoolean(value.showInTooltip());
    }

    @Override
    public ItemEnchantments read(Buffer buf) {
      final var size = buf.readVarInt();
      final var map = new HashMap<Enchantment, Integer>(size);
      for (var i = 0; i < size; i++) {
        map.put(Registries.enchantments().get(buf.readVarInt()), buf.readVarInt());
      }
      return new ItemEnchantments(map, buf.readBoolean());
    }

    @Override
    public @NotNull BinaryTag valueToBinary(@NotNull ItemEnchantments value) {
      final var builder = CompoundBinaryTag.builder();
      for (final var entry : value.enchantments().entrySet()) {
        builder.putInt(entry.getKey().key().asString(), entry.getValue());
      }
      return CompoundBinaryTag.builder()
          .put("levels", builder.build())
          .putBoolean("show_in_tooltip", value.showInTooltip())
          .build();
    }
  }
}
