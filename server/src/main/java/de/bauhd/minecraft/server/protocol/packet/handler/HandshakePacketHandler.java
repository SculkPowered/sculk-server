package de.bauhd.minecraft.server.protocol.packet.handler;

import de.bauhd.minecraft.server.protocol.Connection;
import de.bauhd.minecraft.server.protocol.State;
import de.bauhd.minecraft.server.protocol.packet.PacketHandler;
import de.bauhd.minecraft.server.protocol.packet.handshake.Handshake;
import de.bauhd.minecraft.server.protocol.packet.login.Disconnect;
import net.kyori.adventure.text.Component;

import static de.bauhd.minecraft.server.protocol.Protocol.Version.*;

public final class HandshakePacketHandler extends PacketHandler {

    private final Connection connection;

    public HandshakePacketHandler(final Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean handle(Handshake handshake) {
        this.connection.set(handshake.nextStatus());
        if (handshake.nextStatus() == State.LOGIN
                && (handshake.version().older(MINIMUM_VERSION) || handshake.version().newer(MAXIMUM_VERSION))) {
            this.connection.send(new Disconnect(Component
                    .translatable("multiplayer.disconnect.outdated_client", Component.text(SUPPORTED_VERSIONS))));
            return true;
        }
        this.connection.setVersion(handshake.version());
        this.connection.setServerAddress(handshake.serverAddress());
        return false;
    }
}
