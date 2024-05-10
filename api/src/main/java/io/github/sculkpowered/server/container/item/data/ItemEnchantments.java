package io.github.sculkpowered.server.container.item.data;

import io.github.sculkpowered.server.enchantment.Enchantment;
import java.util.Map;
import org.jetbrains.annotations.NotNull;

public record ItemEnchantments(
    @NotNull Map<Enchantment, Integer> enchantments,
    boolean showInTooltip
) {

}
