package eu.sculkpowered.server.util;

import com.velocitypowered.natives.encryption.JavaVelocityCipher;
import com.velocitypowered.natives.util.Natives;

public final class Constants {

  public static final byte[] EMPTY_BYTE_ARRAY = new byte[0];

  public static final String FRAME_DECODER = "frame-decoder";
  public static final String FRAME_ENCODER = "frame-encoder";
  public static final String MINECRAFT_DECODER = "minecraft-decoder";
  public static final String MINECRAFT_ENCODER = "minecraft-encoder";
  public static final String CIPHER_DECODER = "cipher-decoder";
  public static final String CIPHER_ENCODER = "cipher-encoder";
  public static final String COMPRESSOR_DECODER = "compressor-decoder";
  public static final String COMPRESSOR_ENCODER = "compressor-encoder";
  public static final String HANDLER = "handler";
  public static final boolean IS_JAVA_CIPHER = Natives.cipher.get() == JavaVelocityCipher.FACTORY;
}
