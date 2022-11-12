package de.bauhd.minecraft.server.protocol.packet.play.position;

import de.bauhd.minecraft.server.Worker;
import de.bauhd.minecraft.server.api.world.Position;
import de.bauhd.minecraft.server.protocol.Connection;
import de.bauhd.minecraft.server.protocol.Protocol;
import de.bauhd.minecraft.server.protocol.packet.Packet;
import io.netty5.buffer.Buffer;

public final class PlayerPositionAndRotation implements Packet {

    private double x;
    private double y;
    private double z;
    private float yaw;
    private float pitch;
    private boolean onGround;

    @Override
    public void decode(Buffer buf, Protocol.Version version) {
        this.x = buf.readDouble();
        this.y = buf.readDouble();
        this.z = buf.readDouble();
        this.yaw = buf.readFloat();
        this.pitch = buf.readFloat();
        this.onGround = buf.readBoolean();
    }

    @Override
    public void handle(Connection connection) {
        final var player = connection.player();
        final var position = player.getPosition();
            final var updatePositionAndRotation = new EntityPositionAndRotation(player.getId(),
                    this.delta(position.x(), this.x), this.delta(position.y(), this.y), this.delta(position.z(), this.z),
                    this.yaw, this.pitch, this.onGround);
            final var headRotation = new HeadRotation(player.getId(), this.yaw);
            for (final var otherPlayer : Worker.PLAYERS) {
                if (otherPlayer == player) continue;
                otherPlayer.send(updatePositionAndRotation);
                otherPlayer.send(headRotation);
            }
        player.setPosition(new Position(this.x, this.y, this.z, this.yaw, this.pitch));
    }

    private short delta(final double previous, final double current) {
        return (short) ((current * 32 - previous * 32) * 128);
    }
}
