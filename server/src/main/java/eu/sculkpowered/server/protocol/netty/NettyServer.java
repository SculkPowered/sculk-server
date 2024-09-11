package eu.sculkpowered.server.protocol.netty;

import eu.sculkpowered.server.SculkServer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelOption;
import io.netty.channel.epoll.Epoll;
import io.netty.channel.epoll.EpollEventLoopGroup;
import io.netty.channel.epoll.EpollServerSocketChannel;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class NettyServer {

  private static final Logger LOGGER = LogManager.getLogger(NettyServer.class);

  private final SculkServer server;

  private Channel channel;

  public NettyServer(final SculkServer server) {
    this.server = server;
  }

  public void connect(final String host, final int port) {
    final var bossLoopGroup =
        Epoll.isAvailable() ? new EpollEventLoopGroup() : new NioEventLoopGroup();
    final var workerLoopGroup =
        Epoll.isAvailable() ? new EpollEventLoopGroup() : new NioEventLoopGroup();

    new ServerBootstrap()
        .channelFactory(
            Epoll.isAvailable() ? EpollServerSocketChannel::new : NioServerSocketChannel::new)
        .group(bossLoopGroup, workerLoopGroup)
        .childHandler(new NettyServerInitializer(this.server))
        .childOption(ChannelOption.TCP_NODELAY, true)
        .childOption(ChannelOption.IP_TOS, 24)
        .bind(host, port)
        .addListener(ChannelFutureListener.CLOSE_ON_FAILURE)
        .addListener(ChannelFutureListener.FIRE_EXCEPTION_ON_FAILURE)
        .addListener((ChannelFutureListener) future -> {
          this.channel = future.channel();
          if (future.isSuccess()) {
            LOGGER.info("Listening on {}", this.channel.localAddress());
          } else {
            LOGGER.error("Can not bind to {}", this.channel.localAddress(), future.cause());
          }
        });
  }

  public void close() throws InterruptedException {
    LOGGER.info("Closing listener...");
    this.channel.close().sync();
  }
}
