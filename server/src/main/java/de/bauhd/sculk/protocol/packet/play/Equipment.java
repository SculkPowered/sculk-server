package de.bauhd.sculk.protocol.packet.play;

import de.bauhd.sculk.container.item.ItemStack;
import de.bauhd.sculk.protocol.Buffer;
import de.bauhd.sculk.protocol.packet.Packet;
import java.util.Map;

public final class Equipment implements Packet {

  private final int entityId;
  private final Map<Integer, ItemStack> equipment;

  public Equipment(final int entityId, final Map<Integer, ItemStack> equipment) {
    this.entityId = entityId;
    this.equipment = equipment;
  }

  @Override
  public void encode(Buffer buf) {
    buf.writeVarInt(this.entityId);
    var index = 0;
    for (final var entry : this.equipment.entrySet()) {
      var key = entry.getKey();
      if (index == this.equipment.size()) {
        key |= 0x80; // isn't last so set first bit
      }
      buf
          .writeVarInt(key)
          .writeItem(entry.getValue());
      index++;
    }
  }
}
