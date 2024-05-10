package io.github.sculkpowered.server.container.item.data;

import net.kyori.adventure.util.RGBLike;
import org.jetbrains.annotations.NotNull;

public record DyedColor(
    @NotNull RGBLike color,
    boolean showInTooltip
) {

}
