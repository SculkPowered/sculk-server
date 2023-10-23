package io.github.sculkpowered.server.entity.player;

import static io.github.sculkpowered.server.util.CoordinateUtil.chunkCoordinate;

import io.github.sculkpowered.server.SculkServer;
import io.github.sculkpowered.server.container.Container;
import io.github.sculkpowered.server.container.MineInventory;
import io.github.sculkpowered.server.container.SculkContainer;
import io.github.sculkpowered.server.container.item.ItemStack;
import io.github.sculkpowered.server.entity.AbstractLivingEntity;
import io.github.sculkpowered.server.entity.EntityType;
import io.github.sculkpowered.server.event.connection.PluginMessageEvent;
import io.github.sculkpowered.server.protocol.SculkConnection;
import io.github.sculkpowered.server.protocol.packet.Packet;
import io.github.sculkpowered.server.protocol.packet.login.Disconnect;
import io.github.sculkpowered.server.protocol.packet.play.ActionBar;
import io.github.sculkpowered.server.protocol.packet.play.ClientInformation;
import io.github.sculkpowered.server.protocol.packet.play.EntityMetadata;
import io.github.sculkpowered.server.protocol.packet.play.Equipment;
import io.github.sculkpowered.server.protocol.packet.play.GameEvent;
import io.github.sculkpowered.server.protocol.packet.play.HeldItem;
import io.github.sculkpowered.server.protocol.packet.play.KeepAlive;
import io.github.sculkpowered.server.protocol.packet.play.PlayerAbilities;
import io.github.sculkpowered.server.protocol.packet.play.PluginMessage;
import io.github.sculkpowered.server.protocol.packet.play.Respawn;
import io.github.sculkpowered.server.protocol.packet.play.SynchronizePlayerPosition;
import io.github.sculkpowered.server.protocol.packet.play.SystemChatMessage;
import io.github.sculkpowered.server.protocol.packet.play.TabListHeaderFooter;
import io.github.sculkpowered.server.protocol.packet.play.chunk.CenterChunk;
import io.github.sculkpowered.server.protocol.packet.play.container.ContainerContent;
import io.github.sculkpowered.server.protocol.packet.play.container.OpenScreen;
import io.github.sculkpowered.server.protocol.packet.play.title.Subtitle;
import io.github.sculkpowered.server.protocol.packet.play.title.TitleAnimationTimes;
import io.github.sculkpowered.server.world.Position;
import io.github.sculkpowered.server.world.World;
import io.github.sculkpowered.server.world.chunk.SculkChunk;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import java.net.SocketAddress;
import java.util.ArrayList;
import net.kyori.adventure.bossbar.BossBar;
import net.kyori.adventure.identity.Identity;
import net.kyori.adventure.permission.PermissionChecker;
import net.kyori.adventure.pointer.Pointers;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.title.Title;
import net.kyori.adventure.title.TitlePart;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class SculkPlayer extends AbstractLivingEntity implements Player {

  private final Pointers pointers = Pointers.builder()
      .withDynamic(Identity.NAME, this::getUsername)
      .withDynamic(Identity.UUID, this::getUniqueId)
      .withDynamic(Identity.DISPLAY_NAME, this::getDisplayName)
      .withDynamic(Identity.LOCALE, () -> this.getSettings().getLocale())
      .withDynamic(PermissionChecker.POINTER, () -> this.permissionChecker)
      .build();

  private final SculkServer server;
  private final SculkConnection connection;
  private final GameProfile profile;
  private final ClientInformationWrapper settings = new ClientInformationWrapper();
  private final MineInventory inventory = new MineInventory(this);
  private SculkContainer container;
  private long lastSendKeepAlive;
  private boolean keepAlivePending;
  private GameMode gameMode = GameMode.SURVIVAL;
  private PermissionChecker permissionChecker;
  private Component displayName;
  public int heldItem;
  public boolean flying;
  private boolean allowedFlight;
  private float flyingSpeed = 0.05F;
  private boolean instantBreak;
  private float viewModifier = 0.01F;

  public SculkPlayer(final SculkServer server, final SculkConnection connection,
      final GameProfile profile) {
    super(profile.uniqueId());
    this.server = server;
    this.connection = connection;
    this.profile = profile;
  }

  public @NotNull String getUsername() {
    return this.profile.name();
  }

  @Override
  public @NotNull GameProfile getProfile() {
    return this.profile;
  }

  @Override
  public @NotNull ClientInformationWrapper getSettings() {
    return this.settings;
  }

  @Override
  public @Nullable Component getDisplayName() {
    return this.displayName;
  }

  @Override
  public int getPing() {
    return 1;
  }

  @Override
  public void setDisplayName(@Nullable Component displayName) {
    this.displayName = displayName;
  }

  @Override
  public @NotNull GameMode getGameMode() {
    return this.gameMode;
  }

  @Override
  public void setGameMode(@NotNull GameMode gameMode) {
    if (gameMode != this.gameMode) {
      this.gameMode = gameMode;
      this.send(new GameEvent(3, gameMode.ordinal()));
    }
  }

  @Override
  public void disconnect(@NotNull Component component) {
    this.send(new Disconnect(component));
  }

  @Override
  public @NotNull MineInventory getInventory() {
    return this.inventory;
  }

  @Override
  public @Nullable SculkContainer getOpenedContainer() {
    return this.container;
  }

  public void setContainer(final SculkContainer container) {
    this.container = container;
  }

  @Override
  public void openContainer(@NotNull Container container) {
    this.container = (SculkContainer) container;
    this.container.addViewer(this);
    this.send(new OpenScreen(1, container.getType().ordinal(), container.getTitle()));
    this.send(new ContainerContent((byte) 1, 1, ((SculkContainer) container).items));
    this.container.sendProperties(this);
  }

  @Override
  public int getHeldItemSlot() {
    return this.heldItem;
  }

  @Override
  public void setHeldItemSlot(int slot) {
    this.heldItem = slot;
    this.send(new HeldItem((byte) slot));
  }

  @Override
  public boolean isFlying() {
    return this.flying;
  }

  @Override
  public void setFlying(boolean flying) {
    this.flying = flying;
    this.updateAttributes();
  }

  @Override
  public boolean isAllowFlight() {
    return this.allowedFlight;
  }

  @Override
  public void setAllowFight(boolean allowFight) {
    this.allowedFlight = allowFight;
    if (!this.allowedFlight) {
      this.flying = false;
    }
    this.updateAttributes();
  }

  @Override
  public float getFlyingSpeed() {
    return this.flyingSpeed;
  }

  @Override
  public void setFlyingSpeed(float flyingSpeed) {
    this.flyingSpeed = flyingSpeed;
    this.updateAttributes();
  }

  @Override
  public boolean canInstantBreak() {
    return this.instantBreak || this.gameMode == GameMode.CREATIVE;
  }

  @Override
  public void setInstantBreak(boolean instantBreak) {
    this.instantBreak = instantBreak;
    this.updateAttributes();
  }

  @Override
  public float getViewModifier() {
    return this.viewModifier;
  }

  @Override
  public void setViewModifier(float viewModifier) {
    this.viewModifier = viewModifier;
    this.updateAttributes();
  }

  @Override
  public void sendPluginMessage(@NotNull String channel, byte @NotNull [] bytes) {
    this.send(new PluginMessage(channel, bytes));
  }

  @Override
  public void setWorld(@NotNull World world) {
    this.position = world.getSpawnPosition();
    this.gameMode = world.getDefaultGameMode();
    if (this.world != null) {
      this.connection.forChunksInRange(chunkCoordinate(this.position.x()),
          chunkCoordinate(this.position.z()),
          this.settings.getViewDistance(),
          (x, z) -> this.world.getChunk(x, z).entities().remove(this));
    }
    super.setWorld(world);
    this.send(
        new Respawn(world.getDimension().name(), world.getName(), 0, this.gameMode, (byte) 3));
    this.setPosition(world.getSpawnPosition());
    this.calculateChunks(this.position, this.position, false, false);
  }

  @Override
  public void teleport(@NotNull Position position) {
    this.connection.send(new SynchronizePlayerPosition(position));
    super.teleport(position);
  }

  @Override
  public void sendMessage(@NotNull Component message) {
    this.send(new SystemChatMessage(message, false));
  }

  @Override
  public void sendActionBar(@NotNull Component message) {
    this.send(new ActionBar(message));
  }

  @Override
  public void sendPlayerListHeaderAndFooter(@NotNull Component header, @NotNull Component footer) {
    this.send(new TabListHeaderFooter(header, footer));
  }

  @Override
  public <T> void sendTitlePart(@NotNull TitlePart<T> part, @NotNull T value) {
    if (part == TitlePart.TITLE) {
      this.send(new io.github.sculkpowered.server.protocol.packet.play.title.Title((Component) value));
    } else if (part == TitlePart.SUBTITLE) {
      this.send(new Subtitle((Component) value));
    } else if (part == TitlePart.TIMES) {
      final var times = (Title.Times) value;

      this.send(new TitleAnimationTimes((int) (times.fadeIn().toMillis() / 50),
          (int) (times.stay().toMillis() / 50),
          (int) (times.fadeOut().toMillis() / 50)));
    }
  }

  @Override
  public void showBossBar(@NotNull BossBar bar) {
    this.server.getBossBarListener().showBossBar(this, bar);
  }

  @Override
  public void hideBossBar(@NotNull BossBar bar) {
    this.server.getBossBarListener().hideBossBar(this, bar);
  }

  @NotNull
  @Override
  public Pointers pointers() {
    return this.pointers;
  }

  @Override
  public @NotNull EntityType getType() {
    return EntityType.PLAYER;
  }

  @Override
  public boolean addViewer(@NotNull Player player) {
    if (player != this) {
      final var added = super.addViewer(player);
      if (added) {
        final var sculkPlayer = (SculkPlayer) player;
        sculkPlayer.send(new EntityMetadata(this.getId(), this.metadata.entries()));
        final var inventory = this.getInventory();
        final var equipment = new Int2ObjectOpenHashMap<ItemStack>();
        if (!inventory.getItemInMainHand().isEmpty()) {
          equipment.put(0, inventory.getItemInMainHand());
        } else if (!inventory.getItemInOffHand().isEmpty()) {
          equipment.put(1, inventory.getItemInOffHand());
        } else if (!inventory.getBoots().isEmpty()) {
          equipment.put(2, inventory.getBoots());
        } else if (!inventory.getLeggings().isEmpty()) {
          equipment.put(3, inventory.getLeggings());
        } else if (!inventory.getChestplate().isEmpty()) {
          equipment.put(4, inventory.getChestplate());
        } else if (!inventory.getHelmet().isEmpty()) {
          equipment.put(5, inventory.getHelmet());
        }
        if (!equipment.isEmpty()) {
          sculkPlayer.send(new Equipment(this.id, equipment));
        }
      }
      return added;
    } else {
      return false;
    }
  }

  @Override
  public void tick() {
    super.tick();
    final var time = System.currentTimeMillis();

    final var elapsedTime = System.currentTimeMillis() - this.lastSendKeepAlive;
    if (this.keepAlivePending) {
      if (elapsedTime > 30000) { // disconnect after 30 seconds
        this.disconnect(Component.translatable("disconnect.timeout"));
      }
    } else {
      if (elapsedTime > 15000) { // send all 15 seconds
        this.keepAlivePending = true;
        this.lastSendKeepAlive = time;
        this.send(new KeepAlive(time));
      }
    }
  }

  @Override
  public int getProtocolVersion() {
    return this.connection.getProtocolVersion();
  }

  @Override
  public SocketAddress getAddress() {
    return this.connection.getAddress();
  }

  @Override
  public boolean hasPermission(@NotNull String permission) {
    return this.permissionChecker.test(permission);
  }

  public void init(final GameMode gameMode, final Position position, final World world,
      final PermissionChecker permissionChecker) {
    this.gameMode = gameMode;
    this.position = position;
    super.setWorld(world);
    this.permissionChecker = permissionChecker;
  }

  public void calculateChunks(final Position from, final Position to) {
    this.calculateChunks(from, to, true, true);
  }

  public void calculateChunks(final Position from, final Position to, boolean check,
      boolean checkAlreadyLoaded) {
    final var viewDistance = this.getSettings().getViewDistance();
    this.calculateChunks(from, to, check, checkAlreadyLoaded, viewDistance, viewDistance);
  }

  public void calculateChunks(final Position from, final Position to,
      boolean check, boolean checkAlreadyLoaded,
      int range, int oldRange) {
    final var fromChunkX = chunkCoordinate(from.x());
    final var fromChunkZ = chunkCoordinate(from.z());
    final var chunkX = chunkCoordinate(to.x());
    final var chunkZ = chunkCoordinate(to.z());
    if (check) {
      if (fromChunkX == chunkX && fromChunkZ == chunkZ) {
        return;
      }
      this.send(new CenterChunk(chunkX, chunkZ));
    }
    final var chunks = new ArrayList<SculkChunk>((range * 2 + 1) * (range * 2 + 1));
    this.connection.forChunksInRange(chunkX, chunkZ, range, (x, z) -> {
      final var chunk = world.getChunk(x, z);
      chunks.add(chunk);
      chunk.viewers().add(this); // new in range
      for (final var entity : chunk.entities()) {
        if (entity == this) {
          continue;
        }
        if (entity instanceof Player viewer) {
          this.addViewer(viewer);
        }
        entity.addViewer(this);
      }
    });
    if (checkAlreadyLoaded) {
      this.connection.forChunksInRange(fromChunkX, fromChunkZ, oldRange, (x, z) -> {
        final var chunk = world.getChunk(x, z);
        if (!chunks.remove(chunk)) {
          chunk.viewers().remove(this); // chunk not in range
          for (final var entity : chunk.entities()) {
            entity.removeViewer(this);
          }
        }
      });
    }
    for (final var chunk : chunks) { // send all new chunks
      chunk.send(this);
    }
  }

  public void handleClientInformation(final ClientInformation clientInformation) {
    final var settings = this.settings;
    final var old = settings.clientInformation();
    if (settings.isDefault() || old.skinParts() != clientInformation.skinParts()) {
      this.metadata.setByte(17, (byte) clientInformation.skinParts());
    }
    if (settings.isDefault() || old.mainHand() != clientInformation.mainHand()) {
      this.metadata.setByte(18, (byte) clientInformation.mainHand().ordinal());
    }
    if (old.viewDistance() != clientInformation.viewDistance() && this.world != null) {
      this.calculateChunks(this.position, this.position, false, true,
          clientInformation.viewDistance(), old.viewDistance());
    }
    this.settings.setClientInformation(clientInformation);
  }

  public void handlePluginMessage(final PluginMessage pluginMessage) {
    this.server.getEventHandler().call(
        new PluginMessageEvent(this, pluginMessage.identifier(), pluginMessage.data()));
  }

  public void send(final Packet packet) {
    this.connection.send(packet);
  }

  public void sendViewersAndSelf(final Packet packet) {
    this.send(packet);
    this.sendViewers(packet);
  }

  public void setKeepAlivePending(boolean pending) {
    this.keepAlivePending = pending;
  }

  private void updateAttributes() {
    var flags = (byte) 0;
    if (this.flying) {
      flags |= 0x02;
    }
    if (this.allowedFlight) {
      flags |= 0x04;
    }
    if (this.instantBreak) {
      flags |= 0x08;
    }
    this.send(new PlayerAbilities(flags, this.flyingSpeed, this.viewModifier));
  }
}