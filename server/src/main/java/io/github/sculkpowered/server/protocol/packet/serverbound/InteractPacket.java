package io.github.sculkpowered.server.protocol.packet.serverbound;

import io.github.sculkpowered.server.protocol.Buffer;
import io.github.sculkpowered.server.protocol.packet.PacketHandler;
import io.github.sculkpowered.server.protocol.packet.ServerboundPacket;

public final class InteractPacket implements ServerboundPacket {

  private int entityId;
  private int type;
  private float x;
  private float y;
  private float z;
  private int hand;
  private boolean sneaking;

  @Override
  public void decode(Buffer buf) {
    this.entityId = buf.readVarInt();
    this.type = buf.readVarInt();
    if (this.type == 2) {
      this.x = buf.readFloat();
      this.y = buf.readFloat();
      this.z = buf.readFloat();
      this.hand = buf.readVarInt();
    } else if (this.type == 0) {
      this.hand = buf.readVarInt();
    }
    this.sneaking = buf.readBoolean();
  }

  @Override
  public boolean handle(PacketHandler handler) {
    return handler.handle(this);
  }

  @Override
  public int minLength() {
    return 3;
  }

  @Override
  public int maxLength() {
    return 28;
  }

  public int entityId() {
    return this.entityId;
  }

  public int type() {
    return this.type;
  }

  public float x() {
    return this.x;
  }

  public float y() {
    return this.y;
  }

  public float z() {
    return this.z;
  }

  public int hand() {
    return this.hand;
  }

  public boolean sneaking() {
    return this.sneaking;
  }

  @Override
  public String toString() {
    return "InteractPacket{" +
        "entityId=" + this.entityId +
        ", type=" + this.type +
        ", x=" + this.x +
        ", y=" + this.y +
        ", z=" + this.z +
        ", hand=" + this.hand +
        ", sneaking=" + this.sneaking +
        '}';
  }
}
