package de.bauhd.minecraft.server.protocol.packet.play;

import de.bauhd.minecraft.server.protocol.Buffer;
import de.bauhd.minecraft.server.protocol.Connection;
import de.bauhd.minecraft.server.protocol.Protocol;
import de.bauhd.minecraft.server.protocol.packet.Packet;

public final class PlayerCommand implements Packet {

    private int entityId;
    private Action action;
    private int jumpBoost;

    @Override
    public void decode(Buffer buf, Protocol.Version version) {
        this.entityId = buf.readVarInt();
        this.action = Action.get(buf.readVarInt());
        this.jumpBoost = buf.readVarInt();
    }

    @Override
    public boolean handle(Connection connection) {
        final var player = connection.player();

        /*if (this.action == Action.START_SNEAKING) {
            final var packet = new EntityMetadata(connection.player().getId(), 6, 18, 5);
            for (final var otherPlayer : Worker.PLAYERS) {
                if (otherPlayer == player) continue;
                otherPlayer.send(packet);
            }
        } else if (this.action == Action.STOP_SNEAKING) {
            final var packet = new EntityMetadata(connection.player().getId(), 6, 18, 0);
            for (final var otherPlayer : Worker.PLAYERS) {
                if (otherPlayer == player) continue;
                otherPlayer.send(packet);
            }
        }*/
        return false;
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
