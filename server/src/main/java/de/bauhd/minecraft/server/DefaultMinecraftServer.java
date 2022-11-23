package de.bauhd.minecraft.server;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import de.bauhd.minecraft.server.api.Command;
import de.bauhd.minecraft.server.api.MinecraftServer;
import de.bauhd.minecraft.server.api.command.MinecraftCommandHandler;
import de.bauhd.minecraft.server.api.dimension.MinecraftDimensionHandler;
import de.bauhd.minecraft.server.api.entity.player.GameProfile;
import de.bauhd.minecraft.server.api.event.MinecraftEventHandler;
import de.bauhd.minecraft.server.api.event.lifecycle.ServerInitializeEvent;
import de.bauhd.minecraft.server.api.module.MinecraftModuleHandler;
import de.bauhd.minecraft.server.api.world.biome.BiomeHandler;
import de.bauhd.minecraft.server.api.world.dimension.DimensionHandler;
import de.bauhd.minecraft.server.json.GameProfileDeserializer;
import de.bauhd.minecraft.server.json.GameProfilePropertyDeserializer;
import de.bauhd.minecraft.server.protocol.Protocol;
import de.bauhd.minecraft.server.protocol.netty.NettyServer;
import de.bauhd.minecraft.server.util.BossBarListener;
import net.kyori.adventure.text.serializer.gson.GsonComponentSerializer;
import org.jetbrains.annotations.NotNull;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;

public class DefaultMinecraftServer implements MinecraftServer {

    private static DefaultMinecraftServer instance;

    public static final Gson GSON = new GsonBuilder()
            .registerTypeAdapter(GameProfile.Property.class, new GameProfilePropertyDeserializer())
            .registerTypeAdapter(GameProfile.class, new GameProfileDeserializer())
            .create();
    public static final boolean BUNGEECORD = false; // TODO change me

    private static final GsonComponentSerializer PRE_1_16_SERIALIZER =
            GsonComponentSerializer.builder()
                    .downsampleColors()
                    .emitLegacyHoverEvent()
                    .build();
    private static final GsonComponentSerializer MODERN_SERIALIZER =
            GsonComponentSerializer.gson();

    private final KeyPair keyPair;
    private final DimensionHandler dimensionHandler;
    private final BiomeHandler biomeHandler;
    private final MinecraftModuleHandler moduleHandler;
    private final MinecraftEventHandler eventHandler;
    private final MinecraftCommandHandler commandHandler;
    private final BossBarListener bossBarListener;

    DefaultMinecraftServer() {
        instance = this;


        try {
            final var generator= KeyPairGenerator.getInstance("RSA");
            generator.initialize(1024);
            this.keyPair = generator.generateKeyPair();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

        this.dimensionHandler = new MinecraftDimensionHandler();
        this.biomeHandler = null;
        this.moduleHandler = new MinecraftModuleHandler();
        this.eventHandler = new MinecraftEventHandler();
        this.commandHandler = new MinecraftCommandHandler();
        this.commandHandler.register("foo", new Command());
        this.bossBarListener = new BossBarListener();

        this.moduleHandler.loadModules();

        this.eventHandler.call(new ServerInitializeEvent()).join();

        new NettyServer().connect("0.0.0.0", 25565);

        new Worker().start();
    }

    public static GsonComponentSerializer getGsonSerializer(final Protocol.Version version) {
        return version.compare(Protocol.Version.MINECRAFT_1_16) ? MODERN_SERIALIZER : PRE_1_16_SERIALIZER;
    }

    @Override
    public @NotNull DimensionHandler getDimensionHandler() {
        return this.dimensionHandler;
    }

    @Override
    public @NotNull BiomeHandler getBiomeHandler() {
        return this.biomeHandler;
    }

    @Override
    public @NotNull MinecraftModuleHandler getModuleHandler() {
        return this.moduleHandler;
    }

    @Override
    public @NotNull MinecraftEventHandler getEventHandler() {
        return this.eventHandler;
    }

    @Override
    public @NotNull MinecraftCommandHandler getCommandHandler() {
        return this.commandHandler;
    }

    public KeyPair getKeyPair() {
        return this.keyPair;
    }

    public BossBarListener getBossBarListener() {
        return this.bossBarListener;
    }

    public static DefaultMinecraftServer getInstance() {
        return instance;
    }
}
