package de.bauhd.sculk.protocol.packet.play;

import de.bauhd.sculk.protocol.Buffer;
import de.bauhd.sculk.protocol.packet.Packet;
import de.bauhd.sculk.protocol.packet.PacketHandler;

public final class CommandSuggestionsRequest implements Packet {

    private int transactionId;
    private String text;

    @Override
    public void decode(Buffer buf) {
        this.transactionId = buf.readVarInt();
        this.text = buf.readString(32500);
    }

    @Override
    public boolean handle(PacketHandler handler) {
        return handler.handle(this);
    }

    @Override
    public String toString() {
        return "CommandSuggestionsRequest{" +
                "transactionId=" + this.transactionId +
                ", text='" + this.text + '\'' +
                '}';
    }

    public int transactionId() {
        return this.transactionId;
    }

    public String text() {
        return this.text;
    }
}
