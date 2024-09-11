package eu.sculkpowered.server.container.item.data;

import eu.sculkpowered.server.enchantment.Enchantment;
import java.util.Map;
import org.jetbrains.annotations.NotNull;

public record ItemEnchantments(
    @NotNull Map<Enchantment, Integer> enchantments,
    boolean showInTooltip
) {

}
