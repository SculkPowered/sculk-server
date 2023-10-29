package io.github.sculkpowered.server.protocol.packet.play;

import io.github.sculkpowered.server.entity.player.PlayerInfoEntry;
import io.github.sculkpowered.server.protocol.Buffer;
import io.github.sculkpowered.server.protocol.packet.Packet;
import java.util.Arrays;
import java.util.BitSet;
import java.util.EnumSet;
import java.util.List;
import java.util.function.BiConsumer;

public final class PlayerInfo implements Packet {

  private final EnumSet<Action> actions;
  private final List<? extends PlayerInfoEntry> entries;

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
      buf.writeUniqueId(entry.profile().uniqueId());
      for (final var action : this.actions) {
        action.writer.accept(buf, entry);
      }
    }
  }

  public static PlayerInfo add(final List<? extends PlayerInfoEntry> entries) {
    return new PlayerInfo(
        EnumSet.of(Action.ADD_PLAYER, Action.UPDATE_GAME_MODE, Action.UPDATE_LISTED,
            Action.UPDATE_DISPLAY_NAME, Action.INITIALIZE_CHAT), entries);
  }

  public static PlayerInfo update(final PlayerInfoEntry entry, final Action action) {
    return new PlayerInfo(EnumSet.of(action), List.of(entry));
  }

  public enum Action {
    ADD_PLAYER((buf, entry) -> {
      final var profile = entry.profile();
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
    INITIALIZE_CHAT((buf, entry) -> buf.writeBoolean(false)),
    UPDATE_GAME_MODE((buf, entry) -> buf.writeVarInt(entry.gameMode().ordinal())),
    UPDATE_LISTED((buf, entry) -> buf.writeBoolean(true)),
    UPDATE_LATENCY((buf, entry) -> buf.writeVarInt(entry.ping())),
    UPDATE_DISPLAY_NAME((buf, entry) -> {
      if (buf.writeOptional(entry.displayName())) {
        buf.writeComponent(entry.displayName());
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
        "actions=" + this.actions +
        ", entries=" + this.entries +
        '}';
  }
}
