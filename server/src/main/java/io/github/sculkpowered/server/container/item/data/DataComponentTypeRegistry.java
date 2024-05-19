package io.github.sculkpowered.server.container.item.data;

import io.github.sculkpowered.server.attribute.AttributeModifier;
import io.github.sculkpowered.server.attribute.AttributeOperation;
import io.github.sculkpowered.server.attribute.AttributeSlot;
import io.github.sculkpowered.server.container.item.data.ItemAttributes.Entry;
import io.github.sculkpowered.server.enchantment.Enchantment;
import io.github.sculkpowered.server.entity.player.GameProfile;
import io.github.sculkpowered.server.potion.CustomPotionEffect;
import io.github.sculkpowered.server.potion.PotionEffect;
import io.github.sculkpowered.server.potion.PotionType;
import io.github.sculkpowered.server.protocol.Buffer;
import io.github.sculkpowered.server.protocol.Buffer.Reader;
import io.github.sculkpowered.server.protocol.Buffer.Writer;
import io.github.sculkpowered.server.protocol.ComponentSerializer;
import io.github.sculkpowered.server.registry.Registries;
import io.github.sculkpowered.server.registry.Registry;
import io.github.sculkpowered.server.registry.SimpleRegistry;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import net.kyori.adventure.nbt.BinaryTag;
import net.kyori.adventure.nbt.BinaryTagTypes;
import net.kyori.adventure.nbt.ByteBinaryTag;
import net.kyori.adventure.nbt.CompoundBinaryTag;
import net.kyori.adventure.nbt.IntArrayBinaryTag;
import net.kyori.adventure.nbt.IntBinaryTag;
import net.kyori.adventure.nbt.ListBinaryTag;
import net.kyori.adventure.nbt.StringBinaryTag;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.util.RGBLike;
import org.jetbrains.annotations.NotNull;

public final class DataComponentTypeRegistry {

  public static Registry<DataComponentType<?>> get() {
    final String tooltipKey = "show_in_tooltip";

    // CompoundBinaryTag
    final Writer<CompoundBinaryTag> compoundBinaryTagWriter = Buffer::writeBinaryTag;
    final Reader<CompoundBinaryTag> compoundBinaryTagReader = buf ->
        (CompoundBinaryTag) buf.readBinaryTag();
    final var compoundBinaryTagSerializer = new BinarySerializer<CompoundBinaryTag>() {
      @Override
      public BinaryTag serialize(CompoundBinaryTag compoundBinaryTag) {
        return compoundBinaryTag;
      }

      @Override
      public CompoundBinaryTag deserialize(BinaryTag binaryTag) {
        return (CompoundBinaryTag) binaryTag;
      }
    };

    // VarInt
    final Writer<Integer> varIntWriter = Buffer::writeVarInt;
    final Reader<Integer> varIntReader = Buffer::readVarInt;
    final var intSerializer = new BinarySerializer<Integer>() {
      @Override
      public BinaryTag serialize(Integer integer) {
        return IntBinaryTag.intBinaryTag(integer);
      }

      @Override
      public Integer deserialize(BinaryTag binaryTag) {
        return ((IntBinaryTag) binaryTag).intValue();
      }
    };

    // Component
    final Writer<Component> componentWriter = Buffer::writeComponent;
    final Reader<Component> componentReader = buf ->
        ComponentSerializer.deserialize(buf.readBinaryTag());
    final var componentSerializer = new BinarySerializer<Component>() {
      @Override
      public BinaryTag serialize(Component component) {
        return ComponentSerializer.serializeToNbt(component);
      }

      @Override
      public Component deserialize(BinaryTag binaryTag) {
        return ComponentSerializer.deserialize(binaryTag);
      }
    };

    // Enchantments
    final Writer<ItemEnchantments> enchantmentsWriter = (buf, value) -> {
      buf.writeVarInt(value.enchantments().size());
      for (final var entry : value.enchantments().entrySet()) {
        buf
            .writeVarInt(entry.getKey().id())
            .writeVarInt(entry.getValue());
      }
      buf.writeBoolean(value.showInTooltip());
    };
    final Reader<ItemEnchantments> enchantmentsReader = buf -> {
      final var size = buf.readVarInt();
      final var map = new HashMap<Enchantment, Integer>(size);
      for (var i = 0; i < size; i++) {
        map.put(Registries.enchantments().get(buf.readVarInt()), buf.readVarInt());
      }
      return new ItemEnchantments(map, buf.readBoolean());
    };
    final var enchantmentSerializer = new BinarySerializer<ItemEnchantments>() {
      @Override
      public BinaryTag serialize(ItemEnchantments value) {
        final var builder = CompoundBinaryTag.builder();
        for (final var entry : value.enchantments().entrySet()) {
          builder.putInt(entry.getKey().key().asString(), entry.getValue());
        }
        return CompoundBinaryTag.builder()
            .put("levels", builder.build())
            .putBoolean(tooltipKey, value.showInTooltip())
            .build();
      }

      @Override
      public ItemEnchantments deserialize(BinaryTag binaryTag) {
        final var compound = (CompoundBinaryTag) binaryTag;
        final var levels = compound.getCompound("levels");
        final var enchantments = new HashMap<Enchantment, Integer>(levels.size());
        for (final var enchantment : levels) {
          enchantments.put(Registries.enchantments().get(enchantment.getKey()),
              ((IntBinaryTag) enchantment).intValue());
        }
        return new ItemEnchantments(enchantments, compound
            .getBoolean(tooltipKey, true));
      }
    };

    // Unit
    final Writer<Unit> voidWriter = (buf, value) -> {
    };
    final Reader<Unit> voidReader = buf -> Unit.INSTANCE;
    final var voidSerializer = new BinarySerializer<Unit>() {
      @Override
      public BinaryTag serialize(Unit unused) {
        return CompoundBinaryTag.empty();
      }

      @Override
      public Unit deserialize(BinaryTag binaryTag) {
        return Unit.INSTANCE;
      }
    };

    // BlockPredicates
    final Writer<BlockPredicates> blockPredicatesWriter = (buf, value) -> {
    };
    final Reader<BlockPredicates> blockPredicatesReader = buf -> new BlockPredicates();
    final var blockPredicatesSerializer = new BinarySerializer<BlockPredicates>() {
      @Override
      public BinaryTag serialize(BlockPredicates blockPredicates) {
        return null;
      }

      @Override
      public BlockPredicates deserialize(BinaryTag binaryTag) {
        return null;
      }
    };

    var id = 0;
    final var registry = new SimpleRegistry<DataComponentType<?>>("minecraft:data_component_type",
        new SculkDataComponentType<>("custom_data", id++,
            compoundBinaryTagWriter, compoundBinaryTagReader, compoundBinaryTagSerializer));
    registry.register(new SculkDataComponentType<>("max_stack_size", id++,
        varIntWriter, varIntReader, intSerializer));
    registry.register(new SculkDataComponentType<>("max_damage", id++,
        varIntWriter, varIntReader, intSerializer));
    registry.register(new SculkDataComponentType<>("damage", id++,
        varIntWriter, varIntReader, intSerializer));
    registry.register(new SculkDataComponentType<>("unbreakable", id++,
        (buf, value) -> buf.writeBoolean(value.showInTooltip()),
        buf -> Unbreakable.showInTooltip(buf.readBoolean()),
        new BinarySerializer<>() {
          @Override
          public BinaryTag serialize(Unbreakable unbreakable) {
            return CompoundBinaryTag.builder()
                .putBoolean(tooltipKey, unbreakable.showInTooltip())
                .build();
          }

          @Override
          public Unbreakable deserialize(BinaryTag binaryTag) {
            if (binaryTag instanceof CompoundBinaryTag compound) {
              return Unbreakable.showInTooltip(compound.getBoolean(tooltipKey));
            } else if (binaryTag instanceof ByteBinaryTag byteBinaryTag) {
              return Unbreakable.showInTooltip(byteBinaryTag == ByteBinaryTag.ONE);
            }
            return null;
          }
        }));
    registry.register(new SculkDataComponentType<>("custom_name", id++,
        componentWriter, componentReader, componentSerializer));
    registry.register(new SculkDataComponentType<>("item_name", id++,
        componentWriter, componentReader, componentSerializer));
    registry.register(new SculkDataComponentType<>("lore", id++,
        (buf, value) -> {
          buf.writeVarInt(value.size());
          for (final var component : value) {
            buf.writeComponent(component);
          }
        },
        buf -> {
          final var size = buf.readVarInt();
          final var list = new ArrayList<Component>(size);
          for (var i = 0; i < size; i++) {
            list.add(ComponentSerializer.deserialize(buf.readBinaryTag()));
          }
          return List.copyOf(list);
        },
        new BinarySerializer<>() {
          @Override
          public BinaryTag serialize(List<Component> value) {
            final var list = new ArrayList<BinaryTag>(value.size());
            for (final var component : value) {
              list.add(ComponentSerializer.serializeToNbt(component));
            }
            return ListBinaryTag.listBinaryTag(BinaryTagTypes.COMPOUND, list);
          }

          @Override
          public List<Component> deserialize(BinaryTag binaryTag) {
            final var list = new ArrayList<Component>();
            final var binaryList = (ListBinaryTag) binaryTag;
            for (final var tag : binaryList) {
              list.add(ComponentSerializer.deserialize(tag));
            }
            return list;
          }
        }));
    registry.register(new SculkDataComponentType<>("rarity", id++,
        (buf, value) -> buf.writeVarInt(value.ordinal()),
        buf -> buf.readEnum(Rarity.class),
        new BinarySerializer<>() {
          @Override
          public BinaryTag serialize(Rarity rarity) {
            return StringBinaryTag.stringBinaryTag(rarity.key());
          }

          @Override
          public Rarity deserialize(BinaryTag binaryTag) {
            return Rarity.valueOf(
                ((StringBinaryTag) binaryTag).value().toUpperCase(Locale.ENGLISH));
          }
        }));
    registry.register(new SculkDataComponentType<>("enchantments", id++,
        enchantmentsWriter, enchantmentsReader, enchantmentSerializer));
    registry.register(new SculkDataComponentType<>("can_place_on", id++,
        blockPredicatesWriter, blockPredicatesReader, blockPredicatesSerializer));
    registry.register(new SculkDataComponentType<>("can_break", id++,
        blockPredicatesWriter, blockPredicatesReader, blockPredicatesSerializer));
    registry.register(new SculkDataComponentType<>("attribute_modifiers", id++,
        (buf, value) -> {
          buf.writeVarInt(value.attributes().size());
          for (final var attribute : value.attributes()) {
            buf
                .writeVarInt(attribute.attribute().id())
                .writeUniqueId(attribute.modifier().uniqueId())
                .writeString(attribute.modifier().name())
                .writeDouble(attribute.modifier().amount())
                .writeVarInt(attribute.modifier().operation().ordinal());

            buf.writeVarInt(attribute.slot().ordinal());
          }
          buf.writeBoolean(value.showInTooltip());
        },
        buf -> {
          final var size = buf.readVarInt();
          final var attributes = new ArrayList<Entry>(size);
          for (var i = 0; i < size; i++) {
            attributes.add(
                new Entry(Registries.attributes().get(buf.readVarInt()),
                    new AttributeModifier(
                        buf.readUniqueId(),
                        buf.readString(),
                        buf.readDouble(),
                        buf.readEnum(AttributeOperation.class)),
                    buf.readEnum(AttributeSlot.class))
            );
          }
          return new ItemAttributes(List.copyOf(attributes), buf.readBoolean());
        },
        new BinarySerializer<>() {
          @Override
          public BinaryTag serialize(ItemAttributes attributes) {
            final var list = ListBinaryTag.builder(BinaryTagTypes.COMPOUND);
            for (final var entry : attributes.attributes()) {
              list.add(CompoundBinaryTag.builder()
                  .putString("type", entry.attribute().name())
                  .putIntArray("uuid", uuidToIntArray(entry.modifier().uniqueId()))
                  .putString("name", entry.modifier().name())
                  .putDouble("amount", entry.modifier().amount())
                  .putString("operation", entry.modifier().operation()
                      .name().toLowerCase(Locale.ENGLISH))
                  .putString("slot", entry.slot().name().toLowerCase(Locale.ENGLISH))
                  .build());
            }
            return CompoundBinaryTag.builder()
                .put("modifiers", list.build())
                .putBoolean(tooltipKey, attributes.showInTooltip())
                .build();
          }

          @Override
          public ItemAttributes deserialize(BinaryTag binaryTag) {
            var showInTooltip = true;
            ListBinaryTag modifiers = ListBinaryTag.empty();
            if (binaryTag instanceof CompoundBinaryTag compound) {
              showInTooltip = compound.getBoolean(tooltipKey, true);
              modifiers = compound.getList("modifiers");
            } else if (binaryTag instanceof ListBinaryTag listBinaryTag) {
              modifiers = listBinaryTag;
            }
            final var attributes = new ArrayList<ItemAttributes.Entry>(modifiers.size());
            for (final var tag : modifiers) {
              final var modifier = (CompoundBinaryTag) tag;
              attributes.add(new ItemAttributes.Entry(
                  Registries.attributes().get(modifier.getString("type")),
                  new AttributeModifier(
                      intArrayToUuid(modifier.getIntArray("uuid")),
                      modifier.getString("name"),
                      modifier.getDouble("amount"),
                      AttributeOperation.valueOf(modifier.getString("operation")
                          .toUpperCase(Locale.ENGLISH))),
                  AttributeSlot.valueOf(modifier.getString("slot").toUpperCase(Locale.ENGLISH))));
            }
            return new ItemAttributes(List.copyOf(attributes), showInTooltip);
          }
        }));
    registry.register(new SculkDataComponentType<>("custom_model_data", id++,
        varIntWriter, varIntReader, intSerializer));
    registry.register(new SculkDataComponentType<>("hide_additional_tooltip", id++,
        voidWriter, voidReader, voidSerializer));
    registry.register(new SculkDataComponentType<>("hide_tooltip", id++,
        voidWriter, voidReader, voidSerializer));
    registry.register(new SculkDataComponentType<>("repair_cost", id++,
        varIntWriter, varIntReader, intSerializer));
    registry.register(new SculkDataComponentType<>("creative_slot_lock", id++,
        voidWriter, voidReader, null));
    registry.register(new SculkDataComponentType<>("enchantment_glint_override", id++,
        Buffer::writeBoolean, Buffer::readBoolean, new BinarySerializer<>() {
      @Override
      public BinaryTag serialize(Boolean value) {
        return value ? ByteBinaryTag.ONE : ByteBinaryTag.ZERO;
      }

      @Override
      public Boolean deserialize(BinaryTag binaryTag) {
        return ((ByteBinaryTag) binaryTag).value() == 1;
      }
    }));
    registry.register(new SculkDataComponentType<>("intangible_projectile", id++,
        null, null, voidSerializer));
    registry.register(new SculkDataComponentType<>("food", id++,
        (buf, value) -> {
        },
        buf -> new Food(),
        new BinarySerializer<>() {
          @Override
          public BinaryTag serialize(Food food) {
            return null;
          }

          @Override
          public Food deserialize(BinaryTag binaryTag) {
            return null;
          }
        }));
    registry.register(new SculkDataComponentType<>("fire_resistant", id++,
        voidWriter, voidReader, voidSerializer));
    registry.register(new SculkDataComponentType<>("tool", id++,
        (buf, value) -> {
        },
        buf -> new Tool(),
        new BinarySerializer<>() {
          @Override
          public BinaryTag serialize(Tool tool) {
            return null;
          }

          @Override
          public Tool deserialize(BinaryTag binaryTag) {
            return null;
          }
        }));
    registry.register(new SculkDataComponentType<>("stored_enchantments", id++,
        enchantmentsWriter, enchantmentsReader, enchantmentSerializer));
    registry.register(new SculkDataComponentType<>("dyed_color", id++,
        (buf, value) -> buf
            .writeInt(asRGB(value.color()))
            .writeBoolean(value.showInTooltip()),
        buf -> new DyedColor(TextColor.color(buf.readInt()), buf.readBoolean()),
        new BinarySerializer<>() {
          @Override
          public BinaryTag serialize(DyedColor color) {
            return CompoundBinaryTag.builder()
                .putInt("color", asRGB(color.color()))
                .putBoolean(tooltipKey, color.showInTooltip())
                .build();
          }

          @Override
          public DyedColor deserialize(BinaryTag binaryTag) {
            if (binaryTag instanceof CompoundBinaryTag compound) {
              return new DyedColor(TextColor.color(compound.getInt("color")),
                  compound.getBoolean(tooltipKey));
            } else {
              return new DyedColor(TextColor.color(((IntBinaryTag) binaryTag).value()), true);
            }
          }
        }));
    registry.register(new SculkDataComponentType<>("map_color", id++,
        null, null, null));
    registry.register(new SculkDataComponentType<>("map_id", id++,
        varIntWriter, varIntReader, intSerializer));
    registry.register(new SculkDataComponentType<>("map_decorations", id++,
        null, null, null));
    registry.register(new SculkDataComponentType<>("map_post_processing", id++,
        null, null, null));
    registry.register(new SculkDataComponentType<>("charged_projectiles", id++,
        null, null, null));
    registry.register(new SculkDataComponentType<>("bundle_contents", id++,
        null, null, null));
    registry.register(new SculkDataComponentType<>("potion_contents", id++,
        (buf, value) -> {
          if (buf.writeOptional(value.potion())) {
            assert value.potion() != null;
            buf.writeVarInt(value.potion().id());
          }
          if (buf.writeOptional(value.color())) {
            assert value.color() != null;
            buf.writeInt(asRGB(value.color()));
          }
          buf.writeVarInt(value.customEffects().size());
          for (final var customEffect : value.customEffects()) {
            buf.writeVarInt(customEffect.effect().id());
            writeProperties(buf, customEffect.properties());
          }
        },
        buf -> {
          PotionType potion = null;
          if (buf.readBoolean()) {
            potion = Registries.potions().get(buf.readVarInt());
          }
          RGBLike color = null;
          if (buf.readBoolean()) {
            color = TextColor.color(buf.readVarInt());
          }
          final var size = buf.readVarInt();
          final var list = new ArrayList<CustomPotionEffect>(size);
          for (var i = 0; i < size; i++) {
            list.add(new CustomPotionEffect(
                Registries.mobEffects().get(buf.readVarInt()), readProperties(buf)));
          }
          return new PotionContents(potion, color, List.copyOf(list));
        },
        new BinarySerializer<>() {

          @Override
          public BinaryTag serialize(PotionContents potionContents) {
            return null;
          }

          @Override
          public PotionContents deserialize(BinaryTag binaryTag) {
            return null;
          }
        }));
    registry.register(new SculkDataComponentType<>("suspicious_stew_effects", id++,
        (buf, value) -> {
          for (final var effect : value) {
            buf
                .writeVarInt(effect.effect().id())
                .writeVarInt(effect.duration());
          }
        }, buf -> {
      final var size = buf.readVarInt();
      final var effects = new ArrayList<PotionEffect>(size);
      for (var i = 0; i < size; i++) {
        effects.add(new PotionEffect(
            Registries.mobEffects().get(buf.readVarInt()), buf.readVarInt()));
      }
      return List.copyOf(effects);
    }, new BinarySerializer<>() {
      @Override
      public BinaryTag serialize(List<PotionEffect> effects) {
        final var list = ListBinaryTag.builder(BinaryTagTypes.COMPOUND);
        for (final var effect : effects) {
          list.add(CompoundBinaryTag.builder()
              .putString("id", effect.effect().key().asString())
              .putInt("duration", effect.duration())
              .build());
        }
        return list.build();
      }

      @Override
      public List<PotionEffect> deserialize(BinaryTag binaryTag) {
        final var list = (ListBinaryTag) binaryTag;
        final var effects = new ArrayList<PotionEffect>(list.size());
        for (final var tag : list) {
          final var compound = (CompoundBinaryTag) tag;
          effects.add(new PotionEffect(Registries.mobEffects()
              .get(compound.getString("id")),
              compound.getInt("duration", 160)));
        }
        return List.copyOf(effects);
      }
    }));
    registry.register(new SculkDataComponentType<>("writable_book_content", id++,
        null, null, null));
    registry.register(new SculkDataComponentType<>("written_book_content", id++,
        null, null, null));
    registry.register(new SculkDataComponentType<>("trim", id++,
        null, null, null));
    registry.register(new SculkDataComponentType<>("debug_stick_state", id++,
        null, null, null));
    registry.register(new SculkDataComponentType<>("entity_data", id++,
        compoundBinaryTagWriter, compoundBinaryTagReader, compoundBinaryTagSerializer));
    registry.register(new SculkDataComponentType<>("bucket_entity_data", id++,
        compoundBinaryTagWriter, compoundBinaryTagReader, compoundBinaryTagSerializer));
    registry.register(new SculkDataComponentType<>("block_entity_data", id++,
        compoundBinaryTagWriter, compoundBinaryTagReader, compoundBinaryTagSerializer));
    registry.register(new SculkDataComponentType<>("instrument", id++,
        null, null, null));
    registry.register(new SculkDataComponentType<>("ominous_bottle_amplifier", id++,
        varIntWriter, varIntReader, intSerializer));
    registry.register(new SculkDataComponentType<>("recipes", id++,
        null, null, null));
    registry.register(new SculkDataComponentType<>("lodestone_tracker", id++,
        null, null, null));
    registry.register(new SculkDataComponentType<>("firework_explosion", id++,
        null, null, null));
    registry.register(new SculkDataComponentType<>("fireworks", id++,
        null, null, null));
    registry.register(new SculkDataComponentType<>("profile", id++,
        (buf, value) -> {
          if (buf.writeOptional(value.name())) {
            buf.writeString(value.name());
          }
          if (buf.writeOptional(value.uniqueId())) {
            buf.writeUniqueId(value.uniqueId());
          }
          buf.writeProfileProperties(value.properties());
        },
        buf -> {
          String name = null;
          if (buf.readBoolean()) {
            name = buf.readString();
          }
          UUID uniqueId = null;
          if (buf.readBoolean()) {
            uniqueId = buf.readUniqueId();
          }
          final var size = buf.readVarInt();
          final var properties = new ArrayList<GameProfile.Property>(size);
          for (var i = 0; i < size; i++) {
            final var key = buf.readString();
            final var value = buf.readString();
            String signature = null;
            if (buf.readBoolean()) {
              signature = buf.readString();
            }
            properties.add(new GameProfile.Property(key, value, signature));
          }
          return new GameProfile(uniqueId, name, List.copyOf(properties));
        }, new BinarySerializer<>() {
      @Override
      public BinaryTag serialize(GameProfile profile) {
        final var builder = CompoundBinaryTag.builder();
        if (profile.name() != null) {
          builder.putString("name", profile.name());
        }
        if (profile.uniqueId() != null) {
          builder.putIntArray("uuid", uuidToIntArray(profile.uniqueId()));
        }
        if (!profile.properties().isEmpty()) {
          final var list = ListBinaryTag.builder(BinaryTagTypes.COMPOUND);
          for (final var property : profile.properties()) {
            final var propertyBuilder = CompoundBinaryTag.builder()
                .putString("name", property.key())
                .putString("value", property.value());
            if (property.signature() != null) {
              propertyBuilder.putString("signature", property.signature());
            }
            list.add(propertyBuilder.build());
          }
          builder.put("properties", list.build());
        }
        return builder.build();
      }

      @Override
      public GameProfile deserialize(BinaryTag binaryTag) {
        final var compound = (CompoundBinaryTag) binaryTag;
        UUID uniqueId = null;
        if (compound.get("uuid") instanceof IntArrayBinaryTag uuidTag) {
          uniqueId = intArrayToUuid(uuidTag.value());
        }
        String name = null;
        if (compound.get("name") instanceof StringBinaryTag nameTag) {
          name = nameTag.value();
        }
        final var propertiesTag = compound.getList("properties");
        final List<GameProfile.Property> properties = new ArrayList<>(propertiesTag.size());
        for (final var tag : propertiesTag) {
          final var property = (CompoundBinaryTag) tag;
          properties.add(new GameProfile.Property(
              property.getString("name"), property.getString("value"),
              property.get("signature")
                  instanceof StringBinaryTag signatureTag ? signatureTag.value() : null));
        }
        return new GameProfile(uniqueId, name, List.copyOf(properties));
      }
    }));
    registry.register(new SculkDataComponentType<>("note_block_sound", id++,
        null, null, null));
    registry.register(new SculkDataComponentType<>("banner_patterns", id++,
        null, null, null));
    registry.register(new SculkDataComponentType<>("base_color", id++,
        null, null, null));
    registry.register(new SculkDataComponentType<>("pot_decorations", id++,
        null, null, null));
    registry.register(new SculkDataComponentType<>("container", id++,
        null, null, null));
    registry.register(new SculkDataComponentType<>("block_state", id++,
        null, null, null));
    registry.register(new SculkDataComponentType<>("bees", id++,
        null, null, null));
    registry.register(new SculkDataComponentType<>("lock", id++,
        null, null, null));
    registry.register(new SculkDataComponentType<>("container_loot", id,
        null, null, null));
    return registry;
  }

  private static void writeProperties(final Buffer buf,
      final CustomPotionEffect.Properties properties) {
    buf
        .writeByte(properties.amplifier())
        .writeVarInt(properties.duration())
        .writeBoolean(properties.ambient())
        .writeBoolean(properties.showParticles())
        .writeBoolean(properties.showIcon());
    if (buf.writeOptional(properties.hiddenProperties())) {
      assert properties.hiddenProperties() != null;
      writeProperties(buf, properties.hiddenProperties());
    }
  }

  private static CustomPotionEffect.Properties readProperties(final Buffer buf) {
    final var amplifier = buf.readByte();
    final var duration = buf.readVarInt();
    final var ambient = buf.readBoolean();
    final var showParticles = buf.readBoolean();
    final var showIcon = buf.readBoolean();
    final var hiddenProperties = buf.readBoolean();
    return new CustomPotionEffect.Properties(
        amplifier, duration, ambient, showParticles, showIcon,
        (hiddenProperties ? readProperties(buf) : null)
    );
  }

  private static int asRGB(final @NotNull RGBLike rgbLike) {
    var rgb = rgbLike.red();
    rgb = (rgb << 8) + rgbLike.green();
    return (rgb << 8) + rgbLike.blue();
  }

  private static int[] uuidToIntArray(final UUID uniqueId) {
    final var mostBits = uniqueId.getMostSignificantBits();
    final var leastBits = uniqueId.getLeastSignificantBits();
    return new int[]{
        (int) (mostBits >> 32),
        (int) mostBits,
        (int) (leastBits >> 32),
        (int) leastBits
    };
  }

  private static UUID intArrayToUuid(final int[] ints) {
    return new UUID(
        (long) ints[0] << 32 | ints[1] & 0xFFFFFFFFL,
        (long) ints[2] << 32 | ints[3] & 0xFFFFFFFFL
    );
  }
}
