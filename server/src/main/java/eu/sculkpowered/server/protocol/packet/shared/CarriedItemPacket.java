package eu.sculkpowered.server.protocol.packet.shared;

import eu.sculkpowered.server.protocol.Buffer;
import eu.sculkpowered.server.protocol.packet.ClientboundPacket;
import eu.sculkpowered.server.protocol.packet.PacketHandler;
import eu.sculkpowered.server.protocol.packet.ServerboundPacket;

// yes, decode -> short and encode -> byte, is right
public final class CarriedItemPacket implements ServerboundPacket, ClientboundPacket {

  private byte slot;

  public CarriedItemPacket(final byte slot) {
    this.slot = slot;
  }

  public CarriedItemPacket() {}

  @Override
  public void decode(Buffer buf) {
    this.slot = (byte) buf.readShort();
  }

  @Override
  public void encode(Buffer buf) {
    buf.writeByte(this.slot);
  }

  @Override
  public boolean handle(PacketHandler handler) {
    return handler.handle(this);
  }

  @Override
  public int minLength() {
    return 1;
  }

  @Override
  public int maxLength() {
    return Short.BYTES;
  }

  @Override
  public String toString() {
    return "CarriedItemPacket{" +
        "slot=" + this.slot +
        '}';
  }

  public byte slot() {
    return this.slot;
  }
}
