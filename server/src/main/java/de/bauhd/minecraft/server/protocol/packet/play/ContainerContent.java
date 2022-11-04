package de.bauhd.minecraft.server.protocol.packet.play;

import de.bauhd.minecraft.server.api.inventory.Slot;
import de.bauhd.minecraft.server.api.world.Material;
import de.bauhd.minecraft.server.protocol.Protocol;
import de.bauhd.minecraft.server.protocol.packet.Packet;
import io.netty5.buffer.Buffer;

import static de.bauhd.minecraft.server.protocol.packet.PacketUtils.writeSlot;
import static de.bauhd.minecraft.server.protocol.packet.PacketUtils.writeVarInt;

public final class ContainerContent implements Packet {

    @Override
    public void decode(Buffer buf, Protocol.Version version) {

    }

    @Override
    public void encode(Buffer buf, Protocol.Version version) {
        buf.writeUnsignedByte(0);
        writeVarInt(buf, 0);
        writeVarInt(buf, 1);
        writeSlot(buf, new Slot(Material.STONE, 5));
        buf.writeBoolean(false);
    }
}
