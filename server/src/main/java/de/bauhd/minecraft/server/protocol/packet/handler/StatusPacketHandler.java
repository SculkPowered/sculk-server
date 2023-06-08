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

public final class StatusPacketHandler extends PacketHandler {

    private static final Component COMPONENT = Component.text("Hello world!", TextColor.color(109, 331, 221));

    private final Connection connection;

    public StatusPacketHandler(final Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean handle(StatusRequest statusRequest) {
        this.connection.send(new StatusResponse("{\"version\":{" +
                "\"name\":\"not vanilla " + Protocol.VERSION_NAME + "\"," +
                "\"protocol\":" + Protocol.VERSION_PROTOCOL + "}," +
                "\"players\":{\"max\":50,\"online\":" + this.connection.server().getPlayerCount() + ",\"sample\":[]}," +
                "\"description\":" + AdvancedMinecraftServer.getGsonSerializer(this.connection.version()).serialize(COMPONENT) + "," +
                "\"previewsChat\":true}"));
        return true;
    }

    @Override
    public boolean handle(StatusPing statusPing) {
        this.connection.sendAndClose(statusPing);
        return true;
    }
}
