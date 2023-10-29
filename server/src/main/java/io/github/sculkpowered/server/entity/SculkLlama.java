package io.github.sculkpowered.server.entity;

import org.jetbrains.annotations.NotNull;

public class SculkLlama extends AbstractAnimal implements Llama {

  @Override
  public @NotNull EntityType type() {
    return EntityType.LLAMA;
  }
}
