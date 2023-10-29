package io.github.sculkpowered.server.entity;

import io.github.sculkpowered.server.entity.player.Player;
import io.github.sculkpowered.server.entity.player.SculkPlayer;
import io.github.sculkpowered.server.protocol.packet.Packet;
import io.github.sculkpowered.server.protocol.packet.play.EntityMetadata;
import io.github.sculkpowered.server.protocol.packet.play.RemoveEntities;
import io.github.sculkpowered.server.protocol.packet.play.SpawnEntity;
import io.github.sculkpowered.server.world.Position;
import io.github.sculkpowered.server.world.SculkWorld;
import io.github.sculkpowered.server.world.World;
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

  protected final UUID uniqueId;
  protected final int id = CURRENT_ID.getAndIncrement();
  protected final Metadata metadata = new Metadata();
  protected final Set<SculkPlayer> viewers = new HashSet<>();
  protected SculkWorld world;
  protected Position position = Position.zero();

  public AbstractEntity() {
    this(UUID.randomUUID());
  }

  public AbstractEntity(UUID uniqueId) {
    this.uniqueId = uniqueId;
  }

  @Override
  public @NotNull UUID uniqueId() {
    return this.uniqueId;
  }

  @Override
  public int id() {
    return this.id;
  }

  @Override
  public SculkWorld world() {
    return this.world;
  }

  @Override
  public void world(@NotNull World world) {
    if (this.world != null) {
      this.world.chunkAt(this.position).entities().remove(this);
    }
    this.world = (SculkWorld) world;
    this.world.chunkAt(this.position).entities().add(this);
  }

  @Override
  public @NotNull Position position() {
    return this.position;
  }

  public void setPosition(final Position position) {
    this.world.chunkAt(this.position).entities().remove(this);
    this.position = position;
    this.world.chunkAt(this.position).entities().add(this);
  }

  @Override
  public boolean onFire() {
    return this.metadata.inMask(0, 0x01);
  }

  @Override
  public boolean crouching() {
    return this.metadata.inMask(0, 0x02);
  }

  @Override
  public boolean sprinting() {
    return this.metadata.inMask(0, 0x08);
  }

  @Override
  public boolean swimming() {
    return this.metadata.inMask(0, 0x10);
  }

  @Override
  public boolean invisible() {
    return this.metadata.inMask(0, 0x20);
  }

  @Override
  public void invisible(boolean invisible) {
    this.metadata.setMask(0, 0x20, invisible);
  }

  @Override
  public boolean glowing() {
    return this.metadata.inMask(0, 0x40);
  }

  @Override
  public void glowing(boolean glowing) {
    this.metadata.setMask(0, 0x40, glowing);
  }

  @Override
  public @Nullable Component customName() {
    return this.metadata.getComponent(2, Component.empty());
  }

  @Override
  public void customName(@Nullable Component customName) {
    this.metadata.setComponent(2, customName);
  }

  @Override
  public boolean customNameVisible() {
    return this.metadata.getBoolean(3, false);
  }

  @Override
  public void customNameVisible(boolean visible) {
    if (this.customNameVisible() == visible) {
      return;
    }
    this.metadata.setBoolean(3, visible);
  }

  @Override
  public boolean silent() {
    return this.metadata.getBoolean(4, false);
  }

  @Override
  public void silent(boolean silent) {
    if (this.silent() == silent) {
      return;
    }
    this.metadata.setBoolean(4, silent);
  }

  @Override
  public boolean hasGravity() {
    return !this.metadata.getBoolean(5, false);
  }

  @Override
  public void gravity(boolean gravity) {
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
  public @NotNull Pose pose() {
    return this.metadata.get(6, Pose.STANDING);
  }

  @Override
  public void pose(@NotNull Pose pose) {
    this.metadata.setPose(6, pose);
  }

  @Override
  public @NotNull Collection<Player> viewers() {
    return List.copyOf(this.viewers);
  }

  @Override
  public boolean addViewer(@NotNull Player player) {
    final var sculkPlayer = ((SculkPlayer) player);
    final var added = this.viewers.add(sculkPlayer);
    if (added) {
      sculkPlayer.send(
          new SpawnEntity(this.id, this.uniqueId, this.type().ordinal(), this.position));
      if (this.metadata.entries().isEmpty()) {
        sculkPlayer.send(new EntityMetadata(this.id, this.metadata.entries()));
      }
    }
    return added;
  }

  @Override
  public boolean removeViewer(@NotNull Player player) {
    final var sculkPlayer = (SculkPlayer) player;
    final var removed = this.viewers.remove(sculkPlayer);
    if (removed) {
      sculkPlayer.send(new RemoveEntities(this.id));
    }
    return removed;
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
}
