package de.bauhd.minecraft.server.protocol.packet.play;

import de.bauhd.minecraft.server.api.entity.MinecraftPlayer;
import de.bauhd.minecraft.server.protocol.Protocol;
import de.bauhd.minecraft.server.protocol.packet.Packet;
import io.netty5.buffer.Buffer;

import static de.bauhd.minecraft.server.protocol.packet.PacketUtils.writeUUID;
import static de.bauhd.minecraft.server.protocol.packet.PacketUtils.writeVarInt;

public final class SpawnPlayer implements Packet {

    private final MinecraftPlayer player;

    public SpawnPlayer(final MinecraftPlayer player) {
        this.player = player;
    }

    @Override
    public void encode(Buffer buf, Protocol.Version version) {
        writeVarInt(buf, this.player.getId());
        writeUUID(buf, this.player.getUniqueId());

        final var position = this.player.getPosition();

        buf.writeDouble(position.x())
                .writeDouble(position.y())
                .writeDouble(position.z())
                .writeByte((byte) (position.yaw() * 256 / 360))
                .writeByte((byte) (position.pitch() * 256 / 360));
    }

    @Override
    public String toString() {
        return "SpawnPlayer{" +
                "player=" + this.player +
                '}';
    }
}
