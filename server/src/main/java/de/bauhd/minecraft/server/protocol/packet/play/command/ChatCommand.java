package de.bauhd.minecraft.server.protocol.packet.play.command;

import com.mojang.brigadier.exceptions.CommandSyntaxException;
import de.bauhd.minecraft.server.AdvancedMinecraftServer;
import de.bauhd.minecraft.server.protocol.Buffer;
import de.bauhd.minecraft.server.protocol.Connection;
import de.bauhd.minecraft.server.protocol.Protocol;
import de.bauhd.minecraft.server.protocol.packet.Packet;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;

public final class ChatCommand implements Packet {

    private String command;
    private long timestamp;
    private long salt;
    private boolean signedPreview;

    @Override
    public void decode(Buffer buf, Protocol.Version version) {
        this.command = buf.readString();
        this.timestamp = buf.readLong();
        this.salt = buf.readLong();
        final var signatures = buf.readVarInt();
        for (int i = 0; i < signatures; i++) {
            buf.readString();
            buf.readByteArray();
        }
        this.signedPreview = buf.readBoolean();

        if (version.newerOr(Protocol.Version.MINECRAFT_1_19_1)) {
            // ignore for now
            buf.readVarInt();
            buf.readBoolean();
        }
    }

    @Override
    public boolean handle(Connection connection) {
        try {
            AdvancedMinecraftServer.getInstance().getCommandHandler().dispatcher().execute(this.command, connection.player());
        } catch (CommandSyntaxException e) {
            connection.player().sendMessage(Component.text(e.getMessage(), NamedTextColor.RED));
        }
        return false;
    }

    @Override
    public String toString() {
        return "ChatCommand{" +
                "command='" + this.command + '\'' +
                ", timestamp=" + this.timestamp +
                ", salt=" + this.salt +
                ", signedPreview=" + this.signedPreview +
                '}';
    }
}
