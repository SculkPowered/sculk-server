package io.github.sculkpowered.server;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.velocitypowered.natives.util.Natives;
import io.github.sculkpowered.server.adventure.BossBarListener;
import io.github.sculkpowered.server.command.CommandSource;
import io.github.sculkpowered.server.command.SculkCommandHandler;
import io.github.sculkpowered.server.command.defaults.InfoCommand;
import io.github.sculkpowered.server.command.defaults.ShutdownCommand;
import io.github.sculkpowered.server.container.Container;
import io.github.sculkpowered.server.container.GenericContainer;
import io.github.sculkpowered.server.container.SculkAnvilContainer;
import io.github.sculkpowered.server.container.SculkBeaconContainer;
import io.github.sculkpowered.server.container.SculkBrewingStandContainer;
import io.github.sculkpowered.server.container.SculkEnchantingTableContainer;
import io.github.sculkpowered.server.container.SculkFurnaceContainer;
import io.github.sculkpowered.server.container.SculkLoomContainer;
import io.github.sculkpowered.server.container.SculkStonecutterContainer;
import io.github.sculkpowered.server.container.item.Material;
import io.github.sculkpowered.server.damage.DamageType;
import io.github.sculkpowered.server.damage.DamageTypeRegistry;
import io.github.sculkpowered.server.entity.Entity;
import io.github.sculkpowered.server.entity.EntityClassToSupplierMap;
import io.github.sculkpowered.server.entity.player.GameProfile;
import io.github.sculkpowered.server.entity.player.GameProfile.Property;
import io.github.sculkpowered.server.entity.player.Player;
import io.github.sculkpowered.server.entity.player.SculkPlayer;
import io.github.sculkpowered.server.event.SculkEventHandler;
import io.github.sculkpowered.server.event.lifecycle.ServerInitializeEvent;
import io.github.sculkpowered.server.event.lifecycle.ServerShutdownEvent;
import io.github.sculkpowered.server.json.GameProfileDeserializer;
import io.github.sculkpowered.server.json.GameProfilePropertyDeserializer;
import io.github.sculkpowered.server.plugin.SculkPluginHandler;
import io.github.sculkpowered.server.protocol.SculkConnection;
import io.github.sculkpowered.server.protocol.netty.NettyServer;
import io.github.sculkpowered.server.protocol.packet.Packet;
import io.github.sculkpowered.server.protocol.packet.login.CompressionPacket;
import io.github.sculkpowered.server.registry.Registry;
import io.github.sculkpowered.server.registry.SimpleRegistry;
import io.github.sculkpowered.server.scheduler.SculkScheduler;
import io.github.sculkpowered.server.team.SculkTeamHandler;
import io.github.sculkpowered.server.terminal.SimpleTerminal;
import io.github.sculkpowered.server.world.SculkWorld;
import io.github.sculkpowered.server.world.SlimeFormat;
import io.github.sculkpowered.server.world.World;
import io.github.sculkpowered.server.world.WorldLoader;
import io.github.sculkpowered.server.world.biome.Biome;
import io.github.sculkpowered.server.world.block.BlockParent;
import io.github.sculkpowered.server.world.chunk.loader.AnvilLoader;
import io.github.sculkpowered.server.world.chunk.loader.ChunkLoader;
import io.github.sculkpowered.server.world.chunk.loader.DefaultChunkLoader;
import io.github.sculkpowered.server.world.dimension.Dimension;
import io.github.sculkpowered.server.world.dimension.DimensionRegistry;
import io.netty.channel.epoll.Epoll;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
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
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class SculkServer implements Server {

  private static final Logger LOGGER = LogManager.getLogger(SculkServer.class);

  public static final Gson GSON = new GsonBuilder()
      .registerTypeAdapter(Property.class, new GameProfilePropertyDeserializer())
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
  private final SculkScheduler scheduler;
  private final BossBarListener bossBarListener;
  private final NettyServer nettyServer;
  private final EntityClassToSupplierMap entities = EntityClassToSupplierMap.get();

  SculkServer() {
    final var startTime = System.currentTimeMillis();
    this.terminal = new SimpleTerminal(this);
    this.terminal.setupStreams();
    final var packge = this.getClass().getPackage();
    LOGGER.info("Booting up {} {}...", packge.getImplementationTitle(),
        packge.getImplementationVersion());

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
        Epoll.isAvailable() ? "epoll" : "nio", Natives.compress.getLoadedVariant(),
        Natives.cipher.getLoadedVariant());

    this.dimensionRegistry = new DimensionRegistry();
    this.biomeRegistry = new SimpleRegistry<>("minecraft:worldgen/biome", Biome.PLAINS);
    this.damageTypeRegistry = DamageTypeRegistry.get();
    this.pluginHandler = new SculkPluginHandler(this);
    this.eventHandler = new SculkEventHandler();
    this.commandHandler = (SculkCommandHandler) new SculkCommandHandler(this) // register defaults
        .register(ShutdownCommand.get(this))
        .register(InfoCommand.get());
    this.teamHandler = new SculkTeamHandler(this);
    this.scheduler = new SculkScheduler();
    this.bossBarListener = new BossBarListener();

    BlockParent.addBlocks();
    this.loadMaterials();

    this.pluginHandler.loadPlugins();

    this.eventHandler.call(new ServerInitializeEvent()).join();

    this.nettyServer = new NettyServer(this);
    this.nettyServer.connect(this.configuration.host(), this.configuration.port());

    LOGGER.info("Done ({}s)!", DECIMAL_FORMAT
        .format((System.currentTimeMillis() - startTime) / 1000D));

    Runtime.getRuntime().addShutdownHook(
        new Thread(() -> this.shutdown(false), "Sculk Shutdown Thread"));

    if (this.configuration.compressionThreshold() != -1) {
      SculkConnection.COMPRESSION_PACKET = new CompressionPacket(
          this.configuration.compressionThreshold());
    }

    this.terminal.start();
  }

  public void shutdown(final boolean runtime) {
    LOGGER.info("Shutting down...");
    this.running = false;

    this.nettyServer.close();

    final var component = Component.text("Shutting down...", NamedTextColor.RED);
    for (final var player : this.players.values()) {
      player.disconnect(component);
    }

    for (final var world : this.worlds.values()) {
      world.setAlive(false);
    }

    this.eventHandler.call(new ServerShutdownEvent()).join();

    final var plugins = this.pluginHandler.plugins();
    final var iterator = plugins.iterator();
    while (iterator.hasNext()) {
      final var plugin = iterator.next();
      if (plugin.hasExecutorService()) {
        plugin.executorService().shutdown();
      } else {
        iterator.remove();
      }
    }
    for (final var plugin : plugins) {
      try {
        //noinspection ResultOfMethodCallIgnored
        plugin.executorService().awaitTermination(10, TimeUnit.SECONDS);
      } catch (InterruptedException e) {
        LOGGER.info("Something took over 10 seconds to shutdown!");
        Thread.currentThread().interrupt();
      }
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

  public boolean isRunning() {
    return this.running;
  }

  @Override
  public @NotNull Registry<Dimension> dimensionRegistry() {
    return this.dimensionRegistry;
  }

  @Override
  public @NotNull Registry<Biome> biomeRegistry() {
    return this.biomeRegistry;
  }

  @Override
  public @NotNull Registry<DamageType> damageTypeRegistry() {
    return this.damageTypeRegistry;
  }

  @Override
  public @NotNull SculkPluginHandler pluginHandler() {
    return this.pluginHandler;
  }

  @Override
  public @NotNull SculkEventHandler eventHandler() {
    return this.eventHandler;
  }

  @Override
  public @NotNull SculkCommandHandler commandHandler() {
    return this.commandHandler;
  }

  @Override
  public @NotNull SculkTeamHandler teamHandler() {
    return this.teamHandler;
  }

  @Override
  public @NotNull SculkScheduler scheduler() {
    return this.scheduler;
  }

  @Override
  public @NotNull Collection<Player> onlinePlayers() {
    return List.copyOf(this.players.values());
  }

  @Override
  public int playerCount() {
    return this.players.size();
  }

  @Override
  public @Nullable Player player(@NotNull UUID uniqueId) {
    return this.players.get(uniqueId);
  }

  @Override
  public @NotNull World createWorld(World.@NotNull Builder builder) {
    final var loader = builder.loader();
    ChunkLoader chunkLoader;
    if (loader instanceof WorldLoader.Anvil anvil) {
      chunkLoader = new AnvilLoader(this, builder.generator(), anvil);
    } else {
      chunkLoader = new DefaultChunkLoader(builder.generator());
    }
    final var world = this.createWorld(builder, chunkLoader);
    if (loader instanceof WorldLoader.Slime slime) {
      SlimeFormat.load(this, (SculkWorld) world, slime);
    }
    return world;
  }

  private @NotNull World createWorld(final @NotNull World.Builder builder,
      @NotNull ChunkLoader chunkLoader) {
    final var name = Objects.requireNonNull(builder.name(), "a world requires a name");
    final var world = new SculkWorld(this, builder, chunkLoader);
    this.worlds.put(name, world);
    return world;
  }

  @Override
  public @Nullable World world(@NotNull String name) {
    return this.worlds.get(name);
  }

  @Override
  public void unloadWorld(@NotNull World world, @NotNull Consumer<Player> consumer) {
    ((SculkWorld) world).unload(consumer);
    this.worlds.remove(world.name());
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
  public @NotNull Container createContainer(Container.@NotNull Type type,
      @NotNull Component title) {
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
  public @NotNull CommandSource consoleCommandSource() {
    return this.terminal;
  }

  @Override
  public @NotNull SculkConfiguration config() {
    return this.configuration;
  }

  @Override
  public void shutdown() {
    this.shutdown(true);
  }

  private void loadMaterials() {
    final var materialArray = Material.values();
    final var materials = new Int2ObjectOpenHashMap<Material>(materialArray.length);
    for (final var material : materialArray) {
      materials.put(material.ordinal(), material);
    }
    Material.setMaterials(materials);
  }

  public KeyPair getKeyPair() {
    return this.keyPair;
  }

  public BossBarListener getBossBarListener() {
    return this.bossBarListener;
  }

  public void sendAll(final Packet packet) {
    for (final var player : this.players.values()) {
      player.send(packet);
    }
  }

  public void addPlayer(final SculkPlayer player) {
    this.players.put(player.uniqueId(), player);
  }

  public void removePlayer(final UUID uniqueId) {
    this.players.remove(uniqueId);
  }
}
