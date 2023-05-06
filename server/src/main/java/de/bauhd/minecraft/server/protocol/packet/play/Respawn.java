package de.bauhd.minecraft.server.protocol.packet.play;

import de.bauhd.minecraft.server.entity.player.GameMode;
import de.bauhd.minecraft.server.protocol.Buffer;
import de.bauhd.minecraft.server.protocol.packet.Packet;

public final class Respawn implements Packet {

    private final String dimensionType;
    private final String dimensionName;
    private final long hashedSeed;
    private final GameMode gameMode;
    private final byte dataKept;

    public Respawn(final String dimensionType, final String dimensionName, final long hashedSeed, final GameMode gameMode, final byte dataKept) {
        this.dimensionType = dimensionType;
        this.dimensionName = dimensionName;
        this.hashedSeed = hashedSeed;
        this.gameMode = gameMode;
        this.dataKept = dataKept;
    }

    @Override
    public void encode(Buffer buf) {
        buf
                .writeString(this.dimensionType)
                .writeString(this.dimensionType)
                .writeLong(this.hashedSeed)
                .writeUnsignedByte(this.gameMode.ordinal())
                .writeByte((byte) -1)
                .writeBoolean(false)
                .writeBoolean(false)
                .writeByte((byte) this.dataKept)
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
