package de.bauhd.minecraft.server.protocol;

import com.google.gson.reflect.TypeToken;
import de.bauhd.minecraft.server.AdvancedMinecraftServer;
import de.bauhd.minecraft.server.api.MinecraftConfig;
import de.bauhd.minecraft.server.api.entity.MinecraftPlayer;
import de.bauhd.minecraft.server.api.entity.player.GameProfile;
import de.bauhd.minecraft.server.api.entity.player.PlayerInfoEntry;
import de.bauhd.minecraft.server.api.world.Chunk;
import de.bauhd.minecraft.server.api.world.World;
import de.bauhd.minecraft.server.protocol.netty.codec.CompressorDecoder;
import de.bauhd.minecraft.server.protocol.netty.codec.CompressorEncoder;
import de.bauhd.minecraft.server.protocol.netty.codec.MinecraftDecoder;
import de.bauhd.minecraft.server.protocol.netty.codec.MinecraftEncoder;
import de.bauhd.minecraft.server.protocol.packet.Packet;
import de.bauhd.minecraft.server.protocol.packet.login.CompressionPacket;
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

    private static final AdvancedMinecraftServer SERVER = AdvancedMinecraftServer.getInstance();
    private static final PluginMessage BRAND_PACKET;
    private static final CompressionPacket COMPRESSION_PACKET;
    private static final List<Chunk> CHUNKS;

    static {
        BRAND_PACKET = new PluginMessage("minecraft:brand", new byte[]{11, 110, 111, 116, 32, 118, 97, 110, 105, 108, 108, 97});

        final var threshold = SERVER.getConfiguration().compressionThreshold();
        if (threshold != -1) {
            COMPRESSION_PACKET = new CompressionPacket(threshold);
        } else {
            COMPRESSION_PACKET = null;
        }

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
            SERVER.removePlayer(this.player.getUniqueId());
            SERVER.getBossBarListener().onDisconnect(this.player);
        }
        ctx.close();
    }

    public void play(GameProfile profile) {
        if (profile == null) {
            if (SERVER.getConfiguration().mode() == MinecraftConfig.Mode.BUNGEECORD) {
                final var arguments = this.serverAddress.split("\00");
                this.serverAddress = arguments[0];
                final var properties = (Property[]) AdvancedMinecraftServer.GSON.fromJson(arguments[3], TypeToken.getArray(Property.class).getType());
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
        if (COMPRESSION_PACKET != null) {
            this.send(COMPRESSION_PACKET);
            this.addCompressionHandler();
        }
        this.send(new LoginSuccess(profile.uniqueId(), this.username));

        this.setState(State.PLAY);
        this.player = new MinecraftPlayer(this.channel, profile.uniqueId(), this.username, profile);
        this.send(new Login(this.player.getId()));
        this.send(new SynchronizePlayerPosition(this.player.getPosition()));

        SERVER.addPlayer(this.player);

        this.send(PlayerInfo.add((List<? extends PlayerInfoEntry>) SERVER.getAllPlayers()));
        this.send(new Commands(SERVER.getCommandHandler().dispatcher().getRoot()));
        this.send(BRAND_PACKET);

        for (final var chunk : CHUNKS) {
            chunk.send(this.player);
        }

        this.player.sendViewers(
                PlayerInfo.add(this.player),
                new SpawnPlayer(this.player)
        );
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

    private void addCompressionHandler() {
        this.channel.pipeline()
                .addAfter("frame-decoder", "compressor-decoder", new CompressorDecoder())
                .addAfter("compressor-decoder", "compressor-encoder", new CompressorEncoder())
                .remove("frame-decoder");
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
