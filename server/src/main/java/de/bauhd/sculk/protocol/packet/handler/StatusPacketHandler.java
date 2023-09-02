package de.bauhd.sculk.protocol.packet.handler;

import de.bauhd.sculk.SculkMinecraftServer;
import de.bauhd.sculk.event.connection.ServerPingEvent;
import de.bauhd.sculk.protocol.Buffer;
import de.bauhd.sculk.protocol.MineConnection;
import de.bauhd.sculk.protocol.Protocol;
import de.bauhd.sculk.protocol.packet.PacketHandler;
import de.bauhd.sculk.protocol.packet.status.StatusPing;
import de.bauhd.sculk.protocol.packet.status.StatusRequest;
import de.bauhd.sculk.protocol.packet.status.StatusResponse;
import net.kyori.adventure.text.Component;

public final class StatusPacketHandler extends PacketHandler {

    private final SculkMinecraftServer server;
    private final MineConnection connection;

    public StatusPacketHandler(final MineConnection connection, final SculkMinecraftServer server) {
        this.connection = connection;
        this.server = server;
    }

    @Override
    public boolean handle(StatusRequest statusRequest) {
        final var event = this.server.getEventHandler().callSync(new ServerPingEvent(this.connection));
        final var response = event.getResponse();
        if (response != null) {
            if (response.protocol() == Integer.MIN_VALUE) {
                response.protocol(Protocol.VERSION_PROTOCOL);
            }
            if (response.online() == Integer.MIN_VALUE) {
                response.online(this.server.getPlayerCount());
            }
            if (response.description() == null) {
                response.description(Component.empty());
            }
            this.connection.send(new StatusResponse("{" +
                    "\"version\":{" +
                    "\"name\":\"" + response.name() + "\"," + "\"protocol\":" + response.protocol() + "}," +
                    "\"players\":{" +
                    "\"max\":" + response.max() + ",\"online\":" + response.online() + ",\"sample\":[]}," +
                    "\"description\":" + Buffer.getGsonSerializer(this.connection.getProtocolVersion()).serialize(response.description()) +
                    "}"));
        } else {
            this.connection.close();
        }
        return true;
    }

    @Override
    public boolean handle(StatusPing statusPing) {
        this.connection.sendAndClose(statusPing);
        return true;
    }
}
