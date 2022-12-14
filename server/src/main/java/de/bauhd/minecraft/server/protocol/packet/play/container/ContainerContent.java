package de.bauhd.minecraft.server.protocol.packet.play.container;

import de.bauhd.minecraft.server.api.inventory.Slot;
import de.bauhd.minecraft.server.api.world.Material;
import de.bauhd.minecraft.server.protocol.Buffer;
import de.bauhd.minecraft.server.protocol.Protocol;
import de.bauhd.minecraft.server.protocol.packet.Packet;

public final class ContainerContent implements Packet {

    @Override
    public void encode(Buffer buf, Protocol.Version version) {
        buf
                .writeUnsignedByte(0)
                .writeInt(0)
                .writeInt(1)
                .writeSlot(new Slot(Material.STONE, 5))
                .writeBoolean(false);
    }
}
