package de.bauhd.minecraft.server.protocol.packet.play.container;

import de.bauhd.minecraft.server.protocol.Buffer;
import de.bauhd.minecraft.server.protocol.packet.Packet;
import net.kyori.adventure.text.Component;

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
    public void encode(Buffer buf) {
        buf
                .writeVarInt(this.windowId)
                .writeVarInt(this.windowType)
                .writeComponent(this.title);
    }
}
