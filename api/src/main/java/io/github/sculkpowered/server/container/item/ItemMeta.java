package io.github.sculkpowered.server.container.item;

import net.kyori.adventure.nbt.CompoundBinaryTag;
import net.kyori.adventure.nbt.ListBinaryTag;
import net.kyori.adventure.nbt.StringBinaryTag;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.gson.GsonComponentSerializer;
import org.jetbrains.annotations.NotNull;

public class ItemMeta {

  private final CompoundBinaryTag.Builder builder;
  private final CompoundBinaryTag.Builder display;

  ItemMeta(final CompoundBinaryTag.Builder builder, final CompoundBinaryTag.Builder display) {
    this.builder = builder;
    this.display = display;
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

  CompoundBinaryTag build() {
    return this.builder.put("display", this.display.build()).build();
  }
}
