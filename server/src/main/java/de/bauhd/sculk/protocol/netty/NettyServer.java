package de.bauhd.sculk.protocol.netty;

import de.bauhd.sculk.SculkServer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.epoll.Epoll;
import io.netty.channel.epoll.EpollEventLoopGroup;
import io.netty.channel.epoll.EpollServerSocketChannel;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.concurrent.Future;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class NettyServer {

    private static final Logger LOGGER = LogManager.getLogger(NettyServer.class);

    private final SculkServer server;

    private Future<? super Void> channelFuture;
    private EventLoopGroup bossLoopGroup;
    private EventLoopGroup workerLoopGroup;

    public NettyServer(final SculkServer server) {
        this.server = server;
    }

    public void connect(final String host, final int port) {
        this.bossLoopGroup = Epoll.isAvailable() ? new EpollEventLoopGroup() : new NioEventLoopGroup();
        this.workerLoopGroup = Epoll.isAvailable() ? new EpollEventLoopGroup() : new NioEventLoopGroup();

        new ServerBootstrap()
                .channelFactory(Epoll.isAvailable() ? EpollServerSocketChannel::new : NioServerSocketChannel::new)
                .group(this.bossLoopGroup, this.workerLoopGroup)
                .childHandler(new NettyServerInitializer(this.server))
                .childOption(ChannelOption.TCP_NODELAY, true)
                .childOption(ChannelOption.IP_TOS, 24)
                .bind(host, port)
                .addListener(ChannelFutureListener.CLOSE_ON_FAILURE)
                .addListener(ChannelFutureListener.FIRE_EXCEPTION_ON_FAILURE)
                .addListener(future -> {
                    if (future.isSuccess()) {
                        LOGGER.info("Listening on " + host + ":" + port);
                    } else {
                        throw new RuntimeException(future.cause());
                    }
                    this.channelFuture = future;
                });
    }

    public void close() {
        this.channelFuture.cancel(true);
        this.bossLoopGroup.shutdownGracefully();
        this.workerLoopGroup.shutdownGracefully();
    }
}
