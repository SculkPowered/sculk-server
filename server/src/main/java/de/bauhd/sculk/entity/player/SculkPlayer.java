package de.bauhd.sculk.entity.player;

import static de.bauhd.sculk.util.CoordinateUtil.chunkCoordinate;

import de.bauhd.sculk.SculkServer;
import de.bauhd.sculk.container.Container;
import de.bauhd.sculk.container.MineInventory;
import de.bauhd.sculk.container.SculkContainer;
import de.bauhd.sculk.container.item.ItemStack;
import de.bauhd.sculk.entity.AbstractLivingEntity;
import de.bauhd.sculk.entity.EntityType;
import de.bauhd.sculk.protocol.SculkConnection;
import de.bauhd.sculk.protocol.packet.Packet;
import de.bauhd.sculk.protocol.packet.login.Disconnect;
import de.bauhd.sculk.protocol.packet.play.ActionBar;
import de.bauhd.sculk.protocol.packet.play.EntityMetadata;
import de.bauhd.sculk.protocol.packet.play.Equipment;
import de.bauhd.sculk.protocol.packet.play.GameEvent;
import de.bauhd.sculk.protocol.packet.play.HeldItem;
import de.bauhd.sculk.protocol.packet.play.KeepAlive;
import de.bauhd.sculk.protocol.packet.play.PlayerAbilities;
import de.bauhd.sculk.protocol.packet.play.PluginMessage;
import de.bauhd.sculk.protocol.packet.play.Respawn;
import de.bauhd.sculk.protocol.packet.play.SpawnPlayer;
import de.bauhd.sculk.protocol.packet.play.SynchronizePlayerPosition;
import de.bauhd.sculk.protocol.packet.play.SystemChatMessage;
import de.bauhd.sculk.protocol.packet.play.TabListHeaderFooter;
import de.bauhd.sculk.protocol.packet.play.container.ContainerContent;
import de.bauhd.sculk.protocol.packet.play.container.OpenScreen;
import de.bauhd.sculk.protocol.packet.play.title.Subtitle;
import de.bauhd.sculk.protocol.packet.play.title.TitleAnimationTimes;
import de.bauhd.sculk.world.Position;
import de.bauhd.sculk.world.World;
import java.net.SocketAddress;
import java.util.HashMap;
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
    this.send(new HeldItem((short) slot));
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
    this.connection.calculateChunks(position, position, false, false);
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
      this.send(new de.bauhd.sculk.protocol.packet.play.title.Title((Component) value));
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
  public void addViewer(@NotNull Player player) {
    if (player != this) {
      final var sculkPlayer = (SculkPlayer) player;
      sculkPlayer.send(new SpawnPlayer(this));
      sculkPlayer.send(new EntityMetadata(this.getId(), this.metadata.entries()));
      final var inventory = this.getInventory();
      final var equipment = new HashMap<Integer, ItemStack>();
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
        sculkPlayer.send(new Equipment(this.getId(), equipment));
      }
      this.viewers.add(sculkPlayer);
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
    this.spawned = true;
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
