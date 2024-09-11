package eu.sculkpowered.server.enchantment;

import eu.sculkpowered.server.registry.Registry;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.nbt.CompoundBinaryTag;
import net.kyori.adventure.nbt.ListBinaryTag;
import net.kyori.adventure.nbt.StringBinaryTag;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

/**
 * An enum of all enchantments supported.
 */
@SuppressWarnings("unused")
@ApiStatus.Experimental // TODO
public enum Enchantment implements Registry.Entry {

  // START,
  EFFICIENCY("efficiency")
  // END
  ;

  private final Key key;

  Enchantment(final String key) {
    this.key = Key.key(Key.MINECRAFT_NAMESPACE, key);
  }

  @Override
  public @NotNull Key key() {
    return this.key;
  }

  @Override
  public int id() {
    return this.ordinal();
  }

  @Override
  public @NotNull CompoundBinaryTag asNBT() {
    return CompoundBinaryTag.builder()
        .putInt("anvil_cost", 1)
        .put("description", CompoundBinaryTag.builder()
            .putString("translate", "enchantment.minecraft.efficiency")
            .build())
        .put("effects", CompoundBinaryTag.builder()
            .put("minecraft:attributes", ListBinaryTag.builder()
                .add(CompoundBinaryTag.builder()
                    .put("amount", CompoundBinaryTag.builder()
                        .putString("type", "minecraft:levels_squared")
                        .putDouble("added", 1.0)
                        .build())
                    .putString("attribute", "minecraft:player.mining_efficiency")
                    .putString("id", "minecraft:enchantment.efficiency")
                    .putString("operation", "add_value")
                    .build())
                .build())
            .build())
        .put("max_cost", CompoundBinaryTag.builder()
            .putInt("base", 51)
            .putInt("per_level_above_first", 10)
            .build())
        .putInt("max_level", 5)
        .put("min_cost", CompoundBinaryTag.builder()
            .putInt("base", 1)
            .putInt("per_level_above_first", 10)
            .build())
        .put("slots", ListBinaryTag.builder()
            .add(StringBinaryTag.stringBinaryTag("mainhand"))
            .build())
        .putString("supported_items", "#minecraft:enchantable/mining")
        .putInt("weight", 10)
        .build();
  }
}
