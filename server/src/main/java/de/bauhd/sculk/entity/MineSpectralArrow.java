package de.bauhd.sculk.entity;

import org.jetbrains.annotations.NotNull;

public class MineSpectralArrow extends MineArrow implements SpectralArrow {

    @Override
    public @NotNull EntityType getType() {
        return EntityType.SPECTRAL_ARROW;
    }
}
