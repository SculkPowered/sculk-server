package de.bauhd.minecraft.server.protocol.packet.play;

import de.bauhd.minecraft.server.DefaultMinecraftServer;
import de.bauhd.minecraft.server.protocol.Protocol;
import de.bauhd.minecraft.server.protocol.packet.Packet;
import io.netty5.buffer.Buffer;
import net.kyori.adventure.text.Component;

import static de.bauhd.minecraft.server.protocol.packet.PacketUtils.writeString;

public final class ActionBar implements Packet {

    private Component actionBar;

    public ActionBar(final Component actionBar) {
        this.actionBar = actionBar;
    }

    public ActionBar() {}

    @Override
    public void decode(Buffer buf, Protocol.Version version) {

    }

    @Override
    public void encode(Buffer buf, Protocol.Version version) {
        writeString(buf, DefaultMinecraftServer.getGsonSerializer(version).serialize(this.actionBar));
    }

    @Override
    public String toString() {
        return "ActionBar{" +
                "actionBar=" + this.actionBar +
                '}';
    }
}
