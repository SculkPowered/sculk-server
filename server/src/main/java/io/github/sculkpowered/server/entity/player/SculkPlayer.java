package io.github.sculkpowered.server.entity.player;

import static io.github.sculkpowered.server.protocol.packet.play.BossBar.add;
import static io.github.sculkpowered.server.protocol.packet.play.BossBar.remove;
import static io.github.sculkpowered.server.util.CoordinateUtil.chunkCoordinate;
import static io.github.sculkpowered.server.util.CoordinateUtil.forChunksInRange;

import io.github.sculkpowered.server.SculkServer;
import io.github.sculkpowered.server.adventure.BossBarProvider;
import io.github.sculkpowered.server.adventure.BossBarProvider.Impl;
import io.github.sculkpowered.server.attribute.SculkAttributeValue;
import io.github.sculkpowered.server.container.Container;
import io.github.sculkpowered.server.container.SculkContainer;
import io.github.sculkpowered.server.container.SculkInventory;
import io.github.sculkpowered.server.container.item.ItemStack;
import io.github.sculkpowered.server.entity.AbstractLivingEntity;
import io.github.sculkpowered.server.entity.Entity;
import io.github.sculkpowered.server.entity.EntityType;
import io.github.sculkpowered.server.event.connection.PluginMessageEvent;
import io.github.sculkpowered.server.protocol.SculkConnection;
import io.github.sculkpowered.server.protocol.packet.Packet;
import io.github.sculkpowered.server.protocol.packet.play.ActionBar;
import io.github.sculkpowered.server.protocol.packet.play.AddResourcePack;
import io.github.sculkpowered.server.protocol.packet.play.ChatSuggestions;
import io.github.sculkpowered.server.protocol.packet.play.ClientInformation;
import io.github.sculkpowered.server.protocol.packet.play.Disconnect;
import io.github.sculkpowered.server.protocol.packet.play.Equipment;
import io.github.sculkpowered.server.protocol.packet.play.GameEvent;
import io.github.sculkpowered.server.protocol.packet.play.HeldItem;
import io.github.sculkpowered.server.protocol.packet.play.KeepAlive;
import io.github.sculkpowered.server.protocol.packet.play.PlayerAbilities;
import io.github.sculkpowered.server.protocol.packet.play.PluginMessage;
import io.github.sculkpowered.server.protocol.packet.play.RemoveEntities;
import io.github.sculkpowered.server.protocol.packet.play.RemoveResourcePack;
import io.github.sculkpowered.server.protocol.packet.play.Respawn;
import io.github.sculkpowered.server.protocol.packet.play.SynchronizePlayerPosition;
import io.github.sculkpowered.server.protocol.packet.play.SystemChatMessage;
import io.github.sculkpowered.server.protocol.packet.play.TabListHeaderFooter;
import io.github.sculkpowered.server.protocol.packet.play.UpdateAttributes;
import io.github.sculkpowered.server.protocol.packet.play.chunk.CenterChunk;
import io.github.sculkpowered.server.protocol.packet.play.container.ContainerContent;
import io.github.sculkpowered.server.protocol.packet.play.sound.EntitySoundEffect;
import io.github.sculkpowered.server.protocol.packet.play.sound.SoundEffect;
import io.github.sculkpowered.server.protocol.packet.play.sound.StopSound;
import io.github.sculkpowered.server.protocol.packet.play.title.ClearTitles;
import io.github.sculkpowered.server.protocol.packet.play.title.Subtitle;
import io.github.sculkpowered.server.protocol.packet.play.title.TitleAnimationTimes;
import io.github.sculkpowered.server.util.ItemList;
import io.github.sculkpowered.server.util.OneInt2ObjectMap;
import io.github.sculkpowered.server.world.Position;
import io.github.sculkpowered.server.world.SculkWorld;
import io.github.sculkpowered.server.world.Vector;
import io.github.sculkpowered.server.world.World;
import io.github.sculkpowered.server.world.chunk.SculkChunk;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import java.net.SocketAddress;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
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
  private final SculkInventory inventory = new SculkInventory(this);
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
  private final Set<BossBar> bossBars = new HashSet<>();
  private boolean receivedTeleportConfirmation;

  public SculkPlayer(final SculkServer server, final SculkConnection connection,
      final GameProfile profile) {
    super(server, profile.uniqueId());
    this.connection = connection;
    this.profile = profile;
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
    return 1;
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
      this.send(new GameEvent(3, gameMode.ordinal()));
    }
  }

  @Override
  public void disconnect(@NotNull Component component) {
    this.send(new Disconnect(component));
  }

  @Override
  public @NotNull SculkInventory inventory() {
    return this.inventory;
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
    return this.heldItem;
  }

  @Override
  public void heldItemSlot(int slot) {
    this.heldItem = slot;
    this.send(new HeldItem((byte) slot));
    this.sendViewers(new Equipment(this.id(),
        OneInt2ObjectMap.of(0, this.inventory.item(this.heldItem))));
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
    this.send(new PluginMessage(channel, bytes));
  }

  @Override
  public boolean onGround() {
    return this.onGround;
  }

  @Override
  public void addChatSuggestions(@NotNull String... suggestions) {
    this.send(new ChatSuggestions(0, suggestions));
  }

  @Override
  public void removeChatSuggestions(@NotNull String... suggestions) {
    this.send(new ChatSuggestions(1, suggestions));
  }

  @Override
  public void setChatSuggestions(@NotNull String... suggestions) {
    this.send(new ChatSuggestions(2, suggestions));
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
        this.sendViewers(new RemoveEntities(this.id));
        this.viewers.clear();
        oldWorld.chunkAt(oldPosition).entities().remove(this);
        this.world.chunkAt(this.position).entities().add(this);
      });
      this.position = world.spawnPosition();
      this.gameMode = world.defaultGameMode();
      this.send(
          new Respawn(world.dimension().name(), world.name(), 0, this.gameMode, (byte) 3));
      this.calculateChunks(this.position, this.position, false, false);
      this.inventory().resend();
      this.send(new GameEvent(13, -1));
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
    this.send(new SynchronizePlayerPosition(this.position));
    this.receivedTeleportConfirmation = false;
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
      this.send(
          new io.github.sculkpowered.server.protocol.packet.play.title.Title((Component) value));
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
  public void clearTitle() {
    this.send(new ClearTitles(false));
  }

  @Override
  public void resetTitle() {
    this.send(new ClearTitles(true));
  }

  @Override
  public void showBossBar(@NotNull BossBar bar) {
    @SuppressWarnings("UnstableApiUsage") final var impl = BossBarImplementation
        .get(bar, Impl.class);
    if (impl.players().add(this)) {
      this.send(add(impl.uniqueId(), bar.name(), bar.progress(), bar.color().ordinal(),
          bar.overlay().ordinal(), BossBarProvider.flags(bar)));
    }
  }

  @Override
  public void hideBossBar(@NotNull BossBar bar) {
    final var impl = BossBarImplementation.get(bar, Impl.class);
    if (impl.players().remove(this)) {
      this.send(remove(impl.uniqueId()));
    }
  }

  @Override
  public void playSound(@NotNull Sound sound) {
    this.send(new EntitySoundEffect(sound, this.id));
  }

  @Override
  public void playSound(@NotNull Sound sound, double x, double y, double z) {
    this.send(new SoundEffect(sound, (int) (x * 8), (int) (y * 8), (int) (z * 8)));
  }

  @Override
  public void playSound(@NotNull Sound sound, @NotNull Sound.Emitter emitter) {
    if (emitter instanceof Entity entity) {
      this.send(new EntitySoundEffect(sound, entity.id()));
    }
  }

  @Override
  public void stopSound(@NotNull SoundStop stop) {
    this.send(new StopSound(stop));
  }

  @Override
  public void sendResourcePacks(@NotNull ResourcePackRequest request) {
    for (final var pack : request.packs()) {
      this.send(new AddResourcePack(pack.id(), pack.uri().toString(),
          pack.hash(), request.required(), request.prompt()));
    }
  }

  @Override
  public void removeResourcePacks(@NotNull UUID id, @NotNull UUID @NotNull ... others) {
    this.send(new RemoveResourcePack(id));
    for (final var other : others) {
      this.send(new RemoveResourcePack(other));
    }
  }

  @Override
  public void clearResourcePacks() {
    this.send(new RemoveResourcePack(null));
  }

  @NotNull
  @Override
  public Pointers pointers() {
    return this.pointers;
  }

  @Override
  public @NotNull EntityType type() {
    return EntityType.PLAYER;
  }

  @Override
  public boolean addViewer(@NotNull Player player) {
    if (player != this) {
      final var added = super.addViewer(player);
      if (added) {
        final var sculkPlayer = (SculkPlayer) player;
        final var inventory = this.inventory();
        final var equipment = new Int2ObjectOpenHashMap<ItemStack>();
        if (!inventory.itemInMainHand().isEmpty()) {
          equipment.put(0, inventory.itemInMainHand());
        }
        if (!inventory.itemInOffHand().isEmpty()) {
          equipment.put(1, inventory.itemInOffHand());
        }
        if (!inventory.boots().isEmpty()) {
          equipment.put(2, inventory.boots());
        }
        if (!inventory.leggings().isEmpty()) {
          equipment.put(3, inventory.leggings());
        }
        if (!inventory.chestplate().isEmpty()) {
          equipment.put(4, inventory.chestplate());
        }
        if (!inventory.helmet().isEmpty()) {
          equipment.put(5, inventory.helmet());
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

    final var elapsedTime = time - this.lastSendKeepAlive;
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
    this.sendViewersAndSelf(new UpdateAttributes(this.id, value));
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
    this.send(new CenterChunk(chunkX, chunkZ));
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
    this.server.eventHandler().call(
        new PluginMessageEvent(this, pluginMessage.identifier(), pluginMessage.data()));
  }

  public void send(final Packet packet) {
    this.connection.send(packet);
  }

  public void onDisconnect() {
    for (final var bossBar : this.bossBars) {
      BossBarImplementation.get(bossBar, Impl.class).players().remove(this);
    }
  }

  public void sendViewersAndSelf(final Packet packet) {
    this.send(packet);
    this.sendViewers(packet);
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

  public void resendContainer() {
    if (this.container == null) {
      this.inventory.resend();
    } else {
      // item list with container size and the 36 "chest inventory"
      final var items = new ItemList(this.container.type().size() + 36);
      for (var i = 8; i < 44; i++) {
        items.set(i - 9 + this.container.type().size(), inventory.items().get(i));
      }
      for (var i = 0; i < this.container.items().size(); i++) {
        items.set(i, this.container.items().get(i));
      }
      this.send(new ContainerContent((byte) 1, this.container.state(), items));
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
    this.send(new PlayerAbilities(flags, this.flyingSpeed, this.viewModifier));
  }
}
