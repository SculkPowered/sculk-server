package de.bauhd.minecraft.server.protocol.packet.play.position;

import de.bauhd.minecraft.server.Worker;
import de.bauhd.minecraft.server.api.world.Position;
import de.bauhd.minecraft.server.protocol.Connection;
import de.bauhd.minecraft.server.protocol.Protocol;
import de.bauhd.minecraft.server.protocol.packet.Packet;
import io.netty5.buffer.Buffer;

public final class PlayerRotation implements Packet {

    private float yaw;
    private float pitch;
    private boolean onGround;

    @Override
    public void decode(Buffer buf, Protocol.Version version) {
        this.yaw = buf.readFloat();
        this.pitch = buf.readFloat();
        this.onGround = buf.readBoolean();
    }

    @Override
    public void handle(Connection connection) {
        final var player = connection.player();
        final var position = player.getPosition();
        final var updateRotation = new EntityRotation(player.getId(), this.yaw, this.pitch, this.onGround);
        final var headRotation = new HeadRotation(player.getId(), this.yaw);
        for (final var otherPlayer : Worker.PLAYERS) {
            if (otherPlayer == player) continue;
            otherPlayer.send(updateRotation);
            otherPlayer.send(headRotation);
        }
        player.setPosition(new Position(position.x(), position.y(), position.z(), this.yaw, this.pitch));
    }
}
