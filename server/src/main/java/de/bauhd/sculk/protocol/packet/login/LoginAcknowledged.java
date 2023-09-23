package de.bauhd.sculk.protocol.packet.login;

import de.bauhd.sculk.protocol.packet.Packet;
import de.bauhd.sculk.protocol.packet.PacketHandler;

public final class LoginAcknowledged implements Packet {

    public static final LoginAcknowledged INSTANCE = new LoginAcknowledged();

    @Override
    public int maxLength() {
        return 0;
    }

    @Override
    public boolean handle(PacketHandler handler) {
        return handler.handle(this);
    }
}
