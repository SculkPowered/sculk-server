package io.github.sculkpowered.server.protocol.packet.handler;

import io.github.sculkpowered.server.MinecraftConfig;
import io.github.sculkpowered.server.SculkServer;
import io.github.sculkpowered.server.protocol.SculkConnection;
import io.github.sculkpowered.server.protocol.State;
import io.github.sculkpowered.server.protocol.packet.PacketHandler;
import io.github.sculkpowered.server.protocol.packet.login.Disconnect;
import io.github.sculkpowered.server.protocol.packet.login.EncryptionRequest;
import io.github.sculkpowered.server.protocol.packet.login.EncryptionResponse;
import io.github.sculkpowered.server.protocol.packet.login.LoginAcknowledged;
import io.github.sculkpowered.server.protocol.packet.login.LoginPluginResponse;
import io.github.sculkpowered.server.protocol.packet.login.LoginStart;
import io.github.sculkpowered.server.util.EncryptionUtil;
import io.github.sculkpowered.server.util.MojangUtil;
import java.security.MessageDigest;
import java.util.concurrent.ThreadLocalRandom;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;

public final class LoginPacketHandler extends PacketHandler {

  private final SculkConnection connection;
  private final SculkServer server;

  private byte[] verifyToken;

  public LoginPacketHandler(final SculkConnection connection, final SculkServer server) {
    this.connection = connection;
    this.server = server;
  }

  @Override
  public boolean handle(LoginStart loginStart) {
    this.connection.setUsername(loginStart.username());

    if (this.server.config().mode() == MinecraftConfig.Mode.ONLINE) {
      final var publicKey = this.server.getKeyPair().getPublic().getEncoded();
      this.verifyToken = new byte[4];
      ThreadLocalRandom.current().nextBytes(this.verifyToken);
      this.connection.send(new EncryptionRequest("", publicKey, this.verifyToken));
    } else {
      this.connection.initPlayer(null);
    }
    return true;
  }

  @Override
  public boolean handle(EncryptionResponse encryptionResponse) {
    try {
      if (encryptionResponse.verifyToken() != null) {
        final var decryptedVerifyToken = EncryptionUtil.decryptRsa(this.server.getKeyPair(), encryptionResponse.verifyToken());
        if (!MessageDigest.isEqual(decryptedVerifyToken, this.verifyToken)) {
          this.connection.send(new Disconnect(Component.text("Verify token does not match!", NamedTextColor.RED)));
          return true;
        }
      }

      final var decryptedSecret = EncryptionUtil.decryptRsa(this.server.getKeyPair(), encryptionResponse.sharedSecret());
      final var gameProfile = MojangUtil.hasJoined(this.connection.username(),
          EncryptionUtil.generateServerId(this.server.getKeyPair(), decryptedSecret));

      this.connection.enableEncryption(decryptedSecret);
      this.connection.initPlayer(gameProfile);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
    return true;
  }

  @Override
  public boolean handle(LoginPluginResponse pluginResponse) {
    return true;
  }

  @Override
  public boolean handle(LoginAcknowledged loginAcknowledged) {
    this.connection.setState(State.CONFIG);
    this.connection.configuration();
    return true;
  }
}