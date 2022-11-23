package de.bauhd.minecraft.server.api.entity;

import de.bauhd.minecraft.server.DefaultMinecraftServer;
import de.bauhd.minecraft.server.api.entity.player.GameMode;
import de.bauhd.minecraft.server.api.entity.player.GameProfile;
import de.bauhd.minecraft.server.api.entity.player.Player;
import de.bauhd.minecraft.server.api.world.Position;
import de.bauhd.minecraft.server.protocol.packet.GameEvent;
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
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public final class MinecraftPlayer extends AbstractLivingEntity implements Player {

    private final Channel channel;
    private final UUID uniqueId;
    private final String name;
    private final GameProfile profile;
    private GameMode gameMode;
    private Component displayName;
    private Position position = new Position(8.5, 40, 8.5);

    public MinecraftPlayer(final Channel channel, final UUID uniqueId, final String name, final GameProfile profile) {
        this.channel = channel;
        this.uniqueId = uniqueId;
        this.name = name;
        this.profile = profile;
    }

    public @NotNull UUID getUniqueId() {
        return this.uniqueId;
    }

    public @NotNull String getUsername() {
        return this.name;
    }

    @Override
    public @NotNull GameProfile getProfile() {
        return this.profile;
    }

    public Position getPosition() {
        return this.position;
    }

    public void setPosition(final Position position) {
        this.position = position;
    }

    @Override
    public @Nullable Component getDisplayName() {
        return this.displayName;
    }

    @Override
    public int getPing() {
        return 1;
    }

    @Override
    public void setDisplayName(@Nullable Component displayName) {
        this.displayName = displayName;
    }

    @Override
    public @NotNull GameMode getGameMode() {
        return this.gameMode;
    }

    @Override
    public void setGameMode(@NotNull GameMode gameMode) {
        this.gameMode = gameMode;
        this.send(new GameEvent(3, gameMode.ordinal()));
    }

    @Override
    public void disconnect(@NotNull Component component) {
        this.send(new Disconnect(component));
    }

    @Override
    public void sendMessage(@NotNull Component message) {
        this.send(new SystemChatMessage(message, false));
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
        DefaultMinecraftServer.getInstance().getBossBarListener().showBossBar(this, bar);
    }

    @Override
    public void hideBossBar(@NotNull BossBar bar) {
        DefaultMinecraftServer.getInstance().getBossBarListener().hideBossBar(this, bar);
    }

    @Override
    public @NotNull EntityType getType() {
        return EntityType.PLAYER;
    }

    public void send(final Packet packet) {
        this.channel.writeAndFlush(packet);
    }
}
