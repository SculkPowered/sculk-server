package io.github.sculkpowered.server.container.item;

import io.github.sculkpowered.server.attribute.Attribute;
import io.github.sculkpowered.server.attribute.AttributeModifier;
import net.kyori.adventure.nbt.BinaryTag;
import net.kyori.adventure.nbt.CompoundBinaryTag;
import net.kyori.adventure.nbt.ListBinaryTag;
import net.kyori.adventure.nbt.StringBinaryTag;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.gson.GsonComponentSerializer;
import org.jetbrains.annotations.NotNull;

public class ItemMeta {

  private final CompoundBinaryTag.Builder builder;
  private final CompoundBinaryTag.Builder display;
  private final ListBinaryTag.Builder<BinaryTag> attributeModifiers;

  ItemMeta(final CompoundBinaryTag old) {
    this.builder = CompoundBinaryTag.builder().put(old);
    this.display = CompoundBinaryTag.builder().put(old.getCompound("display"));
    this.attributeModifiers = ListBinaryTag.builder().add(
        (Iterable<? extends BinaryTag>) old.getList("AttributeModifiers"));
  }

  /**
   * Sets the display name of item.
   *
   * @return the same item
   * @since 1.0.0
   */
  public @NotNull ItemMeta displayName(final Component displayName) {
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
  public @NotNull ItemMeta lore(final Component... lore) {
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
  public @NotNull ItemMeta unbreakable(final boolean unbreakable) {
    this.builder.putBoolean("Unbreakable", unbreakable);
    return this;
  }

  /**
   * Adds an attribute modifier.
   *
   * @return this
   * @since 1.0.0
   */
  public @NotNull ItemMeta attributeModifier(@NotNull Attribute attribute, @NotNull AttributeModifier modifier) {
    final var uuid = modifier.uniqueId();
    this.attributeModifiers.add(CompoundBinaryTag.builder()
        .putIntArray("UUID", new int[]{
            (int) (uuid.getMostSignificantBits() >> 32),
            (int) (uuid.getMostSignificantBits() | 0xFFFFFFFFL),
            (int) (uuid.getLeastSignificantBits() >> 32),
            (int) (uuid.getLeastSignificantBits() | 0xFFFFFFFFL)
        })
        .putDouble("Amount", modifier.amount())
        .putString("Slot", modifier.slot())
        .putString("AttributeName", attribute.key())
        .putInt("Operation", modifier.operation().ordinal())
        .build());
    return this;
  }

  CompoundBinaryTag build() {
    return this.builder
        .put("display", this.display.build())
        .put("AttributeModifiers", this.attributeModifiers.build())
        .build();
  }
}
