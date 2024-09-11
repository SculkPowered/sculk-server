package eu.sculkpowered.server.util;

import java.math.BigInteger;
import java.security.KeyPair;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Cipher;

public final class EncryptionUtil {

  public static byte[] decryptRsa(final KeyPair keyPair, final byte[] bytes) throws Exception {
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
