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
import de.bauhd.minecraft.server.api.module.MinecraftModuleHandler;
import de.bauhd.minecraft.server.api.world.biome.BiomeHandler;
import de.bauhd.minecraft.server.api.world.dimension.DimensionHandler;
import de.bauhd.minecraft.server.json.GameProfileDeserializer;
import de.bauhd.minecraft.server.json.GameProfilePropertyDeserializer;
import de.bauhd.minecraft.server.protocol.Protocol;
import de.bauhd.minecraft.server.protocol.netty.NettyServer;
import de.bauhd.minecraft.server.protocol.packet.Packet;
import de.bauhd.minecraft.server.util.BossBarListener;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.serializer.gson.GsonComponentSerializer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public final class AdvancedMinecraftServer implements MinecraftServer {

    private static AdvancedMinecraftServer instance;

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

    private MinecraftConfiguration configuration;
    private KeyPair keyPair;

    private final Map<UUID, MinecraftPlayer> players = new ConcurrentHashMap<>();
    private final DimensionHandler dimensionHandler;
    private final BiomeHandler biomeHandler;
    private final MinecraftModuleHandler moduleHandler;
    private final MinecraftEventHandler eventHandler;
    private final MinecraftCommandHandler commandHandler;
    private final BossBarListener bossBarListener;

    AdvancedMinecraftServer() {
        instance = this;

        this.loadConfig();

        if (this.configuration.mode() == MinecraftConfig.Mode.ONLINE) {
            try {
                final var generator= KeyPairGenerator.getInstance("RSA");
                generator.initialize(1024);
                this.keyPair = generator.generateKeyPair();
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            }
        }

        this.dimensionHandler = new MinecraftDimensionHandler();
        this.biomeHandler = null;
        this.moduleHandler = new MinecraftModuleHandler();
        this.eventHandler = new MinecraftEventHandler();
        this.commandHandler = new MinecraftCommandHandler();
        this.bossBarListener = new BossBarListener();

        this.moduleHandler.loadModules();

        this.eventHandler.call(new ServerInitializeEvent()).join();

        new NettyServer().connect(this.configuration.host(), this.configuration.port());

        new Worker(this).start();
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

    @Override
    public @NotNull Collection<Player> getAllPlayers() {
        return List.copyOf(this.players.values());
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

    public static AdvancedMinecraftServer getInstance() {
        return instance;
    }

    public static GsonComponentSerializer getGsonSerializer(final Protocol.Version version) {
        return version.compare(Protocol.Version.MINECRAFT_1_16) ? MODERN_SERIALIZER : PRE_1_16_SERIALIZER;
    }
}
