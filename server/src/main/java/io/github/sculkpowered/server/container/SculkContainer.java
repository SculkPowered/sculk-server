package io.github.sculkpowered.server.container;

import io.github.sculkpowered.server.Viewable;
import io.github.sculkpowered.server.container.item.ItemStack;
import io.github.sculkpowered.server.entity.player.Player;
import io.github.sculkpowered.server.entity.player.SculkPlayer;
import io.github.sculkpowered.server.protocol.packet.Packet;
import io.github.sculkpowered.server.protocol.packet.play.container.ContainerContent;
import io.github.sculkpowered.server.protocol.packet.play.container.ContainerProperty;
import io.github.sculkpowered.server.protocol.packet.play.container.ContainerSlot;
import io.github.sculkpowered.server.protocol.packet.play.container.OpenScreen;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;

public abstract class SculkContainer extends AbstractContainer implements Container, Viewable {

  private final Component title;
  private final Set<SculkPlayer> viewers = new HashSet<>();

  public SculkContainer(final Type type, final Component title) {
    super(type);
    this.title = title;
  }

  @Override
  public @NotNull Component title() {
    return this.title;
  }

  @Override
  public void item(int index, @NotNull ItemStack itemStack) {
    final var containerSlotPacket = new ContainerSlot(
        (byte) 1, this.incrementState(), (short) index, itemStack);
    for (final var viewer : this.viewers) {
      viewer.send(containerSlotPacket);
    }
    super.item(index, itemStack);
  }

  @Override
  public @NotNull Collection<Player> viewers() {
    return List.copyOf(this.viewers);
  }

  @Override
  public boolean addViewer(@NotNull Player player) {
    final var sculkPlayer = (SculkPlayer) player;
    sculkPlayer.send(new OpenScreen(1, this.type().ordinal(), this.title));
    sculkPlayer.send(new ContainerContent((byte) 1, this.incrementState(),
        this.items(), ItemStack.empty()));
    this.sendProperties(sculkPlayer);
    return this.viewers.add(sculkPlayer);
  }

  @Override
  public boolean removeViewer(@NotNull Player player) {
    return this.viewers.remove((SculkPlayer) player);
  }

  public Packet property(final int key, final int value) {
    return new ContainerProperty(1, (short) key, (short) value);
  }

  public abstract void sendProperties(SculkPlayer player);

  protected void sendProperty(final int key, final int value) {
    final var packet = this.property(key, value);
    for (final var viewer : this.viewers) {
      viewer.send(packet);
    }
  }
}
