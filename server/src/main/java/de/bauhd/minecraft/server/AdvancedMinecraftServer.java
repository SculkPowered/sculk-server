package de.bauhd.minecraft.server;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import de.bauhd.minecraft.server.api.MinecraftConfig;
import de.bauhd.minecraft.server.api.MinecraftConfiguration;
import de.bauhd.minecraft.server.api.MinecraftServer;
import de.bauhd.minecraft.server.api.command.MinecraftCommandHandler;
import de.bauhd.minecraft.server.api.dimension.MinecraftDimensionHandler;
import de.bauhd.minecraft.server.api.entity.MinecraftPlayer;
import de.bauhd.minecraft.server.api.entity.player.GameProfile;
import de.bauhd.minecraft.server.api.entity.player.Player;
import de.bauhd.minecraft.server.api.event.MinecraftEventHandler;
import de.bauhd.minecraft.server.api.event.lifecycle.ServerInitializeEvent;
import de.bauhd.minecraft.server.api.plugin.MinecraftPluginHandler;
import de.bauhd.minecraft.server.api.world.biome.BiomeHandler;
import de.bauhd.minecraft.server.api.world.dimension.DimensionHandler;
import de.bauhd.minecraft.server.json.GameProfileDeserializer;
import de.bauhd.minecraft.server.json.GameProfilePropertyDeserializer;
import de.bauhd.minecraft.server.protocol.Protocol;
import de.bauhd.minecraft.server.protocol.netty.NettyServer;
import de.bauhd.minecraft.server.protocol.packet.Packet;
import de.bauhd.minecraft.server.terminal.SimpleTerminal;
import de.bauhd.minecraft.server.util.BossBarListener;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.serializer.gson.GsonComponentSerializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public final class AdvancedMinecraftServer implements MinecraftServer {

    private static final Logger LOGGER = LogManager.getLogger(AdvancedMinecraftServer.class);

    public static final Gson GSON = new GsonBuilder()
            .registerTypeAdapter(GameProfile.Property.class, new GameProfilePropertyDeserializer())
            .registerTypeAdapter(GameProfile.class, new GameProfileDeserializer())
            .setPrettyPrinting()
            .create();
    public static final Component SUS_COMPONENT = Component.text("Very sus!", NamedTextColor.RED);

    private static final GsonComponentSerializer PRE_1_16_SERIALIZER =
            GsonComponentSerializer.builder()
                    .downsampleColors()
                    .emitLegacyHoverEvent()
                    .build();
    private static final GsonComponentSerializer MODERN_SERIALIZER =
            GsonComponentSerializer.gson();

    static {
        System.setProperty("java.util.logging.manager", "org.apache.logging.log4j.jul.LogManager");
    }

    private boolean running = true;

    private MinecraftConfiguration configuration;
    private KeyPair keyPair;

    private final Map<UUID, MinecraftPlayer> players = new ConcurrentHashMap<>();
    private final DimensionHandler dimensionHandler;
    private final BiomeHandler biomeHandler;
    private final MinecraftPluginHandler pluginHandler;
    private final MinecraftEventHandler eventHandler;
    private final MinecraftCommandHandler commandHandler;
    private final BossBarListener bossBarListener;
    private final NettyServer nettyServer;

    AdvancedMinecraftServer() {
        final var startTime = System.currentTimeMillis();
        final var terminal = new SimpleTerminal(this);

        this.loadConfig();

        if (this.configuration.mode() == MinecraftConfig.Mode.ONLINE) {
            try {
                final var generator = KeyPairGenerator.getInstance("RSA");
                generator.initialize(1024);
                this.keyPair = generator.generateKeyPair();
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            }
        }

        this.dimensionHandler = new MinecraftDimensionHandler();
        this.biomeHandler = null;
        this.pluginHandler = new MinecraftPluginHandler(this);
        this.eventHandler = new MinecraftEventHandler();
        this.commandHandler = new MinecraftCommandHandler(this);
        this.bossBarListener = new BossBarListener();

        this.pluginHandler.loadPlugins();

        this.eventHandler.call(new ServerInitializeEvent()).join();

        this.nettyServer = new NettyServer(this);
        this.nettyServer.connect(this.configuration.host(), this.configuration.port());

        LOGGER.info("Done ({}s)!", new DecimalFormat("#.##")
                .format((System.currentTimeMillis() - startTime) / 1000D));

        Runtime.getRuntime().addShutdownHook(new Thread(() -> this.shutdown(false), "Minecraft Shutdown Thread"));

        terminal.start();

        new Worker(this).start();
    }

    public void shutdown(final boolean runtime) {
        LOGGER.info("Shutdown!");
        this.running = false;

        LogManager.shutdown(false);

        this.nettyServer.close();

        if (runtime) {
            Runtime.getRuntime().exit(0);
        }
    }

    private void loadConfig() {
        final var path = Path.of("config.json");

        try {
            if (Files.notExists(path)) {
                Files.createFile(path);
                this.configuration = new MinecraftConfiguration();
                try (final var writer = Files.newBufferedWriter(path)) {
                    writer.write(GSON.toJson(this.configuration));
                }
            } else {
                try (final var reader = Files.newBufferedReader(path)) {
                    this.configuration = GSON.fromJson(reader, MinecraftConfiguration.class);
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean isRunning() {
        return this.running;
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
    public @NotNull MinecraftPluginHandler getPluginHandler() {
        return this.pluginHandler;
    }

    @Override
    public @NotNull MinecraftEventHandler getEventHandler() {
        return this.eventHandler;
    }

    @Override
    public @NotNull MinecraftCommandHandler getCommandHandler() {
        return this.commandHandler;
    }

    @Override
    public @NotNull Collection<Player> getAllPlayers() {
        return List.copyOf(this.players.values());
    }

    public Collection<MinecraftPlayer> getMinecraftPlayers() {
        return this.players.values();
    }

    @Override
    public int getPlayerCount() {
        return this.players.size();
    }

    @Override
    public @Nullable Player getPlayer(@NotNull UUID uniqueId) {
        return null;
    }

    public MinecraftConfiguration getConfiguration() {
        return this.configuration;
    }

    public KeyPair getKeyPair() {
        return this.keyPair;
    }

    public BossBarListener getBossBarListener() {
        return this.bossBarListener;
    }

    public void sendAll(final Packet packet) {
        this.players.values().forEach(player -> player.send(packet));
    }

    public void addPlayer(final MinecraftPlayer player) {
        this.players.put(player.getUniqueId(), player);
    }

    public void removePlayer(final UUID uniqueId) {
        this.players.remove(uniqueId);
    }

    public static GsonComponentSerializer getGsonSerializer(final Protocol.Version version) {
        return version.newerOr(Protocol.Version.MINECRAFT_1_16) ? MODERN_SERIALIZER : PRE_1_16_SERIALIZER;
    }
}
