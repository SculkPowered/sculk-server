package de.bauhd.minecraft.server.protocol.netty;

import io.netty5.bootstrap.ServerBootstrap;
import io.netty5.channel.*;
import io.netty5.channel.epoll.Epoll;
import io.netty5.channel.epoll.EpollHandler;
import io.netty5.channel.epoll.EpollServerSocketChannel;
import io.netty5.channel.nio.NioHandler;
import io.netty5.channel.socket.nio.NioServerSocketChannel;
import io.netty5.util.concurrent.Future;

public final class NettyServer {

    private Future<Void> channelFuture;

    public void connect(final String host, final int port) {
        final var factory = Epoll.isAvailable() ? EpollHandler.newFactory() : NioHandler.newFactory();

        new ServerBootstrap()
                .channelFactory(Epoll.isAvailable() ? EpollServerSocketChannel::new : NioServerSocketChannel::new)
                .group(new MultithreadEventLoopGroup(factory), new MultithreadEventLoopGroup(factory))
                .childHandler(new NettyServerInitializer())
                .childOption(ChannelOption.TCP_NODELAY, true)
                .childOption(ChannelOption.IP_TOS, 24)
                .bind(host, port)
                .addListener(future -> {
                    if (future.isFailed()) {
                        throw new RuntimeException(future.cause());
                    }

                    final var channel = future.getNow();
                    this.channelFuture = channel.closeFuture()
                            .addListener(channel, ChannelFutureListeners.CLOSE_ON_FAILURE)
                            .addListener(channel, ChannelFutureListeners.FIRE_EXCEPTION_ON_FAILURE);
                });
    }

    public void close() {
        this.channelFuture.cancel();
    }

}
