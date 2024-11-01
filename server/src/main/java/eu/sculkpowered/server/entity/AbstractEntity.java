package eu.sculkpowered.server.entity;

import eu.sculkpowered.server.SculkServer;
import eu.sculkpowered.server.entity.meta.EntityMeta;
import eu.sculkpowered.server.entity.player.Player;
import eu.sculkpowered.server.entity.player.SculkPlayer;
import eu.sculkpowered.server.protocol.packet.ClientboundPacket;
import eu.sculkpowered.server.protocol.packet.clientbound.SetEntityDataPacket;
import eu.sculkpowered.server.protocol.packet.clientbound.SetEntityMotionPacket;
import eu.sculkpowered.server.protocol.packet.clientbound.RemoveEntitiesPacket;
import eu.sculkpowered.server.protocol.packet.clientbound.AddEntityPacket;
import eu.sculkpowered.server.protocol.packet.clientbound.MoveEntityPosRotPacket;
import eu.sculkpowered.server.protocol.packet.clientbound.MoveEntityRot;
import eu.sculkpowered.server.protocol.packet.clientbound.RotateHeadPacket;
import eu.sculkpowered.server.protocol.packet.clientbound.TeleportEntityPacket;
import eu.sculkpowered.server.world.Position;
import eu.sculkpowered.server.world.SculkWorld;
import eu.sculkpowered.server.world.Vector;
import eu.sculkpowered.server.world.World;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class AbstractEntity<M extends EntityMeta> implements Entity<M> {

  private static final AtomicInteger CURRENT_ID = new AtomicInteger(0);

  protected final UUID uniqueId;
  protected final EntityType<Entity<M>> type;
  public SculkServer server;
  protected final int id = CURRENT_ID.getAndIncrement();
  protected final Metadata metadata = new Metadata();
  protected final Set<SculkPlayer> viewers = new HashSet<>();
  public SculkWorld world;
  public Position position = Position.zero();
  public boolean onGround;
  protected Vector velocity = Vector.zero();

  public AbstractEntity(final EntityType<Entity<M>> type) {
    this(type, UUID.randomUUID());
  }

  public AbstractEntity(final EntityType<Entity<M>> type, final UUID uniqueId) {
    this.uniqueId = uniqueId;
    this.type = type;
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
  public @NotNull EntityType<Entity<M>> type() {
    return this.type;
  }

  @Override
  public SculkWorld world() {
    return this.world;
  }

  @Override
  public void world(@NotNull World world) {
    if (this.world != world) {
      final var sculkWorld = (SculkWorld) world;
      final var oldWorld = this.world;
      this.server.addTask(() -> {
        this.sendViewers(new RemoveEntitiesPacket(this.id));
        this.viewers.clear();
        oldWorld.chunkAt(this.position).entities().remove(this);
        sculkWorld.chunkAt(this.position).entities().add(this);
      });
      this.world = sculkWorld;
    }
  }

  @Override
  public @NotNull Position position() {
    return this.position;
  }

  @Override
  public void velocity(@NotNull Vector vector) {
    this.velocity = vector;
    this.sendViewers(this.velocityPacket(vector));
  }

  @Override
  public @NotNull Vector velocity() {
    return this.velocity;
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
    this.move(position);
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
          new AddEntityPacket(this.id, this.uniqueId, this.type().id(),
              this.position, this.velocity));
      if (!this.metadata.entries().isEmpty()) {
        sculkPlayer.send(new SetEntityDataPacket(this.id, this.metadata.entries()));
      }
    }
    return added;
  }

  @Override
  public boolean removeViewer(@NotNull Player player) {
    final var sculkPlayer = (SculkPlayer) player;
    final var removed = this.viewers.remove(sculkPlayer);
    if (removed) {
      sculkPlayer.send(new RemoveEntitiesPacket(this.id));
    }
    return removed;
  }

  public void tick() {
    if (!this.metadata.changes().isEmpty()) {
      final var entityMetadata = new SetEntityDataPacket(this.id, this.metadata.changes());
      this.metadata.reset();
      this.sendViewers(entityMetadata);
      if (this instanceof SculkPlayer player) {
        player.send(entityMetadata);
      }
    }

    this.velocity = Vector.zero(); // for now we reset every tick // TODO: make a calculation
  }

  public void move(final Position position) {
    final var previous = this.position;
    final var distanceX = Math.abs(position.x() - previous.x());
    final var distanceY = Math.abs(position.y() - previous.y());
    final var distanceZ = Math.abs(position.z() - previous.z());
    this.server.addTask(() -> {
      this.world.chunkAt(previous).entities().remove(this);
      this.world.chunkAt(position).entities().add(this);
    });
    this.position = position;

    if (distanceX > 8 || distanceY > 8 || distanceZ > 8) {
      this.sendViewers(new TeleportEntityPacket(this.id, position, this.onGround));
      return;
    }
    final var positionChange = (distanceX + distanceY + distanceZ) > 0;
    final var viewChange = !(Float.compare(position.yaw(), previous.yaw()) == 0
        && Float.compare(position.pitch(), previous.pitch()) == 0);

    if (positionChange) {
      if (this instanceof SculkPlayer player) {
        player.calculateChunks(previous, position);
      }
      if (viewChange) {
        this.sendViewers(
            new MoveEntityPosRotPacket(this.id,
                this.delta(position.x(), previous.x()), this.delta(position.y(), previous.y()),
                this.delta(position.z(), previous.z()),
                position.yaw(), position.pitch(), this.onGround),
            new RotateHeadPacket(this.id, position.yaw())
        );
      } else {
        this.sendViewers(
            new MoveEntityPosRotPacket(this.id, this.delta(position.x(), previous.x()),
                this.delta(position.y(), previous.y()), this.delta(position.z(), previous.z()),
                position.yaw(), position.pitch(), this.onGround));
      }
    } else if (viewChange) {
      this.sendViewers(
          new MoveEntityRot(this.id, position.yaw(), position.pitch(), this.onGround),
          new RotateHeadPacket(this.id, position.yaw())
      );
    }
  }

  public void sendViewers(final ClientboundPacket packet) {
    for (final var player : this.viewers) {
      player.send(packet);
    }
  }

  public void sendViewers(final ClientboundPacket packet1, final ClientboundPacket packet2) {
    for (final var player : this.viewers) {
      player.send(packet1);
      player.send(packet2);
    }
  }

  protected SetEntityMotionPacket velocityPacket(final Vector vector) {
    vector.multiply(400D);
    return new SetEntityMotionPacket(this.id,
        (short) Math.min(Math.max(vector.x(), Short.MIN_VALUE), Short.MAX_VALUE),
        (short) Math.min(Math.max(vector.y(), Short.MIN_VALUE), Short.MAX_VALUE),
        (short) Math.min(Math.max(vector.z(), Short.MIN_VALUE), Short.MAX_VALUE)
    );
  }

  private short delta(final double current, final double previous) {
    return (short) ((current * 32 - previous * 32) * 128);
  }
}
