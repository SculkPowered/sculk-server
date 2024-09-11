package eu.sculkpowered.server.container.item.data;

import eu.sculkpowered.server.potion.CustomPotionEffect;
import eu.sculkpowered.server.potion.PotionType;
import java.util.List;
import net.kyori.adventure.util.RGBLike;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public record PotionContents(
    @Nullable PotionType potion,
    @Nullable RGBLike color,
    @NotNull List<CustomPotionEffect> customEffects
) {

}
