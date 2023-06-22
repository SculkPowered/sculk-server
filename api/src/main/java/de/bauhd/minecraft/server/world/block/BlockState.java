package de.bauhd.minecraft.server.world.block;

import org.jetbrains.annotations.NotNull;

import java.util.Map;

/**
 * Represents a state of a block.
 */
public interface BlockState {

    /**
     * Gets the id of the state.
     * @return the state's id
     */
    int getId();

    /**
     * Gets the properties of the state.
     * @return the state's properties
     */
    @NotNull Map<String, String> getProperties();

    /**
     * Checks if the state has the property with the specified key.
     * @param key the key of the property
     * @return whether the state has the key or not
     */
    boolean hasProperty(@NotNull String key);

    /**
     * Checks if the state is powered.
     * @return whether the state is powered
     */
    boolean isPowered();

    /**
     * Sets whether the state should be powered.
     * @param powered powered or not
     * @return the state
     */
    BlockState powered(boolean powered);

    /**
     * Checks if the state is lit.
     * @return whether the state is lit
     */
    boolean isLit();

    /**
     * Sets whether the state should be lit.
     * @param lit lit or not
     * @return the state
     */
    BlockState lit(boolean lit);

    /**
     * Sets the face of the state.
     * @param face the face to set
     * @return the state
     */
    BlockState face(@NotNull Block.Face face);

    /**
     * Sets the facing of the state.
     * @param facing the facing to set
     * @return the state
     */
    BlockState facing(@NotNull Block.Facing facing);

    /**
     * Sets which half the state should be.
     * @param half the half to set
     * @return the state
     */
    BlockState half(@NotNull Block.Half half);

    /**
     * Checks if the state is open.
     * @return whether the state is open
     */
    boolean isOpen();

    /**
     * Sets the state open or not.
     * @param open open or not
     * @return the state
     */
    BlockState open(boolean open);

    /**
     * Checks if the state is waterlogged.
     * @return whether the state is waterlogged
     */
    boolean isWaterlogged();

    /**
     * Sets the state waterlogged or not.
     * @param waterlogged waterlogged or not
     * @return the state
     */
    BlockState waterlogged(boolean waterlogged);

    /**
     * Sets the property with the specified key to the specified value.
     * @param key the key of the property
     * @param value the value the porperty should be set to
     * @return the state
     */
    BlockState property(@NotNull String key, @NotNull String value);

    /**
     * Sets the property with the specified key to the specified value.
     * @param key the key of the property
     * @param value the value the property should be set to
     * @return the state
     */
    BlockState property(@NotNull String key, boolean value);

    /**
     * Sets the properties.
     * @param properties the properties to set
     * @return the state
     */
    BlockState properties(@NotNull Map<String, String> properties);
}
