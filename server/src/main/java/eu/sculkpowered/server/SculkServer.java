package eu.sculkpowered.server;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.velocitypowered.natives.util.Natives;
import eu.sculkpowered.server.attribute.AttributeRegistry;
import eu.sculkpowered.server.command.CommandSource;
import eu.sculkpowered.server.command.SculkCommandHandler;
import eu.sculkpowered.server.command.defaults.InfoCommand;
import eu.sculkpowered.server.command.defaults.StopCommand;
import eu.sculkpowered.server.container.Container;
import eu.sculkpowered.server.container.GenericContainer;
import eu.sculkpowered.server.container.SculkAnvilContainer;
import eu.sculkpowered.server.container.SculkBeaconContainer;
import eu.sculkpowered.server.container.SculkBrewingStandContainer;
import eu.sculkpowered.server.container.SculkEnchantingTableContainer;
import eu.sculkpowered.server.container.SculkFurnaceContainer;
import eu.sculkpowered.server.container.SculkLoomContainer;
import eu.sculkpowered.server.container.SculkStonecutterContainer;
import eu.sculkpowered.server.container.item.Material;
import eu.sculkpowered.server.container.item.data.DataComponentTypeRegistry;
import eu.sculkpowered.server.damage.DamageTypeRegistry;
import eu.sculkpowered.server.enchantment.Enchantment;
import eu.sculkpowered.server.entity.AbstractEntity;
import eu.sculkpowered.server.entity.Entity;
import eu.sculkpowered.server.entity.EntityClassToSupplierMap;
import eu.sculkpowered.server.entity.player.GameProfile;
import eu.sculkpowered.server.entity.player.GameProfile.Property;
import eu.sculkpowered.server.entity.player.Player;
import eu.sculkpowered.server.entity.player.SculkPlayer;
import eu.sculkpowered.server.event.SculkEventHandler;
import eu.sculkpowered.server.event.lifecycle.ServerInitializeEvent;
import eu.sculkpowered.server.event.lifecycle.ServerShutdownEvent;
import eu.sculkpowered.server.json.GameProfileDeserializer;
import eu.sculkpowered.server.json.GameProfilePropertyDeserializer;
import eu.sculkpowered.server.plugin.SculkPluginHandler;
import eu.sculkpowered.server.potion.MobEffectType;
import eu.sculkpowered.server.potion.PotionType;
import eu.sculkpowered.server.protocol.SculkConnection;
import eu.sculkpowered.server.protocol.netty.NettyServer;
import eu.sculkpowered.server.protocol.packet.ClientboundPacket;
import eu.sculkpowered.server.protocol.packet.clientbound.LoginCompressionPacket;
import eu.sculkpowered.server.registry.EnumRegistry;
import eu.sculkpowered.server.registry.Registries;
import eu.sculkpowered.server.registry.SimpleRegistry;
import eu.sculkpowered.server.scheduler.SculkScheduler;
import eu.sculkpowered.server.scoreboard.DisplaySlot;
import eu.sculkpowered.server.scoreboard.NumberFormat;
import eu.sculkpowered.server.scoreboard.Scoreboard;
import eu.sculkpowered.server.scoreboard.SculkScoreboard;
import eu.sculkpowered.server.team.SculkTeamHandler;
import eu.sculkpowered.server.terminal.SimpleTerminal;
import eu.sculkpowered.server.world.SculkWorld;
import eu.sculkpowered.server.world.SlimeFormat;
import eu.sculkpowered.server.world.World;
import eu.sculkpowered.server.world.WorldLoader;
import eu.sculkpowered.server.world.biome.Biome;
import eu.sculkpowered.server.world.block.BlockRegistry;
import eu.sculkpowered.server.world.chunk.loader.AnvilLoader;
import eu.sculkpowered.server.world.chunk.loader.ChunkLoader;
import eu.sculkpowered.server.world.chunk.loader.DefaultChunkLoader;
import eu.sculkpowered.server.world.dimension.Dimension;
import io.netty.channel.epoll.Epoll;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
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
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Consumer;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.nbt.BinaryTagIO;
import net.kyori.adventure.nbt.BinaryTagIO.Compression;
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

  static {
    // I do not like this, at some point registries would need a rework
    Registries.set(
        new SimpleRegistry<>("minecraft:dimension_type", Dimension.OVERWORLD),
        new SimpleRegistry<>("minecraft:worldgen/biome", Biome.PLAINS),
        DamageTypeRegistry.get(),
        BlockRegistry.get(),
        new EnumRegistry<>("minecraft:item", Material.AIR),
        new EnumRegistry<>("minecraft:enchantment", Enchantment.EFFICIENCY),
        new EnumRegistry<>("minecraft:potion", PotionType.AWKWARD),
        new EnumRegistry<>("minecraft:mob_effect", MobEffectType.SPEED),
        DataComponentTypeRegistry.get(),
        AttributeRegistry.get()
    );
  }

  private final AtomicBoolean running = new AtomicBoolean(true);

  private SculkConfiguration configuration;
  private KeyPair keyPair;

  private final Map<UUID, SculkPlayer> players = new ConcurrentHashMap<>();
  private final Int2ObjectMap<AbstractEntity> entities = new Int2ObjectOpenHashMap<>();
  private final Map<String, SculkWorld> worlds = new ConcurrentHashMap<>();
  private final SimpleTerminal terminal;
  private final SculkPluginHandler pluginHandler;
  private final SculkEventHandler eventHandler;
  private final SculkCommandHandler commandHandler;
  private final SculkTeamHandler teamHandler;
  private final SculkScheduler scheduler;
  private final Worker worker;
  private final NettyServer nettyServer;
  private final EntityClassToSupplierMap entitySupplier = EntityClassToSupplierMap.get();

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

    this.pluginHandler = new SculkPluginHandler(this);
    this.eventHandler = new SculkEventHandler();
    this.commandHandler = (SculkCommandHandler) new SculkCommandHandler(this) // register defaults
        .register(StopCommand.get(this))
        .register(InfoCommand.get());
    this.teamHandler = new SculkTeamHandler(this);
    this.scheduler = new SculkScheduler();

    this.pluginHandler.loadPlugins();
    this.worker = new Worker(this);

    this.eventHandler.call(new ServerInitializeEvent()).join();

    this.nettyServer = new NettyServer(this);
    this.nettyServer.connect(this.configuration.host(), this.configuration.port());

    LOGGER.info("Done ({}s)!", DECIMAL_FORMAT
        .format((System.currentTimeMillis() - startTime) / 1000D));

    Runtime.getRuntime().addShutdownHook(
        new Thread(() -> this.shutdown(false), "Sculk Shutdown Thread"));

    if (this.configuration.compressionThreshold() != -1) {
      SculkConnection.COMPRESSION_PACKET = new LoginCompressionPacket(
          this.configuration.compressionThreshold());
    }

    this.worker.start();
    this.terminal.start();
  }

  public void shutdown(final boolean runtime) {
    if (!this.running.compareAndSet(true, false)) {
      return;
    }

    final Runnable runnable = () -> {
      LOGGER.info("Shutting down...");
      var timedOut = false;
      try {
        this.nettyServer.close();

        final var component = Component.text("Shutting down...", NamedTextColor.RED);
        final var players = this.players.values();
        for (final var player : players) {
          player.disconnect(component);
        }
        for (final var player : players) {
          try {
            player.disconnectFuture().get(10, TimeUnit.SECONDS);
          } catch (TimeoutException e) {
            timedOut = true;
          } catch (ExecutionException e) {
            timedOut = true;
            LOGGER.error("Exception while player disconnection", e);
          }
        }

        for (final var world : this.worlds.values()) {
          world.setAlive(false);
        }

        this.eventHandler.call(new ServerShutdownEvent()).join();
        if (!this.scheduler.shutdown()) {
          timedOut = true;
        }

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
          //noinspection ResultOfMethodCallIgnored
          plugin.executorService().awaitTermination(10, TimeUnit.SECONDS);
        }
      } catch (InterruptedException e) {
        timedOut = true;
        LOGGER.error("Something interrupted", e);
        Thread.currentThread().interrupt();
      }
      if (timedOut) {
        LOGGER.error("Something took over 10 seconds to shutdown.");
      }

      LogManager.shutdown(false);

      if (runtime) {
        System.exit(0);
      }
    };

    if (runtime) {
      new Thread(runnable, "Sculk Shutdown Process").start();
    } else {
      runnable.run();
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
    return this.running.get();
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
    } else if (loader instanceof WorldLoader.Anvil anvil) {
      final var levelData = anvil.path().resolve("level.dat");
      if (Files.exists(levelData)) {
        try {
          world.extraData(BinaryTagIO.reader().read(levelData, Compression.GZIP));
        } catch (IOException e) {
          LOGGER.warn("Error during level data loading: ", e);
        }
      }
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
    final var supplier = this.entitySupplier.get(clazz);
    if (supplier == null) {
      throw new NullPointerException("No supplier for class" + clazz + " found!");
    }
    return (T) supplier.apply(this);
  }

  @Override
  public @Nullable AbstractEntity entity(int id) {
    return this.entities.get(id);
  }

  @Override
  public @NotNull Container createContainer(Container.@NotNull Type type,
      @NotNull Component title) {
    return switch (type) {
      case GENERIC_9x1, GENERIC_9x2, GENERIC_9x3, GENERIC_9x6, GENERIC_9x5, GENERIC_9x4,
           GENERIC_3x3,
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
  public @NotNull Scoreboard createScoreboard(@NotNull String name, @NotNull Component displayName,
      @Nullable NumberFormat numberFormat, @NotNull DisplaySlot displaySlot) {
    return new SculkScoreboard(name, displayName, numberFormat, displaySlot);
  }

  @Override
  public @NotNull Iterable<? extends Audience> audiences() {
    return this.players.values();
  }

  @Override
  public void shutdown() {
    this.shutdown(true);
  }

  public void addTask(final Runnable task) {
    this.worker.addTask(task);
  }

  public Collection<SculkWorld> worlds() {
    return this.worlds.values();
  }

  public KeyPair getKeyPair() {
    return this.keyPair;
  }

  public void sendAll(final ClientboundPacket packet) {
    for (final var player : this.players.values()) {
      player.send(packet);
    }
  }

  public void addPlayer(final SculkPlayer player) {
    this.players.put(player.uniqueId(), player);
  }

  public void removePlayer(final SculkPlayer player) {
    this.players.remove(player.uniqueId());
    this.entities.remove(player.id());
  }

  public void addEntity(final AbstractEntity abstractEntity) {
    this.entities.put(abstractEntity.id(), abstractEntity);
  }
}
