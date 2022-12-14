package de.bauhd.minecraft.server.protocol.packet.play;

import de.bauhd.minecraft.server.protocol.Buffer;
import de.bauhd.minecraft.server.protocol.Protocol;
import de.bauhd.minecraft.server.protocol.packet.Packet;
import net.kyori.adventure.text.Component;

public final class SystemChatMessage implements Packet {

    private final Component data;
    private final boolean overlay;

    public SystemChatMessage(final Component data, final boolean overlay) {
        this.data = data;
        this.overlay = overlay;
    }

    @Override
    public void encode(Buffer buf, Protocol.Version version) {
        buf
                .writeComponent(this.data, version)
                .writeBoolean(this.overlay);
    }

    @Override
    public String toString() {
        return "SystemChatMessage{" +
                "data=" + this.data +
                ", overlay=" + this.overlay +
                '}';
    }
}
