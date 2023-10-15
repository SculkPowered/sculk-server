package de.bauhd.sculk.protocol.packet.play;

import de.bauhd.sculk.protocol.Buffer;
import de.bauhd.sculk.protocol.packet.Packet;
import de.bauhd.sculk.protocol.packet.PacketHandler;
import de.bauhd.sculk.world.Position;

public final class PlayerAction implements Packet {

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
    return "PlayerAction{" +
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
