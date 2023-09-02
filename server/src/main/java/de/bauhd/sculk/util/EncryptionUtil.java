package de.bauhd.sculk.util;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public final class EncryptionUtil {

    public static byte[] decryptRsa(final KeyPair keyPair, final byte[] bytes)
            throws IllegalBlockSizeException, BadPaddingException, NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException {
        final var cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, keyPair.getPrivate());
        return cipher.doFinal(bytes);
    }

    public static String generateServerId(final KeyPair keyPair, final byte[] secret) {
        try {
            final var messageDigest = MessageDigest.getInstance("SHA-1");
            messageDigest.update(secret);
            messageDigest.update(keyPair.getPublic().getEncoded());
            return new BigInteger(messageDigest.digest()).toString(16);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
