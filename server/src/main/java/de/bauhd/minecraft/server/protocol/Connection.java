package de.bauhd.minecraft.server.protocol;

import de.bauhd.minecraft.server.Worker;
import de.bauhd.minecraft.server.api.entity.Player;
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
import io.netty5.buffer.pool.PooledBufferAllocator;
import io.netty5.channel.Channel;
import io.netty5.channel.ChannelFutureListeners;
import io.netty5.channel.ChannelHandlerAdapter;
import io.netty5.channel.ChannelHandlerContext;
import net.kyori.adventure.bossbar.BossBar;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.title.Title;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

public final class Connection extends ChannelHandlerAdapter {

    private final Channel channel;

    public Connection(final Channel channel) {
        this.channel = channel;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object message) {
        if (message instanceof Packet packet) {
            if (packet instanceof Handshake handshake) {
                this.set(handshake.getNextStatus() == 1 ? State.STATUS : State.LOGIN, handshake.getVersion());
            } else if (packet instanceof StatusRequest) {
                System.out.println("response");
                ctx.writeAndFlush(new StatusResponse());
            } else if (packet instanceof StatusPing) {
                this.sendAndClose(packet);
            } else if (packet instanceof LoginStart loginStart) {
                final var uuid = UUID.nameUUIDFromBytes(("OfflinePlayer:" + loginStart.getUsername()).getBytes(StandardCharsets.UTF_8));
                ctx.writeAndFlush(new LoginSuccess(uuid, loginStart.getUsername()));

                this.setState(State.PLAY);
                ctx.writeAndFlush(new Login());
                final var player = new Player(ctx.channel(), uuid, loginStart.getUsername());
                final var world = new World();

                Worker.PLAYERS.add(player);

                ctx.writeAndFlush(new UpdateAttributes());
                ctx.writeAndFlush(new SpawnPosition());
                ctx.writeAndFlush(new CenterChunk());
                ctx.writeAndFlush(new SynchronizePlayerPosition());
                ctx.writeAndFlush(new ContainerContent());
                ctx.writeAndFlush(new UpdateRecipes());
                ctx.writeAndFlush(new UpdateTags());
                ctx.writeAndFlush(new RenderDistance());
                ctx.writeAndFlush(new SimulationDistance());
                ctx.writeAndFlush(new Health());
                ctx.writeAndFlush(new Experience());
                ctx.writeAndFlush(PlayerInfo.add(player));

                player.sendPlayerListHeaderAndFooter(
                        Component.text("The best server ever!", TextColor.color(10, 43, 23)),
                        Component.text("lol")
                );


                final var bossBar = BossBar
                        .bossBar(Component.text("0"), 0F, BossBar.Color.GREEN, BossBar.Overlay.NOTCHED_6);

                player.showBossBar(bossBar);

                player.sendActionBar(Component.text("Hallo Noah", NamedTextColor.AQUA));


                /*ctx.writeAndFlush(new UpdateObjectives());
                ctx.writeAndFlush(new DisplayObjective());
                ctx.writeAndFlush(new UpdateScore("Score 0", 0));
                ctx.writeAndFlush(new UpdateScore("Score 1", 1));
                ctx.writeAndFlush(new UpdateScore("Score 2", 2));
                ctx.writeAndFlush(new UpdateScore("Score 3", 3));
                ctx.writeAndFlush(new UpdateScore("Score 4", 4));
                ctx.writeAndFlush(new UpdateScore("Score 5", 5));
                ctx.writeAndFlush(new UpdateScore("Score 6", 6));
                ctx.writeAndFlush(new UpdateScore("Score 7", 7));
                ctx.writeAndFlush(new UpdateScore("Score 8", 8));
                ctx.writeAndFlush(new UpdateScore("Score 9", 9));
                ctx.writeAndFlush(new UpdateScore("Score 10", 10));
                ctx.writeAndFlush(new UpdateScore("Score 11", 11));
                ctx.writeAndFlush(new UpdateScore("Score 12", 12));
                ctx.writeAndFlush(new UpdateScore("Score 13", 13));
                ctx.writeAndFlush(new UpdateScore("Score 14", 14));
                ctx.writeAndFlush(new UpdateScore("Score 15", 15));
                ctx.writeAndFlush(new UpdateScore("Score 16", 16));
                ctx.writeAndFlush(new UpdateScore("Score 17", 17));
                ctx.writeAndFlush(new UpdateScore("Score 18", 18));
                ctx.writeAndFlush(new UpdateScore("Score 19", 19));*/

                world.chunksInRange(0, 0, 10, (x, z) -> {
                    final var chunk = world.createChunk(x, z);
                    chunk.send(player);
                });

                player.showTitle(Title.title(Component.text("Title"), Component.text("Subtitle")));

                ctx.writeAndFlush(new SystemChatMessage(Component.text("Okay bro", NamedTextColor.RED), true));

                final var npnName = "HttpMarco";
                final var npcUniqueId = UUID.nameUUIDFromBytes(("OfflinePlayer:" + npnName).getBytes(StandardCharsets.UTF_8));

                ctx.writeAndFlush(PlayerInfo.add(npcUniqueId, npnName, -1, -1, null));
                ctx.writeAndFlush(new SpawnPlayer(npcUniqueId));
              //  ctx.writeAndFlush(PlayerInfo.remove(npcUniqueId));
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
