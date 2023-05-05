package de.bauhd.minecraft.server.world.chunk;

import net.kyori.adventure.key.Key;

public interface Chunk {

    int getX();

    int getZ();

    void setBlock(int x, int y, int z, Key key);

}
