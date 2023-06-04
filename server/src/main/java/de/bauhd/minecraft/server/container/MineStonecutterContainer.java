package de.bauhd.minecraft.server.container;

import de.bauhd.minecraft.server.entity.player.MinecraftPlayer;
import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;

public final class MineStonecutterContainer extends MineContainer implements StonecutterContainer {

    private int selectedRecipe;

    public MineStonecutterContainer(final Component title) {
        super(title);
    }

    @Override
    public void setSelectedRecipe(int recipe) {
        this.sendProperty(0, this.selectedRecipe);
        this.selectedRecipe = recipe;
    }

    @Override
    public int getSelectedRecipe() {
        return this.selectedRecipe;
    }

    @Override
    public @NotNull Type type() {
        return Type.STONECUTTER;
    }

    @Override
    public void sendProperties(MinecraftPlayer player) {
        player.send(this.property(0, this.selectedRecipe));
    }
}
