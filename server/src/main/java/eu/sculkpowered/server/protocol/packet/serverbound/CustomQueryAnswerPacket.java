package eu.sculkpowered.server.protocol.packet.serverbound;

import static eu.sculkpowered.server.util.Constants.EMPTY_BYTE_ARRAY;

import eu.sculkpowered.server.protocol.Buffer;
import eu.sculkpowered.server.protocol.packet.PacketHandler;
import eu.sculkpowered.server.protocol.packet.ServerboundPacket;

public final class CustomQueryAnswerPacket implements ServerboundPacket {

  private int messageId;
  private byte[] data = EMPTY_BYTE_ARRAY;

  @Override
  public void decode(Buffer buf) {
    this.messageId = buf.readVarInt();
    if (buf.readBoolean()) {
      this.data = buf.readByteArray();
    }
  }

  @Override
  public boolean handle(PacketHandler handler) {
    return handler.handle(this);
  }

  @Override
  public String toString() {
    return "CustomQueryAnswerPacket{" +
        "messageId=" + this.messageId +
        ", data=byte[" + this.data.length + "]" +
        '}';
  }
}
