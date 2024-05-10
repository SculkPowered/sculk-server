package io.github.sculkpowered.server.container.item.data;

import io.github.sculkpowered.server.attribute.Attribute;
import io.github.sculkpowered.server.attribute.AttributeModifier;
import io.github.sculkpowered.server.attribute.AttributeSlot;
import java.util.List;
import org.jetbrains.annotations.NotNull;

public record ItemAttributes(
    @NotNull List<Modifier> attributes,
    boolean showInTooltip
) {

  public record Modifier(
      @NotNull Attribute attribute,
      @NotNull AttributeModifier modifier,
      @NotNull AttributeSlot slot
  ) {

  }
}
