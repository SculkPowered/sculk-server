package io.github.sculkpowered.server.container;

import io.github.sculkpowered.server.container.item.ItemStack;
import io.github.sculkpowered.server.entity.player.SculkPlayer;
import io.github.sculkpowered.server.protocol.packet.play.Equipment;
import io.github.sculkpowered.server.protocol.packet.play.container.ContainerContent;
import io.github.sculkpowered.server.protocol.packet.play.container.ContainerSlot;
import io.github.sculkpowered.server.util.OneInt2ObjectMap;
import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;

public final class SculkInventory extends AbstractContainer implements Inventory {

  private final SculkPlayer player;

  public SculkInventory(final SculkPlayer player) {
    super(Type.PLAYER);
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
    super.item(index, itemStack);
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
      this.player.send(new ContainerSlot((byte) 0, this.incrementState(), (short) index, itemStack));
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
    return super.item(index);
  }

  @Override
  public @NotNull ItemStack itemInMainHand() {
    return this.item(this.player.heldItemSlot());
  }

  @Override
  public void itemInMainHand(@NotNull ItemStack item) {
    this.item(this.player.heldItemSlot(), item);
  }

  @Override
  public @NotNull ItemStack itemInOffHand() {
    return super.item(45);
  }

  @Override
  public void itemInOffHand(@NotNull ItemStack item) {
    this.item0(45, item, false);
  }

  @Override
  public void helmet(@NotNull ItemStack helmet) {
    this.item0(5, helmet, false);
  }

  @Override
  public @NotNull ItemStack helmet() {
    return super.item(5);
  }

  @Override
  public void chestplate(@NotNull ItemStack chestplate) {
    this.item0(6, chestplate, false);
  }

  @Override
  public @NotNull ItemStack chestplate() {
    return super.item(6);
  }

  @Override
  public void leggings(@NotNull ItemStack leggings) {
    this.item0(7, leggings, false);
  }

  @Override
  public @NotNull ItemStack leggings() {
    return super.item(7);
  }

  @Override
  public void boots(@NotNull ItemStack boots) {
    this.item0(8, boots, false);
  }

  @Override
  public @NotNull ItemStack boots() {
    return super.item(8);
  }

  @Override
  public void resend() {
    this.player.send(new ContainerContent((byte) 0, this.state(),
        this.items(), ItemStack.empty()));
  }

  @Override
  public @NotNull Component title() {
    return Component.empty();
  }
}
