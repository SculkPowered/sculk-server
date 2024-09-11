package eu.sculkpowered.server.protocol.packet.serverbound;

import eu.sculkpowered.server.protocol.Buffer;
import eu.sculkpowered.server.protocol.packet.PacketHandler;
import eu.sculkpowered.server.protocol.packet.ServerboundPacket;
import eu.sculkpowered.server.world.Position;

public final class PlayerActionPacket implements ServerboundPacket {

  private int status;
  private Position position;
  private byte face;
  private int sequence;

  @Override
  public void decode(Buffer buf) {
    this.status = buf.readVarInt();
    this.position = buf.readPosition();
    this.face = buf.readByte();
    this.sequence = buf.readVarInt();
  }

  @Override
  public boolean handle(PacketHandler handler) {
    return handler.handle(this);
  }

  @Override
  public String toString() {
    return "PlayerActionPacket{" +
        "status=" + this.status +
        ", position=" + this.position +
        ", face=" + this.face +
        ", sequence=" + this.sequence +
        '}';
  }

  public int status() {
    return this.status;
  }

  public Position position() {
    return this.position;
  }

  public byte face() {
    return this.face;
  }

  public int sequence() {
    return this.sequence;
  }
}
