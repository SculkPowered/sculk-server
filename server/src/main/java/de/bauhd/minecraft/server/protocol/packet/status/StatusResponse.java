package de.bauhd.minecraft.server.protocol.packet.status;

import de.bauhd.minecraft.server.DefaultMinecraftServer;
import de.bauhd.minecraft.server.protocol.Protocol;
import de.bauhd.minecraft.server.protocol.packet.Packet;
import io.netty5.buffer.Buffer;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;

import static de.bauhd.minecraft.server.protocol.packet.PacketUtils.writeString;

public final class StatusResponse implements Packet {

    private final Component component = Component.text("Hallo Welt!", TextColor.color(109, 331, 221));

    @Override
    public void decode(Buffer buf, Protocol.Version version) {

    }

    @Override
    public void encode(Buffer buf, Protocol.Version version) {
        writeString(buf, "{\"version\":{\"name\":\"Minecraft Server\",\"protocol\":"
                + version.protocolVersion() + "},\"players\":{\"max\":50,\"online\":0,\"sample\":[]},\"description\":"
                + DefaultMinecraftServer.getGsonSerializer(version).serialize(this.component) + ",\"previewsChat\":true}\"\"\")");
    }
}
