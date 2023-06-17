package de.bauhd.minecraft.server.protocol.packet.handler;

import de.bauhd.minecraft.server.protocol.MineConnection;
import de.bauhd.minecraft.server.protocol.Protocol;
import de.bauhd.minecraft.server.protocol.State;
import de.bauhd.minecraft.server.protocol.packet.PacketHandler;
import de.bauhd.minecraft.server.protocol.packet.handshake.Handshake;
import de.bauhd.minecraft.server.protocol.packet.login.Disconnect;
import net.kyori.adventure.text.Component;

public final class HandshakePacketHandler extends PacketHandler {

    private final MineConnection connection;

    public HandshakePacketHandler(final MineConnection connection) {
        this.connection = connection;
    }

    @Override
    public boolean handle(Handshake handshake) {
        this.connection.setState(handshake.nextStatus());
        if (handshake.nextStatus() == State.LOGIN
                && (handshake.version() != Protocol.VERSION_PROTOCOL)) {
            this.connection.send(new Disconnect(Component
                    .translatable("multiplayer.disconnect.outdated_client", Component.text(Protocol.VERSION_NAME))));
            this.connection.close();
            return true;
        }
        this.connection.setVersion(handshake.version());
        this.connection.setServerAddress(handshake.serverAddress());
        return true;
    }
}
