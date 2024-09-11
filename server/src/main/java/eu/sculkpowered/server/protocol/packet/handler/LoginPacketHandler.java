package eu.sculkpowered.server.protocol.packet.handler;

import eu.sculkpowered.server.protocol.packet.serverbound.HelloPacket;
import eu.sculkpowered.server.MinecraftConfig;
import eu.sculkpowered.server.SculkServer;
import eu.sculkpowered.server.protocol.SculkConnection;
import eu.sculkpowered.server.protocol.State;
import eu.sculkpowered.server.protocol.packet.PacketHandler;
import eu.sculkpowered.server.protocol.packet.clientbound.LoginDisconnect;
import eu.sculkpowered.server.protocol.packet.serverbound.KeyPacket;
import eu.sculkpowered.server.protocol.packet.serverbound.LoginAcknowledgedPacket;
import eu.sculkpowered.server.protocol.packet.serverbound.CustomQueryAnswerPacket;
import eu.sculkpowered.server.util.EncryptionUtil;
import eu.sculkpowered.server.util.MojangUtil;
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
  public boolean handle(HelloPacket hello) {
    this.connection.setUsername(hello.username());

    if (this.server.config().mode() == MinecraftConfig.Mode.ONLINE) {
      final var publicKey = this.server.getKeyPair().getPublic().getEncoded();
      this.verifyToken = new byte[4];
      ThreadLocalRandom.current().nextBytes(this.verifyToken);
      this.connection.send(new eu.sculkpowered.server.protocol.packet.clientbound.HelloPacket("", publicKey, this.verifyToken, true));
    } else {
      this.connection.initPlayer(null);
    }
    return true;
  }

  @Override
  public boolean handle(KeyPacket key) {
    try {
      final var decryptedVerifyToken = EncryptionUtil.decryptRsa(this.server.getKeyPair(),
          key.verifyToken());
      if (!MessageDigest.isEqual(decryptedVerifyToken, this.verifyToken)) {
        this.connection.send(
            new LoginDisconnect(Component.text("Verify token does not match!", NamedTextColor.RED)));
        return true;
      }

      final var decryptedSecret = EncryptionUtil.decryptRsa(this.server.getKeyPair(),
          key.sharedSecret());
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
  public boolean handle(CustomQueryAnswerPacket pluginResponse) {
    return true;
  }

  @Override
  public boolean handle(LoginAcknowledgedPacket loginAcknowledged) {
    this.connection.setState(State.CONFIG);
    this.connection.configuration();
    return true;
  }
}