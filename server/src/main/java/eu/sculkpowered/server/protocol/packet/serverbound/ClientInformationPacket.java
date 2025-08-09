package eu.sculkpowered.server.protocol.packet.serverbound;

import eu.sculkpowered.server.entity.player.PlayerSettings;
import eu.sculkpowered.server.protocol.Buffer;
import eu.sculkpowered.server.protocol.packet.PacketHandler;
import eu.sculkpowered.server.protocol.packet.ServerboundPacket;
import java.util.function.Supplier;

public final class ClientInformationPacket implements ServerboundPacket {

  public static final Supplier<ClientInformationPacket> SUPPLIER = ClientInformationPacket::new;

  private String locale;
  private byte viewDistance;
  private PlayerSettings.ChatMode chatMode;
  private boolean chatColors;
  private int skinParts;
  private PlayerSettings.Hand mainHand;
  private boolean enableTextFiltering;
  private boolean allowServerListings;
  private int particleStatus;

  public ClientInformationPacket(final String locale, final byte viewDistance,
      final PlayerSettings.ChatMode chatMode,
      final boolean chatColors, final int skinParts,
      final PlayerSettings.Hand mainHand, final boolean enableTextFiltering,
      final boolean allowServerListings, final int particleStatus) {
    this.locale = locale;
    this.viewDistance = viewDistance;
    this.chatMode = chatMode;
    this.chatColors = chatColors;
    this.skinParts = skinParts;
    this.mainHand = mainHand;
    this.enableTextFiltering = enableTextFiltering;
    this.allowServerListings = allowServerListings;
    this.particleStatus = particleStatus;
  }

  private ClientInformationPacket() {}

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
    this.particleStatus = buf.readVarInt();
  }

  @Override
  public boolean handle(PacketHandler handler) {
    return handler.handle(this);
  }

  @Override
  public String toString() {
    return "ClientInformationPacket{" +
        "locale='" + this.locale + '\'' +
        ", viewDistance=" + this.viewDistance +
        ", chatMode=" + this.chatMode +
        ", chatColors=" + this.chatColors +
        ", skinParts=" + this.skinParts +
        ", mainHand=" + this.mainHand +
        ", enableTextFiltering=" + this.enableTextFiltering +
        ", allowServerListings=" + this.allowServerListings +
        ", particleStatus=" + this.particleStatus +
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

  public int particleStatus() {
    return this.particleStatus;
  }
}
