package de.bauhd.minecraft.server.protocol.packet.login;

import de.bauhd.minecraft.server.AdvancedMinecraftServer;
import de.bauhd.minecraft.server.protocol.Buffer;
import de.bauhd.minecraft.server.protocol.Connection;
import de.bauhd.minecraft.server.protocol.Protocol;
import de.bauhd.minecraft.server.protocol.packet.Packet;
import de.bauhd.minecraft.server.util.MojangUtil;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public final class EncryptionResponse implements Packet {

    private static final Cipher CIPHER;

    static {

        try {
            CIPHER = Cipher.getInstance("RSA");
            CIPHER.init(Cipher.DECRYPT_MODE, AdvancedMinecraftServer.getInstance().getKeyPair().getPrivate());
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException e) {
            throw new RuntimeException(e);
        }
    }

    private byte[] sharedSecret;
    private byte[] verifyToken;
    private long salt;
    private byte[] signature;

    @Override
    public void decode(Buffer buf, Protocol.Version version) {
        this.sharedSecret = buf.readByteArray();

        if (buf.readBoolean()) {
            this.verifyToken = buf.readByteArray();
        } else {
            this.salt = buf.readLong();
            this.signature = buf.readByteArray();
        }
    }

    @Override
    public boolean handle(Connection connection) {
        try {
            final var digest = java.security.MessageDigest.getInstance("SHA-1");
            digest.update(CIPHER.doFinal(this.sharedSecret));
            digest.update(AdvancedMinecraftServer.getInstance().getKeyPair().getPublic().getEncoded());
            connection.play(MojangUtil.hasJoined(connection.username(), new BigInteger(digest.digest()).toString(16)));
        } catch (NoSuchAlgorithmException | IllegalBlockSizeException | BadPaddingException e) {
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
                ", signature=" + Arrays.toString(this.signature) +
                '}';
    }
}
