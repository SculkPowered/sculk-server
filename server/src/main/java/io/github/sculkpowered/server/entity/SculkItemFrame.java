package io.github.sculkpowered.server.entity;

import io.github.sculkpowered.server.SculkServer;
import io.github.sculkpowered.server.container.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class SculkItemFrame extends AbstractEntity implements ItemFrame {

  public SculkItemFrame(final SculkServer server) {
    super(server);
  }

  @Override
  public @NotNull EntityType type() {
    return EntityType.ITEM_FRAME;
  }

  @Override
  public @NotNull ItemStack item() {
    return this.metadata.getItem(8, ItemStack.empty());
  }

  @Override
  public void item(@NotNull ItemStack item) {
    this.metadata.setItem(8, item);
  }

  @Override
  public int rotation() {
    return this.metadata.getVarInt(9, 0);
  }

  @Override
  public void rotation(int rotation) {
    this.metadata.setVarInt(9, rotation);
  }
}
