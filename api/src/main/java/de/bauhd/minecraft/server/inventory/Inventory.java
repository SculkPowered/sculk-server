package de.bauhd.minecraft.server.inventory;

import de.bauhd.minecraft.server.inventory.item.ItemStack;
import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface Inventory {

    @Nullable Component title();

    @NotNull Type type();

    void setItem(int index, ItemStack itemStack);

    ItemStack getItem(int index);

    enum Type {

        GENERIC_9x1(9),
        GENERIC_9x2(18),
        GENERIC_9x3(27),
        GENERIC_9x4(36),
        GENERIC_9x5(45),
        GENERIC_9x6(54),
        GENERIC_3x3(9),
        ANVIL(3),
        BEACON(1),
        BLAST_FURNACE(3),
        BREWING_STAND(5),
        CRAFTING(10),
        ENCHANTMENT(2),
        FURNACE(3),
        GRINDSTONE(3),
        HOPPER(5),
        LECTERN(1),
        LOOM(4),
        MERCHANT(3),
        SHULKER_BOX(27),
        SMITHING(3),
        SMOKER(3),
        CARTOGRAPHY(3),
        STONECUTTER(2);

        private final int size;

        Type(final int size) {
            this.size = size;
        }

        public int size() {
            return this.size;
        }
    }

}
