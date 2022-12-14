package de.bauhd.minecraft.server.protocol.packet.play;

import de.bauhd.minecraft.server.protocol.Buffer;
import de.bauhd.minecraft.server.protocol.Protocol;
import de.bauhd.minecraft.server.protocol.packet.Packet;

public final class Cooldown implements Packet {

    private final int itemId;
    private final int cooldownTicks;

    public Cooldown(final int itemId, final int cooldownTicks) {
        this.itemId = itemId;
        this.cooldownTicks = cooldownTicks;
    }

    @Override
    public void encode(Buffer buf, Protocol.Version version) {
        buf
                .writeVarInt(this.itemId)
                .writeVarInt(this.cooldownTicks);
    }
}
