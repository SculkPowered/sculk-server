package de.bauhd.sculk.protocol.packet.handler;

import de.bauhd.sculk.SculkMinecraftServer;
import de.bauhd.sculk.MinecraftConfig;
import de.bauhd.sculk.protocol.MineConnection;
import de.bauhd.sculk.protocol.packet.PacketHandler;
import de.bauhd.sculk.protocol.packet.login.*;
import de.bauhd.sculk.util.EncryptionUtil;
import de.bauhd.sculk.util.MojangUtil;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.ThreadLocalRandom;

public final class LoginPacketHandler extends PacketHandler {

    private final MineConnection connection;
    private final SculkMinecraftServer server;

    private byte[] verifyToken;

    public LoginPacketHandler(final MineConnection connection, final SculkMinecraftServer server) {
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
            this.connection.play(null);
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
            this.connection.play(gameProfile);
        } catch (IllegalBlockSizeException | BadPaddingException | NoSuchPaddingException | NoSuchAlgorithmException |
                 InvalidKeyException e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    @Override
    public boolean handle(LoginPluginResponse pluginResponse) {
        return true;
    }
}