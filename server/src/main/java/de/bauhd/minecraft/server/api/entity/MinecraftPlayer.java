package de.bauhd.minecraft.server.api.entity;

import de.bauhd.minecraft.server.api.entity.player.GameProfile;
import de.bauhd.minecraft.server.api.entity.player.Player;
import de.bauhd.minecraft.server.protocol.packet.Packet;
import de.bauhd.minecraft.server.protocol.packet.login.Disconnect;
import de.bauhd.minecraft.server.protocol.packet.play.ActionBar;
import de.bauhd.minecraft.server.protocol.packet.play.SystemChatMessage;
import de.bauhd.minecraft.server.protocol.packet.play.TabListHeaderFooter;
import de.bauhd.minecraft.server.protocol.packet.play.title.Subtitle;
import de.bauhd.minecraft.server.protocol.packet.play.title.TitleAnimationTimes;
import io.netty5.channel.Channel;
import net.kyori.adventure.bossbar.BossBar;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.title.Title;
import net.kyori.adventure.title.TitlePart;
import org.jetbrains.annotations.NotNull;

import java.util.Set;
import java.util.UUID;

public final class MinecraftPlayer extends AbstractEntity implements Player {

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
    public @NotNull GameProfile getProfile() {
        return null;
    }

    @Override
    public void disconnect(@NotNull Component component) {
        this.send(new Disconnect(component));
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
    public <T> void sendTitlePart(@NotNull TitlePart<T> part, @NotNull T value) {
        if (part == TitlePart.TITLE) {
            this.send(new de.bauhd.minecraft.server.protocol.packet.play.title.Title((Component) value));
        } else if (part == TitlePart.SUBTITLE) {
            this.send(new Subtitle((Component) value));
        } else if (part == TitlePart.TIMES) {
            final var times = (Title.Times) value;

            this.send(new TitleAnimationTimes(times.fadeIn().getNano(), times.stay().getNano(), times.fadeOut().getNano())); // TODO to ticks
        }
    }

    @Override
    public void showBossBar(@NotNull BossBar bar) {
        byte flags = 0;
        if (bar.flags().contains(BossBar.Flag.DARKEN_SCREEN)) {
            flags |= 0x1;
        }
        if (bar.flags().contains(BossBar.Flag.PLAY_BOSS_MUSIC)) {
            flags |= 0x2;
        }
        if (bar.flags().contains(BossBar.Flag.CREATE_WORLD_FOG)) {
            flags |= 0x4;
        }

        bar.addListener(this.bossBarListener);
        this.send(de.bauhd.minecraft.server.protocol.packet.play.BossBar.add(this.bossBarUniqueId,
                bar.name(), bar.progress(), bar.color().ordinal(), bar.overlay().ordinal(), flags));
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
