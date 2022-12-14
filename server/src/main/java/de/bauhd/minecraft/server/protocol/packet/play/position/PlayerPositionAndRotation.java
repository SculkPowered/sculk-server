package de.bauhd.minecraft.server.protocol.packet.play.position;

import de.bauhd.minecraft.server.api.world.Position;
import de.bauhd.minecraft.server.protocol.Buffer;
import de.bauhd.minecraft.server.protocol.Connection;
import de.bauhd.minecraft.server.protocol.Protocol;
import de.bauhd.minecraft.server.protocol.packet.Packet;

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
    public boolean handle(Connection connection) {
        final var player = connection.player();
        final var position = player.getPosition();
        player.sendViewers(new EntityPositionAndRotation(player.getId(),
                this.delta(position.x(), this.x), this.delta(position.y(), this.y), this.delta(position.z(), this.z),
                this.yaw, this.pitch, this.onGround),
                new HeadRotation(player.getId(), this.yaw));
        player.setPosition(new Position(this.x, this.y, this.z, this.yaw, this.pitch));
        return false;
    }

    @Override
    public int minLength() {
        return 33;
    }

    @Override
    public int maxLength() {
        return this.minLength();
    }

    private short delta(final double previous, final double current) {
        return (short) ((current * 32 - previous * 32) * 128);
    }
}
