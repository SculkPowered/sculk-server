package de.bauhd.minecraft.server.protocol;

import com.google.gson.reflect.TypeToken;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import de.bauhd.minecraft.server.DefaultMinecraftServer;
import de.bauhd.minecraft.server.Worker;
import de.bauhd.minecraft.server.api.entity.MinecraftPlayer;
import de.bauhd.minecraft.server.api.entity.player.GameProfile;
import de.bauhd.minecraft.server.api.world.Chunk;
import de.bauhd.minecraft.server.api.world.World;
import de.bauhd.minecraft.server.protocol.netty.codec.MinecraftDecoder;
import de.bauhd.minecraft.server.protocol.netty.codec.MinecraftEncoder;
import de.bauhd.minecraft.server.protocol.packet.Packet;
import de.bauhd.minecraft.server.protocol.packet.handshake.Handshake;
import de.bauhd.minecraft.server.protocol.packet.login.LoginStart;
import de.bauhd.minecraft.server.protocol.packet.login.LoginSuccess;
import de.bauhd.minecraft.server.protocol.packet.play.*;
import de.bauhd.minecraft.server.protocol.packet.status.StatusPing;
import de.bauhd.minecraft.server.protocol.packet.status.StatusRequest;
import de.bauhd.minecraft.server.protocol.packet.status.StatusResponse;
import de.bauhd.minecraft.server.util.MojangUtil;
import io.netty5.channel.Channel;
import io.netty5.channel.ChannelFutureListeners;
import io.netty5.channel.ChannelHandlerAdapter;
import io.netty5.channel.ChannelHandlerContext;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;

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
            if (packet instanceof Handshake handshake) {
                this.set(handshake.getNextStatus() == 1 ? State.STATUS : State.LOGIN, handshake.getVersion());
                this.serverAddress = handshake.getServerAddress();
            } else if (packet instanceof StatusRequest) {
                ctx.writeAndFlush(new StatusResponse());
            } else if (packet instanceof StatusPing) {
                this.sendAndClose(packet);
            } else if (packet instanceof LoginStart loginStart) {
                this.username = loginStart.getUsername();
                this.play(ctx);
                /*final var publicKey = DefaultMinecraftServer.getInstance().getKeyPair().getPublic().getEncoded();
                final var verifyToken = new byte[4];
                ThreadLocalRandom.current().nextBytes(verifyToken);
                ctx.writeAndFlush(new EncryptionRequest("", publicKey, verifyToken));*/
            } else if (packet instanceof ChatCommand command) {
                try {
                    DefaultMinecraftServer.getInstance().getCommandHandler().dispatcher().execute(command.command(), this.player);
                } catch (CommandSyntaxException e) {
                    this.player.sendMessage(Component.text(e.getMessage(), NamedTextColor.RED));
                }
            }
        }
    }

    private void play(final ChannelHandlerContext ctx) {
        GameProfile profile;
        if (DefaultMinecraftServer.BUNGEECORD) {
            final var arguments = this.serverAddress.split("\00");
            this.serverAddress = arguments[0];
            final var uniqueId = UUID.fromString(arguments[2]
                    .replaceFirst("(\\p{XDigit}{8})(\\p{XDigit}{4})(\\p{XDigit}{4})(\\p{XDigit}{4})(\\p{XDigit}+)",
                            "$1-$2-$3-$4-$5"));
            final var properties = (Property[]) DefaultMinecraftServer.GSON.fromJson(arguments[3], TypeToken.getArray(Property.class));
            profile = new GameProfile(uniqueId, this.username, List.of(properties));
        } else {
            profile = new GameProfile(
                    UUID.nameUUIDFromBytes(("OfflinePlayer:" + this.username).getBytes(StandardCharsets.UTF_8)),
                    this.username,
                    List.of(MojangUtil.getSkinFromName(this.username))
            );
        }
        ctx.writeAndFlush(new LoginSuccess(profile.uniqueId(), this.username));

        this.setState(State.PLAY);
        ctx.writeAndFlush(new Login());
        this.player = new MinecraftPlayer(ctx.channel(), profile.uniqueId(), this.username, profile);

        Worker.PLAYERS.add(this.player);

        //ctx.writeAndFlush(new UpdateAttributes());
        //ctx.writeAndFlush(new SpawnPosition());
        //ctx.writeAndFlush(new CenterChunk());
        //ctx.writeAndFlush(new SynchronizePlayerPosition());
        //ctx.writeAndFlush(new ContainerContent());
        //ctx.writeAndFlush(new UpdateRecipes());
        //ctx.writeAndFlush(new UpdateTags());
        //ctx.writeAndFlush(new RenderDistance());
        //ctx.writeAndFlush(new SimulationDistance());
        //ctx.writeAndFlush(new Health());
        //ctx.writeAndFlush(new Experience());

        ctx.writeAndFlush(PlayerInfo.add(this.player));
        ctx.writeAndFlush(new Commands(DefaultMinecraftServer.getInstance().getCommandHandler().dispatcher().getRoot()));

        for (final var chunk : CHUNKS) {
            chunk.send(this.player);
        }

        Worker.PLAYERS.forEach(player -> {
            if (player != this.player) {
                this.send(PlayerInfo.add(player));
                System.out.println(player.getId() + " - " + player.getUniqueId());
                this.send(new SpawnPlayer(player.getId(), player.getUniqueId()));
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

}
