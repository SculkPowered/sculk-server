package de.bauhd.minecraft.server.api.entity;

import de.bauhd.minecraft.server.protocol.packet.Packet;
import de.bauhd.minecraft.server.protocol.packet.play.ActionBar;
import de.bauhd.minecraft.server.protocol.packet.play.SystemChatMessage;
import de.bauhd.minecraft.server.protocol.packet.play.TabListHeaderFooter;
import de.bauhd.minecraft.server.protocol.packet.play.title.Subtitle;
import de.bauhd.minecraft.server.protocol.packet.play.title.TitleAnimationTimes;
import io.netty5.channel.Channel;
import net.kyori.adventure.bossbar.BossBar;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.title.Title;
import org.jetbrains.annotations.NotNull;

import java.util.Set;
import java.util.UUID;

public final class MinecraftPlayer implements Player {

    private final Channel channel;
    private final UUID uniqueId;
    private final String name;
    private final UUID bossBarUniqueId = UUID.randomUUID(); // maybe change
    private final BossBar.Listener bossBarListener = new BossBar.Listener() {

        private final UUID uniqueId = MinecraftPlayer.this.bossBarUniqueId;

        @Override
        public void bossBarNameChanged(@NotNull BossBar bar, @NotNull Component oldName, @NotNull Component newName) {
            MinecraftPlayer.this.send(de.bauhd.minecraft.server.protocol.packet.play.BossBar.update(this.uniqueId, newName));
        }

        @Override
        public void bossBarProgressChanged(@NotNull BossBar bar, float oldProgress, float newProgress) {
            MinecraftPlayer.this.send(de.bauhd.minecraft.server.protocol.packet.play.BossBar.update(this.uniqueId, newProgress));
        }

        @Override
        public void bossBarColorChanged(@NotNull BossBar bar, BossBar.@NotNull Color oldColor, BossBar.@NotNull Color newColor) {
            MinecraftPlayer.this.send(de.bauhd.minecraft.server.protocol.packet.play.BossBar.update(this.uniqueId, newColor.ordinal(), bar.overlay().ordinal()));
        }

        @Override
        public void bossBarOverlayChanged(@NotNull BossBar bar, BossBar.@NotNull Overlay oldOverlay, BossBar.@NotNull Overlay newOverlay) {
            MinecraftPlayer.this.send(de.bauhd.minecraft.server.protocol.packet.play.BossBar.update(this.uniqueId, bar.color().ordinal(), newOverlay.ordinal()));
        }

        @Override
        public void bossBarFlagsChanged(@NotNull BossBar bar, @NotNull Set<BossBar.Flag> flagsAdded, @NotNull Set<BossBar.Flag> flagsRemoved) {
            // TODO
        }
    };

    public MinecraftPlayer(final Channel channel, final UUID uniqueId, final String name) {
        this.channel = channel;
        this.uniqueId = uniqueId;
        this.name = name;
    }

    public @NotNull UUID getUniqueId() {
        return this.uniqueId;
    }

    public @NotNull String getUsername() {
        return this.name;
    }

    @Override
    public void sendMessage(@NotNull Component message) {
        this.send(new SystemChatMessage(message, true));
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
    public void showTitle(@NotNull Title title) {
        this.send(new de.bauhd.minecraft.server.protocol.packet.play.title.Title(title.title()));
        this.send(new Subtitle(title.subtitle()));

        final var times = title.times() != null ? title.times() : Title.DEFAULT_TIMES;
        assert times != null;
        this.send(new TitleAnimationTimes(times.fadeIn().getNano(), times.stay().getNano(), times.fadeOut().getNano())); // TODO to ticks
    }

    @Override
    public void showBossBar(@NotNull BossBar bar) {
        bar.addListener(this.bossBarListener);
        this.send(de.bauhd.minecraft.server.protocol.packet.play.BossBar.add(this.bossBarUniqueId,
                bar.name(), bar.progress(), bar.color().ordinal(), bar.overlay().ordinal(), 0x01)); // TODO flags
    }

    @Override
    public void hideBossBar(@NotNull BossBar bar) {
        bar.removeListener(this.bossBarListener);
        this.send(de.bauhd.minecraft.server.protocol.packet.play.BossBar.remove(this.bossBarUniqueId));
    }

    @Override
    public @NotNull EntityType getType() {
        return EntityType.PLAYER;
    }

    public void send(final Packet packet) {
        this.channel.writeAndFlush(packet);
    }
}
