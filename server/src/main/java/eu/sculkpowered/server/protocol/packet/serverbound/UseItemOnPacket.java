package eu.sculkpowered.server.protocol.packet.serverbound;

import eu.sculkpowered.server.protocol.Buffer;
import eu.sculkpowered.server.protocol.packet.PacketHandler;
import eu.sculkpowered.server.protocol.packet.ServerboundPacket;
import eu.sculkpowered.server.world.Position;
import eu.sculkpowered.server.world.block.Block;

public final class UseItemOnPacket implements ServerboundPacket {

  private int hand;
  private Position position;
  private Block.Facing face;
  private float x;
  private float y;
  private float z;
  private boolean insideBlock;
  private int sequence;

  @Override
  public void decode(Buffer buf) {
    this.hand = buf.readVarInt();
    this.position = buf.readPosition();
    this.face = Block.Facing.get(buf.readVarInt());
    this.x = buf.readFloat();
    this.y = buf.readFloat();
    this.z = buf.readFloat();
    this.insideBlock = buf.readBoolean();
    this.sequence = buf.readVarInt();
  }

  @Override
  public boolean handle(PacketHandler handler) {
    return handler.handle(this);
  }

  @Override
  public String toString() {
    return "UseItemOnPacket{" +
        "hand=" + this.hand +
        ", position=" + this.position +
        ", face=" + this.face +
        ", x=" + this.x +
        ", y=" + this.y +
        ", z=" + this.z +
        ", insideBlock=" + this.insideBlock +
        ", sequence=" + this.sequence +
        '}';
  }

  public int hand() {
    return this.hand;
  }

  public Position position() {
    return this.position;
  }

  public Block.Facing face() {
    return this.face;
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

  public boolean insideBlock() {
    return this.insideBlock;
  }

  public int sequence() {
    return this.sequence;
  }
}
