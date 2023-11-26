package io.github.sculkpowered.server.attribute;

import java.util.UUID;
import org.jetbrains.annotations.NotNull;

public record AttributeModifier(
    @NotNull UUID uniqueId,
    @NotNull AttributeOperation operation,
    String slot,
    double amount
) {
}
