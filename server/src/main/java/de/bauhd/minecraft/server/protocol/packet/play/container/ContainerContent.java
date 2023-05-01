package de.bauhd.minecraft.server.protocol.packet.play.container;

import de.bauhd.minecraft.server.inventory.item.ItemStack;
import de.bauhd.minecraft.server.inventory.item.Material;
import de.bauhd.minecraft.server.protocol.Buffer;
import de.bauhd.minecraft.server.protocol.packet.Packet;

public final class ContainerContent implements Packet {

    @Override
    public void encode(Buffer buf) {
        buf
                .writeUnsignedByte(0)
                .writeInt(0)
                .writeInt(1)
                .writeSlot(new ItemStack(Material.STONE, 5))
                .writeBoolean(false);
    }
}
