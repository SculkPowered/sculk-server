package de.bauhd.sculk.protocol.packet.play;

import de.bauhd.sculk.protocol.Buffer;
import de.bauhd.sculk.protocol.packet.Packet;
import de.bauhd.sculk.team.Team;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import net.kyori.adventure.text.format.NamedTextColor;

import java.util.Arrays;

public final class UpdateTeams implements Packet {

    private static final Object2IntMap<NamedTextColor> COLOR_TO_ID = new Object2IntOpenHashMap<>(16);

    static {
        COLOR_TO_ID.defaultReturnValue(21);
        COLOR_TO_ID.put(NamedTextColor.BLACK, 0);
        COLOR_TO_ID.put(NamedTextColor.DARK_BLUE, 1);
        COLOR_TO_ID.put(NamedTextColor.DARK_GREEN, 2);
        COLOR_TO_ID.put(NamedTextColor.DARK_AQUA, 3);
        COLOR_TO_ID.put(NamedTextColor.DARK_RED, 4);
        COLOR_TO_ID.put(NamedTextColor.DARK_PURPLE, 5);
        COLOR_TO_ID.put(NamedTextColor.GOLD, 6);
        COLOR_TO_ID.put(NamedTextColor.GRAY, 7);
        COLOR_TO_ID.put(NamedTextColor.DARK_GRAY, 8);
        COLOR_TO_ID.put(NamedTextColor.BLUE, 9);
        COLOR_TO_ID.put(NamedTextColor.GREEN, 10);
        COLOR_TO_ID.put(NamedTextColor.AQUA, 11);
        COLOR_TO_ID.put(NamedTextColor.RED, 12);
        COLOR_TO_ID.put(NamedTextColor.LIGHT_PURPLE, 13);
        COLOR_TO_ID.put(NamedTextColor.YELLOW, 14);
        COLOR_TO_ID.put(NamedTextColor.WHITE, 15);
    }

    private final Team team;
    private final byte mode;
    private final String[] entries;

    public UpdateTeams(final Team team, final byte mode, final String[] entries) {
        this.team = team;
        this.mode = mode;
        this.entries = entries;
    }

    @Override
    public void encode(Buffer buf) {
        buf
                .writeString(this.team.getName())
                .writeByte(this.mode);
        if (this.mode == 0) {
            this.writeData(buf);
            this.writeEntries(buf);
        } else if (this.mode == 2) {
            this.writeData(buf);
        } else if (this.mode == 3 || this.mode == 4) {
            this.writeEntries(buf);
        }
    }

    private void writeData(final Buffer buf) {
        buf
                .writeComponent(this.team.getDisplayName())
                .writeByte((byte) 0)
                .writeString("always")
                .writeString("always")
                .writeVarInt(COLOR_TO_ID.getInt(this.team.getColor()))
                .writeComponent(this.team.getPrefix())
                .writeComponent(this.team.getSuffix());
    }

    private void writeEntries(final Buffer buf) {
        buf.writeVarInt(this.entries.length);
        for (final var entry : this.entries) {
            buf.writeString(entry);
        }
    }

    @Override
    public String toString() {
        return "UpdateTeams{" +
                "team=" + this.team +
                ", mode=" + this.mode +
                ", entries=" + Arrays.toString(this.entries) +
                '}';
    }
}
