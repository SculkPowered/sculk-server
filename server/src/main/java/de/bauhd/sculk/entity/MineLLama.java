package de.bauhd.sculk.entity;

import org.jetbrains.annotations.NotNull;

public class MineLLama extends AbstractAnimal implements Llama {

    @Override
    public @NotNull EntityType getType() {
        return EntityType.LLAMA;
    }
}
