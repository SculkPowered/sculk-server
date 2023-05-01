package de.bauhd.minecraft.server.entity;

import de.bauhd.minecraft.server.entity.player.Player;
import de.bauhd.minecraft.server.protocol.packet.Packet;
import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class AbstractEntity implements Entity {

    private static final AtomicInteger CURRENT_ID = new AtomicInteger(0);

    private final int id = CURRENT_ID.getAndIncrement();
    private final Collection<MinecraftPlayer> viewers = new ArrayList<>();

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public boolean isOnFire() {
        return false;
    }

    @Override
    public boolean isCrouching() {
        return false;
    }

    @Override
    public boolean isSprinting() {
        return false;
    }

    @Override
    public boolean isSwimming() {
        return false;
    }

    @Override
    public boolean isInvisible() {
        return false;
    }

    @Override
    public void setInvisible(boolean invisible) {

    }

    @Override
    public boolean isGlowing() {
        return false;
    }

    @Override
    public void setGlowing(boolean glowing) {

    }

    @Override
    public @Nullable Component getCustomName() {
        return null;
    }

    @Override
    public void setCustomName(@Nullable Component customName) {

    }

    @Override
    public boolean hasGravity() {
        return false;
    }

    @Override
    public void setGravity(boolean gravity) {

    }

    @Override
    public @NotNull Pose getPose() {
        return null;
    }

    @Override
    public void setPose(@NotNull Pose pose) {
        /*final var packet = new EntityMetadata(this.getId(), 6, 18, pose.ordinal());
        for (final var player : Worker.PLAYERS) {
            player.send(packet);
        }*/
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
