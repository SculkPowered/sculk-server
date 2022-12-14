package de.bauhd.minecraft.server.protocol.packet.play;

import de.bauhd.minecraft.server.AdvancedMinecraftServer;
import de.bauhd.minecraft.server.api.inventory.Inventory;
import de.bauhd.minecraft.server.api.world.Position;
import de.bauhd.minecraft.server.api.world.block.Block;
import de.bauhd.minecraft.server.protocol.Buffer;
import de.bauhd.minecraft.server.protocol.Connection;
import de.bauhd.minecraft.server.protocol.Protocol;
import de.bauhd.minecraft.server.protocol.packet.Packet;
import de.bauhd.minecraft.server.protocol.packet.play.block.BlockUpdate;
import de.bauhd.minecraft.server.protocol.packet.play.container.OpenScreen;
import net.kyori.adventure.text.Component;

public final class UseItemOn implements Packet {

    private int hand;
    private Position position;
    private Block.Face face;
    private float x;
    private float y;
    private float z;
    private boolean insideBlock;
    private int sequence;

    @Override
    public void decode(Buffer buf, Protocol.Version version) {
        this.hand = buf.readVarInt();
        this.position = buf.readPosition();
        this.face = Block.Face.class.getEnumConstants()[buf.readVarInt()];
        this.x = buf.readFloat();
        this.y = buf.readFloat();
        this.z = buf.readFloat();
        this.insideBlock = buf.readBoolean();
        this.sequence = buf.readVarInt();
    }

    @Override
    public boolean handle(Connection connection) {
        connection.send(new OpenScreen(1, Inventory.Type.GENERIC_9x2.ordinal(), Component.empty()));
        final var player = connection.player();
        final var slot = player.getItem(player.getHeldItemSlot() + 36);
        if (slot == null) {
            return false;
        }
        this.position = switch (this.face) {
            case BOTTOM -> this.position.subtract(0, 1, 0);
            case TOP -> this.position.add(0, 1, 0);
            case NORTH -> this.position.subtract(0, 0, 1);
            case SOUTH -> this.position.add(0, 0, 1);
            case WEST -> this.position.subtract(1, 0, 0);
            case EAST -> this.position.add(1, 0, 0);
        };
        AdvancedMinecraftServer.getInstance().sendAll(new BlockUpdate(this.position, slot.materialId()));
        return false;
    }

    @Override
    public String toString() {
        return "UseItemOn{" +
                "hand=" + this.hand +
                ", position=" + this.position +
                ", face=" + this.face +
                ", x=" + this.x +
                ", y=" + this.y +
                ", z=" + this.z +
                ", insideBlock=" + this.insideBlock +
                ", sequence=" + this.sequence +
                '}';
    }
}
