package de.bauhd.minecraft.server.protocol.packet.play;

import de.bauhd.minecraft.server.entity.player.PlayerInfoEntry;
import de.bauhd.minecraft.server.protocol.Buffer;
import de.bauhd.minecraft.server.protocol.Protocol;
import de.bauhd.minecraft.server.protocol.packet.Packet;

import java.util.Arrays;
import java.util.BitSet;
import java.util.EnumSet;
import java.util.List;
import java.util.function.BiConsumer;

public final class PlayerInfo implements Packet {

    private Integer action;
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
    public void encode(Buffer buf) {
        final var actions = Action.class.getEnumConstants();
        final var bitSet = new BitSet(actions.length);
        for (var index = 0; index < actions.length; index++) {
            bitSet.set(index, this.actions.contains(actions[index]));
        }
        buf.writeBytes(Arrays.copyOf(bitSet.toByteArray(), -Math.floorDiv(-actions.length, 8)));

        buf.writeVarInt(this.entries.size());
        for (final var entry : entries) {
            buf.writeUniqueId(entry.getProfile().uniqueId());
            for (final var action : this.actions) {
                action.writer.accept(buf, entry);
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

    public enum Action {
        ADD_PLAYER((buf, entry) -> {
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
        INITIALIZE_CHAT((buf, entry) -> {
        }),
        UPDATE_GAME_MODE((buf, entry) -> buf.writeVarInt(entry.getGameMode().ordinal())),
        UPDATE_LISTED((buf, entry) -> buf.writeBoolean(true)),
        UPDATE_LATENCY((buf, entry) -> buf.writeVarInt(entry.getPing())),
        UPDATE_DISPLAY_NAME((buf, entry) -> {
            if (entry.getDisplayName() != null) {
                buf
                        .writeBoolean(true)
                        .writeComponent(entry.getDisplayName());
            } else {
                buf.writeBoolean(false);
            }
        });

        private final BiConsumer<Buffer, PlayerInfoEntry> writer;

        Action(final BiConsumer<Buffer, PlayerInfoEntry> writer) {
            this.writer = writer;
        }
    }

    @Override
    public String toString() {
        return "PlayerInfo{" +
                (this.action == null ? "actions=" + this.actions : "action=" + this.action) +
                ", entries=" + this.entries +
                '}';
    }
}
