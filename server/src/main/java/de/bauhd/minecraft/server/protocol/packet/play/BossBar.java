package de.bauhd.minecraft.server.protocol.packet.play;

import de.bauhd.minecraft.server.MinecraftServer;
import de.bauhd.minecraft.server.protocol.Protocol;
import de.bauhd.minecraft.server.protocol.packet.Packet;
import io.netty5.buffer.Buffer;
import net.kyori.adventure.text.Component;

import java.util.UUID;

import static de.bauhd.minecraft.server.protocol.packet.PacketUtils.*;

public final class BossBar implements Packet {

    private UUID uniqueId;
    private int action;
    private Component title;
    private float health;
    private int color;
    private int division;
    private int flags;

    private BossBar(final UUID uniqueId, final int action, final Component title, final float health, final int color, final int division, final int flags) {
        this.uniqueId = uniqueId;
        this.action = action;
        this.title = title;
        this.health = health;
        this.color = color;
        this.division = division;
        this.flags = flags;
    }

    public BossBar() {}

    @Override
    public void decode(Buffer buf, Protocol.Version version) {

    }

    @Override
    public void encode(Buffer buf, Protocol.Version version) {
        writeUUID(buf, this.uniqueId);
        writeVarInt(buf, this.action);

        if (this.action == 0) {
            writeString(buf, MinecraftServer.getGsonSerializer(version).serialize(this.title));
            buf.writeFloat(this.health);
            writeVarInt(buf, this.color);
            writeVarInt(buf, this.division);
            buf.writeUnsignedByte(this.flags);
        } else if (this.action == 2) {
            buf.writeFloat(this.health);
        } else if (this.action == 3) {
            writeString(buf, MinecraftServer.getGsonSerializer(version).serialize(this.title));
        } else if (this.action == 4) {
            writeVarInt(buf, this.color);
            writeVarInt(buf, this.division);
        } else if (this.action == 5) {
            buf.writeUnsignedByte(this.flags);
        }
    }

    public static BossBar add(final UUID uniqueId, final Component title, final float health, final int color, final int division, final int flags) {
        return new BossBar(uniqueId, 0, title, health, color, division, flags);
    }

    public static BossBar remove(final UUID uniqueId) {
        return new BossBar(uniqueId, 1, null, -1, -1, -1, -1);
    }

    public static BossBar update(final UUID uniqueId, final float health) {
        return new BossBar(uniqueId, 2, null, health, -1, -1, -1);
    }

    public static BossBar update(final UUID uniqueId, final Component title) {
        return new BossBar(uniqueId, 3, title, -1, -1, -1, -1);
    }

    public static BossBar update(final UUID uniqueId, final int color, final int division) {
        return new BossBar(uniqueId, 4, null, -1, color, division, -1);
    }

    public static BossBar update(final UUID uniqueId, final int flags) {
        return new BossBar(uniqueId, 3, null, -1, -1, -1, flags);
    }
}
