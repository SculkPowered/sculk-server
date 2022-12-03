package de.bauhd.minecraft.server.protocol.packet.play;

import de.bauhd.minecraft.server.AdvancedMinecraftServer;
import de.bauhd.minecraft.server.api.entity.player.GameMode;
import de.bauhd.minecraft.server.api.inventory.Slot;
import de.bauhd.minecraft.server.protocol.Connection;
import de.bauhd.minecraft.server.protocol.Protocol;
import de.bauhd.minecraft.server.protocol.packet.Packet;
import io.netty5.buffer.Buffer;

import static de.bauhd.minecraft.server.protocol.packet.PacketUtils.readSlot;

public final class CreativeModeSlot implements Packet {

    private short slot;
    private Slot clickedItem;

    @Override
    public void decode(Buffer buf, Protocol.Version version) {
        this.slot = buf.readShort();
        this.clickedItem = readSlot(buf);
    }

    @Override
    public void handle(Connection connection) {
        final var player = connection.player();
        if (player.getGameMode() != GameMode.CREATIVE) {
            player.sendMessage(AdvancedMinecraftServer.SUS_COMPONENT);
            return;
        }
        player.setItem(this.slot, this.clickedItem);
    }

    @Override
    public String toString() {
        return "CreativeModeSlot{" +
                "slot=" + this.slot +
                ", clickedItem=" + this.clickedItem +
                '}';
    }
}
