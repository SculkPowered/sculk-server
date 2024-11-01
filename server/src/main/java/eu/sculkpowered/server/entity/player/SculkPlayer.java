package eu.sculkpowered.server.entity.player;

import static eu.sculkpowered.server.util.CoordinateUtil.chunkCoordinate;
import static eu.sculkpowered.server.util.CoordinateUtil.forChunksInRange;

import eu.sculkpowered.server.SculkServer;
import eu.sculkpowered.server.adventure.BossBarProvider;
import eu.sculkpowered.server.adventure.BossBarProvider.Impl;
import eu.sculkpowered.server.attribute.SculkAttributeValue;
import eu.sculkpowered.server.container.Container;
import eu.sculkpowered.server.container.SculkContainer;
import eu.sculkpowered.server.container.SculkInventory;
import eu.sculkpowered.server.container.equipment.EquipmentSlot;
import eu.sculkpowered.server.container.item.ItemStack;
import eu.sculkpowered.server.entity.AbstractLivingEntity;
import eu.sculkpowered.server.entity.Entity;
import eu.sculkpowered.server.entity.EntityTypes;
import eu.sculkpowered.server.event.connection.PluginMessageEvent;
import eu.sculkpowered.server.event.player.PlayerDisconnectEvent;
import eu.sculkpowered.server.protocol.SculkConnection;
import eu.sculkpowered.server.protocol.packet.ClientboundPacket;
import eu.sculkpowered.server.protocol.packet.clientbound.BossEventPacket;
import eu.sculkpowered.server.protocol.packet.clientbound.ClearTitlesPacket;
import eu.sculkpowered.server.protocol.packet.clientbound.SetActionBarTextPacket;
import eu.sculkpowered.server.protocol.packet.clientbound.ResourcePackPushPacket;
import eu.sculkpowered.server.protocol.packet.clientbound.CustomChatCompletionsPacket;
import eu.sculkpowered.server.protocol.packet.serverbound.ClientInformationPacket;
import eu.sculkpowered.server.protocol.packet.clientbound.DisconnectPacket;
import eu.sculkpowered.server.protocol.packet.clientbound.GameEventPacket;
import eu.sculkpowered.server.protocol.packet.clientbound.SetTitleTextPacket;
import eu.sculkpowered.server.protocol.packet.shared.CarriedItemPacket;
import eu.sculkpowered.server.protocol.packet.shared.KeepAlivePacket;
import eu.sculkpowered.server.protocol.packet.clientbound.PlayerAbilitiesPacket;
import eu.sculkpowered.server.protocol.packet.clientbound.PlayerInfoUpdatePacket;
import eu.sculkpowered.server.protocol.packet.clientbound.PlayerInfoRemovePacket;
import eu.sculkpowered.server.protocol.packet.shared.CustomPayloadPacket;
import eu.sculkpowered.server.protocol.packet.clientbound.RemoveEntitiesPacket;
import eu.sculkpowered.server.protocol.packet.clientbound.ResourcePackPopPacket;
import eu.sculkpowered.server.protocol.packet.clientbound.RespawnPacket;
import eu.sculkpowered.server.protocol.packet.clientbound.PlayerPositionPacket;
import eu.sculkpowered.server.protocol.packet.clientbound.SystemChatPacket;
import eu.sculkpowered.server.protocol.packet.clientbound.TabListPacket;
import eu.sculkpowered.server.protocol.packet.clientbound.UpdateAttributesPacket;
import eu.sculkpowered.server.protocol.packet.clientbound.SetChunkCacheCenterPacket;
import eu.sculkpowered.server.protocol.packet.clientbound.ContainerSetContentPacket;
import eu.sculkpowered.server.protocol.packet.clientbound.SoundEntityPacket;
import eu.sculkpowered.server.protocol.packet.clientbound.SoundPacket;
import eu.sculkpowered.server.protocol.packet.clientbound.StopSoundPacket;
import eu.sculkpowered.server.protocol.packet.clientbound.SetSubtitleTextPacket;
import eu.sculkpowered.server.protocol.packet.clientbound.SetTitlesAnimationPacket;
import eu.sculkpowered.server.util.ItemList;
import eu.sculkpowered.server.world.Position;
import eu.sculkpowered.server.world.SculkWorld;
import eu.sculkpowered.server.world.Vector;
import eu.sculkpowered.server.world.World;
import eu.sculkpowered.server.world.chunk.SculkChunk;
import java.net.SocketAddress;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import net.kyori.adventure.bossbar.BossBar;
import net.kyori.adventure.bossbar.BossBarImplementation;
import net.kyori.adventure.identity.Identity;
import net.kyori.adventure.permission.PermissionChecker;
import net.kyori.adventure.pointer.Pointers;
import net.kyori.adventure.resource.ResourcePackRequest;
import net.kyori.adventure.sound.Sound;
import net.kyori.adventure.sound.SoundStop;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.title.Title;
import net.kyori.adventure.title.TitlePart;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.UnmodifiableView;

public final class SculkPlayer extends AbstractLivingEntity implements Player {

  private final Pointers pointers = Pointers.builder()
      .withDynamic(Identity.NAME, this::name)
      .withDynamic(Identity.UUID, this::uniqueId)
      .withDynamic(Identity.DISPLAY_NAME, this::displayName)
      .withDynamic(Identity.LOCALE, () -> this.settings().locale())
      .withDynamic(PermissionChecker.POINTER, () -> this.permissionChecker)
      .build();

  private final SculkConnection connection;
  private final GameProfile profile;
  private final ClientInformationWrapper settings = new ClientInformationWrapper();
  private final CompletableFuture<Void> disconnectFuture = new CompletableFuture<>();
  private SculkContainer container;
  private int ping;
  private long lastSendKeepAlive;
  private boolean keepAlivePending;
  private GameMode gameMode = GameMode.SURVIVAL;
  private PermissionChecker permissionChecker;
  private Component displayName;
  private int heldItemSlot;
  public boolean flying;
  private boolean allowedFlight;
  private float flyingSpeed = 0.05F;
  private boolean instantBreak;
  private float viewModifier = 0.01F;
  private final Set<BossBar> bossBars = new HashSet<>();
  private boolean receivedTeleportConfirmation;

  public SculkPlayer(final SculkServer server, final SculkConnection connection,
      final GameProfile profile) {
    super(EntityTypes.PLAYER, profile.uniqueId());
    this.server = server;
    this.connection = connection;
    this.profile = profile;
    this.equipment = new SculkInventory(this);
  }

  public @NotNull String name() {
    return this.profile.name();
  }

  @Override
  public @NotNull GameProfile profile() {
    return this.profile;
  }

  @Override
  public @NotNull ClientInformationWrapper settings() {
    return this.settings;
  }

  @Override
  public @Nullable Component displayName() {
    return this.displayName;
  }

  @Override
  public int ping() {
    return this.ping;
  }

  @Override
  public void displayName(@Nullable Component displayName) {
    this.displayName = displayName;
  }

  @Override
  public @NotNull GameMode gameMode() {
    return this.gameMode;
  }

  @Override
  public void gameMode(@NotNull GameMode gameMode) {
    if (gameMode != this.gameMode) {
      this.gameMode = gameMode;
      this.send(new GameEventPacket(3, gameMode.ordinal()));
    }
  }

  @Override
  public void disconnect(@NotNull Component component) {
    this.connection.sendAndClose(new DisconnectPacket(component));
  }

  @Override
  public @NotNull SculkInventory inventory() {
    return (SculkInventory) this.equipment;
  }

  @Override
  public @Nullable SculkContainer openedContainer() {
    return this.container;
  }

  public void setContainer(final SculkContainer container) {
    this.container = container;
  }

  @Override
  public void openContainer(@NotNull Container container) {
    this.container = (SculkContainer) container;
    this.container.addViewer(this);
  }

  @Override
  public int heldItemSlot() {
    return this.heldItemSlot;
  }

  @Override
  public void heldItemSlot(int slot) {
    this.send(new CarriedItemPacket((byte) slot));
    this.heldItemSlot0(slot);
  }

  public void heldItemSlot0(int slot) {
    this.heldItemSlot = slot;
    this.equipment.set(EquipmentSlot.MAIN_HAND, this.inventory().item(this.heldItemSlot));
  }

  @Override
  public boolean flying() {
    return this.flying;
  }

  @Override
  public void flying(boolean flying) {
    this.flying = flying;
    this.updateAttributes();
  }

  @Override
  public boolean allowFlight() {
    return this.allowedFlight;
  }

  @Override
  public void allowFlight(boolean allowFight) {
    this.allowedFlight = allowFight;
    if (!this.allowedFlight) {
      this.flying = false;
    }
    this.updateAttributes();
  }

  @Override
  public float flyingSpeed() {
    return this.flyingSpeed;
  }

  @Override
  public void flyingSpeed(float flyingSpeed) {
    this.flyingSpeed = flyingSpeed;
    this.updateAttributes();
  }

  @Override
  public boolean instantBreak() {
    return this.instantBreak || this.gameMode == GameMode.CREATIVE;
  }

  @Override
  public void instantBreak(boolean instantBreak) {
    this.instantBreak = instantBreak;
    this.updateAttributes();
  }

  @Override
  public float viewModifier() {
    return this.viewModifier;
  }

  @Override
  public void viewModifier(float viewModifier) {
    this.viewModifier = viewModifier;
    this.updateAttributes();
  }

  @Override
  public void sendPluginMessage(@NotNull String channel, byte @NotNull [] bytes) {
    this.send(new CustomPayloadPacket(channel, bytes));
  }

  @Override
  public boolean onGround() {
    return this.onGround;
  }

  @Override
  public void addChatSuggestions(@NotNull String... suggestions) {
    this.send(new CustomChatCompletionsPacket(0, suggestions));
  }

  @Override
  public void removeChatSuggestions(@NotNull String... suggestions) {
    this.send(new CustomChatCompletionsPacket(1, suggestions));
  }

  @Override
  public void setChatSuggestions(@NotNull String... suggestions) {
    this.send(new CustomChatCompletionsPacket(2, suggestions));
  }

  @Override
  public void world(@NotNull World world) {
    if (this.world != world) {
      final var oldWorld = this.world;
      final var oldPosition = this.position;
      this.world = (SculkWorld) world;
      this.server.addTask(() -> {
        forChunksInRange(
            chunkCoordinate(oldPosition.x()), chunkCoordinate(oldPosition.z()),
            this.settings.viewDistance(),
            (x, z) -> oldWorld.chunk(x, z).viewers().remove(this));
        this.sendViewers(new RemoveEntitiesPacket(this.id));
        this.viewers.clear();
        oldWorld.chunkAt(oldPosition).entities().remove(this);
        this.world.chunkAt(this.position).entities().add(this);
      });
      this.position = world.spawnPosition();
      this.gameMode = world.defaultGameMode();
      this.send(
          new RespawnPacket(world.dimension(), world.name(), 0, this.gameMode, (byte) 3));
      this.calculateChunks(this.position, this.position, false, false);
      this.inventory().resend();
      this.send(new GameEventPacket(13, -1));
    }
  }

  @Override
  public void velocity(@NotNull Vector vector) {
    this.velocity = vector;
    this.sendViewersAndSelf(this.velocityPacket(vector));
  }

  @Override
  public void teleport(@NotNull Position position) {
    super.teleport(position);
    this.send(new PlayerPositionPacket(this.position));
    this.receivedTeleportConfirmation = false;
  }

  @Override
  public void sendMessage(@NotNull Component message) {
    this.send(new SystemChatPacket(message, false));
  }

  @Override
  public void sendActionBar(@NotNull Component message) {
    this.send(new SetActionBarTextPacket(message));
  }

  @Override
  public void sendPlayerListHeaderAndFooter(@NotNull Component header, @NotNull Component footer) {
    this.send(new TabListPacket(header, footer));
  }

  @Override
  public <T> void sendTitlePart(@NotNull TitlePart<T> part, @NotNull T value) {
    if (part == TitlePart.TITLE) {
      this.send(
          new SetTitleTextPacket((Component) value));
    } else if (part == TitlePart.SUBTITLE) {
      this.send(new SetSubtitleTextPacket((Component) value));
    } else if (part == TitlePart.TIMES) {
      final var times = (Title.Times) value;

      this.send(new SetTitlesAnimationPacket((int) (times.fadeIn().toMillis() / 50),
          (int) (times.stay().toMillis() / 50),
          (int) (times.fadeOut().toMillis() / 50)));
    }
  }

  @Override
  public void clearTitle() {
    this.send(new ClearTitlesPacket(false));
  }

  @Override
  public void resetTitle() {
    this.send(new ClearTitlesPacket(true));
  }

  @Override
  public void showBossBar(@NotNull BossBar bar) {
    @SuppressWarnings("UnstableApiUsage") final var impl = BossBarImplementation
        .get(bar, Impl.class);
    if (impl.players().add(this)) {
      this.send(BossEventPacket.add(impl.uniqueId(), bar.name(), bar.progress(), bar.color().ordinal(),
          bar.overlay().ordinal(), BossBarProvider.flags(bar)));
    }
  }

  @Override
  public void hideBossBar(@NotNull BossBar bar) {
    final var impl = BossBarImplementation.get(bar, Impl.class);
    if (impl.players().remove(this)) {
      this.send(BossEventPacket.remove(impl.uniqueId()));
    }
  }

  @Override
  public void playSound(@NotNull Sound sound) {
    this.send(new SoundEntityPacket(sound, this.id));
  }

  @Override
  public void playSound(@NotNull Sound sound, double x, double y, double z) {
    this.send(new SoundPacket(sound, (int) (x * 8), (int) (y * 8), (int) (z * 8)));
  }

  @Override
  public void playSound(@NotNull Sound sound, @NotNull Sound.Emitter emitter) {
    if (emitter instanceof Entity entity) {
      this.send(new SoundEntityPacket(sound, entity.id()));
    }
  }

  @Override
  public void stopSound(@NotNull SoundStop stop) {
    this.send(new StopSoundPacket(stop));
  }

  @Override
  public void sendResourcePacks(@NotNull ResourcePackRequest request) {
    for (final var pack : request.packs()) {
      this.send(new ResourcePackPushPacket(pack.id(), pack.uri().toString(),
          pack.hash(), request.required(), request.prompt()));
    }
  }

  @Override
  public void removeResourcePacks(@NotNull UUID id, @NotNull UUID @NotNull ... others) {
    this.send(new ResourcePackPopPacket(id));
    for (final var other : others) {
      this.send(new ResourcePackPopPacket(other));
    }
  }

  @Override
  public void clearResourcePacks() {
    this.send(new ResourcePackPopPacket(null));
  }

  @NotNull
  @Override
  public Pointers pointers() {
    return this.pointers;
  }

  @Override
  public void tick() {
    super.tick();
    final var time = System.currentTimeMillis();

    final var elapsedTime = time - this.lastSendKeepAlive;
    if (this.keepAlivePending) {
      if (elapsedTime > 30000) { // disconnect after 30 seconds
        this.disconnect(Component.translatable("disconnect.timeout"));
      }
    } else {
      if (elapsedTime > 15000) { // send all 15 seconds
        this.keepAlivePending = true;
        this.lastSendKeepAlive = time;
        this.send(new KeepAlivePacket(time));
      }
    }
    this.connection.flush();
  }

  @Override
  public int protocolVersion() {
    return this.connection.protocolVersion();
  }

  @Override
  public @NotNull SocketAddress address() {
    return this.connection.address();
  }

  @Override
  public boolean hasPermission(@NotNull String permission) {
    return this.permissionChecker.test(permission);
  }

  @Override
  public @UnmodifiableView @NotNull Iterable<? extends BossBar> activeBossBars() {
    return this.bossBars;
  }

  @Override
  protected void attributeChange(SculkAttributeValue value) {
    this.sendViewersAndSelf(new UpdateAttributesPacket(this.id, List.of(value)));
  }

  public void init(final GameMode gameMode, final Position position, final SculkWorld world,
      final PermissionChecker permissionChecker) {
    this.gameMode = gameMode;
    this.position = position;
    this.world = world;
    this.permissionChecker = permissionChecker;
    this.server.addTask(() -> this.world.chunkAt(world.spawnPosition()).entities().add(this));
  }

  public void calculateChunks(final Position from, final Position to) {
    this.calculateChunks(from, to, true, true);
  }

  public void calculateChunks(final Position from, final Position to, boolean check,
      boolean checkAlreadyLoaded) {
    final var viewDistance = this.settings().viewDistance();
    this.calculateChunks(from, to, check, checkAlreadyLoaded, viewDistance, viewDistance);
  }

  public void calculateChunks(final Position from, final Position to,
      boolean check, boolean checkAlreadyLoaded,
      byte range, byte oldRange) {
    final var fromChunkX = chunkCoordinate(from.x());
    final var fromChunkZ = chunkCoordinate(from.z());
    final var chunkX = chunkCoordinate(to.x());
    final var chunkZ = chunkCoordinate(to.z());
    if (check) {
      if (fromChunkX == chunkX && fromChunkZ == chunkZ) {
        return;
      }
    }
    this.send(new SetChunkCacheCenterPacket(chunkX, chunkZ));
    this.server.addTask(() -> {
      final var chunks = new ArrayList<SculkChunk>((range * 2 + 1) * (range * 2 + 1));
      forChunksInRange(chunkX, chunkZ, range, (x, z) -> {
        final var chunk = this.world.chunk(x, z);
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
        forChunksInRange(fromChunkX, fromChunkZ, oldRange, (x, z) -> {
          final var chunk = this.world.chunk(x, z);
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
    });
  }

  public void handleClientInformation(final ClientInformationPacket clientInformation) {
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

  public void handlePluginMessage(final CustomPayloadPacket customPayload) {
    this.server.eventHandler().call(
        new PluginMessageEvent(this, customPayload.identifier(), customPayload.data()));
  }

  public void send(final ClientboundPacket packet) {
    this.connection.send(packet);
  }

  public void onDisconnect() {
    for (final var bossBar : this.bossBars) {
      BossBarImplementation.get(bossBar, Impl.class).players().remove(this);
    }
    final var world = this.world();
    final var position = this.position();
    if (world != null) {
      this.server.addTask(() -> {
        world.chunkAt(position).entities().remove(this);
        forChunksInRange(
            chunkCoordinate(position.x()), chunkCoordinate(position.z()),
            this.settings.viewDistance(), (x, z) -> {
              final var chunk = world.chunk(x, z);
              chunk.viewers().remove(this);
              for (final var entity : chunk.entities()) {
                entity.removeViewer(this);
              }
            });
      });
    }
    this.sendViewers(new RemoveEntitiesPacket(this.id()));
    this.server.sendAll(new PlayerInfoRemovePacket(List.of(this)));
    if (this.container != null) {
      this.container.removeViewer(this);
    }
    this.server.eventHandler().call(new PlayerDisconnectEvent(this))
        .whenComplete((event, throwable) -> {
          if (throwable != null) {
            this.disconnectFuture.completeExceptionally(throwable);
          } else {
            this.disconnectFuture.complete(null);
          }
        });
  }

  public void sendViewersAndSelf(final ClientboundPacket packet) {
    this.send(packet);
    this.sendViewers(packet);
  }

  public void setPing(final int ping) {
    this.ping = ping;
    this.sendViewersAndSelf(PlayerInfoUpdatePacket.update(this, PlayerInfoUpdatePacket.Action.UPDATE_LATENCY));
  }

  public void setKeepAlivePending(boolean pending) {
    this.keepAlivePending = pending;
  }

  public boolean receivedTeleportConfirmation() {
    return this.receivedTeleportConfirmation;
  }

  public void receivedTeleportConfirmation(boolean received) {
    this.receivedTeleportConfirmation = received;
  }

  public CompletableFuture<Void> disconnectFuture() {
    return this.disconnectFuture;
  }

  public void resendContainer() {
    if (this.container == null) {
      this.inventory().resend();
    } else {
      // item list with container size and the 36 "chest inventory"
      final var items = new ItemList(this.container.type().size() + 36);
      for (var i = 8; i < 44; i++) {
        items.set(i - 9 + this.container.type().size(), this.inventory().items().get(i));
      }
      for (var i = 0; i < this.container.items().size(); i++) {
        items.set(i, this.container.items().get(i));
      }
      this.send(new ContainerSetContentPacket((byte) 1, this.container.state(), items, ItemStack.empty()));
    }
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
    this.send(new PlayerAbilitiesPacket(flags, this.flyingSpeed, this.viewModifier));
  }
}
