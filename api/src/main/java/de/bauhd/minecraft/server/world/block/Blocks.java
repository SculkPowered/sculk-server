package de.bauhd.minecraft.server.world.block;

import org.jetbrains.annotations.ApiStatus;

import java.util.Map;

/**
 * This class is only for internal use!
 */
@ApiStatus.Internal
final class Blocks {

    private static Map<String, BlockState> BLOCKS_BY_NAME;
    private static Map<Integer, BlockState> BLOCKS_BY_ID;

    private Blocks() {}

    static BlockState get(final String name) {
        return BLOCKS_BY_NAME.get(name);
    }

    static BlockState get(int id) {
        return BLOCKS_BY_ID.get(id);
    }

    static void set(final Map<String, BlockState> byName, final Map<Integer, BlockState> byId) {
        BLOCKS_BY_NAME = byName;
        BLOCKS_BY_ID = byId;
    }
}