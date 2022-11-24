package de.bauhd.minecraft.server.protocol.packet.play;

import de.bauhd.minecraft.server.AdvancedMinecraftServer;
import de.bauhd.minecraft.server.protocol.Protocol;
import de.bauhd.minecraft.server.protocol.packet.Packet;
import io.netty5.buffer.Buffer;
import net.kyori.adventure.text.Component;

import static de.bauhd.minecraft.server.protocol.packet.PacketUtils.writeString;

public final class SystemChatMessage implements Packet {

    private final Component data;
    private final boolean overlay;

    public SystemChatMessage(final Component data, final boolean overlay) {
        this.data = data;
        this.overlay = overlay;
    }

    @Override
    public void encode(Buffer buf, Protocol.Version version) {
        writeString(buf, AdvancedMinecraftServer.getGsonSerializer(version).serialize(this.data));
        buf.writeBoolean(this.overlay);
    }

    @Override
    public String toString() {
        return "SystemChatMessage{" +
                "data=" + this.data +
                ", overlay=" + this.overlay +
                '}';
    }
}
