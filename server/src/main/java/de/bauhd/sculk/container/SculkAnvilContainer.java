package de.bauhd.sculk.container;

import de.bauhd.sculk.entity.player.SculkPlayer;
import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;

public final class SculkAnvilContainer extends SculkContainer implements AnvilContainer {

    private int repairCost;

    public SculkAnvilContainer(final Component title) {
        super(title);
    }

    @Override
    public void setRepairCost(int repairCost) {
        this.sendProperty(0, repairCost);
        this.repairCost = repairCost;
    }

    @Override
    public int getRepairCost() {
        return this.repairCost;
    }

    @Override
    public @NotNull Type getType() {
        return Type.ANVIL;
    }

    @Override
    public void sendProperties(SculkPlayer player) {
        player.send(this.property(0, this.repairCost));
    }
}
