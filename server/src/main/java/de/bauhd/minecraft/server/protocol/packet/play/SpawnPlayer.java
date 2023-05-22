package de.bauhd.minecraft.server.protocol.packet.play;

import de.bauhd.minecraft.server.entity.player.MinecraftPlayer;
import de.bauhd.minecraft.server.protocol.Buffer;
import de.bauhd.minecraft.server.protocol.packet.Packet;

public final class SpawnPlayer implements Packet {

    private final MinecraftPlayer player;

    public SpawnPlayer(final MinecraftPlayer player) {
        this.player = player;
    }

    @Override
    public void encode(Buffer buf) {
        buf
                .writeVarInt(this.player.getId())
                .writeUniqueId(this.player.getUniqueId());
        final var position = this.player.getPosition();
        buf
                .writeDouble(position.x())
                .writeDouble(position.y())
                .writeDouble(position.z())
                .writeAngel(position.yaw())
                .writeAngel(position.pitch());
    }

    @Override
    public String toString() {
        return "SpawnPlayer{" +
                "player=" + this.player +
                '}';
    }
}
