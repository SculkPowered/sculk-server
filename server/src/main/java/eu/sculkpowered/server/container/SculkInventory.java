package eu.sculkpowered.server.container;

import eu.sculkpowered.server.container.equipment.EquipmentSlot;
import eu.sculkpowered.server.container.item.ItemStack;
import eu.sculkpowered.server.container.item.data.DataComponent;
import eu.sculkpowered.server.entity.player.SculkPlayer;
import eu.sculkpowered.server.protocol.packet.clientbound.SetEquipmentPacket;
import eu.sculkpowered.server.protocol.packet.clientbound.ContainerSetContentPacket;
import eu.sculkpowered.server.protocol.packet.clientbound.ContainerSetSlotPacket;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;

public final class SculkInventory extends AbstractContainer implements Inventory {

  private final SculkPlayer player;

  public SculkInventory(final SculkPlayer player) {
    super(Type.PLAYER);
    this.player = player;
  }

  @Override
  public @NotNull ItemStack item(int index, @NotNull ItemStack itemStack) {
    if (index < 9) {
      index += 36;
    } else if (index > 39) {
      index += 5;
    } else if (index > 35) {
      index = 8 - (index - 36);
    }
    return this.item0(index, itemStack, true);
  }

  public ItemStack item0(final int index, final @NotNull ItemStack itemStack,
      final boolean slotPacket) {
    if (index == 5) {
      return this.set(EquipmentSlot.HEAD, itemStack);
    } else if (index == 6) {
      return this.set(EquipmentSlot.CHEST, itemStack);
    } else if (index == 7) {
      return this.set(EquipmentSlot.LEGS, itemStack);
    } else if (index == 8) {
      return this.set(EquipmentSlot.FEET, itemStack);
    } else if (index == 45) {
      return this.set(EquipmentSlot.OFF_HAND, itemStack);
    } else if ((index - 36) == this.player.heldItemSlot()) {
      return this.set(EquipmentSlot.MAIN_HAND, itemStack);
    }
    if (slotPacket) {
      this.player.send(
          new ContainerSetSlotPacket((byte) 0, this.incrementState(), (short) index, itemStack));
    }
    return super.item(index, itemStack);
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
  public @NotNull ItemStack get(@NotNull EquipmentSlot slot) {
    return switch (slot) {
      case MAIN_HAND -> super.item(36 + this.player.heldItemSlot());
      case OFF_HAND -> super.item(45);
      case FEET -> super.item(8);
      case LEGS -> super.item(7);
      case CHEST -> super.item(6);
      case HEAD -> super.item(5);
      case BODY -> throw new IllegalArgumentException();
    };
  }

  @Override
  public @NotNull ItemStack set(@NotNull EquipmentSlot slot, @NotNull ItemStack equipment) {
    // TODO: fix scroll bug
    this.player.sendViewersAndSelf(new SetEquipmentPacket(this.player.id(), Map.of(slot, equipment)));
    var index = switch (slot) {
      case MAIN_HAND -> 36 + this.player.heldItemSlot();
      case OFF_HAND -> 45;
      case FEET -> 8;
      case LEGS -> 7;
      case CHEST -> 6;
      case HEAD -> 5;
      case BODY -> throw new IllegalArgumentException();
    };
    // remove previous attributes
    final var previous = super.item(index, equipment);
    var attributes = Objects.requireNonNull(previous
        .get(DataComponent.ATTRIBUTE_MODIFIERS)).attributes();
    for (final var attribute : attributes) {
      if (attribute.slot().is(slot)) {
        this.player.attribute(attribute.attribute()).removeModifier(attribute.modifier());
      }
    }
    // add new attributes
    attributes = Objects.requireNonNull(equipment
        .get(DataComponent.ATTRIBUTE_MODIFIERS)).attributes();
    for (final var attribute : attributes) {
      if (attribute.slot().is(slot)) {
        this.player.attribute(attribute.attribute()).addModifier(attribute.modifier());
      }
    }
    return previous;
  }

  @Override
  public Map<EquipmentSlot, ItemStack> asMap() {
    final var equipment = new HashMap<EquipmentSlot, ItemStack>();
    if (!this.itemInMainHand().isEmpty()) {
      equipment.put(EquipmentSlot.MAIN_HAND, this.itemInMainHand());
    }
    if (!this.itemInOffHand().isEmpty()) {
      equipment.put(EquipmentSlot.OFF_HAND, this.itemInOffHand());
    }
    if (!this.boots().isEmpty()) {
      equipment.put(EquipmentSlot.FEET, this.boots());
    }
    if (!this.leggings().isEmpty()) {
      equipment.put(EquipmentSlot.LEGS, this.leggings());
    }
    if (!this.chestplate().isEmpty()) {
      equipment.put(EquipmentSlot.CHEST, this.chestplate());
    }
    if (!this.helmet().isEmpty()) {
      equipment.put(EquipmentSlot.HEAD, this.helmet());
    }
    return equipment;
  }

  @Override
  public void resend() {
    this.player.send(new ContainerSetContentPacket((byte) 0, this.state(), this.items(), ItemStack.empty()));
  }

  @Override
  public @NotNull Component title() {
    return Component.empty();
  }

  @Override
  public String toString() {
    return "SculkInventory{" +
        "player=" + this.player +
        '}';
  }
}
