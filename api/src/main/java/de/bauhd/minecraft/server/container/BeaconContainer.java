package de.bauhd.minecraft.server.container;

import de.bauhd.minecraft.server.potion.PotionEffect;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Range;

/**
 * Represents a container of a beacon block.
 */
public interface BeaconContainer extends Container {

    void setPowerLevel(@Range(from = 0, to = 4) int powerLevel);

    int getPowerLevel();

    void setFirstPotionEffect(@NotNull PotionEffect potionEffect);

    @NotNull PotionEffect getFirstPotionEffect();

    void setSecondPotionEffect(@NotNull PotionEffect  potionEffect);

    @NotNull PotionEffect getSecondPotionEffect();
}
