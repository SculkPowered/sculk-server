package de.bauhd.minecraft.server.world.dimension;

import org.jetbrains.annotations.NotNull;

import java.util.Collection;

public interface DimensionHandler {

    /**
     * Registers the specified {@link Dimension}.
     * @param dimension the dimension to register
     */
    void register(@NotNull Dimension dimension);

    /**
     * Gets all registered dimensions.
     * @return a collection with all registered dimensions
     */
    @NotNull Collection<Dimension> getDimensions();

    /**
     * Gets a dimension from its name.
     * @param name the name of the dimension
     * @return dimension from name or {@code Dimension.OVERWORLD} if is does not exist
     */
    @NotNull Dimension getDimension(@NotNull String name);
}
