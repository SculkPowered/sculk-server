package de.bauhd.sculk.entity;

import org.jetbrains.annotations.NotNull;

public final class MineIllusioner extends AbstractMob implements Illusioner {

    @Override
    public @NotNull EntityType getType() {
        return EntityType.ILLUSIONER;
    }
}
