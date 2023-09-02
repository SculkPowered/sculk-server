package de.bauhd.sculk.protocol.packet.play.scoreboard;

import de.bauhd.sculk.protocol.Buffer;
import de.bauhd.sculk.protocol.packet.Packet;
import net.kyori.adventure.text.Component;

public final class UpdateObjectives implements Packet {

    private final String name;
    private final byte mode;
    private final Component value;
    private final int type;

    public UpdateObjectives(final String name, final byte mode, final Component value, final int type) {
        this.name = name;
        this.mode = mode;
        this.value = value;
        this.type = type;
    }

    @Override
    public void encode(Buffer buf) {
        buf
                .writeString(this.name)
                .writeByte(this.mode)
                .writeComponent(this.value)
                .writeVarInt(this.type);
    }
}
