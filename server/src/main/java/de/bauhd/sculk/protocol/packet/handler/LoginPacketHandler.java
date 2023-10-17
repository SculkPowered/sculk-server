package de.bauhd.sculk.protocol.packet.handler;

import de.bauhd.sculk.MinecraftConfig;
import de.bauhd.sculk.SculkServer;
import de.bauhd.sculk.protocol.SculkConnection;
import de.bauhd.sculk.protocol.State;
import de.bauhd.sculk.protocol.packet.PacketHandler;
import de.bauhd.sculk.protocol.packet.login.Disconnect;
import de.bauhd.sculk.protocol.packet.login.EncryptionRequest;
import de.bauhd.sculk.protocol.packet.login.EncryptionResponse;
import de.bauhd.sculk.protocol.packet.login.LoginAcknowledged;
import de.bauhd.sculk.protocol.packet.login.LoginPluginResponse;
import de.bauhd.sculk.protocol.packet.login.LoginStart;
import de.bauhd.sculk.util.EncryptionUtil;
import de.bauhd.sculk.util.MojangUtil;
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

    if (this.server.getConfig().mode() == MinecraftConfig.Mode.ONLINE) {
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