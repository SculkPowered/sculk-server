package de.bauhd.minecraft.server.protocol;

import com.mojang.brigadier.exceptions.CommandSyntaxException;
import de.bauhd.minecraft.server.DefaultMinecraftServer;
import de.bauhd.minecraft.server.Worker;
import de.bauhd.minecraft.server.api.entity.MinecraftPlayer;
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

public final class Connection extends ChannelHandlerAdapter {

    private static final List<Chunk> CHUNKS;

    static {
        CHUNKS = new ArrayList<>();
        final var world = new World();
        world.chunksInRange(0, 0, 10, (x, z) -> CHUNKS.add(world.createChunk(x, z)));
    }

    private final Channel channel;
    private MinecraftPlayer player;

    public Connection(final Channel channel) {
        this.channel = channel;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object message) {
        if (message instanceof Packet packet) {
            if (packet instanceof Handshake handshake) {
                this.set(handshake.getNextStatus() == 1 ? State.STATUS : State.LOGIN, handshake.getVersion());
            } else if (packet instanceof StatusRequest) {
                ctx.writeAndFlush(new StatusResponse());
            } else if (packet instanceof StatusPing) {
                this.sendAndClose(packet);
            } else if (packet instanceof LoginStart loginStart) {
                final var uuid = UUID.nameUUIDFromBytes(("OfflinePlayer:" + loginStart.getUsername()).getBytes(StandardCharsets.UTF_8));
                ctx.writeAndFlush(new LoginSuccess(uuid, loginStart.getUsername()));

                this.setState(State.PLAY);
                ctx.writeAndFlush(new Login());
                this.player = new MinecraftPlayer(ctx.channel(), uuid, loginStart.getUsername());

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
            } else if (packet instanceof ChatCommand command) {
                try {
                    DefaultMinecraftServer.getInstance().getCommandHandler().dispatcher().execute(command.command(), this.player);
                } catch (CommandSyntaxException e) {
                    this.player.sendMessage(Component.text(e.getMessage(), NamedTextColor.RED));
                }
            }
        }
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
