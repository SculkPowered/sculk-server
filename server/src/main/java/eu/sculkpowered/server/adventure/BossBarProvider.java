package eu.sculkpowered.server.adventure;

import eu.sculkpowered.server.entity.player.SculkPlayer;
import eu.sculkpowered.server.protocol.packet.ClientboundPacket;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import net.kyori.adventure.bossbar.BossBar;
import net.kyori.adventure.bossbar.BossBarImplementation;
import net.kyori.adventure.bossbar.BossBarViewer;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings("UnstableApiUsage") // this is a platform
public final class BossBarProvider implements BossBarImplementation.Provider {

  private static final BossBarListener LISTENER = new BossBarListener();

  @Override
  public @NotNull BossBarImplementation create(@NotNull BossBar bar) {
    bar.addListener(LISTENER);
    return new Impl();
  }

  public static final class Impl implements BossBarImplementation {

    private final UUID uniqueId;
    private final Set<SculkPlayer> viewers;

    public Impl() {
      this.uniqueId = UUID.randomUUID();
      this.viewers = new HashSet<>();
    }

    public UUID uniqueId() {
      return this.uniqueId;
    }

    public Set<SculkPlayer> players() {
      return this.viewers;
    }

    public void send(final ClientboundPacket packet) {
      for (final var viewer : this.viewers) {
        viewer.send(packet);
      }
    }

    @Override
    public @NotNull Iterable<? extends BossBarViewer> viewers() {
      return this.viewers;
    }
  }

  public static byte flags(final BossBar bar) {
    final var flagSet = bar.flags();
    byte flags = 0;
    if (flagSet.contains(BossBar.Flag.DARKEN_SCREEN)) {
      flags |= 0x1;
    }
    if (flagSet.contains(BossBar.Flag.PLAY_BOSS_MUSIC)) {
      flags |= 0x2;
    }
    if (flagSet.contains(BossBar.Flag.CREATE_WORLD_FOG)) {
      flags |= 0x4;
    }
    return flags;
  }
}
