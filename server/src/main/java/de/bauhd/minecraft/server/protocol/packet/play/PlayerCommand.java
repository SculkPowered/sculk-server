package de.bauhd.minecraft.server.protocol.packet.play;

import de.bauhd.minecraft.server.protocol.Protocol;
import de.bauhd.minecraft.server.protocol.packet.Packet;
import io.netty5.buffer.Buffer;

import static de.bauhd.minecraft.server.protocol.packet.PacketUtils.readVarInt;

public final class PlayerCommand implements Packet {

    private int entityId;
    private Action action;
    private int jumpBoost;

    @Override
    public void decode(Buffer buf, Protocol.Version version) {

    }

    @Override
    public void encode(Buffer buf, Protocol.Version version) {
        this.entityId = readVarInt(buf);
        this.action = Action.get(readVarInt(buf));
        if (this.action == Action.START_JUMP_WITH_HORSE) {
            this.jumpBoost = readVarInt(buf);
        }
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

    @Override
    public String toString() {
        return "PlayerCommand{" +
                "entityId=" + this.entityId +
                ", action=" + this.action +
                ", jumpBoost=" + this.jumpBoost +
                '}';
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
