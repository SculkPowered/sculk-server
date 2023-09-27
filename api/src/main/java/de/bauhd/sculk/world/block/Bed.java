package de.bauhd.sculk.world.block;

import org.jetbrains.annotations.NotNull;

public interface Bed extends BlockState.Facing<Bed> {

    /**
     * Checks if the state is occupied.
     *
     * @return whether the state is occupied
     * @since 1.0.0
     */
    boolean occupied();

    /**
     * Sets whether the state should be occupied.
     *
     * @param occupied occupied or not
     * @return the state
     * @since 1.0.0
     */
    @NotNull Bed occupied(boolean occupied);

    /**
     * Gets the part.
     *
     * @return the part
     * @since 1.0.0
     */
    @NotNull Part part();

    /**
     * Sets the part.
     *
     * @param part the part to set
     * @return the state
     * @since 1.0.0
     */
    @NotNull Bed part(@NotNull Part part);

    /**
     * @since 1.0.0
     */
    enum Part {
        HEAD,
        FOOT
    }
}
