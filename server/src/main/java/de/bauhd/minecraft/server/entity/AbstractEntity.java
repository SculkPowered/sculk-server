package de.bauhd.minecraft.server.entity;

import de.bauhd.minecraft.server.entity.player.Player;
import de.bauhd.minecraft.server.protocol.packet.Packet;
import de.bauhd.minecraft.server.protocol.packet.play.EntityMetadata;
import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class AbstractEntity implements Entity {

    private static final AtomicInteger CURRENT_ID = new AtomicInteger(0);

    private final int id = CURRENT_ID.getAndIncrement();
    public final Metadata metadata = new Metadata();
    private final Collection<MinecraftPlayer> viewers = new ArrayList<>();
    private byte mask;
    private Component customName = Component.empty();
    private boolean customNameVisible;
    private boolean silent;
    private boolean gravity = true;

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public boolean isOnFire() {
        return (this.mask & 0x01) == 0x01;
    }

    @Override
    public boolean isCrouching() {
        return (this.mask & 0x02) == 0x02;
    }

    @Override
    public boolean isSprinting() {
        return (this.mask & 0x08) == 0x08;
    }

    @Override
    public boolean isSwimming() {
        return (this.mask & 0x10) == 0x10;
    }

    @Override
    public boolean isInvisible() {
        return (this.mask & 0x20) == 0x20;
    }

    @Override
    public void setInvisible(boolean invisible) {
        if (this.isInvisible() == invisible) return;
        if (invisible) {
            this.mask |= 0x20;
        } else {
            this.mask &= ~0x20;
        }
        this.metadata.setByte(0, this.mask);
    }

    @Override
    public boolean isGlowing() {
        return (this.mask & 0x40) == 0x40;
    }

    @Override
    public void setGlowing(boolean glowing) {
        if (this.isGlowing() == glowing) return;
        if (glowing) {
            this.mask |= 0x20;
        } else {
            this.mask &= ~0x20;
        }
        this.metadata.setByte(0, this.mask);
    }

    @Override
    public @Nullable Component getCustomName() {
        return this.customName;
    }

    @Override
    public void setCustomName(@Nullable Component customName) {
        this.customName = customName;
        this.metadata.setComponent(2, customName);
    }

    @Override
    public boolean isCustomNameVisible() {
        return this.customNameVisible;
    }

    @Override
    public void setCustomNameVisible(boolean visible) {
        if (this.isCustomNameVisible() == visible) return;
        this.customNameVisible = visible;
        this.metadata.setBoolean(3, visible);
    }

    @Override
    public boolean isSilent() {
        return this.silent;
    }

    @Override
    public void setSilent(boolean silent) {
        if (this.isSilent() == silent) return;
        this.silent = silent;
        this.metadata.setBoolean(4, silent);
    }

    @Override
    public boolean hasGravity() {
        return this.gravity;
    }

    @Override
    public void setGravity(boolean gravity) {
        if (this.hasGravity() == gravity) return;
        this.gravity = gravity;
        this.metadata.setBoolean(5, !gravity);
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
        // TODO send add packets
    }

    @Override
    public void removeViewer(@NotNull Player player) {
        // TODO send remove packets
    }

    public void tick() {
        if (!this.metadata.changes().isEmpty()) {
            final var entityMetadata = new EntityMetadata(this.id, this.metadata.changes());
            this.metadata.reset();
            this.sendViewers(entityMetadata);
            if (this instanceof MinecraftPlayer player) {
                player.send(entityMetadata);
            }
        }
    }

    public void sendViewers(final Packet packet) {
        this.viewers.forEach(player -> player.send(packet));
    }

    public void sendViewers(final Packet packet1, final Packet packet2) {
        this.viewers.forEach(player -> {
            player.send(packet1);
            player.send(packet2);
        });
    }
}
