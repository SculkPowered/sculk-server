package de.bauhd.minecraft.server.protocol.packet.play;

import de.bauhd.minecraft.server.entity.player.PlayerInfoEntry;
import de.bauhd.minecraft.server.protocol.Buffer;
import de.bauhd.minecraft.server.protocol.packet.Packet;

import java.util.List;

public final class PlayerInfoRemove implements Packet {

    private final List<PlayerInfoEntry> entries;

    public PlayerInfoRemove(final List<PlayerInfoEntry> entries) {
        this.entries = entries;
    }

    @Override
    public void encode(Buffer buf) {
        this.entries.forEach(entry -> buf.writeUniqueId(entry.getProfile().uniqueId()));
    }

    @Override
    public String toString() {
        return "PlayerInfoRemove{" +
                "entries=" + this.entries +
                '}';
    }
}
