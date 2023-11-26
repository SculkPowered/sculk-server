package io.github.sculkpowered.server.container.item;

import io.github.sculkpowered.server.attribute.Attribute;
import io.github.sculkpowered.server.attribute.AttributeModifier;
import io.github.sculkpowered.server.enchantment.Enchantment;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.kyori.adventure.nbt.CompoundBinaryTag;
import net.kyori.adventure.nbt.ListBinaryTag;
import net.kyori.adventure.nbt.StringBinaryTag;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.gson.GsonComponentSerializer;
import org.jetbrains.annotations.NotNull;

public class ItemMeta {

  private final CompoundBinaryTag nbt;
  private final Map<Attribute, List<AttributeModifier>> attributeModifiers;
  private final Map<Enchantment, Short> enchantments;

  ItemMeta(
      final CompoundBinaryTag nbt,
      final Map<Attribute, List<AttributeModifier>> attributeModifiers,
      final Map<Enchantment, Short> enchantments
  ) {
    this.nbt = nbt;
    this.attributeModifiers = attributeModifiers;
    this.enchantments = enchantments;
  }

  public short enchantmentLevel(@NotNull Enchantment enchantment) {
    return this.enchantments.getOrDefault(enchantment, (short) 0);
  }

  public @NotNull ItemMeta.Builder toBuilder() {
    return new Builder(
        CompoundBinaryTag.builder().put(this.nbt),
        CompoundBinaryTag.builder().put(this.nbt.getCompound("display")),
        new HashMap<>(this.attributeModifiers),
        new HashMap<>(this.enchantments)
    );
  }

  public @NotNull CompoundBinaryTag asNbt() {
    final var attributeModifiers = ListBinaryTag.builder();
    for (final var entry : this.attributeModifiers.entrySet()) {
      final var attribute = entry.getKey().key();
      for (final var modifier : entry.getValue()) {
        final var uuid = modifier.uniqueId();
        attributeModifiers.add(CompoundBinaryTag.builder()
            .putIntArray("UUID", new int[]{
                (int) (uuid.getMostSignificantBits() >> 32),
                (int) (uuid.getMostSignificantBits() | 0xFFFFFFFFL),
                (int) (uuid.getLeastSignificantBits() >> 32),
                (int) (uuid.getLeastSignificantBits() | 0xFFFFFFFFL)
            })
            .putDouble("Amount", modifier.amount())
            .putString("Slot", modifier.slot())
            .putString("AttributeName", attribute)
            .putInt("Operation", modifier.operation().ordinal())
            .build());
      }
    }

    final var enchantments = ListBinaryTag.builder();
    for (final var entry : this.enchantments.entrySet()) {
      enchantments.add(CompoundBinaryTag.builder()
          .putString("id", entry.getKey().key())
          .putShort("lvl", entry.getValue())
          .build());
    }

    return this.nbt
        .put("AttributeModifiers", attributeModifiers.build())
        .put("Enchantments", enchantments.build());
  }

  public static @NotNull Builder builder() {
    return new Builder(CompoundBinaryTag.builder(), CompoundBinaryTag.builder(),
        new HashMap<>(), new HashMap<>());
  }

  public static final class Builder {

    private final CompoundBinaryTag.Builder builder;
    private final CompoundBinaryTag.Builder display;
    private final Map<Attribute, List<AttributeModifier>> attributeModifiers;
    private final Map<Enchantment, Short> enchantments;

    private Builder(
        final CompoundBinaryTag.Builder builder,
        final CompoundBinaryTag.Builder display,
        final Map<Attribute, List<AttributeModifier>> attributeModifiers,
        final Map<Enchantment, Short> enchantments
    ) {
      this.builder = builder;
      this.display = display;
      this.attributeModifiers = attributeModifiers;
      this.enchantments = enchantments;
    }

    /**
     * Sets the display name of item.
     *
     * @return the same item
     * @since 1.0.0
     */
    public @NotNull ItemMeta.Builder displayName(final Component displayName) {
      this.display.put("Name",
          StringBinaryTag.stringBinaryTag(GsonComponentSerializer.gson().serialize(displayName)));
      return this;
    }

    /**
     * Sets the lore of item.
     *
     * @return the same item
     * @since 1.0.0
     */
    public @NotNull ItemMeta.Builder lore(final Component... lore) {
      final var list = ListBinaryTag.builder();
      for (final var component : lore) {
        list.add(
            StringBinaryTag.stringBinaryTag(GsonComponentSerializer.gson().serialize(component)));
      }
      this.display.put("Lore", list.build());
      return this;
    }

    /**
     * Sets the item unbreakable or not.
     *
     * @return the same item
     * @since 1.0.0
     */
    public @NotNull ItemMeta.Builder unbreakable(final boolean unbreakable) {
      this.builder.putBoolean("Unbreakable", unbreakable);
      return this;
    }

    /**
     * Adds an attribute modifier.
     *
     * @return this
     * @since 1.0.0
     */
    public @NotNull ItemMeta.Builder attributeModifier(@NotNull Attribute attribute,
        @NotNull AttributeModifier modifier) {
      var attributeModifiers = this.attributeModifiers.get(attribute);
      if (attributeModifiers == null) {
        attributeModifiers = new ArrayList<>();
      }
      attributeModifiers.add(modifier);
      this.attributeModifiers.put(attribute, attributeModifiers);
      return this;
    }

    /**
     * Enchants the item.
     *
     * @param enchantment the enchantment
     * @param level       the enchantment level
     * @return this
     * @since 1.0.0
     */
    public @NotNull ItemMeta.Builder enchant(@NotNull Enchantment enchantment, short level) {
      this.enchantments.put(enchantment, level);
      return this;
    }

    public @NotNull ItemMeta build() {
      return new ItemMeta(this.builder
          .put("display", this.display.build())
          .build(), this.attributeModifiers, this.enchantments);
    }
  }
}
