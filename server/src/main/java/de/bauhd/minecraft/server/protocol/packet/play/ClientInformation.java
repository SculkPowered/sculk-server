package de.bauhd.minecraft.server.protocol.packet.play;

import de.bauhd.minecraft.server.entity.player.PlayerSettings;
import de.bauhd.minecraft.server.protocol.Buffer;
import de.bauhd.minecraft.server.protocol.packet.Packet;
import de.bauhd.minecraft.server.protocol.packet.PacketHandler;

public final class ClientInformation implements Packet {

    private String locale;
    private byte viewDistance;
    private PlayerSettings.ChatMode chatMode;
    private boolean chatColors;
    private int skinParts;
    private PlayerSettings.Hand mainHand;
    private boolean enableTextFiltering;
    private boolean allowServerListings;

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
