package de.bauhd.minecraft.server;

import de.bauhd.minecraft.server.protocol.Protocol;
import de.bauhd.minecraft.server.protocol.netty.NettyServer;
import net.kyori.adventure.text.serializer.gson.GsonComponentSerializer;

public class MinecraftServer {

    private static final GsonComponentSerializer PRE_1_16_SERIALIZER =
            GsonComponentSerializer.builder()
                    .downsampleColors()
                    .emitLegacyHoverEvent()
                    .build();
    private static final GsonComponentSerializer MODERN_SERIALIZER =
            GsonComponentSerializer.builder()
                    .build();

    public static void main(String[] args) {

        new NettyServer().connect("0.0.0.0", 25565);
        new Worker().start();

    }

    public static GsonComponentSerializer getGsonSerializer(final Protocol.Version version) {
        return version.compare(Protocol.Version.MINECRAFT_1_16) ? MODERN_SERIALIZER : PRE_1_16_SERIALIZER;
    }

}
