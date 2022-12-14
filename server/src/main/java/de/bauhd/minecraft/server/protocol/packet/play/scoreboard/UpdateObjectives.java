package de.bauhd.minecraft.server.protocol.packet.play.scoreboard;

import de.bauhd.minecraft.server.protocol.Buffer;
import de.bauhd.minecraft.server.protocol.Protocol;
import de.bauhd.minecraft.server.protocol.packet.Packet;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;

public final class UpdateObjectives implements Packet {

    @Override
    public void encode(Buffer buf, Protocol.Version version) {
        buf
                .writeString("board")
                .writeByte((byte) 0)
                .writeComponent(Component.text("Title", NamedTextColor.RED), version)
                .writeVarInt(0);
    }
}
