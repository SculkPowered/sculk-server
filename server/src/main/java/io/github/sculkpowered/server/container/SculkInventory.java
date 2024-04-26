package io.github.sculkpowered.server.container;

import io.github.sculkpowered.server.container.item.ItemStack;
import io.github.sculkpowered.server.entity.player.SculkPlayer;
import io.github.sculkpowered.server.protocol.packet.play.Equipment;
import io.github.sculkpowered.server.protocol.packet.play.container.ContainerContent;
import io.github.sculkpowered.server.protocol.packet.play.container.ContainerSlot;
import io.github.sculkpowered.server.util.ItemList;
import io.github.sculkpowered.server.util.OneInt2ObjectMap;
import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;

public final class SculkInventory implements Inventory {

  private final ItemList items = new ItemList(this.type().size());
  private final SculkPlayer player;

  public SculkInventory(final SculkPlayer player) {
    this.player = player;
  }

  @Override
  public void item(int index, @NotNull ItemStack itemStack) {
    if (index < 9) {
      index += 36;
    } else if (index > 39) {
      index += 5;
    } else if (index > 35) {
      index = 8 - (index - 36);
    }
    this.item0(index, itemStack, true);
  }

  public void item0(int index, @NotNull ItemStack itemStack, boolean slotPacket) {
    this.items.set(index, itemStack);
    if (index == 5) {
      this.player.sendViewersAndSelf(
          new Equipment(this.player.id(), OneInt2ObjectMap.of(5, itemStack)));
    } else if (index == 6) {
      this.player.sendViewersAndSelf(
          new Equipment(this.player.id(), OneInt2ObjectMap.of(4, itemStack)));
    } else if (index == 7) {
      this.player.sendViewersAndSelf(
          new Equipment(this.player.id(), OneInt2ObjectMap.of(3, itemStack)));
    } else if (index == 8) {
      this.player.sendViewersAndSelf(
          new Equipment(this.player.id(), OneInt2ObjectMap.of(2, itemStack)));
    } else if (index == 45) {
      this.player.sendViewersAndSelf(
          new Equipment(this.player.id(), OneInt2ObjectMap.of(1, itemStack)));
    } else if ((index - 36) == this.player.heldItemSlot()) {
      this.player.sendViewersAndSelf(
          new Equipment(this.player.id(), OneInt2ObjectMap.of(0, itemStack)));
    }
    if (slotPacket) {
      this.player.send(new ContainerSlot((byte) 0, 1, (short) index, itemStack));
    }
  }

  @Override
  public @NotNull ItemStack item(int index) {
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
  public @NotNull ItemStack itemInHand() {
    return this.item(this.player.heldItemSlot());
  }

  @Override
  public void itemInHand(@NotNull ItemStack item) {
    this.item(this.player.heldItemSlot(), item);
  }

  @Override
  public @NotNull ItemStack itemInOffHand() {
    return this.items.get(45);
  }

  @Override
  public void itemInOffHand(@NotNull ItemStack item) {
    this.item0(45, item, false);
  }

  @Override
  public void helmet(@NotNull ItemStack helmet) {
    this.items.set(5, helmet);
    this.player.sendViewersAndSelf(new Equipment(this.player.id(), OneInt2ObjectMap.of(5, helmet)));
  }

  @Override
  public @NotNull ItemStack helmet() {
    return this.items.get(5);
  }

  @Override
  public void chestplate(@NotNull ItemStack chestplate) {
    this.item0(6, chestplate, false);
  }

  @Override
  public @NotNull ItemStack chestplate() {
    return this.items.get(6);
  }

  @Override
  public void leggings(@NotNull ItemStack leggings) {
    this.item0(7, leggings, false);
  }

  @Override
  public @NotNull ItemStack leggings() {
    return this.items.get(7);
  }

  @Override
  public void boots(@NotNull ItemStack boots) {
    this.item0(8, boots, false);
  }

  @Override
  public @NotNull ItemStack boots() {
    return this.items.get(8);
  }

  @Override
  public void resend() {
    this.player.send(new ContainerContent((byte) 0, 1, this.items, ItemStack.empty()));
  }

  @Override
  public @NotNull Component title() {
    return Component.empty();
  }

  @Override
  public @NotNull Type type() {
    return Type.PLAYER;
  }

  @Override
  public @NotNull ItemList items() {
    return this.items;
  }
}
