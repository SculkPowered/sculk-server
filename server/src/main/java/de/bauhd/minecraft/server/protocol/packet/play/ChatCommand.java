package de.bauhd.minecraft.server.protocol.packet.play;

import com.mojang.brigadier.exceptions.CommandSyntaxException;
import de.bauhd.minecraft.server.AdvancedMinecraftServer;
import de.bauhd.minecraft.server.protocol.Connection;
import de.bauhd.minecraft.server.protocol.Protocol;
import de.bauhd.minecraft.server.protocol.packet.Packet;
import de.bauhd.minecraft.server.protocol.packet.play.container.OpenScreen;
import io.netty5.buffer.Buffer;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;

import java.util.concurrent.atomic.AtomicInteger;

import static de.bauhd.minecraft.server.protocol.packet.PacketUtils.*;

public final class ChatCommand implements Packet {

    private String command;
    private long timestamp;
    private long salt;
    private boolean signedPreview;

    @Override
    public void decode(Buffer buf, Protocol.Version version) {
        this.command = readString(buf);
        this.timestamp = buf.readLong();
        this.salt = buf.readLong();
        final var signatures = readVarInt(buf);
        for (int i = 0; i < signatures; i++) {
            readString(buf);
            readByteArray(buf);
        }
        this.signedPreview = buf.readBoolean();

        if (version.compare(Protocol.Version.MINECRAFT_1_19_1)) {
            // ignore for now
            readVarInt(buf);
            buf.readBoolean();
        }
    }

    @Override
    public void handle(Connection connection) {
        try {
            AdvancedMinecraftServer.getInstance().getCommandHandler().dispatcher().execute(this.command, connection.player());
        } catch (CommandSyntaxException e) {
            connection.player().sendMessage(Component.text(e.getMessage(), NamedTextColor.RED));
        }
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
