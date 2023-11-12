package io.github.sculkpowered.server.adventure;

import static io.github.sculkpowered.server.protocol.packet.play.BossBar.update;

import java.util.Set;
import net.kyori.adventure.bossbar.BossBar;
import net.kyori.adventure.bossbar.BossBarImplementation;
import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings("UnstableApiUsage")
public final class BossBarListener implements BossBar.Listener {

  @Override
  public void bossBarNameChanged(@NotNull BossBar bar, @NotNull Component oldName,
      @NotNull Component newName) {
    final var impl = BossBarImplementation.get(bar, BossBarProvider.Impl.class);
    if (!impl.players().isEmpty()) {
      impl.send(update(impl.uniqueId(), newName));
    }
  }

  @Override
  public void bossBarProgressChanged(@NotNull BossBar bar, float oldProgress, float newProgress) {
    final var impl = BossBarImplementation.get(bar, BossBarProvider.Impl.class);
    if (!impl.players().isEmpty()) {
      impl.send(update(impl.uniqueId(), newProgress));
    }
  }

  @Override
  public void bossBarColorChanged(@NotNull BossBar bar, BossBar.@NotNull Color oldColor,
      BossBar.@NotNull Color newColor) {
    final var impl = BossBarImplementation.get(bar, BossBarProvider.Impl.class);
    if (!impl.players().isEmpty()) {
      impl.send(update(impl.uniqueId(), newColor.ordinal(), bar.overlay().ordinal()));
    }
  }

  @Override
  public void bossBarOverlayChanged(@NotNull BossBar bar, BossBar.@NotNull Overlay oldOverlay,
      BossBar.@NotNull Overlay newOverlay) {
    final var impl = BossBarImplementation.get(bar, BossBarProvider.Impl.class);
    if (!impl.players().isEmpty()) {
      impl.send(update(impl.uniqueId(), bar.color().ordinal(), newOverlay.ordinal()));
    }
  }

  @Override
  public void bossBarFlagsChanged(@NotNull BossBar bar, @NotNull Set<BossBar.Flag> flagsAdded,
      @NotNull Set<BossBar.Flag> flagsRemoved) {
    final var impl = BossBarImplementation.get(bar, BossBarProvider.Impl.class);
    if (!impl.players().isEmpty()) {
      impl.send(update(impl.uniqueId(), BossBarProvider.flags(bar)));
    }
  }
}
