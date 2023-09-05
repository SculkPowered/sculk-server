package de.bauhd.sculk;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.velocitypowered.natives.util.Natives;
import de.bauhd.sculk.command.CommandSource;
import de.bauhd.sculk.command.SculkCommandHandler;
import de.bauhd.sculk.command.defaults.InfoCommand;
import de.bauhd.sculk.command.defaults.ShutdownCommand;
import de.bauhd.sculk.container.*;
import de.bauhd.sculk.damage.DamageType;
import de.bauhd.sculk.entity.Entity;
import de.bauhd.sculk.entity.EntityClassToSupplierMap;
import de.bauhd.sculk.entity.player.GameProfile;
import de.bauhd.sculk.entity.player.SculkPlayer;
import de.bauhd.sculk.entity.player.Player;
import de.bauhd.sculk.event.SculkEventHandler;
import de.bauhd.sculk.event.lifecycle.ServerInitializeEvent;
import de.bauhd.sculk.event.lifecycle.ServerShutdownEvent;
import de.bauhd.sculk.json.GameProfileDeserializer;
import de.bauhd.sculk.json.GameProfilePropertyDeserializer;
import de.bauhd.sculk.plugin.SculkPluginHandler;
import de.bauhd.sculk.protocol.SculkConnection;
import de.bauhd.sculk.protocol.netty.NettyServer;
import de.bauhd.sculk.protocol.packet.Packet;
import de.bauhd.sculk.protocol.packet.login.CompressionPacket;
import de.bauhd.sculk.registry.Registry;
import de.bauhd.sculk.registry.SimpleRegistry;
import de.bauhd.sculk.team.SculkTeamHandler;
import de.bauhd.sculk.terminal.SimpleTerminal;
import de.bauhd.sculk.util.BossBarListener;
import de.bauhd.sculk.world.SculkWorld;
import de.bauhd.sculk.world.World;
import de.bauhd.sculk.world.biome.Biome;
import de.bauhd.sculk.world.block.BlockParent;
import de.bauhd.sculk.world.block.BlockState;
import de.bauhd.sculk.world.block.SculkBlockState;
import de.bauhd.sculk.world.chunk.loader.AnvilLoader;
import de.bauhd.sculk.world.chunk.loader.ChunkLoader;
import de.bauhd.sculk.world.chunk.loader.DefaultChunkLoader;
import de.bauhd.sculk.world.dimension.Dimension;
import de.bauhd.sculk.world.dimension.DimensionRegistry;
import io.netty.channel.epoll.Epoll;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;

public final class SculkServer implements MinecraftServer {

    private static final Logger LOGGER = LogManager.getLogger(SculkServer.class);

    public static final Gson GSON = new GsonBuilder()
            .registerTypeAdapter(GameProfile.Property.class, new GameProfilePropertyDeserializer())
            .registerTypeAdapter(GameProfile.class, new GameProfileDeserializer())
            .setPrettyPrinting()
            .create();
    public static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("#.##");

    private boolean running = true;

    private SculkConfiguration configuration;
    private KeyPair keyPair;

    private final Map<UUID, SculkPlayer> players = new ConcurrentHashMap<>();
    private final Map<String, SculkWorld> worlds = new ConcurrentHashMap<>();
    private final SimpleTerminal terminal;
    private final Registry<Dimension> dimensionRegistry;
    private final Registry<Biome> biomeRegistry;
    private final Registry<DamageType> damageTypeRegistry;
    private final SculkPluginHandler pluginHandler;
    private final SculkEventHandler eventHandler;
    private final SculkCommandHandler commandHandler;
    private final SculkTeamHandler teamHandler;
    private final BossBarListener bossBarListener;
    private final NettyServer nettyServer;
    @SuppressWarnings("MismatchedQueryAndUpdateOfCollection")
    private final EntityClassToSupplierMap entities = new EntityClassToSupplierMap();

    SculkServer() {
        final var startTime = System.currentTimeMillis();
        this.terminal = new SimpleTerminal(this);
        this.terminal.setupStreams();
        final var packge = this.getClass().getPackage();
        LOGGER.info("Booting up {} {}...", packge.getImplementationTitle(), packge.getImplementationVersion());

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

        LOGGER.info("Connections will use {} channels, {} compression, {} ciphers",
                Epoll.isAvailable() ? "epoll" : "nio", Natives.compress.getLoadedVariant(), Natives.cipher.getLoadedVariant());

        this.dimensionRegistry = new DimensionRegistry();
        this.biomeRegistry = new SimpleRegistry<>("minecraft:worldgen/biome", Biome.PLAINS);
        this.damageTypeRegistry = new SimpleRegistry<>("minecraft:damage_type", DamageType.OUT_OF_WORLD);
        try { // load DamageTypes
            for (final var field : DamageType.class.getDeclaredFields()) {
                if (field.getType() == DamageType.class) {
                    this.damageTypeRegistry.register((DamageType) field.get(null));
                }
            }
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        this.pluginHandler = new SculkPluginHandler(this);
        this.eventHandler = new SculkEventHandler();
        this.commandHandler = (SculkCommandHandler) new SculkCommandHandler(this) // register defaults
                .register(ShutdownCommand.get(this))
                .register(InfoCommand.get());
        this.teamHandler = new SculkTeamHandler(this);
        this.bossBarListener = new BossBarListener();

        this.loadBlocks();

        this.pluginHandler.loadPlugins();

        this.eventHandler.call(new ServerInitializeEvent()).join();

        this.nettyServer = new NettyServer(this);
        this.nettyServer.connect(this.configuration.host(), this.configuration.port());

        LOGGER.info("Done ({}s)!", DECIMAL_FORMAT
                .format((System.currentTimeMillis() - startTime) / 1000D));

        Runtime.getRuntime().addShutdownHook(new Thread(() -> this.shutdown(false), "Sculk Shutdown Thread"));

        if (this.configuration.compressionThreshold() != -1) {
            SculkConnection.COMPRESSION_PACKET = new CompressionPacket(this.configuration.compressionThreshold());
        }

        this.terminal.start();
    }

    public void shutdown(final boolean runtime) {
        LOGGER.info("Shutting down...");
        this.running = false;

        try {
            this.nettyServer.close();

            final var component = Component.text("Shutting down...", NamedTextColor.RED);
            for (final var player : this.players.values()) {
                player.disconnect(component);
            }

            for (final var world : this.worlds.values()) {
                world.setAlive(false);
            }

            this.eventHandler.call(new ServerShutdownEvent()).join();

            if (!this.eventHandler.shutdown()) {
                LOGGER.info("Something took over 10 seconds to shutdown!");
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        LogManager.shutdown(false);

        if (runtime) {
            Runtime.getRuntime().exit(0);
        }
    }

    private void loadConfig() {
        final var path = Path.of("config.json");

        try {
            if (Files.notExists(path)) {
                Files.createFile(path);
                this.configuration = new SculkConfiguration();
                try (final var writer = Files.newBufferedWriter(path)) {
                    writer.write(GSON.toJson(this.configuration));
                }
            } else {
                try (final var reader = Files.newBufferedReader(path)) {
                    this.configuration = GSON.fromJson(reader, SculkConfiguration.class);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // I don't like this solution so much...
    private void loadBlocks() {
        try (final var resource = this.getClass().getClassLoader().getResourceAsStream("registries/blocks.json");
             final var reader = new InputStreamReader(Objects.requireNonNull(resource))) {
            final var stringJsonMap = TypeToken.getParameterized(Map.class, String.class, JsonObject.class).getType();
            final var stringStringMap = TypeToken.getParameterized(Map.class, String.class, String.class).getType();
            final var jsonArray = TypeToken.getArray(JsonObject.class).getType();
            final Map<String, JsonObject> map = GSON.fromJson(reader, stringJsonMap);
            final var byName = new HashMap<String, BlockState>(map.size());
            final var byId = new HashMap<Integer, BlockState>(map.size());
            for (final var entry : map.entrySet()) {
                final var key = entry.getKey();
                final var block = new BlockParent(key);
                final JsonObject[] states = GSON.fromJson(entry.getValue().get("states"), jsonArray);
                final var stateArray = new SculkBlockState[states.length];
                for (var i = 0; i < states.length; i++) {
                    final var state = states[i];
                    final var id = state.get("id").getAsInt();
                    Map<String, String> properties = (state.has("properties") ?
                            Map.copyOf(GSON.fromJson(state.get("properties"), stringStringMap)) : Map.of());
                    final var blockState = new SculkBlockState(block, id, properties);
                    byId.put(blockState.getId(), blockState);
                    if (state.has("default") && state.get("default").getAsBoolean()) {
                        byName.put(key, blockState);
                    }
                    stateArray[i] = blockState;
                }
                block.setStates(stateArray);
            }
            BlockParent.set(byName, byId);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean isRunning() {
        return this.running;
    }

    @Override
    public @NotNull Registry<Dimension> getDimensionRegistry() {
        return this.dimensionRegistry;
    }

    @Override
    public @NotNull Registry<Biome> getBiomeRegistry() {
        return this.biomeRegistry;
    }

    @Override
    public @NotNull Registry<DamageType> getDamageTypeRegistry() {
        return this.damageTypeRegistry;
    }

    @Override
    public @NotNull SculkPluginHandler getPluginHandler() {
        return this.pluginHandler;
    }

    @Override
    public @NotNull SculkEventHandler getEventHandler() {
        return this.eventHandler;
    }

    @Override
    public @NotNull SculkCommandHandler getCommandHandler() {
        return this.commandHandler;
    }

    @Override
    public @NotNull SculkTeamHandler getTeamHandler() {
        return this.teamHandler;
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
        return this.players.get(uniqueId);
    }

    @Override
    public @NotNull World createWorld(World.@NotNull Builder builder) {
        return this.createWorld(builder, new DefaultChunkLoader(builder.generator()));
    }

    @Override
    public @NotNull World loadWorld(World.@NotNull Builder builder, @NotNull Path path) {
        return this.createWorld(builder, new AnvilLoader(this, builder.generator(), path));
    }

    @Override
    public void loadWorld(@NotNull World world) {
        final var mcWorld = (SculkWorld) world;
        mcWorld.load();
        this.worlds.put(world.getName(), mcWorld);
    }

    private @NotNull World createWorld(final @NotNull World.Builder builder, @NotNull ChunkLoader chunkLoader) {
        final var name = Objects.requireNonNull(builder.name(), "a world requires a name");
        final var world = new SculkWorld(this, name, builder.dimension(), chunkLoader,
                builder.spawnPosition(), builder.defaultGameMode());
        this.worlds.put(name, world);
        return world;
    }

    @Override
    public @Nullable World getWorld(@NotNull String name) {
        return this.worlds.get(name);
    }

    @Override
    public void unloadWorld(@NotNull World world, @NotNull Consumer<Player> consumer) {
        ((SculkWorld) world).unload(consumer);
        this.worlds.remove(world.getName());
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T extends Entity> T createEntity(@NotNull Class<T> clazz) {
        final var supplier = this.entities.get(clazz);
        if (supplier == null) {
            throw new NullPointerException("No supplier for class" + clazz + " found!");
        }
        return (T) supplier.get();
    }

    @Override
    public @NotNull Container createContainer(Container.@NotNull Type type, @NotNull Component title) {
        return switch (type) {
            case GENERIC_9x1, GENERIC_9x2, GENERIC_9x3, GENERIC_9x6, GENERIC_9x5, GENERIC_9x4, GENERIC_3x3,
                    CRAFTING, GRINDSTONE, HOPPER, LECTERN, MERCHANT, SHULKER_BOX, SMITHING, CARTOGRAPHY ->
                    new GenericContainer(type, title);
            case ANVIL -> new SculkAnvilContainer(title);
            case BEACON -> new SculkBeaconContainer(title);
            case BLAST_FURNACE, FURNACE, SMOKER -> new SculkFurnaceContainer(type, title);
            case BREWING_STAND -> new SculkBrewingStandContainer(title);
            case ENCHANTMENT -> new SculkEnchantingTableContainer(title);
            case LOOM -> new SculkLoomContainer(title);
            case STONECUTTER -> new SculkStonecutterContainer(title);
            default -> throw new IllegalStateException("Unexpected value: " + type);
        };
    }

    @Override
    public @NotNull CommandSource getConsoleCommandSource() {
        return this.terminal;
    }

    @Override
    public @NotNull SculkConfiguration getConfig() {
        return this.configuration;
    }

    @Override
    public void shutdown() {
        this.shutdown(true);
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

    public void addPlayer(final SculkPlayer player) {
        this.players.put(player.getUniqueId(), player);
    }

    public void removePlayer(final UUID uniqueId) {
        this.players.remove(uniqueId);
    }
}
