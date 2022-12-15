package de.bauhd.minecraft.server.protocol.packet.play;

import de.bauhd.minecraft.server.api.entity.player.PlayerInfoEntry;
import de.bauhd.minecraft.server.protocol.Buffer;
import de.bauhd.minecraft.server.protocol.Protocol;
import de.bauhd.minecraft.server.protocol.packet.Packet;
import org.apache.logging.log4j.util.TriConsumer;

import java.util.Arrays;
import java.util.BitSet;
import java.util.EnumSet;
import java.util.List;

public final class PlayerInfo implements Packet {

    private int action = -1;
    private EnumSet<Action> actions;
    private final List<? extends PlayerInfoEntry> entries;

    public PlayerInfo(final int action, final List<? extends PlayerInfoEntry> entries) {
        this.action = action;
        this.entries = entries;
    }

    public PlayerInfo(final EnumSet<Action> actions, final List<? extends PlayerInfoEntry> entries) {
        this.actions = actions;
        this.entries = entries;
    }

    @Override
    public void encode(Buffer buf, Protocol.Version version) {
        if (version.newerOr(Protocol.Version.MINECRAFT_1_19_3)) {
            final var actions = Action.class.getEnumConstants();
            final var bitSet = new BitSet(actions.length);
            for (int idx = 0; idx < actions.length; idx++) {
                bitSet.set(idx, this.actions.contains(actions[idx]));
            }

            byte[] bytes = bitSet.toByteArray();
            buf.writeBytes(Arrays.copyOf(bytes, -Math.floorDiv(-actions.length, 8)));

            buf.writeVarInt(this.entries.size());
            for (final var entry : entries) {
                buf.writeUniqueId(entry.getProfile().uniqueId());
                for (final var action : this.actions) {
                    action.writer.accept(buf, entry, version);
                }
            }
        } else {
            buf.writeVarInt(this.action);
            buf.writeVarInt(this.entries.size());

            for (final var entry : this.entries) {
                buf.writeUniqueId(entry.getProfile().uniqueId());

                switch (this.action) {
                    case 0 -> {
                        Action.ADD_PLAYER.writer.accept(buf, entry, version);
                        Action.UPDATE_GAME_MODE.writer.accept(buf, entry, version);
                        Action.UPDATE_LATENCY.writer.accept(buf, entry, version);
                        Action.UPDATE_DISPLAY_NAME.writer.accept(buf, entry, version);
                        buf.writeBoolean(false);
                    }
                    case 1 -> Action.UPDATE_GAME_MODE.writer.accept(buf, entry, version);
                    case 2 -> Action.UPDATE_LATENCY.writer.accept(buf, entry, version);
                    case 3 -> Action.UPDATE_DISPLAY_NAME.writer.accept(buf, entry, version);
                }
            }
        }
    }

    public static PlayerInfo add(final List<? extends PlayerInfoEntry> entries, final Protocol.Version version) {
        if (version.newerOr(Protocol.Version.MINECRAFT_1_19_3)) {
            return new PlayerInfo(EnumSet.of(Action.ADD_PLAYER, Action.UPDATE_GAME_MODE, Action.UPDATE_LISTED, Action.UPDATE_DISPLAY_NAME), entries);
        } else {
            return new PlayerInfo(0, entries);
        }
    }

    public static PlayerInfo add(final PlayerInfoEntry entry, final Protocol.Version version) {
        return add(List.of(entry), version);
    }

    public static PlayerInfo remove(final List<? extends PlayerInfoEntry> entries) {
        return new PlayerInfo(4, entries);
    }

    public static PlayerInfo remove(final PlayerInfoEntry entry) {
        return remove(List.of(entry));
    }

    public enum Action {
        ADD_PLAYER((buf, entry, version) -> {
            final var profile = entry.getProfile();
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
        }),
        INITIALIZE_CHAT((buf, entry, version) -> {}),
        UPDATE_GAME_MODE((buf, entry, version) -> buf.writeVarInt(entry.getGameMode().ordinal())),
        UPDATE_LISTED((buf, entry, version) -> buf.writeBoolean(true)),
        UPDATE_LATENCY((buf, entry, version) -> buf.writeVarInt(entry.getPing())),
        UPDATE_DISPLAY_NAME((buf, entry, version) -> {
            if (entry.getDisplayName() != null) {
                buf
                        .writeBoolean(true)
                        .writeComponent(entry.getDisplayName(), version);
            } else {
                buf.writeBoolean(false);
            }
        });

        private final TriConsumer<Buffer, PlayerInfoEntry, Protocol.Version> writer;

        Action(final TriConsumer<Buffer, PlayerInfoEntry, Protocol.Version> writer) {
            this.writer = writer;
        }
    }

    @Override
    public String toString() {
        return "PlayerInfo{" +
                (this.action == -1 ? "actions=" + this.actions : "action=" + this.action) +
                ", entries=" + this.entries +
                '}';
    }
}
