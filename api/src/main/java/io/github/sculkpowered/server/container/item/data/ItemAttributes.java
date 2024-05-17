package io.github.sculkpowered.server.container.item.data;

import io.github.sculkpowered.server.attribute.Attribute;
import io.github.sculkpowered.server.attribute.AttributeModifier;
import io.github.sculkpowered.server.attribute.AttributeSlot;
import java.util.List;
import org.jetbrains.annotations.NotNull;

public record ItemAttributes(
    @NotNull List<Entry> attributes,
    boolean showInTooltip
) {

  public record Entry(
      @NotNull Attribute attribute,
      @NotNull AttributeModifier modifier,
      @NotNull AttributeSlot slot
  ) {

  }
}
