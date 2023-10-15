package de.bauhd.sculk.entity;

import de.bauhd.sculk.entity.player.Player;
import de.bauhd.sculk.entity.player.SculkPlayer;
import de.bauhd.sculk.protocol.packet.Packet;
import de.bauhd.sculk.protocol.packet.play.EntityMetadata;
import de.bauhd.sculk.protocol.packet.play.RemoveEntities;
import de.bauhd.sculk.protocol.packet.play.SpawnEntity;
import de.bauhd.sculk.world.Position;
import de.bauhd.sculk.world.SculkWorld;
import de.bauhd.sculk.world.World;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public abstract class AbstractEntity implements Entity {

  private static final AtomicInteger CURRENT_ID = new AtomicInteger(0);

  private final UUID uniqueId;
  private final int id = CURRENT_ID.getAndIncrement();
  public final Metadata metadata = new Metadata();
  protected final Set<SculkPlayer> viewers = new HashSet<>();
  protected SculkWorld world;
  protected Position position = Position.ZERO;
  protected boolean spawned;

  public AbstractEntity() {
    this(UUID.randomUUID());
  }

  public AbstractEntity(UUID uniqueId) {
    this.uniqueId = uniqueId;
  }

  @Override
  public @NotNull UUID getUniqueId() {
    return this.uniqueId;
  }

  @Override
  public int getId() {
    return this.id;
  }

  @Override
  public SculkWorld getWorld() {
    return this.world;
  }

  @Override
  public void setWorld(@NotNull World world) {
    if (this.world != null) {
      this.world.getChunkAt(this.position).entities().remove(this);
    }
    this.world = (SculkWorld) world;
    this.world.getChunkAt(this.position).entities().add(this);
  }

  @Override
  public @NotNull Position getPosition() {
    return this.position;
  }

  public void setPosition(final Position position) {
    this.world.getChunkAt(this.position).entities().remove(this);
    this.position = position;
    this.world.getChunkAt(this.position).entities().add(this);
  }

  @Override
  public boolean isOnFire() {
    return this.metadata.inMask(0, 0x01);
  }

  @Override
  public boolean isCrouching() {
    return this.metadata.inMask(0, 0x02);
  }

  @Override
  public boolean isSprinting() {
    return this.metadata.inMask(0, 0x08);
  }

  @Override
  public boolean isSwimming() {
    return this.metadata.inMask(0, 0x10);
  }

  @Override
  public boolean isInvisible() {
    return this.metadata.inMask(0, 0x20);
  }

  @Override
  public void setInvisible(boolean invisible) {
    this.metadata.setMask(0, 0x20, invisible);
  }

  @Override
  public boolean isGlowing() {
    return this.metadata.inMask(0, 0x40);
  }

  @Override
  public void setGlowing(boolean glowing) {
    this.metadata.setMask(0, 0x40, glowing);
  }

  @Override
  public @Nullable Component getCustomName() {
    return this.metadata.getComponent(2, Component.empty());
  }

  @Override
  public void setCustomName(@Nullable Component customName) {
    this.metadata.setComponent(2, customName);
  }

  @Override
  public boolean isCustomNameVisible() {
    return this.metadata.getBoolean(3, false);
  }

  @Override
  public void setCustomNameVisible(boolean visible) {
    if (this.isCustomNameVisible() == visible) {
      return;
    }
    this.metadata.setBoolean(3, visible);
  }

  @Override
  public boolean isSilent() {
    return this.metadata.getBoolean(4, false);
  }

  @Override
  public void setSilent(boolean silent) {
    if (this.isSilent() == silent) {
      return;
    }
    this.metadata.setBoolean(4, silent);
  }

  @Override
  public boolean hasGravity() {
    return !this.metadata.getBoolean(5, false);
  }

  @Override
  public void setGravity(boolean gravity) {
    if (this.hasGravity() == gravity) {
      return;
    }
    this.metadata.setBoolean(5, !gravity);
  }

  @Override
  public void teleport(@NotNull Position position) {
    this.setPosition(position);
  }

  @Override
  public @NotNull Pose getPose() {
    return this.metadata.get(6, Pose.STANDING);
  }

  @Override
  public void setPose(@NotNull Pose pose) {
    this.metadata.setPose(6, pose);
  }

  @Override
  public @NotNull Collection<Player> getViewers() {
    return List.copyOf(this.viewers);
  }

  @Override
  public void addViewer(@NotNull Player player) {
    final var sculkPlayer = ((SculkPlayer) player);
    if (this.viewers.add(sculkPlayer)) {
      sculkPlayer.send(
          new SpawnEntity(this.id, this.uniqueId, this.getType().ordinal(), this.position));
      if (this.metadata.entries().isEmpty()) {
        sculkPlayer.send(new EntityMetadata(this.id, this.metadata.entries()));
      }
    }
  }

  @Override
  public void removeViewer(@NotNull Player player) {
    final var sculkPlayer = (SculkPlayer) player;
    if (this.viewers.remove(sculkPlayer)) {
      sculkPlayer.send(new RemoveEntities(this.id));
    }
  }

  public void tick() {
    if (!this.metadata.changes().isEmpty()) {
      final var entityMetadata = new EntityMetadata(this.id, this.metadata.changes());
      this.metadata.reset();
      this.sendViewers(entityMetadata);
      if (this instanceof SculkPlayer player) {
        player.send(entityMetadata);
      }
    }
  }

  public void sendViewers(final Packet packet) {
    for (final var player : this.viewers) {
      player.send(packet);
    }
  }

  public void sendViewers(final Packet packet1, final Packet packet2) {
    for (final var player : this.viewers) {
      player.send(packet1);
      player.send(packet2);
    }
  }

  public void spawn(@NotNull World world, @NotNull Position position) {
    this.setWorld(world);
    this.setPosition(position);
    this.spawned = true;
  }
}
