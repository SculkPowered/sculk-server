package de.bauhd.minecraft.server.protocol;

import com.google.gson.reflect.TypeToken;
import de.bauhd.minecraft.server.DefaultMinecraftServer;
import de.bauhd.minecraft.server.Worker;
import de.bauhd.minecraft.server.api.entity.MinecraftPlayer;
import de.bauhd.minecraft.server.api.entity.player.GameProfile;
import de.bauhd.minecraft.server.api.world.Chunk;
import de.bauhd.minecraft.server.api.world.World;
import de.bauhd.minecraft.server.protocol.netty.codec.MinecraftDecoder;
import de.bauhd.minecraft.server.protocol.netty.codec.MinecraftEncoder;
import de.bauhd.minecraft.server.protocol.packet.Packet;
import de.bauhd.minecraft.server.protocol.packet.login.LoginSuccess;
import de.bauhd.minecraft.server.protocol.packet.play.Commands;
import de.bauhd.minecraft.server.protocol.packet.play.Login;
import de.bauhd.minecraft.server.protocol.packet.play.PlayerInfo;
import de.bauhd.minecraft.server.protocol.packet.play.SpawnPlayer;
import de.bauhd.minecraft.server.util.MojangUtil;
import io.netty5.channel.Channel;
import io.netty5.channel.ChannelFutureListeners;
import io.netty5.channel.ChannelHandlerAdapter;
import io.netty5.channel.ChannelHandlerContext;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static de.bauhd.minecraft.server.api.entity.player.GameProfile.Property;

public final class Connection extends ChannelHandlerAdapter {

    private static final List<Chunk> CHUNKS;

    static {
        CHUNKS = new ArrayList<>();
        final var world = new World();
        world.chunksInRange(0, 0, 10, (x, z) -> CHUNKS.add(world.createChunk(x, z)));
    }

    private final Channel channel;
    private String serverAddress;
    private String username;
    private MinecraftPlayer player;

    public Connection(final Channel channel) {
        this.channel = channel;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object message) {
        if (message instanceof Packet packet) {
            packet.handle(this);
        }
    }

    public void play(GameProfile profile) {
        if (profile == null) {
            if (DefaultMinecraftServer.BUNGEECORD) {
                final var arguments = this.serverAddress.split("\00");
                this.serverAddress = arguments[0];
                final var properties = (Property[]) DefaultMinecraftServer.GSON.fromJson(arguments[3], TypeToken.getArray(Property.class));
                profile = new GameProfile(MojangUtil.fromMojang(arguments[2]), this.username, List.of(properties));
            } else {
                profile = new GameProfile(
                        UUID.nameUUIDFromBytes(("OfflinePlayer:" + this.username).getBytes(StandardCharsets.UTF_8)),
                        this.username,
                        List.of(MojangUtil.getSkinFromName(this.username))
                );
            }
        }
        this.send(new LoginSuccess(profile.uniqueId(), this.username));

        this.setState(State.PLAY);
        this.player = new MinecraftPlayer(this.channel, profile.uniqueId(), this.username, profile);
        this.send(new Login(this.player.getId()));

        Worker.PLAYERS.add(this.player);

        this.send(PlayerInfo.add(this.player));
        this.send(new Commands(DefaultMinecraftServer.getInstance().getCommandHandler().dispatcher().getRoot()));

        for (final var chunk : CHUNKS) {
            chunk.send(this.player);
        }

        /*Worker.PLAYERS.forEach(player -> {
            if (player != this.player) {
                this.send(PlayerInfo.add(player));
                System.out.println(player.getId() + " - " + player.getUniqueId());
                this.send(new SpawnPlayer(player.getId(), player.getUniqueId()));
            }
        });*/
    }

    public void send(final Packet packet) {
        this.channel.writeAndFlush(packet);
    }

    public void sendAndClose(final Packet packet) {
        this.channel.writeAndFlush(packet).addListener(this.channel, ChannelFutureListeners.CLOSE);
    }

    public void set(final State state, final Protocol.Version version) {
        this.channel.pipeline().get(MinecraftEncoder.class).set(state, version);
        this.channel.pipeline().get(MinecraftDecoder.class).set(state, version);
    }

    public void setState(final State state) {
        this.channel.pipeline().get(MinecraftEncoder.class).setState(state);
        this.channel.pipeline().get(MinecraftDecoder.class).setState(state);
    }

    public void setServerAddress(final String serverAddress) {
        this.serverAddress = serverAddress;
    }

    public String username() {
        return this.username;
    }

    public void setUsername(final String username) {
        this.username = username;
    }

    public MinecraftPlayer player() {
        return this.player;
    }
}
