package de.bauhd.minecraft.server.protocol.packet.play;

import de.bauhd.minecraft.server.DefaultMinecraftServer;
import de.bauhd.minecraft.server.api.entity.MinecraftPlayer;
import de.bauhd.minecraft.server.protocol.Protocol;
import de.bauhd.minecraft.server.protocol.packet.Packet;
import io.netty5.buffer.Buffer;

import java.util.List;

import static de.bauhd.minecraft.server.protocol.packet.PacketUtils.*;

public final class PlayerInfo implements Packet {

    private final int action;
    private final List<MinecraftPlayer> players;

    public PlayerInfo(final int action, final List<MinecraftPlayer> players) {
        this.action = action;
        this.players = players;
    }

    @Override
    public void encode(Buffer buf, Protocol.Version version) {
        writeVarInt(buf, this.action);
        writeVarInt(buf, this.players.size());
        for (final var player : this.players) {
            writeUUID(buf, player.getUniqueId());

            if (this.action == 0) {
                writeString(buf, player.getUsername());
                writeVarInt(buf, player.getProfile().properties().size());
                for (final var property : player.getProfile().properties()) {
                    writeString(buf, property.key());
                    writeString(buf, property.value());
                    if (property.signature() != null) {
                        buf.writeBoolean(true);
                        writeString(buf, property.signature());
                    } else {
                        buf.writeBoolean(false);
                    }
                }
                // TODO add game mode and ping
                writeVarInt(buf, 0);
                writeVarInt(buf, 1);
                if (player.getDisplayName() != null) {
                    buf.writeBoolean(true);
                    writeString(buf, DefaultMinecraftServer.getGsonSerializer(version).serialize(player.getDisplayName()));
                } else {
                    buf.writeBoolean(false);
                }
                buf.writeBoolean(false);
            } else if (this.action == 1) {

            } else if (this.action == 2) {

            } else if (this.action == 3) {

            }

        }
    }

    public static PlayerInfo add(List<MinecraftPlayer> players) {
        return new PlayerInfo(0, players);
    }

    public static PlayerInfo add(MinecraftPlayer player) {
        return add(List.of(player));
    }

    public static PlayerInfo remove(List<MinecraftPlayer> players) {
        return new PlayerInfo(4, players);
    }

    public static PlayerInfo remove(MinecraftPlayer player) {
        return add(List.of(player));
    }

    @Override
    public String toString() {
        return "PlayerInfo{" +
                "action=" + this.action +
                ", players=" + this.players +
                '}';
    }
}
