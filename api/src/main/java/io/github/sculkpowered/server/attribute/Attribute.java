package io.github.sculkpowered.server.attribute;

import org.jetbrains.annotations.NotNull;

public record Attribute(@NotNull String key, int id, float def, float max) {

}
