package eu.sculkpowered.server.protocol;

import static eu.sculkpowered.server.util.Constants.CIPHER_DECODER;
import static eu.sculkpowered.server.util.Constants.CIPHER_ENCODER;
import static eu.sculkpowered.server.util.Constants.COMPRESSOR_DECODER;
import static eu.sculkpowered.server.util.Constants.COMPRESSOR_ENCODER;
import static eu.sculkpowered.server.util.Constants.FRAME_DECODER;
import static eu.sculkpowered.server.util.Constants.FRAME_ENCODER;
import static eu.sculkpowered.server.util.Constants.MINECRAFT_DECODER;
import static eu.sculkpowered.server.util.Constants.MINECRAFT_ENCODER;

import com.mojang.brigadier.tree.RootCommandNode;
import com.velocitypowered.natives.util.Natives;
import eu.sculkpowered.server.entity.player.GameProfile;
import eu.sculkpowered.server.MinecraftConfig;
import eu.sculkpowered.server.SculkServer;
import eu.sculkpowered.server.command.CommandSource;
import eu.sculkpowered.server.connection.Connection;
import eu.sculkpowered.server.entity.player.GameProfile.Property;
import eu.sculkpowered.server.entity.player.PlayerInfoEntry;
import eu.sculkpowered.server.entity.player.SculkPlayer;
import eu.sculkpowered.server.event.player.PlayerInitialEvent;
import eu.sculkpowered.server.event.player.PlayerJoinEvent;
import eu.sculkpowered.server.protocol.netty.codec.CipherDecoder;
import eu.sculkpowered.server.protocol.netty.codec.CipherEncoder;
import eu.sculkpowered.server.protocol.netty.codec.CompressorDecoder;
import eu.sculkpowered.server.protocol.netty.codec.CompressorEncoder;
import eu.sculkpowered.server.protocol.netty.codec.MinecraftDecoder;
import eu.sculkpowered.server.protocol.netty.codec.MinecraftEncoder;
import eu.sculkpowered.server.protocol.packet.ClientboundPacket;
import eu.sculkpowered.server.protocol.packet.PacketHandler;
import eu.sculkpowered.server.protocol.packet.ServerboundPacket;
import eu.sculkpowered.server.protocol.packet.clientbound.LoginFinishedPacket;
import eu.sculkpowered.server.protocol.packet.shared.FinishConfigurationPacket;
import eu.sculkpowered.server.protocol.packet.clientbound.RegistryDataPacket;
import eu.sculkpowered.server.protocol.packet.handler.ConfigPacketHandler;
import eu.sculkpowered.server.protocol.packet.handler.HandshakePacketHandler;
import eu.sculkpowered.server.protocol.packet.handler.LoginPacketHandler;
import eu.sculkpowered.server.protocol.packet.handler.PlayPacketHandler;
import eu.sculkpowered.server.protocol.packet.handler.StatusPacketHandler;
import eu.sculkpowered.server.protocol.packet.clientbound.LoginCompressionPacket;
import eu.sculkpowered.server.protocol.packet.clientbound.LoginDisconnect;
import eu.sculkpowered.server.protocol.packet.clientbound.GameEventPacket;
import eu.sculkpowered.server.protocol.packet.clientbound.LoginPacket;
import eu.sculkpowered.server.protocol.packet.clientbound.PlayerInfoUpdatePacket;
import eu.sculkpowered.server.protocol.packet.shared.CustomPayloadPacket;
import eu.sculkpowered.server.protocol.packet.clientbound.SetDefaultSpawnPositionPacket;
import eu.sculkpowered.server.protocol.packet.clientbound.PlayerPositionPacket;
import eu.sculkpowered.server.protocol.packet.clientbound.SetPlayerTeamPacket;
import eu.sculkpowered.server.protocol.packet.clientbound.SetTimePacket;
import eu.sculkpowered.server.protocol.packet.clientbound.CommandsPacket;
import eu.sculkpowered.server.registry.Registries;
import eu.sculkpowered.server.registry.Registry;
import eu.sculkpowered.server.util.MojangUtil;
import eu.sculkpowered.server.world.SculkWorld;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;
import java.net.SocketAddress;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeoutException;
import javax.crypto.spec.SecretKeySpec;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.nbt.CompoundBinaryTag;
import net.kyori.adventure.permission.PermissionChecker;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.util.TriState;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

public final class SculkConnection extends ChannelInboundHandlerAdapter implements Connection {

  private static final Logger LOGGER = LogManager.getLogger(SculkConnection.class);

  private static final CustomPayloadPacket BRAND_PACKET =
      new CustomPayloadPacket("minecraft:brand", new byte[]{5, 115, 99, 117, 108, 107});

  public static LoginCompressionPacket COMPRESSION_PACKET;

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

      if (message instanceof ServerboundPacket packet) {
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
      this.server.removePlayer(this.player);
      this.player.onDisconnect();
      LOGGER.info("{} has disconnected.", this.username);
    }
    ctx.close();
  }

  @Override
  public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
    if (!ctx.channel().isActive()) {
      return;
    }

    if (cause instanceof TimeoutException) {
      LOGGER.error("{} timed out", this.username);
    } else {
      super.exceptionCaught(ctx, cause);
    }
  }

  public void initPlayer(GameProfile profile) {
    if (profile == null) {
      if (this.server.config().mode() == MinecraftConfig.Mode.BUNGEECORD) {
        final var arguments = this.serverAddress.split("\00");
        this.serverAddress = arguments[0];
        try {
          final var properties = SculkServer.GSON.fromJson(arguments[3], Property[].class);
          profile = new GameProfile(MojangUtil.fromMojang(arguments[2]), this.username,
              List.of(properties));
        } catch (Exception e) {
          this.send(
              new LoginDisconnect(
                  Component.text("Connect through your proxy!", NamedTextColor.RED)));
          return;
        }
      } else {
        profile = new GameProfile(
            UUID.nameUUIDFromBytes(
                ("OfflinePlayer:" + this.username).getBytes(StandardCharsets.UTF_8)),
            this.username,
            List.of()
        );
      }
    }
    if (COMPRESSION_PACKET != null) {
      this.send(COMPRESSION_PACKET);
      this.addCompressionHandler();
    }

    this.send(new LoginFinishedPacket(profile));
    this.player = new SculkPlayer(this.server, this, profile);
  }

  public void configuration() {
    this.send(SculkConnection.BRAND_PACKET);
    this.send(new RegistryDataPacket(Registries.biomes()));
    this.send(new RegistryDataPacket(Registries.dimensions()));
    this.send(new RegistryDataPacket(Registries.damageTypes()));
    this.send(new RegistryDataPacket(Registries.enchantments()));
    // TODO: this would be a part of the registry rewrite
    this.send(new RegistryDataPacket(new Registry<>() {
      @Override
      public @NotNull String type() {
        return "wolf_variant";
      }

      @Override
      public Entry get(@NotNull String key, Entry def) {
        return null;
      }

      @Override
      public Entry get(int id) {
        return null;
      }

      @Override
      public @NotNull Collection<Entry> entries() {
        return List.of(new Entry() {
          @Override
          public int id() {
            return 0;
          }

          @Override
          public @NotNull CompoundBinaryTag asNBT() {
            return CompoundBinaryTag.builder()
                .putString("angry_texture", "minecraft:entity/wolf/wolf_ashen_angry")
                .putString("biomes", "minecraft:plains")
                .putString("tame_texture", "minecraft:entity/wolf/wolf_ashen_tame")
                .putString("wild_texture", "minecraft:entity/wolf/wolf_ashen")
                .build();
          }

          @Override
          public @NotNull Key key() {
            return Key.key(Key.MINECRAFT_NAMESPACE, "ashen");
          }
        });
      }

      @Override
      public @NotNull Entry defaultValue() {
        return null;
      }
    }));
    this.send(new RegistryDataPacket(new Registry<>() {
      @Override
      public @NotNull String type() {
        return "painting_variant";
      }

      @Override
      public Entry get(@NotNull String key, Entry def) {
        return null;
      }

      @Override
      public Entry get(int id) {
        return null;
      }

      @Override
      public @NotNull Collection<Entry> entries() {
        return List.of(new Entry() {
          @Override
          public int id() {
            return 0;
          }

          @Override
          public @NotNull CompoundBinaryTag asNBT() {
            return CompoundBinaryTag.builder()
                .putString("asset_id", "minecraft:alban")
                .putInt("height", 1)
                .putInt("width", 1)
                .build();
          }

          @Override
          public @NotNull Key key() {
            return Key.key(Key.MINECRAFT_NAMESPACE, "alban");
          }
        });
      }

      @Override
      public @NotNull Entry defaultValue() {
        return null;
      }
    }));
    this.send(FinishConfigurationPacket.INSTANCE);
  }

  public void play() {
    this.setState(State.PLAY);
    this.server.eventHandler().call(new PlayerInitialEvent(this.player))
        .thenAcceptAsync(event -> {
          final var world = (SculkWorld) event.world();
          var position = event.position();
          if (world == null || !world.isAlive()) {
            this.player.disconnect(Component.text("No world found.", NamedTextColor.RED));
            return;
          }
          if (event.gameMode() == null) {
            event.gameMode(world.defaultGameMode());
          }
          if (position == null) {
            position = world.spawnPosition();
          }
          if (event.permissionChecker() == null) {
            event.permissionChecker(PermissionChecker.always(TriState.NOT_SET));
          }
          this.player.init(event.gameMode(), position, world, event.permissionChecker());
          this.server.addPlayer(this.player);

          this.send(new LoginPacket(this.player.id(), (byte) this.player.gameMode().ordinal(),
              world.dimension()));

          this.sendCommands();
          this.send(new GameEventPacket(13, -1));
          this.player.calculateChunks(position, position, false, false);
          this.send(new SetDefaultSpawnPositionPacket(position));
          this.send(new PlayerPositionPacket(position));
          this.send(new SetTimePacket(0, -6000));

          this.send(PlayerInfoUpdatePacket.add((List<? extends PlayerInfoEntry>) this.server.onlinePlayers()));
          final var playerInfo = PlayerInfoUpdatePacket.add(List.of(this.player));
          for (final var other : this.server.onlinePlayers()) {
            if (other != this.player) {
              ((SculkPlayer) other).send(playerInfo);
            }
          }

          for (final var team : this.server.teamHandler().teams()) {
            this.send(new SetPlayerTeamPacket(team, (byte) 0, team.entries().toArray(new String[]{})));
          }

          this.server.eventHandler().justCall(new PlayerJoinEvent(this.player));
        }, this.executor()).exceptionally(throwable -> {
          LOGGER.error("Exception during login of player {}", this.player.name(), throwable);
          return null;
        });
  }

  private void sendCommands() {
    final var rootNode = new RootCommandNode<CommandSource>();
    for (final var child : this.server.commandHandler().root().getChildren()) {
      if (child.canUse(this.player)) {
        rootNode.addChild(child);
      }
    }
    this.send(new CommandsPacket(rootNode));
  }

  public void send(final ClientboundPacket packet) {
    if (this.state == State.PLAY) {
      this.channel.write(packet);
    } else {
      this.channel.writeAndFlush(packet);
    }
  }

  public void sendAndClose(final ClientboundPacket packet) {
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
      case CONFIG -> new ConfigPacketHandler(this, this.player);
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
          .addBefore(FRAME_DECODER, CIPHER_DECODER,
              new CipherDecoder(cipherFactory.forDecryption(secretKey)))
          .addBefore(FRAME_ENCODER, CIPHER_ENCODER,
              new CipherEncoder(cipherFactory.forEncryption(secretKey)));
    } catch (GeneralSecurityException e) {
      throw new RuntimeException(e);
    }
  }

  private void addCompressionHandler() {
    final var compressor = Natives.compress.get()
        .create(this.server.config().compressionLevel());

    this.channel.pipeline().remove(FRAME_ENCODER);
    this.channel.pipeline()
        .addBefore(MINECRAFT_DECODER, COMPRESSOR_DECODER, new CompressorDecoder(compressor))
        .addBefore(MINECRAFT_ENCODER, COMPRESSOR_ENCODER,
            new CompressorEncoder(compressor, this.server.config()));
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

  public void flush() {
    this.channel.flush();
  }

  public void close() {
    this.channel.close();
  }

  @Override
  public int protocolVersion() {
    return this.version;
  }

  @Override
  public @NotNull SocketAddress address() {
    return this.channel.remoteAddress();
  }
}
