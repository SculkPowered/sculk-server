package de.bauhd.minecraft.server.protocol.packet.handler;

import de.bauhd.minecraft.server.AdvancedMinecraftServer;
import de.bauhd.minecraft.server.protocol.Connection;
import de.bauhd.minecraft.server.protocol.Protocol;
import de.bauhd.minecraft.server.protocol.packet.PacketHandler;
import de.bauhd.minecraft.server.protocol.packet.status.StatusPing;
import de.bauhd.minecraft.server.protocol.packet.status.StatusRequest;
import de.bauhd.minecraft.server.protocol.packet.status.StatusResponse;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;

import static de.bauhd.minecraft.server.protocol.Protocol.Version.MINIMUM_VERSION;

public final class StatusPacketHandler extends PacketHandler {

    private static final Component COMPONENT = Component.text("Hello world!", TextColor.color(109, 331, 221));

    private final Connection connection;

    public StatusPacketHandler(final Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean handle(StatusRequest statusRequest) {
        final var version = this.connection.version();
        this.connection.send(new StatusResponse("{\"version\":{" +
                "\"name\":\"not vanilla " + Protocol.Version.SUPPORTED_VERSIONS + "\"," +
                "\"protocol\":" + (version.older(MINIMUM_VERSION) ? MINIMUM_VERSION.protocolId() : version.protocolId()) + "}," +
                "\"players\":{\"max\":50,\"online\":" + this.connection.server().getPlayerCount() + ",\"sample\":[]}," +
                "\"description\":" + AdvancedMinecraftServer.getGsonSerializer(version).serialize(COMPONENT) + "," +
                "\"previewsChat\":true}"));
        return false;
    }

    @Override
    public boolean handle(StatusPing statusPing) {
        this.connection.sendAndClose(statusPing);
        return false;
    }
}
