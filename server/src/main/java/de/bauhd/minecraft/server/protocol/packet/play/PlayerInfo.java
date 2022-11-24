package de.bauhd.minecraft.server.protocol.packet.play;

import de.bauhd.minecraft.server.AdvancedMinecraftServer;
import de.bauhd.minecraft.server.api.entity.player.PlayerInfoEntry;
import de.bauhd.minecraft.server.protocol.Protocol;
import de.bauhd.minecraft.server.protocol.packet.Packet;
import io.netty5.buffer.Buffer;

import java.util.List;

import static de.bauhd.minecraft.server.protocol.packet.PacketUtils.*;

public final class PlayerInfo implements Packet {

    private final int action;
    private final List<PlayerInfoEntry> entries;

    public PlayerInfo(final int action, final List<PlayerInfoEntry> players) {
        this.action = action;
        this.entries = players;
    }

    @Override
    public void encode(Buffer buf, Protocol.Version version) {
        writeVarInt(buf, this.action);
        writeVarInt(buf, this.entries.size());
        for (final var entry : this.entries) {
            final var profile = entry.getProfile();
            writeUUID(buf, profile.uniqueId());

            if (this.action == 0) {
                writeString(buf, profile.name());
                writeVarInt(buf, profile.properties().size());
                for (final var property : profile.properties()) {
                    writeString(buf, property.key());
                    writeString(buf, property.value());
                    if (property.signature() != null) {
                        buf.writeBoolean(true);
                        writeString(buf, property.signature());
                    } else {
                        buf.writeBoolean(false);
                    }
                }
                writeVarInt(buf, entry.getGameMode().ordinal());
                writeVarInt(buf, entry.getPing());
                if (entry.getDisplayName() != null) {
                    buf.writeBoolean(true);
                    writeString(buf, AdvancedMinecraftServer.getGsonSerializer(version).serialize(entry.getDisplayName()));
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

    public static PlayerInfo add(final List<PlayerInfoEntry> entries) {
        return new PlayerInfo(0, entries);
    }

    public static PlayerInfo add(final PlayerInfoEntry player) {
        return add(List.of(player));
    }

    public static PlayerInfo remove(final List<PlayerInfoEntry> entries) {
        return new PlayerInfo(4, entries);
    }

    public static PlayerInfo remove(final PlayerInfoEntry entry) {
        return add(List.of(entry));
    }

    @Override
    public String toString() {
        return "PlayerInfo{" +
                "action=" + this.action +
                ", entries=" + this.entries +
                '}';
    }
}
