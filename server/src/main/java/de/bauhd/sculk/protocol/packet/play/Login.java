package de.bauhd.sculk.protocol.packet.play;

import de.bauhd.sculk.damage.DamageType;
import de.bauhd.sculk.protocol.Buffer;
import de.bauhd.sculk.protocol.packet.Packet;
import de.bauhd.sculk.registry.Registry;
import de.bauhd.sculk.world.biome.Biome;
import de.bauhd.sculk.world.dimension.Dimension;
import net.kyori.adventure.nbt.CompoundBinaryTag;

public final class Login implements Packet {

    private final int entityId;
    private final byte gameMode;
    private final String dimensionType;

    public Login(final int entityId, final byte gameMode, final String dimensionType) {
        this.entityId = entityId;
        this.gameMode = gameMode;
        this.dimensionType = dimensionType;
    }

    @Override
    public void encode(Buffer buf) {
        buf
                .writeInt(this.entityId) // Entity id
                .writeBoolean(false) // Hardcode
                .writeVarInt(1) // Dimensions
                .writeString("minecraft:world") // Dimensions
                .writeVarInt(20) // Max Players
                .writeVarInt(18) // View Distance
                .writeVarInt(10) // Simulation Distance
                .writeBoolean(false) // Reduced Debug Info
                .writeBoolean(false) // Enable respawn screen
                .writeBoolean(false) // Limited crafting
                .writeString(this.dimensionType) // Dimension Type
                .writeString("minecraft:overworld") // Dimension Name
                .writeLong(0) // Hashed Seed
                .writeByte(this.gameMode) // GameMode
                .writeByte((byte) -1) // Previous GameMode
                .writeBoolean(false) // Debug
                .writeBoolean(true) // Flat
                .writeBoolean(false) // Death Location
                .writeVarInt(0); // Portal Cooldown
    }

    @Override
    public String toString() {
        return "Login{" +
                "entityId=" + this.entityId +
                '}';
    }
}
