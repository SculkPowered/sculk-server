package de.bauhd.minecraft.server.protocol.packet.play;

import de.bauhd.minecraft.server.AdvancedMinecraftServer;
import de.bauhd.minecraft.server.api.world.Position;
import de.bauhd.minecraft.server.protocol.Buffer;
import de.bauhd.minecraft.server.protocol.Connection;
import de.bauhd.minecraft.server.protocol.Protocol;
import de.bauhd.minecraft.server.protocol.packet.Packet;
import de.bauhd.minecraft.server.protocol.packet.play.block.BlockUpdate;

public final class PlayerAction implements Packet {

    private int status;
    private Position position;
    private byte face;
    private int sequence;

    @Override
    public void decode(Buffer buf, Protocol.Version version) {
        this.status = buf.readVarInt();
        this.position = buf.readPosition();
        this.face = buf.readByte();
        this.sequence = buf.readVarInt();
    }

    @Override
    public boolean handle(Connection connection) {
        final var player = connection.player();
        switch (this.status) {
            case 0: // only if instant break
                // TODO get chunk viewers
                AdvancedMinecraftServer.getInstance().sendAll(new BlockUpdate(this.position, 0));
                break;
            case 3:

                break;
            case 4:
                player.getItemInMainHand().count(player.getItemInMainHand().count() - 1);
                break;
        }
        return false;
    }

    @Override
    public String toString() {
        return "PlayerAction{" +
                "status=" + this.status +
                ", position=" + this.position +
                ", face=" + this.face +
                ", sequence=" + this.sequence +
                '}';
    }
}
