package de.bauhd.minecraft.server.protocol.packet.play.position;

import de.bauhd.minecraft.server.api.world.Position;
import de.bauhd.minecraft.server.protocol.Buffer;
import de.bauhd.minecraft.server.protocol.Connection;
import de.bauhd.minecraft.server.protocol.Protocol;
import de.bauhd.minecraft.server.protocol.packet.Packet;

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
    public boolean handle(Connection connection) {
        final var player = connection.player();
        final var position = player.getPosition();
        player.sendViewers(
                new EntityRotation(player.getId(), this.yaw, this.pitch, this.onGround),
                new HeadRotation(player.getId(), this.yaw)
        );
        player.setPosition(new Position(position.x(), position.y(), position.z(), this.yaw, this.pitch));
        return false;
    }

    @Override
    public int minLength() {
        return 9;
    }

    @Override
    public int maxLength() {
        return this.minLength();
    }
}
