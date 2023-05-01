package de.bauhd.minecraft.server.inventory.item;

import net.kyori.adventure.nbt.BinaryTag;
import net.kyori.adventure.nbt.CompoundBinaryTag;
import net.kyori.adventure.nbt.ListBinaryTag;
import net.kyori.adventure.nbt.StringBinaryTag;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.gson.GsonComponentSerializer;
import org.jetbrains.annotations.NotNull;

public class ItemStack {

    private final Material material;
    private int amount;
    private CompoundBinaryTag nbt;

    public ItemStack(final @NotNull Material material) {
        this(material, 1);
    }

    public ItemStack(final @NotNull Material material, final int amount) {
        this(material, amount, CompoundBinaryTag.empty());
    }

    public ItemStack(final @NotNull Material material, final int amount, final @NotNull CompoundBinaryTag nbt) {
        this.material = material;
        this.amount = amount;
        this.nbt = nbt;
    }

    public Material material() {
        return this.material;
    }

    public int amount() {
        return this.amount;
    }

    public @NotNull ItemStack amount(final int amount) {
        this.amount = amount;
        return this;
    }

    public @NotNull ItemStack displayName(final Component displayName) {
        this.display("Name", StringBinaryTag.of(GsonComponentSerializer.gson().serialize(displayName)));
        return this;
    }

    public @NotNull ItemStack lore(final Component... lore) {
        final var list = ListBinaryTag.builder();
        for (final var component : lore) {
            list.add(StringBinaryTag.of(GsonComponentSerializer.gson().serialize(component)));
        }
        this.display("Lore", list.build());
        return this;
    }

    public @NotNull ItemStack unbreakable(final boolean unbreakable) {
        this.nbt = this.nbt.putBoolean("Unbreakable", unbreakable);
        return this;
    }

    public @NotNull CompoundBinaryTag nbt() {
        return this.nbt;
    }

    private void display(final String key, final BinaryTag value) {
        final var tag = this.nbt.getCompound("display");
        this.nbt = this.nbt.put("display", tag.put(key, value));
    }
}
