package de.bauhd.minecraft.server.protocol.packet.play;

import de.bauhd.minecraft.server.protocol.Buffer;
import de.bauhd.minecraft.server.protocol.Protocol;
import de.bauhd.minecraft.server.protocol.packet.Packet;

public final class ClientInformation implements Packet {

    private String locale;
    private byte viewDistance;
    private int chatMode;
    private boolean chatColors;
    private int skinParts;
    private int mainHand;
    private boolean enableTextFiltering;
    private boolean allowServerListings;

    @Override
    public void decode(Buffer buf, Protocol.Version version) {
        this.locale = buf.readString(16);
        this.viewDistance = buf.readByte();
        this.chatMode = buf.readVarInt();
        this.chatColors = buf.readBoolean();
        this.skinParts = buf.readUnsignedByte();
        this.mainHand = buf.readVarInt();
        this.enableTextFiltering = buf.readBoolean();
        this.allowServerListings = buf.readBoolean();
    }

    @Override
    public void encode(Buffer buf, Protocol.Version version) {

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
}
