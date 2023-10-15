package de.bauhd.sculk.protocol.packet.play;

import de.bauhd.sculk.protocol.Buffer;
import de.bauhd.sculk.protocol.packet.Packet;
import de.bauhd.sculk.protocol.packet.PacketHandler;

public final class PlayerCommand implements Packet {

  private int entityId;
  private Action action;
  private int jumpBoost;

  @Override
  public void decode(Buffer buf) {
    this.entityId = buf.readVarInt();
    this.action = Action.get(buf.readVarInt());
    this.jumpBoost = buf.readVarInt();
  }

  @Override
  public boolean handle(PacketHandler handler) {
    return handler.handle(this);
  }

  @Override
  public String toString() {
    return "PlayerCommand{" +
        "entityId=" + this.entityId +
        ", action=" + this.action +
        ", jumpBoost=" + this.jumpBoost +
        '}';
  }

  public int entityId() {
    return this.entityId;
  }

  public Action action() {
    return this.action;
  }

  public int jumpBoost() {
    return this.jumpBoost;
  }

  public enum Action {

    START_SNEAKING,
    STOP_SNEAKING,
    LEAVE_BED,
    START_SPRINTING,
    STOP_SPRINTING,
    START_JUMP_WITH_HORSE,
    STOP_JUMP_WITH_HORSE,
    OPEN_HORSE_INVENTORY,
    START_FLYING_WITH_ELYTRA;

    private static final Action[] ACTIONS = values();

    public static Action get(final int action) {
      return ACTIONS[action];
    }
  }
}
