package de.bauhd.minecraft.server.protocol.packet.play;

import de.bauhd.minecraft.server.api.inventory.Slot;
import de.bauhd.minecraft.server.protocol.Buffer;
import de.bauhd.minecraft.server.protocol.packet.Packet;
import de.bauhd.minecraft.server.protocol.packet.PacketHandler;

public final class EditBook implements Packet {

    private Slot slot;
    private String[] entries;
    private String title;

    @Override
    public void decode(Buffer buf) {
        this.slot = buf.readSlot();
        final var count = buf.readVarInt();
        this.entries = new String[count];
        for (int i = 0; i < count; i++) {
            this.entries[i] = buf.readString();
        }
        if (buf.readBoolean()) {
            this.title = buf.readString();
        }
    }

    @Override
    public boolean handle(PacketHandler handler) {
        return handler.handle(this);
    }

    @Override
    public String toString() {
        return "EditBook{" +
                "slot=" + this.slot +
                ", entries=String[" + this.entries.length + "]" +
                ", title='" + this.title + '\'' +
                '}';
    }

    public Slot slot() {
        return this.slot;
    }

    public String[] entries() {
        return this.entries;
    }

    public String title() {
        return this.title;
    }
}
