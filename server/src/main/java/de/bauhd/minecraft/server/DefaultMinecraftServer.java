package de.bauhd.minecraft.server;

import de.bauhd.minecraft.server.api.Command;
import de.bauhd.minecraft.server.api.MinecraftServer;
import de.bauhd.minecraft.server.api.command.MinecraftCommandHandler;
import de.bauhd.minecraft.server.api.dimension.MinecraftDimensionHandler;
import de.bauhd.minecraft.server.api.world.biome.BiomeHandler;
import de.bauhd.minecraft.server.api.world.dimension.DimensionHandler;
import de.bauhd.minecraft.server.protocol.Protocol;
import de.bauhd.minecraft.server.protocol.netty.NettyServer;
import net.kyori.adventure.text.serializer.gson.GsonComponentSerializer;

public class DefaultMinecraftServer extends MinecraftServer {

    private static final GsonComponentSerializer PRE_1_16_SERIALIZER =
            GsonComponentSerializer.builder()
                    .downsampleColors()
                    .emitLegacyHoverEvent()
                    .build();
    private static final GsonComponentSerializer MODERN_SERIALIZER =
            GsonComponentSerializer.builder()
                    .build();

    public static final MinecraftCommandHandler COMMAND_HANDLER = new MinecraftCommandHandler(); // TODO

    private final DimensionHandler dimensionHandler;
    private final BiomeHandler biomeHandler;

    public DefaultMinecraftServer() {
        COMMAND_HANDLER.register("foo", new Command());

        new NettyServer().connect("0.0.0.0", 25565);

        this.dimensionHandler = new MinecraftDimensionHandler();
        this.biomeHandler = null;

        new Worker().start();
    }

    public static GsonComponentSerializer getGsonSerializer(final Protocol.Version version) {
        return version.compare(Protocol.Version.MINECRAFT_1_16) ? MODERN_SERIALIZER : PRE_1_16_SERIALIZER;
    }

    @Override
    public DimensionHandler getDimensionHandler() {
        return null;
    }

    @Override
    public BiomeHandler getBiomeHandler() {
        return null;
    }
}
