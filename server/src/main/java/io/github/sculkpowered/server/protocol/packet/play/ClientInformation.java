package io.github.sculkpowered.server.protocol.packet.play;

import io.github.sculkpowered.server.entity.player.PlayerSettings;
import io.github.sculkpowered.server.protocol.Buffer;
import io.github.sculkpowered.server.protocol.packet.Packet;
import io.github.sculkpowered.server.protocol.packet.PacketHandler;
import java.util.function.Supplier;

public final class ClientInformation implements Packet {

  public static final Supplier<ClientInformation> SUPPLIER = ClientInformation::new;

  private String locale;
  private byte viewDistance;
  private PlayerSettings.ChatMode chatMode;
  private boolean chatColors;
  private int skinParts;
  private PlayerSettings.Hand mainHand;
  private boolean enableTextFiltering;
  private boolean allowServerListings;

  public ClientInformation(final String locale, final byte viewDistance,
      final PlayerSettings.ChatMode chatMode,
      final boolean chatColors, final int skinParts,
      final PlayerSettings.Hand mainHand,
      final boolean enableTextFiltering, final boolean allowServerListings) {
    this.locale = locale;
    this.viewDistance = viewDistance;
    this.chatMode = chatMode;
    this.chatColors = chatColors;
    this.skinParts = skinParts;
    this.mainHand = mainHand;
    this.enableTextFiltering = enableTextFiltering;
    this.allowServerListings = allowServerListings;
  }

  private ClientInformation() {}

  @Override
  public void decode(Buffer buf) {
    this.locale = buf.readString(16);
    this.viewDistance = buf.readByte();
    this.chatMode = buf.readEnum(PlayerSettings.ChatMode.class);
    this.chatColors = buf.readBoolean();
    this.skinParts = buf.readUnsignedByte();
    this.mainHand = buf.readEnum(PlayerSettings.Hand.class);
    this.enableTextFiltering = buf.readBoolean();
    this.allowServerListings = buf.readBoolean();
  }

  @Override
  public boolean handle(PacketHandler handler) {
    return handler.handle(this);
  }

  @Override
  public String toString() {
    return "ClientInformation{" +
        "locale='" + this.locale + '\'' +
        ", viewDistance=" + this.viewDistance +
        ", chatMode=" + this.chatMode +
        ", chatColors=" + this.chatColors +
        ", skinParts=" + this.skinParts +
        ", mainHand=" + this.mainHand +
        ", enableTextFiltering=" + this.enableTextFiltering +
        ", allowServerListings=" + this.allowServerListings +
        '}';
  }

  public String locale() {
    return this.locale;
  }

  public byte viewDistance() {
    return this.viewDistance;
  }

  public PlayerSettings.ChatMode chatMode() {
    return this.chatMode;
  }

  public boolean chatColors() {
    return this.chatColors;
  }

  public int skinParts() {
    return this.skinParts;
  }

  public PlayerSettings.Hand mainHand() {
    return this.mainHand;
  }

  public boolean enableTextFiltering() {
    return this.enableTextFiltering;
  }

  public boolean allowServerListings() {
    return this.allowServerListings;
  }
}
