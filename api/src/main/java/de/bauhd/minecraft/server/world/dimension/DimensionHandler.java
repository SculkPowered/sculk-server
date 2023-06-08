package de.bauhd.minecraft.server.world.dimension;

import org.jetbrains.annotations.NotNull;

import java.util.Collection;

public interface DimensionHandler {

    /**
     * registers a dimension
     * @param dimension the dimension to register
     */
    void registerDimension(@NotNull Dimension dimension);

    /**
     * @return a collection with all registered dimensions
     */
    @NotNull Collection<Dimension> getDimensions();

    /**
     * gets a dimension from its name
     * @param name the name of the dimension
     * @return dimension from name or plains if is doesn't exist
     */
    @NotNull Dimension getDimension(String name);
}
