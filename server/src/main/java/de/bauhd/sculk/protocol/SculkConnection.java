package de.bauhd.sculk.protocol;

import com.google.gson.reflect.TypeToken;
import com.mojang.brigadier.tree.RootCommandNode;
import com.velocitypowered.natives.util.Natives;
import de.bauhd.sculk.MinecraftConfig;
import de.bauhd.sculk.SculkServer;
import de.bauhd.sculk.command.CommandSource;
import de.bauhd.sculk.connection.Connection;
import de.bauhd.sculk.entity.player.GameProfile;
import de.bauhd.sculk.entity.player.Player;
import de.bauhd.sculk.entity.player.PlayerInfoEntry;
import de.bauhd.sculk.entity.player.SculkPlayer;
import de.bauhd.sculk.event.player.PlayerDisconnectEvent;
import de.bauhd.sculk.event.player.PlayerInitialEvent;
import de.bauhd.sculk.event.player.PlayerJoinEvent;
import de.bauhd.sculk.protocol.netty.codec.*;
import de.bauhd.sculk.protocol.packet.Packet;
import de.bauhd.sculk.protocol.packet.PacketHandler;
import de.bauhd.sculk.protocol.packet.handler.HandshakePacketHandler;
import de.bauhd.sculk.protocol.packet.handler.LoginPacketHandler;
import de.bauhd.sculk.protocol.packet.handler.PlayPacketHandler;
import de.bauhd.sculk.protocol.packet.handler.StatusPacketHandler;
import de.bauhd.sculk.protocol.packet.login.CompressionPacket;
import de.bauhd.sculk.protocol.packet.login.Disconnect;
import de.bauhd.sculk.protocol.packet.login.LoginSuccess;
import de.bauhd.sculk.protocol.packet.play.*;
import de.bauhd.sculk.protocol.packet.play.command.Commands;
import de.bauhd.sculk.util.MojangUtil;
import de.bauhd.sculk.world.Position;
import de.bauhd.sculk.world.chunk.SculkChunk;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;
import net.kyori.adventure.permission.PermissionChecker;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.util.TriState;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

import javax.crypto.spec.SecretKeySpec;
import java.net.SocketAddress;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeoutException;
import java.util.function.BiConsumer;

import static de.bauhd.sculk.entity.player.GameProfile.Property;
import static de.bauhd.sculk.util.Constants.*;
import static de.bauhd.sculk.util.CoordinateUtil.chunkCoordinate;

public final class SculkConnection extends ChannelInboundHandlerAdapter implements Connection {

    private static final Logger LOGGER = LogManager.getLogger(SculkConnection.class);

    private static final PluginMessage BRAND_PACKET =
            new PluginMessage("minecraft:brand", new byte[]{11, 110, 111, 116, 32, 118, 97, 110, 105, 108, 108, 97});

    public static CompressionPacket COMPRESSION_PACKET;

    private final SculkServer server;
    private final Channel channel;
    private State state;
    private PacketHandler packetHandler;
    private int version;
    private String serverAddress;
    private String username;
    private SculkPlayer player;

    public SculkConnection(final SculkServer server, final Channel channel) {
        this.server = server;
        this.channel = channel;
        this.setState(State.HANDSHAKE);
    }

    @Override
    public void channelRead(@NotNull ChannelHandlerContext ctx, @NotNull Object message) {
        try {
            if (!ctx.channel().isActive()) {
                return;
            }

            if (message instanceof Packet packet) {
                if (!packet.handle(this.packetHandler)) {
                    if (this.state == State.HANDSHAKE) {
                        ctx.close();
                    }
                }
            }
        } finally {
            ReferenceCountUtil.release(message);
        }
    }

    @Override
    public void channelInactive(@NotNull ChannelHandlerContext ctx) {
        if (this.player != null) {
            this.server.removePlayer(this.player.getUniqueId());
            this.server.getBossBarListener().onDisconnect(this.player);

            final var world = this.player.getWorld();
            final var position = this.player.getPosition();
            if (world != null) {
                this.forChunksInRange(chunkCoordinate(position.x()), chunkCoordinate(position.z()),
                        this.player.getSettings().getViewDistance(), (x, z) -> {
                            final var chunk = world.getChunk(x, z);
                            chunk.viewers().remove(this.player);
                            for (final var entity : chunk.entities()) {
                                entity.removeViewer(this.player);
                            }
                            chunk.entities().remove(this.player);
                        });
            }
            this.player.sendViewers(new RemoveEntities(this.player.getId()));
            this.server.sendAll(new PlayerInfoRemove(List.of(this.player)));
            if (this.player.getOpenedContainer() != null) {
                this.player.getOpenedContainer().removeViewer(this.player);
            }

            LOGGER.info(this.username + " has disconnected.");
            this.server.getEventHandler().call(new PlayerDisconnectEvent(this.player));
        }
        ctx.close();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        if (!ctx.channel().isActive()) {
            return;
        }

        if (cause instanceof TimeoutException) {
            LOGGER.error(this.username + " timed out");
        } else {
            super.exceptionCaught(ctx, cause);
        }
    }

    public void play(GameProfile profile) {
        if (profile == null) {
            if (this.server.getConfig().mode() == MinecraftConfig.Mode.BUNGEECORD) {
                final var arguments = this.serverAddress.split("\00");
                this.serverAddress = arguments[0];
                try {
                    final var properties = (Property[]) SculkServer.GSON.fromJson(arguments[3], TypeToken.getArray(Property.class).getType());
                    profile = new GameProfile(MojangUtil.fromMojang(arguments[2]), this.username, List.of(properties));
                } catch (Exception e) {
                    this.send(new Disconnect(Component.text("Connect through your proxy!", NamedTextColor.RED)));
                    return;
                }
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

        this.send(new LoginSuccess(profile));
        this.player = new SculkPlayer(this.server, this, profile);
        this.server.addPlayer(this.player);
        this.setState(State.PLAY);
        this.server.getEventHandler().call(new PlayerInitialEvent(this.player)).thenAcceptAsync(event -> {
            final var world = event.getWorld();
            var position = event.getPosition();
            if (world == null || !world.isAlive()) {
                this.player.disconnect(Component.text("No world found.", NamedTextColor.RED));
                return;
            }
            if (event.getGameMode() == null) {
                event.setGameMode(world.getDefaultGameMode());
            }
            if (position == null) {
                position = world.getSpawnPosition();
            }
            if (event.getPermissionChecker() == null) {
                event.setPermissionChecker(PermissionChecker.always(TriState.NOT_SET));
            }
            this.player.init(event.getGameMode(), position, world, event.getPermissionChecker());

            this.send(new Login(this.player.getId(), (byte) this.player.getGameMode().ordinal(),
                    this.server.getBiomeRegistry(), this.server.getDimensionRegistry(),
                    this.server.getDamageTypeRegistry(),
                    world.getDimension().name()));

            this.send(BRAND_PACKET);
            this.sendCommands();
            this.send(new SpawnPosition(position));
            this.send(PlayerInfo.add((List<? extends PlayerInfoEntry>) this.server.getAllPlayers()));
            final var playerInfo = PlayerInfo.add(List.of(this.player));
            for (final var other : this.server.getAllPlayers()) {
                if (other != this.player) ((SculkPlayer) other).send(playerInfo);
            }

            for (final var team : this.server.getTeamHandler().teams()) {
                this.send(new UpdateTeams(team, (byte) 0, team.entries().toArray(new String[]{})));
            }

            this.calculateChunks(position, position, false, false);
            this.send(new SynchronizePlayerPosition(position));

            this.server.getEventHandler().justCall(new PlayerJoinEvent(this.player));
        }, this.executor()).exceptionally(throwable -> {
            LOGGER.error("Exception during login of player {}", this.player.getUsername(), throwable);
            return null;
        });
    }

    private void sendCommands() {
        final var rootNode = new RootCommandNode<CommandSource>();
        for (final var child : this.server.getCommandHandler().root().getChildren()) {
            if (child.canUse(this.player)) {
                rootNode.addChild(child);
            }
        }
        this.send(new Commands(rootNode));
    }

    public void send(final Packet packet) {
        this.channel.writeAndFlush(packet);
    }

    public void sendAndClose(final Packet packet) {
        this.channel.writeAndFlush(packet).addListener(ChannelFutureListener.CLOSE);
    }

    public Executor executor() {
        return this.channel.eventLoop();
    }

    public void setState(final State state) {
        this.state = state;
        this.packetHandler = switch (state) {
            case HANDSHAKE -> new HandshakePacketHandler(this);
            case STATUS -> new StatusPacketHandler(this, this.server);
            case LOGIN -> new LoginPacketHandler(this, this.server);
            case PLAY -> new PlayPacketHandler(this, this.server, this.player);
        };
        this.channel.pipeline().get(MinecraftEncoder.class).setState(state);
        this.channel.pipeline().get(MinecraftDecoder.class).setState(state);
    }

    public void enableEncryption(final byte[] secret) {
        final var secretKey = new SecretKeySpec(secret, "AES");
        try {
            final var cipherFactory = Natives.cipher.get();
            this.channel.pipeline()
                    .addBefore(FRAME_DECODER, CIPHER_DECODER, new CipherDecoder(cipherFactory.forDecryption(secretKey)))
                    .addBefore(FRAME_ENCODER, CIPHER_ENCODER, new CipherEncoder(cipherFactory.forEncryption(secretKey)));
        } catch (GeneralSecurityException e) {
            throw new RuntimeException(e);
        }
    }

    private void addCompressionHandler() {
        final var compressor = Natives.compress.get().create(this.server.getConfig().compressionLevel());

        this.channel.pipeline().remove(FRAME_ENCODER);
        this.channel.pipeline()
                .addBefore(MINECRAFT_DECODER, COMPRESSOR_DECODER, new CompressorDecoder(compressor))
                .addBefore(MINECRAFT_ENCODER, COMPRESSOR_ENCODER, new CompressorEncoder(compressor, this.server.getConfig()));
    }

    public void setVersion(final int version) {
        this.version = version;
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

    public void close() {
        this.channel.close();
    }

    public void forChunksInRange(final int chunkX, final int chunkZ, final int range, final BiConsumer<Integer, Integer> chunk) {
        for (var x = -range; x <= range; x++) {
            for (var z = -range; z <= range; z++) {
                chunk.accept(chunkX + x, chunkZ + z);
            }
        }
    }

    public void calculateChunks(final Position from, final Position to) {
        this.calculateChunks(from, to, true, true);
    }

    public void calculateChunks(final Position from, final Position to, boolean check, boolean checkAlreadyLoaded) {
        final var viewDistance = this.player.getSettings().getViewDistance();
        this.calculateChunks(from, to, check, checkAlreadyLoaded, viewDistance, viewDistance);
    }

    public void calculateChunks(final Position from, final Position to,
                                boolean check, boolean checkAlreadyLoaded,
                                int range, int oldRange) {
        final var fromChunkX = chunkCoordinate(from.x());
        final var fromChunkZ = chunkCoordinate(from.z());
        final var chunkX = chunkCoordinate(to.x());
        final var chunkZ = chunkCoordinate(to.z());
        if (check) {
            if (fromChunkX == chunkX && fromChunkZ == chunkZ) {
                return;
            }
            this.send(new CenterChunk(chunkX, chunkZ));
        }
        final var world = this.player.getWorld();
        final var chunks = new ArrayList<SculkChunk>((range * 2 + 1) * (range * 2 + 1));
        this.forChunksInRange(chunkX, chunkZ, range, (x, z) -> {
            final var chunk = world.getChunk(x, z);
            chunks.add(chunk);
            chunk.viewers().add(this.player); // new in range
            for (final var entity : chunk.entities()) {
                if (entity == this.player) continue;
                if (entity instanceof Player viewer) {
                    this.player.addViewer(viewer);
                }
                entity.addViewer(this.player);
            }
        });
        if (checkAlreadyLoaded) {
            this.forChunksInRange(fromChunkX, fromChunkZ, oldRange, (x, z) -> {
                final var chunk = world.getChunk(x, z);
                if (!chunks.remove(chunk)) {
                    chunk.viewers().remove(this.player); // chunk not in range
                    for (final var entity : chunk.entities()) {
                        entity.removeViewer(this.player);
                    }
                }
            });
        }
        for (final var chunk : chunks) { // send all new chunks
            chunk.send(this.player);
        }
    }

    @Override
    public int getProtocolVersion() {
        return this.version;
    }

    @Override
    public SocketAddress getAddress() {
        return this.channel.remoteAddress();
    }
}
