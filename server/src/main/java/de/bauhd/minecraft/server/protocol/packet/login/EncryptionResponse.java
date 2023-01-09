package de.bauhd.minecraft.server.protocol.packet.login;

import de.bauhd.minecraft.server.protocol.Buffer;
import de.bauhd.minecraft.server.protocol.Connection;
import de.bauhd.minecraft.server.protocol.Protocol;
import de.bauhd.minecraft.server.protocol.packet.Packet;
import de.bauhd.minecraft.server.util.EncryptionUtil;
import de.bauhd.minecraft.server.util.MojangUtil;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public final class EncryptionResponse implements Packet {

    private byte[] sharedSecret;
    private byte[] verifyToken;
    private long salt;
    private byte[] signature;

    @Override
    public void decode(Buffer buf, Protocol.Version version) {
        this.sharedSecret = buf.readByteArray();

        if (version.newerOr(Protocol.Version.MINECRAFT_1_19_3) || buf.readBoolean()) {
            this.verifyToken = buf.readByteArray();
        } else {
            this.salt = buf.readLong();
            this.signature = buf.readByteArray();
        }
    }

    @Override
    public boolean handle(Connection connection) {
        try {
            if (this.verifyToken != null) {
                final var decryptedVerifyToken = EncryptionUtil.decryptRsa(this.verifyToken);
                if (!Arrays.equals(decryptedVerifyToken, connection.verifyToken())) {
                    connection.send(new Disconnect(Component.text("Verify token does not match!", NamedTextColor.RED)));
                    return false;
                }
            } else {
                // TODO verify signature
            }

            final var decryptedSecret = EncryptionUtil.decryptRsa(this.sharedSecret);
            final var gameProfile = MojangUtil.hasJoined(connection.username(), EncryptionUtil.generateServerId(decryptedSecret));

            connection.enableEncryption(decryptedSecret);
            connection.play(gameProfile);
        } catch (IllegalBlockSizeException | BadPaddingException | NoSuchPaddingException | NoSuchAlgorithmException |
                 InvalidKeyException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    @Override
    public String toString() {
        return "EncryptionResponse{" +
                "sharedSecret=" + Arrays.toString(this.sharedSecret) +
                ", verifyToken=" + Arrays.toString(this.verifyToken) +
                ", salt=" + this.salt +
                '}';
    }
}
