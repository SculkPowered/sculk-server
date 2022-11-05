package de.bauhd.minecraft.server.protocol.packet.play;

import de.bauhd.minecraft.server.MinecraftServer;
import de.bauhd.minecraft.server.api.entity.MinecraftPlayer;
import de.bauhd.minecraft.server.protocol.Protocol;
import de.bauhd.minecraft.server.protocol.packet.Packet;
import de.bauhd.minecraft.server.util.MojangUtil;
import io.netty5.buffer.Buffer;
import net.kyori.adventure.text.Component;

import java.util.UUID;

import static de.bauhd.minecraft.server.protocol.packet.PacketUtils.*;

public final class PlayerInfo implements Packet {

    private int action;
    private UUID uniqueId;
    private String name;
    private int gameMode;
    private int ping;
    private Component displayName;

    private PlayerInfo(final int action, final UUID uniqueId, final String name, final int gameMode, final int ping, final Component displayName) {
        this.action = action;
        this.uniqueId = uniqueId;
        this.name = name;
        this.gameMode = gameMode;
        this.ping = ping;
        this.displayName = displayName;
    }

    public PlayerInfo() {}

    @Override
    public void decode(Buffer buf, Protocol.Version version) {

    }

    @Override
    public void encode(Buffer buf, Protocol.Version version) {
        writeVarInt(buf, this.action);
        writeVarInt(buf, 1);
        writeUUID(buf, this.uniqueId);

        if (this.action == 0) {
            writeString(buf, this.name);
            writeVarInt(buf, 1);
            writeString(buf, "textures");
            final var skin = MojangUtil.getSkinFromName(this.name);
            writeString(buf, skin.left());
            buf.writeBoolean(true);
            writeString(buf, skin.right());
            writeVarInt(buf, this.gameMode);
            writeVarInt(buf, this.ping);
            if (this.displayName != null) {
                buf.writeBoolean(true);
                writeString(buf, MinecraftServer.getGsonSerializer(version).serialize(this.displayName));
            } else {
                buf.writeBoolean(false);
            }
            buf.writeBoolean(false);
        } else if (this.action == 1) {

        } else if (this.action == 2) {

        } else if (this.action == 3) {

        }
    }

    public static PlayerInfo add(final UUID uniqueId, final String name, final int gameMode, final int ping, final Component displayName) {
        return new PlayerInfo(0, uniqueId, name, gameMode, ping, displayName);
    }

    public static PlayerInfo add(final MinecraftPlayer player) {
        return PlayerInfo.add(player.getUniqueId(), player.getUsername(), 1, 1, null);
    }

    public static PlayerInfo remove(final UUID uniqueId) {
        return new PlayerInfo(4, uniqueId, null, -1, -1, null);
    }

    @Override
    public String toString() {
        return "PlayerInfo{" +
                "action=" + this.action +
                ", uniqueId=" + this.uniqueId +
                ", name='" + this.name + '\'' +
                ", gameMode=" + this.gameMode +
                ", ping=" + this.ping +
                ", displayName=" + this.displayName +
                '}';
    }
}
