package io.github.sculkpowered.server.attribute;

import java.util.UUID;
import org.jetbrains.annotations.NotNull;

public record AttributeModifier(
    @NotNull UUID uniqueId,
    @NotNull String name,
    double amount,
    @NotNull AttributeOperation operation
) {
}
