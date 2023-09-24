package de.bauhd.sculk.world.block;

import org.jetbrains.annotations.NotNull;

import java.util.Map;

/**
 * Represents a state of a block.
 */
public interface BlockState {

    /**
     * Gets the id of the state.
     *
     * @return the state's id
     * @since 1.0.0
     */
    int getId();

    /**
     * Gets the properties of the state.
     *
     * @return the state's properties
     * @since 1.0.0
     */
    @NotNull Map<String, String> getProperties();

    /**
     * Checks if the state has the property with the specified key.
     *
     * @param key the key of the property
     * @return whether the state has the key or not
     * @since 1.0.0
     */
    boolean hasProperty(@NotNull String key);

    /**
     * Sets the property with the specified key to the specified value.
     *
     * @param key   the key of the property
     * @param value the value the porperty should be set to
     * @return the state
     * @since 1.0.0
     */
    BlockState property(@NotNull String key, @NotNull String value);

    /**
     * Sets the property with the specified key to the specified value.
     *
     * @param key   the key of the property
     * @param value the value the property should be set to
     * @return the state
     * @since 1.0.0
     */
    BlockState property(@NotNull String key, boolean value);

    /**
     * Sets the property with the specified key to the specified value.
     *
     * @param key   the key of the property
     * @param value the value the property should be set to
     * @return the state
     * @since 1.0.0
     */
    BlockState property(@NotNull String key, int value);

    /**
     * Sets the properties.
     *
     * @param properties the properties to set
     * @return the state
     * @since 1.0.0
     */
    BlockState properties(@NotNull Map<String, String> properties);

    interface Waterloggable<T> extends BlockState {

        /**
         * Checks if the state is waterlogged.
         *
         * @return whether the state is waterlogged
         * @since 1.0.0
         */
        default boolean isWaterlogged() {
            return this.getProperties().get("waterlogged").equals("true");
        }

        /**
         * Sets the state waterlogged or not.
         *
         * @param waterlogged waterlogged or not
         * @return the state
         * @since 1.0.0
         */
        @SuppressWarnings("unchecked")
        default @NotNull T waterlogged(boolean waterlogged) {
            return (T) this.property("waterlogged", waterlogged);
        }
    }

    interface Powerable<T extends BlockState> extends BlockState {

        /**
         * Checks if the state is powered.
         *
         * @return whether the state is powered
         * @since 1.0.0
         */
        default boolean isPowered() {
            return this.getProperties().get("powered").equals("true");
        }

        /**
         * Sets whether the state should be powered.
         *
         * @param powered powered or not
         * @return the state
         * @since 1.0.0
         */
        @SuppressWarnings("unchecked")
        default @NotNull T powered(boolean powered) {
            return (T) this.property("powered", powered);
        }
    }

    interface Snowy<T extends BlockState> extends BlockState {

        /**
         * Checks if the state is snowy.
         *
         * @return whether the state is snowy
         * @since 1.0.0
         */
        default boolean isSnowy() {
            return this.getProperties().get("snowy").equals("true");
        }

        /**
         * Sets whether the state should be snowy.
         *
         * @param snowy snowy or not
         * @return the state
         * @since 1.0.0
         */
        @SuppressWarnings("unchecked")
        default @NotNull T powered(boolean snowy) {
            return (T) this.property("snowy", snowy);
        }
    }

    interface Face<T extends BlockState> extends BlockState {

        /**
         * Gets the face.
         *
         * @return the face
         * @since 1.0.0
         */
        default Block.Face face() {
            return Block.Face.valueOf(this.getProperties().get("face"));
        }

        /**
         * Sets the face.
         *
         * @param face the face to set
         * @return the state
         * @since 1.0.0
         */
        @SuppressWarnings("unchecked")
        default @NotNull T face(@NotNull Block.Face face) {
            return (T) this.property("face", face.name().toLowerCase());
        }
    }

    interface Facing<T extends BlockState> extends BlockState {

        /**
         * Gets the facing.
         *
         * @return the facing
         * @since 1.0.0
         */
        default Block.Facing facing() {
            return Block.Facing.valueOf(this.getProperties().get("facing"));
        }

        /**
         * Sets the facing.
         *
         * @param facing the facing to set
         * @return the state
         * @since 1.0.0
         */
        @SuppressWarnings("unchecked")
        default @NotNull T facing(@NotNull Block.Facing facing) {
            return (T) this.property("facing", facing.name().toLowerCase());
        }
    }

    interface Half<T extends BlockState> extends BlockState {

        /**
         * Gets the half.
         *
         * @return the half
         * @since 1.0.0
         */
        default Block.Half half() {
            return Block.Half.valueOf(this.getProperties().get("half"));
        }

        /**
         * Sets the half.
         *
         * @param half the half to set
         * @return the state
         * @since 1.0.0
         */
        @SuppressWarnings("unchecked")
        default @NotNull T half(@NotNull Block.Half half) {
            return (T) this.property("half", half.name().toLowerCase());
        }
    }

    interface Ageable<T extends BlockState> extends BlockState {

        /**
         * Gets the age.
         *
         * @return the age
         * @since 1.0.0
         */
        default int age() {
            return Integer.parseInt(this.getProperties().get("age"));
        }

        /**
         * Sets the age.
         *
         * @param age the age to set
         * @return the state
         * @since 1.0.0
         */
        @SuppressWarnings("unchecked")
        default @NotNull T age(int age) {
            return (T) this.property("age", age);
        }
    }
}
