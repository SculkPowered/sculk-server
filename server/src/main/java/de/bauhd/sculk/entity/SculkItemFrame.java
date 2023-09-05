package de.bauhd.sculk.entity;

import de.bauhd.sculk.container.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class SculkItemFrame extends AbstractEntity implements ItemFrame {

    @Override
    public @NotNull EntityType getType() {
        return EntityType.ITEM_FRAME;
    }

    @Override
    public @NotNull ItemStack getItem() {
        return this.metadata.getItem(8, ItemStack.empty());
    }

    @Override
    public void setItem(@NotNull ItemStack item) {
        this.metadata.setItem(8, item);
    }

    @Override
    public int getRotation() {
        return this.metadata.getVarInt(9, 0);
    }

    @Override
    public void setRotation(int rotation) {
        this.metadata.setVarInt(9, rotation);
    }
}
