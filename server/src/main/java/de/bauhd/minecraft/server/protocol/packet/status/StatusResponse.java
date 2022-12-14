package de.bauhd.minecraft.server.protocol.packet.status;

import de.bauhd.minecraft.server.AdvancedMinecraftServer;
import de.bauhd.minecraft.server.protocol.Buffer;
import de.bauhd.minecraft.server.protocol.Protocol;
import de.bauhd.minecraft.server.protocol.packet.Packet;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;

public final class StatusResponse implements Packet {

    private final Component component = Component.text("Hallo Welt!", TextColor.color(109, 331, 221));

    @Override
    public void encode(Buffer buf, Protocol.Version version) {
        buf.writeString("{\"version\":{" +
                "\"name\":\"not vanilla " + Protocol.Version.SUPPORTED_VERSIONS + "\"," +
                "\"protocol\":" + version.protocolId() + "}," +
                "\"players\":{\"max\":50,\"online\":0,\"sample\":[]}," +
                "\"description\":" + AdvancedMinecraftServer.getGsonSerializer(version).serialize(this.component) + "," +
                "\"previewsChat\":true}");
    }

    @Override
    public String toString() {
        return "StatusResponse{" +
                "component=" + this.component +
                '}';
    }
}
