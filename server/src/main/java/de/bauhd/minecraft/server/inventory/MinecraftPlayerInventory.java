package de.bauhd.minecraft.server.inventory;

import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class MinecraftPlayerInventory implements PlayerInventory {

    @Override
    public @Nullable Component title() {
        return null;
    }

    @Override
    public @NotNull Type type() {
        return null;
    }
}
