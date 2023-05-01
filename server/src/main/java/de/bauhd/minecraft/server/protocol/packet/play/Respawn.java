package de.bauhd.minecraft.server.protocol.packet.play;

import de.bauhd.minecraft.server.entity.player.GameMode;
import de.bauhd.minecraft.server.protocol.Buffer;
import de.bauhd.minecraft.server.protocol.packet.Packet;

public final class Respawn implements Packet {

    private final String dimensionType;
    private final String dimensionName;
    private final long hashedSeed;
    private final GameMode gameMode;

    public Respawn(final String dimensionType, final String dimensionName, final long hashedSeed, final GameMode gameMode) {
        this.dimensionType = dimensionType;
        this.dimensionName = dimensionName;
        this.hashedSeed = hashedSeed;
        this.gameMode = gameMode;
    }

    @Override
    public void encode(Buffer buf) {
        buf
                .writeString(this.dimensionType)
                .writeString(this.dimensionType)
                .writeLong(this.hashedSeed)
                .writeUnsignedByte(this.gameMode.ordinal())
                .writeBoolean(false)
                .writeBoolean(false)
                .writeByte((byte) 0)
                .writeBoolean(false);
    }

    @Override
    public String toString() {
        return "Respawn{" +
                "dimensionType='" + this.dimensionType + '\'' +
                ", dimensionName='" + this.dimensionName + '\'' +
                ", hashedSeed=" + this.hashedSeed +
                ", gameMode=" + this.gameMode +
                '}';
    }
}
