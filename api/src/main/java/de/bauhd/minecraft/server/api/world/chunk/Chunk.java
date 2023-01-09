package de.bauhd.minecraft.server.api.world.chunk;

public interface Chunk {

    int getX();

    int getZ();

    void setBlock(int x, int y, int z, int stateId);

}
