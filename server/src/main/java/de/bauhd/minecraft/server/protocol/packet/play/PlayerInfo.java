package de.bauhd.minecraft.server.protocol.packet.play;

import de.bauhd.minecraft.server.api.entity.player.PlayerInfoEntry;
import de.bauhd.minecraft.server.protocol.Buffer;
import de.bauhd.minecraft.server.protocol.Protocol;
import de.bauhd.minecraft.server.protocol.packet.Packet;

import java.util.List;

public final class PlayerInfo implements Packet {

    private final int action;
    private final List<? extends PlayerInfoEntry> entries;

    public PlayerInfo(final int action, final List<? extends PlayerInfoEntry> players) {
        this.action = action;
        this.entries = players;
    }

    @Override
    public void encode(Buffer buf, Protocol.Version version) {
        buf
                .writeVarInt(this.action)
                .writeVarInt(this.entries.size());
        for (final var entry : this.entries) {
            final var profile = entry.getProfile();
            buf.writeUniqueId(profile.uniqueId());

            if (this.action == 0) {
                buf
                        .writeString(profile.name())
                        .writeVarInt(profile.properties().size());
                for (final var property : profile.properties()) {
                    buf.writeString(property.key()).writeString(property.value());
                    if (property.signature() != null) {
                        buf.writeBoolean(true);
                        buf.writeString(property.signature());
                    } else {
                        buf.writeBoolean(false);
                    }
                }
                buf
                        .writeVarInt(entry.getGameMode().ordinal())
                        .writeVarInt(entry.getPing());
                if (entry.getDisplayName() != null) {
                    buf
                            .writeBoolean(true)
                            .writeComponent(entry.getDisplayName(), version);
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

    public static PlayerInfo add(final List<? extends PlayerInfoEntry> entries) {
        return new PlayerInfo(0, entries);
    }

    public static PlayerInfo add(final PlayerInfoEntry player) {
        return add(List.of(player));
    }

    public static PlayerInfo remove(final List<? extends PlayerInfoEntry> entries) {
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
