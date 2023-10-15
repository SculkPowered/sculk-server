package de.bauhd.sculk.container;

import de.bauhd.sculk.Viewable;
import de.bauhd.sculk.container.item.ItemStack;
import de.bauhd.sculk.entity.player.Player;
import de.bauhd.sculk.entity.player.SculkPlayer;
import de.bauhd.sculk.protocol.packet.Packet;
import de.bauhd.sculk.protocol.packet.play.container.ContainerProperty;
import de.bauhd.sculk.util.ItemList;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;

public abstract class SculkContainer implements Container, Viewable {

  public final ItemList items = new ItemList(this.getType().size());
  private final Component title;
  private final List<Player> viewers = new ArrayList<>();

  public SculkContainer(final Component title) {
    this.title = title;
  }

  @Override
  public @NotNull Component getTitle() {
    return this.title;
  }

  @Override
  public void setItem(int index, @NotNull ItemStack itemStack) {
    this.items.set(index, itemStack);
  }

  @Override
  public @NotNull ItemStack getItem(int index) {
    return this.items.get(index);
  }

  @Override
  public @NotNull Collection<Player> getViewers() {
    return this.viewers;
  }

  @Override
  public void addViewer(@NotNull Player player) {
    this.viewers.add(player);
  }

  @Override
  public void removeViewer(@NotNull Player player) {
    this.viewers.remove(player);
  }

  public Packet property(final int key, final int value) {
    return new ContainerProperty(1, (short) key, (short) value);
  }

  public abstract void sendProperties(SculkPlayer player);

  protected void sendProperty(final int key, final int value) {
    final var packet = this.property(key, value);
    for (final var viewer : this.viewers) {
      ((SculkPlayer) viewer).send(packet);
    }
  }
}
