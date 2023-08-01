package de.bauhd.minecraft.server;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import de.bauhd.minecraft.server.command.CommandSource;
import de.bauhd.minecraft.server.command.MineCommandHandler;
import de.bauhd.minecraft.server.command.defaults.InfoCommand;
import de.bauhd.minecraft.server.command.defaults.ShutdownCommand;
import de.bauhd.minecraft.server.container.*;
import de.bauhd.minecraft.server.damage.DamageType;
import de.bauhd.minecraft.server.entity.Entity;
import de.bauhd.minecraft.server.entity.EntityClassToSupplierMap;
import de.bauhd.minecraft.server.entity.player.GameProfile;
import de.bauhd.minecraft.server.entity.player.MinecraftPlayer;
import de.bauhd.minecraft.server.entity.player.Player;
import de.bauhd.minecraft.server.event.MineEventHandler;
import de.bauhd.minecraft.server.event.lifecycle.ServerInitializeEvent;
import de.bauhd.minecraft.server.event.lifecycle.ServerShutdownEvent;
import de.bauhd.minecraft.server.json.GameProfileDeserializer;
import de.bauhd.minecraft.server.json.GameProfilePropertyDeserializer;
import de.bauhd.minecraft.server.plugin.MinePluginHandler;
import de.bauhd.minecraft.server.protocol.MineConnection;
import de.bauhd.minecraft.server.protocol.netty.NettyServer;
import de.bauhd.minecraft.server.protocol.packet.Packet;
import de.bauhd.minecraft.server.protocol.packet.login.CompressionPacket;
import de.bauhd.minecraft.server.registry.Registry;
import de.bauhd.minecraft.server.registry.SimpleRegistry;
import de.bauhd.minecraft.server.terminal.SimpleTerminal;
import de.bauhd.minecraft.server.util.BossBarListener;
import de.bauhd.minecraft.server.world.MinecraftWorld;
import de.bauhd.minecraft.server.world.World;
import de.bauhd.minecraft.server.world.biome.Biome;
import de.bauhd.minecraft.server.world.block.BlockParent;
import de.bauhd.minecraft.server.world.block.BlockState;
import de.bauhd.minecraft.server.world.block.MineBlockState;
import de.bauhd.minecraft.server.world.chunk.loader.AnvilLoader;
import de.bauhd.minecraft.server.world.chunk.loader.ChunkLoader;
import de.bauhd.minecraft.server.world.chunk.loader.DefaultChunkLoader;
import de.bauhd.minecraft.server.world.dimension.Dimension;
import de.bauhd.minecraft.server.world.dimension.DimensionRegistry;
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

public final class AdvancedMinecraftServer implements MinecraftServer {

    private static final Logger LOGGER = LogManager.getLogger(AdvancedMinecraftServer.class);

    public static final Gson GSON = new GsonBuilder()
            .registerTypeAdapter(GameProfile.Property.class, new GameProfilePropertyDeserializer())
            .registerTypeAdapter(GameProfile.class, new GameProfileDeserializer())
            .setPrettyPrinting()
            .create();

    private boolean running = true;

    private MinecraftConfiguration configuration;
    private KeyPair keyPair;

    private final Map<UUID, MinecraftPlayer> players = new ConcurrentHashMap<>();
    private final Map<String, MinecraftWorld> worlds = new ConcurrentHashMap<>();
    private final SimpleTerminal terminal;
    private final Registry<Dimension> dimensionRegistry;
    private final Registry<Biome> biomeRegistry;
    private final Registry<DamageType> damageTypeRegistry;
    private final MinePluginHandler pluginHandler;
    private final MineEventHandler eventHandler;
    private final MineCommandHandler commandHandler;
    private final BossBarListener bossBarListener;
    private final NettyServer nettyServer;
    @SuppressWarnings("MismatchedQueryAndUpdateOfCollection")
    private final EntityClassToSupplierMap entities = new EntityClassToSupplierMap();

    AdvancedMinecraftServer() {
        final var startTime = System.currentTimeMillis();
        this.terminal = new SimpleTerminal(this);
        this.terminal.setupStreams();

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
        this.pluginHandler = new MinePluginHandler(this);
        this.eventHandler = new MineEventHandler();
        this.commandHandler = (MineCommandHandler) new MineCommandHandler(this) // register defaults
                .register(new ShutdownCommand(this))
                .register(new InfoCommand());
        this.bossBarListener = new BossBarListener();

        this.loadBlocks();

        this.pluginHandler.loadPlugins();

        this.eventHandler.call(new ServerInitializeEvent()).join();

        this.nettyServer = new NettyServer(this);
        this.nettyServer.connect(this.configuration.host(), this.configuration.port());

        LOGGER.info("Done ({}s)!", new DecimalFormat("#.##")
                .format((System.currentTimeMillis() - startTime) / 1000D));

        Runtime.getRuntime().addShutdownHook(new Thread(() -> this.shutdown(false), "Minecraft Shutdown Thread"));

        if (this.configuration.compressionThreshold() != -1) {
            MineConnection.COMPRESSION_PACKET = new CompressionPacket(this.configuration.compressionThreshold());
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

    // I don't like this solution so much...
    private void loadBlocks() {
        try (final var resource = this.getClass().getClassLoader().getResourceAsStream("registries/blocks.json");
             final var reader = new InputStreamReader(Objects.requireNonNull(resource))) {
            final var byName = new HashMap<String, BlockState>();
            final var byId = new HashMap<Integer, BlockState>();
            final var stringJsonMap = TypeToken.getParameterized(Map.class, String.class, JsonObject.class).getType();
            final var stringStringMap = TypeToken.getParameterized(Map.class, String.class, String.class).getType();
            final var jsonArray = TypeToken.getArray(JsonObject.class).getType();
            final Map<String, JsonObject> map = GSON.fromJson(reader, stringJsonMap);
            for (final var entry : map.entrySet()) {
                final var key = entry.getKey();
                final var block = new BlockParent(key);
                final JsonObject[] states = GSON.fromJson(entry.getValue().get("states"), jsonArray);
                final var stateArray = new MineBlockState[states.length];
                for (var i = 0; i < states.length; i++) {
                    final var state = states[i];
                    final var id = state.get("id").getAsInt();
                    Map<String, String> properties;
                    if (state.has("properties")) {
                        properties = Map.copyOf(GSON.fromJson(state.get("properties"), stringStringMap));
                    } else {
                        properties = Map.of();
                    }
                    final var blockState = new MineBlockState(block, id, properties);
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
    public @NotNull MinePluginHandler getPluginHandler() {
        return this.pluginHandler;
    }

    @Override
    public @NotNull MineEventHandler getEventHandler() {
        return this.eventHandler;
    }

    @Override
    public @NotNull MineCommandHandler getCommandHandler() {
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
        final var mcWorld = (MinecraftWorld) world;
        mcWorld.load();
        this.worlds.put(world.getName(), mcWorld);
    }

    private @NotNull World createWorld(final @NotNull World.Builder builder, @NotNull ChunkLoader chunkLoader) {
        final var name = Objects.requireNonNull(builder.name(), "a world requires a name");
        final var world = new MinecraftWorld(this, name, builder.dimension(), chunkLoader,
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
        ((MinecraftWorld) world).unload(consumer);
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
            case ANVIL -> new MineAnvilContainer(title);
            case BEACON -> new MineBeaconContainer(title);
            case BLAST_FURNACE, FURNACE, SMOKER -> new MineFurnaceContainer(type, title);
            case BREWING_STAND -> new MineBrewingStandContainer(title);
            case ENCHANTMENT -> new MineEnchantingTableContainer(title);
            case LOOM -> new MineLoomContainer(title);
            case STONECUTTER -> new MineStonecutterContainer(title);
            default -> throw new IllegalStateException("Unexpected value: " + type);
        };
    }

    @Override
    public @NotNull CommandSource getConsoleCommandSource() {
        return this.terminal;
    }

    @Override
    public @NotNull MinecraftConfiguration getConfig() {
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

    public void addPlayer(final MinecraftPlayer player) {
        this.players.put(player.getUniqueId(), player);
    }

    public void removePlayer(final UUID uniqueId) {
        this.players.remove(uniqueId);
    }
}
