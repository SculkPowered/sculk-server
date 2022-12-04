package de.bauhd.minecraft.server.protocol.packet.play.container;

import de.bauhd.minecraft.server.AdvancedMinecraftServer;
import de.bauhd.minecraft.server.protocol.Protocol;
import de.bauhd.minecraft.server.protocol.packet.Packet;
import io.netty5.buffer.Buffer;
import net.kyori.adventure.text.Component;

import static de.bauhd.minecraft.server.protocol.packet.PacketUtils.writeString;
import static de.bauhd.minecraft.server.protocol.packet.PacketUtils.writeVarInt;

public final class OpenScreen implements Packet {

    private final int windowId;
    private final int windowType;
    private final Component title;

    public OpenScreen(final int windowId, final int windowType, final Component title) {
        this.windowId = windowId;
        this.windowType = windowType;
        this.title = title;
    }

    @Override
    public void encode(Buffer buf, Protocol.Version version) {
        writeVarInt(buf, this.windowId);
        writeVarInt(buf, this.windowType);
        writeString(buf, AdvancedMinecraftServer.getGsonSerializer(version).serialize(this.title));
    }
}
