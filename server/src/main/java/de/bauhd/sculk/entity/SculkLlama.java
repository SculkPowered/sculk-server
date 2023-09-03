package de.bauhd.sculk.entity;

import org.jetbrains.annotations.NotNull;

public class SculkLlama extends AbstractAnimal implements Llama {

    @Override
    public @NotNull EntityType getType() {
        return EntityType.LLAMA;
    }
}
