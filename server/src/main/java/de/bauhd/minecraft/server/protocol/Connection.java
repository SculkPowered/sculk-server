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
import de.bauhd.minecraft.server.protocol.packet.play.*;
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

    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        if (this.player != null) {
            Worker.PLAYERS.remove(this.player);
        }
        ctx.close();
    }

    public void play(GameProfile profile) {
        if (profile == null) {
            if (DefaultMinecraftServer.BUNGEECORD) {
                final var arguments = this.serverAddress.split("\00");
                this.serverAddress = arguments[0];
                final var properties = (Property[]) DefaultMinecraftServer.GSON.fromJson(arguments[3], TypeToken.getArray(Property.class));
                profile = new GameProfile(MojangUtil.fromMojang(arguments[2]), this.username, List.of(properties));
            } else {
                final var skin = MojangUtil.getSkinFromName(this.username);
                profile = new GameProfile(
                        UUID.nameUUIDFromBytes(("OfflinePlayer:" + this.username).getBytes(StandardCharsets.UTF_8)),
                        this.username,
                        skin != null ? List.of(skin) : List.of()
                );
            }
        }
        this.send(new LoginSuccess(profile.uniqueId(), this.username));

        this.setState(State.PLAY);
        this.player = new MinecraftPlayer(this.channel, profile.uniqueId(), this.username, profile);
        this.send(new Login(this.player.getId()));
        this.send(new SynchronizePlayerPosition(this.player.getPosition()));

        Worker.PLAYERS.add(this.player);

        this.send(PlayerInfo.add(Worker.PLAYERS));
        this.send(new Commands(DefaultMinecraftServer.getInstance().getCommandHandler().dispatcher().getRoot()));

        for (final var chunk : CHUNKS) {
            chunk.send(this.player);
        }

        final var addPlayerInfo = PlayerInfo.add(this.player);
        final var spawnPlayer = new SpawnPlayer(this.player);

        Worker.PLAYERS.forEach(player -> {
            if (player != this.player) {
                this.send(new SpawnPlayer(player));
                player.send(addPlayerInfo);
                player.send(spawnPlayer);
            }
        });
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
