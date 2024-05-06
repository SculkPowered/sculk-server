package io.github.sculkpowered.server.container.item.data;

import io.github.sculkpowered.server.enchantment.Enchantment;
import java.util.Map;

public record ItemEnchantments(Map<Enchantment, Integer> enchantments, boolean showInTooltip) {

}
