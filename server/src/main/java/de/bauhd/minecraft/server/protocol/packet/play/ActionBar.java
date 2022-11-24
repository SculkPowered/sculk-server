package de.bauhd.minecraft.server.protocol.packet.play;

import de.bauhd.minecraft.server.AdvancedMinecraftServer;
import de.bauhd.minecraft.server.protocol.Protocol;
import de.bauhd.minecraft.server.protocol.packet.Packet;
import io.netty5.buffer.Buffer;
import net.kyori.adventure.text.Component;

import static de.bauhd.minecraft.server.protocol.packet.PacketUtils.writeString;

public final class ActionBar implements Packet {

    private final Component text;

    public ActionBar(final Component actionBar) {
        this.text = actionBar;
    }

    @Override
    public void decode(Buffer buf, Protocol.Version version) {

    }

    @Override
    public void encode(Buffer buf, Protocol.Version version) {
        writeString(buf, AdvancedMinecraftServer.getGsonSerializer(version).serialize(this.text));
    }

    @Override
    public String toString() {
        return "ActionBar{" +
                "text=" + this.text +
                '}';
    }
}
