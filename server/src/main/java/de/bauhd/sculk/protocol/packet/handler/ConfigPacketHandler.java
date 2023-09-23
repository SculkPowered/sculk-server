package de.bauhd.sculk.protocol.packet.handler;

import de.bauhd.sculk.entity.player.SculkPlayer;
import de.bauhd.sculk.protocol.SculkConnection;
import de.bauhd.sculk.protocol.packet.PacketHandler;
import de.bauhd.sculk.protocol.packet.config.FinishConfiguration;
import de.bauhd.sculk.protocol.packet.play.ClientInformation;
import de.bauhd.sculk.protocol.packet.play.KeepAlive;
import de.bauhd.sculk.protocol.packet.play.PluginMessage;

public class ConfigPacketHandler extends PacketHandler {

    private final SculkConnection connection;
    private final SculkPlayer player;

    public ConfigPacketHandler(final SculkConnection connection, final SculkPlayer player) {
        this.connection = connection;
        this.player = player;
    }

    @Override
    public boolean handle(ClientInformation clientInformation) {
        this.player.handleClientInformation(clientInformation);
        return true;
    }

    @Override
    public boolean handle(PluginMessage pluginMessage) {
        // TODO: implement plugin message system
        return true;
    }

    @Override
    public boolean handle(FinishConfiguration finishConfiguration) {
        this.connection.play();
        return true;
    }

    @Override
    public boolean handle(KeepAlive keepAlive) {
        return true;
    }
}
