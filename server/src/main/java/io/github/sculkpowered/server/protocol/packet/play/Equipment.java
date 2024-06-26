package io.github.sculkpowered.server.protocol.packet.play;

import io.github.sculkpowered.server.container.equipment.EquipmentSlot;
import io.github.sculkpowered.server.container.item.ItemStack;
import io.github.sculkpowered.server.protocol.Buffer;
import io.github.sculkpowered.server.protocol.packet.Packet;
import java.util.Map;

public final class Equipment implements Packet {

  private final int entityId;
  private final Map<EquipmentSlot, ItemStack> equipment;

  public Equipment(final int entityId, final Map<EquipmentSlot, ItemStack> equipment) {
    this.entityId = entityId;
    this.equipment = equipment;
  }

  @Override
  public void encode(Buffer buf) {
    buf.writeVarInt(this.entityId);
    var index = 0;
    for (final var entry : this.equipment.entrySet()) {
      var key = (byte) entry.getKey().ordinal();
      if (index != this.equipment.size() - 1) {
        key |= (byte) 0x80; // isn't last so set first bit
      }
      buf
          .writeByte(key)
          .writeItem(entry.getValue());
      index++;
    }
  }
}
