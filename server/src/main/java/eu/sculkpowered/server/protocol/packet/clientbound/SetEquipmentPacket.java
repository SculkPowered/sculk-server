package eu.sculkpowered.server.protocol.packet.clientbound;

import eu.sculkpowered.server.container.equipment.EquipmentSlot;
import eu.sculkpowered.server.container.item.ItemStack;
import eu.sculkpowered.server.protocol.Buffer;
import eu.sculkpowered.server.protocol.packet.ClientboundPacket;
import java.util.Map;

public final class SetEquipmentPacket implements ClientboundPacket {

  private final int entityId;
  private final Map<EquipmentSlot, ItemStack> equipment;

  public SetEquipmentPacket(final int entityId, final Map<EquipmentSlot, ItemStack> equipment) {
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

  @Override
  public String toString() {
    return "SetEquipmentPacket{" +
        "entityId=" + this.entityId +
        ", equipment=" + this.equipment +
        '}';
  }
}
