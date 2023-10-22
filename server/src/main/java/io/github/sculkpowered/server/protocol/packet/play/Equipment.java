package io.github.sculkpowered.server.protocol.packet.play;

import io.github.sculkpowered.server.container.item.ItemStack;
import io.github.sculkpowered.server.protocol.Buffer;
import io.github.sculkpowered.server.protocol.packet.Packet;
import io.github.sculkpowered.server.util.OneInt2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;

public final class Equipment implements Packet {

  private final int entityId;
  private final Int2ObjectMap<ItemStack> equipment;

  public Equipment(final int entityId, final Int2ObjectMap<ItemStack> equipment) {
    this.entityId = entityId;
    this.equipment = equipment;
  }

  @Override
  public void encode(Buffer buf) {
    buf.writeVarInt(this.entityId);
    if (this.equipment instanceof OneInt2ObjectMap<ItemStack> map) {
      buf.writeByte((byte) map.getKey()).writeItem(map.getValue());
    } else {
      var index = 0;
      for (final var entry : this.equipment.int2ObjectEntrySet()) {
        var key = (byte) entry.getIntKey();
        if (index != this.equipment.size()) {
          key |= (byte) 0x80; // isn't last so set first bit
        }
        buf
            .writeByte(key)
            .writeItem(entry.getValue());
        index++;
      }
    }
  }
}
