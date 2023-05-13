package de.bauhd.minecraft.server.protocol;

import com.google.gson.reflect.TypeToken;
import de.bauhd.minecraft.server.AdvancedMinecraftServer;
import de.bauhd.minecraft.server.MinecraftConfig;
import de.bauhd.minecraft.server.entity.MinecraftPlayer;
import de.bauhd.minecraft.server.entity.player.GameProfile;
import de.bauhd.minecraft.server.entity.player.PlayerInfoEntry;
import de.bauhd.minecraft.server.event.player.PlayerJoinEvent;
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
import de.bauhd.minecraft.server.world.MinecraftWorld;
import io.netty5.buffer.Buffer;
import io.netty5.channel.Channel;
import io.netty5.channel.ChannelFutureListeners;
import io.netty5.channel.ChannelHandlerAdapter;
import io.netty5.channel.ChannelHandlerContext;
import io.netty5.util.ReferenceCountUtil;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.function.BiConsumer;

import static de.bauhd.minecraft.server.entity.player.GameProfile.Property;

public final class Connection extends ChannelHandlerAdapter {

    private static final Logger LOGGER = LogManager.getLogger(Connection.class);

    private static final PluginMessage BRAND_PACKET =
            new PluginMessage("minecraft:brand", new byte[]{11, 110, 111, 116, 32, 118, 97, 110, 105, 108, 108, 97});

    public static CompressionPacket COMPRESSION_PACKET;

    private final AdvancedMinecraftServer server;
    private final Channel channel;
    private PacketHandler packetHandler;
    private Protocol.Version version;
    private String serverAddress;
    private String username;
    private MinecraftPlayer player;
    private boolean afterLoginPacket;

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

            final var world = this.player.getWorld();
            final var position = this.player.getPosition();
            this.forChunksInRange(world.chunkCoordinate((int) position.x()), world.chunkCoordinate((int) position.z()),
                    10, (x, z) -> world.getChunk(x, z).viewers().remove(this.player));

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
                profile = new GameProfile(
                        UUID.nameUUIDFromBytes(("OfflinePlayer:" + this.username).getBytes(StandardCharsets.UTF_8)),
                        this.username,
                        List.of()
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
        this.server.addPlayer(this.player);
        this.server.getEventHandler().call(new PlayerJoinEvent(this.player)).thenAcceptAsync((event) -> {
            final var world = ((MinecraftWorld) this.player.getWorld());
            if (world == null) {
                this.player.disconnect(Component.text("No world found.", NamedTextColor.RED));
                return;
            }
            this.send(new Login(this.player.getId(), (byte) this.player.getGameMode().ordinal(),
                    this.server.getBiomeHandler().nbt(), this.server.getDimensionHandler().nbt(),
                    world.getDimension().nbt().getString("name")));
            this.afterLoginPacket = true;

            this.send(BRAND_PACKET);
            this.send(new Commands(this.server.getCommandHandler().dispatcher().getRoot()));
            final var position = this.player.getPosition();
            this.send(new SynchronizePlayerPosition(position));
            this.send(PlayerInfo.add((List<? extends PlayerInfoEntry>) this.server.getAllPlayers()));
            this.send(new SpawnPosition(position));

            final var chunkX = (int) position.x() >> 4;
            final var chunkZ = (int) position.z() >> 4;
            this.forChunksInRange(chunkX, chunkZ, 10, (x, z) -> {
                final var chunk = world.getChunk(x, z);
                chunk.send(this.player);
                chunk.viewers().add(this.player);
            });
            this.send(new CenterChunk(chunkX, chunkZ));

            final var playerInfo = PlayerInfo.add(Collections.singletonList(this.player));
            this.player.sendViewers(new SpawnPlayer(this.player));
            for (final var other : this.server.getAllPlayers()) {
                if (other != this.player) {
                    final var otherPlayer = (MinecraftPlayer) other;
                    otherPlayer.send(playerInfo);
                    this.player.send(new SpawnPlayer(otherPlayer));
                }
            }
        }, this.channel.executor()).exceptionally(throwable -> {
            LOGGER.error("Exception during login of player {}", this.player.getUsername(), throwable);
            return null;
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

    public boolean afterLoginPacket() {
        return this.afterLoginPacket;
    }

    public void forChunksInRange(final int chunkX, final int chunkZ, final int range, final BiConsumer<Integer, Integer> chunk) {
        for (var x = -range; x <= range; x++) {
            for (var z = -range; z <= range; z++) {
                chunk.accept(chunkX + x, chunkZ + z);
            }
        }
    }
}
