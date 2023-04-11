package de.bauhd.minecraft.server.protocol;

import com.google.gson.reflect.TypeToken;
import de.bauhd.minecraft.server.AdvancedMinecraftServer;
import de.bauhd.minecraft.server.api.MinecraftConfig;
import de.bauhd.minecraft.server.api.entity.MinecraftPlayer;
import de.bauhd.minecraft.server.api.entity.player.GameProfile;
import de.bauhd.minecraft.server.api.entity.player.PlayerInfoEntry;
import de.bauhd.minecraft.server.api.world.MinecraftWorld;
import de.bauhd.minecraft.server.api.world.chunk.MinecraftChunk;
import de.bauhd.minecraft.server.protocol.netty.codec.*;
import de.bauhd.minecraft.server.protocol.packet.Packet;
import de.bauhd.minecraft.server.protocol.packet.PacketHandler;
import de.bauhd.minecraft.server.protocol.packet.handler.HandshakePacketHandler;
import de.bauhd.minecraft.server.protocol.packet.handler.LoginPacketHandler;
import de.bauhd.minecraft.server.protocol.packet.handler.PlayPacketHandler;
import de.bauhd.minecraft.server.protocol.packet.handler.StatusPacketHandler;
import de.bauhd.minecraft.server.protocol.packet.login.CompressionPacket;
import de.bauhd.minecraft.server.protocol.packet.login.LoginSuccess;
import de.bauhd.minecraft.server.protocol.packet.play.*;
import de.bauhd.minecraft.server.protocol.packet.play.command.Commands;
import de.bauhd.minecraft.server.util.MojangUtil;
import io.netty5.buffer.Buffer;
import io.netty5.channel.Channel;
import io.netty5.channel.ChannelFutureListeners;
import io.netty5.channel.ChannelHandlerAdapter;
import io.netty5.channel.ChannelHandlerContext;
import io.netty5.util.ReferenceCountUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static de.bauhd.minecraft.server.api.entity.player.GameProfile.Property;

public final class Connection extends ChannelHandlerAdapter {

    private static final Logger LOGGER = LogManager.getLogger(Connection.class);

    private static final PluginMessage BRAND_PACKET;
    private static final CompressionPacket COMPRESSION_PACKET;
    private static final List<MinecraftChunk> CHUNKS;

    static {
        BRAND_PACKET = new PluginMessage("minecraft:brand", new byte[]{11, 110, 111, 116, 32, 118, 97, 110, 105, 108, 108, 97});

        final var threshold = -1; // TODO configuration
        if (threshold != -1) {
            COMPRESSION_PACKET = new CompressionPacket(threshold);
        } else {
            COMPRESSION_PACKET = null;
        }

        CHUNKS = new ArrayList<>();
        final var world = new MinecraftWorld();
        world.forChunksInRange(0, 0, 10, (x, z) -> CHUNKS.add(world.createChunk(x, z)));

    }

    private final AdvancedMinecraftServer server;
    private final Channel channel;
    private PacketHandler packetHandler;
    private Protocol.Version version;
    private String serverAddress;
    private String username;
    private MinecraftPlayer player;

    public Connection(final AdvancedMinecraftServer server, final Channel channel) {
        this.server = server;
        this.channel = channel;
        this.packetHandler = new HandshakePacketHandler(this);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object message) {
        try {
            if (message instanceof Packet packet) {
                if (packet.handle(this.packetHandler)) {
                    ctx.close();
                }
            }
        } finally {
            if (message instanceof Buffer buf) {
                buf.close();
            } else {
                ReferenceCountUtil.release(message);
            }
        }
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        if (this.player != null) {
            this.server.removePlayer(this.player.getUniqueId());
            this.server.getBossBarListener().onDisconnect(this.player);
            LOGGER.info("Connection from " + this.player.getUsername() + " closed.");
        }
        ctx.close();
    }

    public void play(GameProfile profile) {
        if (profile == null) {
            if (this.server.getConfiguration().mode() == MinecraftConfig.Mode.BUNGEECORD) {
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
        this.player = new MinecraftPlayer(this, profile.uniqueId(), this.username, profile);
        this.packetHandler = new PlayPacketHandler(this);
        this.send(new Login(this.player.getId()));
        this.send(new SynchronizePlayerPosition(this.player.getPosition()));

        this.server.addPlayer(this.player);

        this.send(PlayerInfo.add((List<? extends PlayerInfoEntry>) this.server.getAllPlayers(), this.version));
        this.send(new Commands(this.server.getCommandHandler().dispatcher().getRoot()));
        this.send(BRAND_PACKET);

        for (final var chunk : CHUNKS) {
            chunk.send(this.player);
        }

        this.player.sendViewers(new SpawnPlayer(this.player));
        this.server.getAllPlayers().forEach(other -> {
            if (other != this.player) {
                final var otherPlayer = (MinecraftPlayer) other;
                otherPlayer.send(PlayerInfo.add(this.player, otherPlayer.getVersion()));
                this.player.send(new SpawnPlayer(otherPlayer));
            }
        });
    }

    public void send(final Packet packet) {
        this.channel.writeAndFlush(packet);
    }

    public void sendAndClose(final Packet packet) {
        this.channel.writeAndFlush(packet).addListener(this.channel, ChannelFutureListeners.CLOSE);
    }

    public void set(final State state) {
        this.packetHandler = switch (state) {
            case STATUS -> new StatusPacketHandler(this);
            case LOGIN -> new LoginPacketHandler(this);
            default -> null;
        };

        this.channel.pipeline().get(MinecraftEncoder.class).set(state);
        this.channel.pipeline().get(MinecraftDecoder.class).set(state);
    }

    public void setState(final State state) {
        this.channel.pipeline().get(MinecraftEncoder.class).setState(state);
        this.channel.pipeline().get(MinecraftDecoder.class).setState(state);
    }

    public void enableEncryption(final byte[] secret) {
        final var secretKey = new SecretKeySpec(secret, "AES");

        this.channel.pipeline()
                .addBefore("frame-decoder", "cipher-decoder", new CipherDecoder(new JavaCipher(secretKey, Cipher.DECRYPT_MODE)))
                .addBefore("frame-encoder", "cipher-encoder", new CipherEncoder(new JavaCipher(secretKey, Cipher.ENCRYPT_MODE)));
    }

    private void addCompressionHandler() {
        this.channel.pipeline()
                .addAfter("frame-decoder", "compressor-decoder", new CompressorDecoder())
                .addAfter("compressor-decoder", "compressor-encoder", new CompressorEncoder(this.server))
                .remove("frame-decoder");
    }

    public void setVersion(final Protocol.Version version) {
        this.version = version;
    }

    public AdvancedMinecraftServer server() {
        return this.server;
    }

    public Protocol.Version version() {
        return this.version;
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
