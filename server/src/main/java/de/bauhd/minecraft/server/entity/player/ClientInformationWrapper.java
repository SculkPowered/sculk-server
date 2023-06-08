package de.bauhd.minecraft.server.entity.player;

import de.bauhd.minecraft.server.protocol.packet.play.ClientInformation;

import java.util.Locale;

public final class ClientInformationWrapper implements PlayerSettings {

    private ClientInformation clientInformation;
    private Locale locale;

    public ClientInformation clientInformation() {
        return this.clientInformation;
    }

    public void setClientInformation(final ClientInformation clientInformation) {
        this.clientInformation = clientInformation;
        this.locale = null;
    }

    @Override
    public Locale getLocale() {
        if (this.locale == null) this.locale = Locale.forLanguageTag(this.clientInformation.locale().replaceAll("_", "-"));
        return this.locale;
    }

    @Override
    public byte getViewDistance() {
        return this.clientInformation.viewDistance();
    }

    @Override
    public ChatMode getChatMode() {
        return this.clientInformation.chatMode();
    }

    @Override
    public boolean enabledChatColors() {
        return this.clientInformation.chatColors();
    }

    @Override
    public int getSkinParts() {
        return this.clientInformation.skinParts();
    }

    @Override
    public Hand getMainHand() {
        return this.clientInformation.mainHand();
    }

    @Override
    public boolean enableTextFiltering() {
        return this.clientInformation.enableTextFiltering();
    }

    @Override
    public boolean allowServerListings() {
        return this.clientInformation.allowServerListings();
    }
}
