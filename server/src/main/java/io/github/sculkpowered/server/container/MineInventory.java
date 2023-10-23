package io.github.sculkpowered.server.container;

import io.github.sculkpowered.server.container.item.ItemStack;
import io.github.sculkpowered.server.entity.player.SculkPlayer;
import io.github.sculkpowered.server.protocol.packet.play.Equipment;
import io.github.sculkpowered.server.protocol.packet.play.container.ContainerSlot;
import io.github.sculkpowered.server.util.ItemList;
import io.github.sculkpowered.server.util.OneInt2ObjectMap;
import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;

public final class MineInventory implements Inventory {

  public final ItemList items = new ItemList(this.getType().size());
  private final SculkPlayer player;

  public MineInventory(final SculkPlayer player) {
    this.player = player;
  }

  @Override
  public void setItem(int index, @NotNull ItemStack itemStack) {
    if (index < 9) {
      index += 36;
    } else if (index > 39) {
      index += 5;
    } else if (index > 35) {
      index = 8 - (index - 36);
    }
    this.items.set(index, itemStack);
    this.player.send(new ContainerSlot((byte) 0, 1, (short) index, itemStack));
  }

  @Override
  public @NotNull ItemStack getItem(int index) {
    if (index < 9) {
      index += 36;
    } else if (index > 39) {
      index += 5;
    } else if (index > 35) {
      index = 8 - (index - 36);
    }
    return this.items.get(index);
  }

  @Override
  public @NotNull ItemStack getItemInMainHand() {
    return this.getItem(this.player.getHeldItemSlot());
  }

  @Override
  public void setItemInMainHand(@NotNull ItemStack item) {
    this.setItem(this.player.getHeldItemSlot(), item);
  }

  @Override
  public @NotNull ItemStack getItemInOffHand() {
    return this.items.get(45);
  }

  @Override
  public void setItemInOffHand(@NotNull ItemStack item) {
    this.setItem(40, item);
  }

  @Override
  public void setHelmet(@NotNull ItemStack helmet) {
    this.items.set(5, helmet);
    this.player.sendViewersAndSelf(new Equipment(this.player.getId(), OneInt2ObjectMap.of(5, helmet)));
  }

  @Override
  public @NotNull ItemStack getHelmet() {
    return this.items.get(5);
  }

  @Override
  public void setChestplate(@NotNull ItemStack chestplate) {
    this.items.set(6, chestplate);
    this.player.sendViewersAndSelf(new Equipment(this.player.getId(), OneInt2ObjectMap.of(4, chestplate)));
  }

  @Override
  public @NotNull ItemStack getChestplate() {
    return this.items.get(6);
  }

  @Override
  public void setLeggings(@NotNull ItemStack leggings) {
    this.items.set(7, leggings);
    this.player.sendViewersAndSelf(new Equipment(this.player.getId(), OneInt2ObjectMap.of(3, leggings)));
  }

  @Override
  public @NotNull ItemStack getLeggings() {
    return this.items.get(7);
  }

  @Override
  public void setBoots(@NotNull ItemStack boots) {
    this.items.set(8, boots);
    this.player.sendViewersAndSelf(new Equipment(this.player.getId(), OneInt2ObjectMap.of(2, boots)));
  }

  @Override
  public @NotNull ItemStack getBoots() {
    return this.items.get(8);
  }

  @Override
  public @NotNull Component getTitle() {
    return Component.empty();
  }

  @Override
  public @NotNull Type getType() {
    return Type.PLAYER;
  }
}