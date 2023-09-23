package de.bauhd.sculk.protocol.packet.config;

import de.bauhd.sculk.protocol.packet.Packet;
import de.bauhd.sculk.protocol.packet.PacketHandler;

public final class FinishConfiguration implements Packet {

    public static final FinishConfiguration INSTANCE = new FinishConfiguration();

    @Override
    public boolean handle(PacketHandler handler) {
        return handler.handle(this);
    }

    @Override
    public int maxLength() {
        return 0;
    }
}
