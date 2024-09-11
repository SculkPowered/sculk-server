package eu.sculkpowered.server.entity.player;

import eu.sculkpowered.server.protocol.packet.serverbound.ClientInformationPacket;
import java.util.Locale;
import org.jetbrains.annotations.NotNull;

public final class ClientInformationWrapper implements PlayerSettings {

  private static final ClientInformationPacket DEFAULT =
      new ClientInformationPacket("en_US", (byte) 10, ChatMode.ENABLED, true,
          127, Hand.RIGHT, true, false);

  private ClientInformationPacket clientInformation = DEFAULT;
  private Locale locale;

  public @NotNull ClientInformationPacket clientInformation() {
    return this.clientInformation;
  }

  public void setClientInformation(final ClientInformationPacket clientInformation) {
    this.clientInformation = clientInformation;
    this.locale = null;
  }

  @Override
  public @NotNull Locale locale() {
    if (this.locale == null) {
      this.locale = Locale.forLanguageTag(this.clientInformation.locale().replaceAll("_", "-"));
    }
    return this.locale;
  }

  @Override
  public byte viewDistance() {
    return this.clientInformation.viewDistance();
  }

  @Override
  public @NotNull ChatMode chatMode() {
    return this.clientInformation.chatMode();
  }

  @Override
  public boolean chatColorsEnabled() {
    return this.clientInformation.chatColors();
  }

  @Override
  public int skinParts() {
    return this.clientInformation.skinParts();
  }

  @Override
  public @NotNull Hand mainHand() {
    return this.clientInformation.mainHand();
  }

  @Override
  public boolean textFilteringEnabled() {
    return this.clientInformation.enableTextFiltering();
  }

  @Override
  public boolean serverListingsAllowed() {
    return this.clientInformation.allowServerListings();
  }

  public boolean isDefault() {
    return this.clientInformation == DEFAULT;
  }
}
