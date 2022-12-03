package de.bauhd.minecraft.server.api.inventory;

import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface Inventory {

    @Nullable Component title();

    @NotNull Type type();

    enum Type {

        GENERIC_9x1,
        GENERIC_9x2,
        GENERIC_9x3,
        GENERIC_9x4,
        GENERIC_9x5,
        GENERIC_9x6,
        GENERIC_3x3,
        ANVIL,
        BEACON,
        BLAST_FURNACE,
        BREWING_STAND,
        CRAFTING,
        ENCHANTMENT,
        FURNACE,
        GRINDSTONE,
        HOPPER,
        LECTERN,
        LOOM,
        MERCHANT,
        SHULKER_BOX,
        SMITHING,
        SMOKER,
        CARTOGRAPHY,
        STONECUTTER

    }

}
