package io.github.sculkpowered.server.container.item.data;

import io.github.sculkpowered.server.container.item.ItemStack;
import java.util.List;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.key.Keyed;
import net.kyori.adventure.nbt.CompoundBinaryTag;
import net.kyori.adventure.text.Component;
import org.intellij.lang.annotations.Subst;
import org.jetbrains.annotations.NotNull;

public final class DataComponent<T> implements Keyed {

  public static final DataComponent<CompoundBinaryTag> CUSTOM_DATA = create(0, "custom_data");
  public static final DataComponent<Integer> MAX_STACK_SIZE = create(1, "max_stack_size");
  public static final DataComponent<Integer> MAX_DAMAGE = create(2, "max_damage");
  public static final DataComponent<Integer> DAMAGE = create(3, "damage");
  public static final DataComponent<Object> UNBREAKABLE = create(4, "unbreakable");
  public static final DataComponent<Component> CUSTOM_NAME = create(5, "custom_name");
  public static final DataComponent<Component> ITEM_NAME = create(6, "item_name");
  public static final DataComponent<Object> LORE = create(7, "lore");
  public static final DataComponent<Object> RARITY = create(8, "rarity");
  public static final DataComponent<Object> ENCHANTMENTS = create(9, "enchantments");
  public static final DataComponent<Object> CAN_PLACE_ON = create(10, "can_place_on");
  public static final DataComponent<Object> CAN_BREAK = create(11, "can_break");
  public static final DataComponent<Object> ATTRIBUTE_MODIFIERS = create(12, "attribute_modifiers");
  public static final DataComponent<Object> CUSTOM_MODEL_DATA = create(13, "custom_model_data");
  public static final DataComponent<Void> HIDE_ADDITIONAL_TOOLTIP = create(14,
      "hide_additional_tooltip");
  public static final DataComponent<Void> HIDE_TOOLTIP = create(15, "hide_tooltip");
  public static final DataComponent<Integer> REPAIR_COST = create(16, "repair_cost");
  public static final DataComponent<Void> CREATIVE_SLOT_LOCK = create(17, "creative_slot_lock");
  public static final DataComponent<Boolean> ENCHANTMENT_GLINT_OVERRIDE = create(18,
      "enchantment_glint_override");
  public static final DataComponent<Object> INTANGIBLE_PROJECTILE = create(19,
      "intangible_projectile");
  public static final DataComponent<Object> FOOD = create(20, "food");
  public static final DataComponent<Object> FIRE_RESISTANT = create(21, "fire_resistant");
  public static final DataComponent<Object> TOOL = create(22, "tool");
  public static final DataComponent<Object> STORED_ENCHANTMENTS = create(23, "stored_enchantments");
  public static final DataComponent<Object> DYED_COLOR = create(24, "dyed_color");
  public static final DataComponent<Object> MAP_COLOR = create(25, "map_color");
  public static final DataComponent<Object> MAP_ID = create(26, "map_id");
  public static final DataComponent<Object> MAP_DECORATIONS = create(27, "map_decorations");
  public static final DataComponent<Object> MAP_POST_PROCESSING = create(28, "map_post_processing");
  public static final DataComponent<Object> CHARGED_PROJECTILES = create(29, "charged_projectiles");
  public static final DataComponent<Object> BUNDLE_CONTENTS = create(30, "bundle_contents");
  public static final DataComponent<Object> POTION_CONTENTS = create(31, "potion_contents");
  public static final DataComponent<Object> SUSPICIOUS_STEW_EFFECTS = create(32,
      "suspicious_stew_effects");
  public static final DataComponent<Object> WRITABLE_BOOK_CONTENT = create(33,
      "writable_book_content");
  public static final DataComponent<Object> WRITTEN_BOOK_CONTENT = create(34,
      "written_book_content");
  public static final DataComponent<Object> TRIM = create(35, "trim");
  public static final DataComponent<Object> DEBUG_STICK_STATE = create(36, "debug_stick_state");
  public static final DataComponent<CompoundBinaryTag> ENTITY_DATA = create(37, "entity_data");
  public static final DataComponent<CompoundBinaryTag> BUCKET_ENTITY_DATA = create(38,
      "bucket_entity_data");
  public static final DataComponent<CompoundBinaryTag> BLOCK_ENTITY_DATA = create(39,
      "block_entity_data");
  public static final DataComponent<Object> INSTRUMENT = create(40, "instrument");
  public static final DataComponent<Integer> OMINOUS_BOTTLE_AMPLIFIER = create(41,
      "ominous_bottle_amplifier");
  public static final DataComponent<Object> RECIPES = create(42, "recipes");
  public static final DataComponent<Object> LODESTONE_TRACKER = create(43, "lodestone_tracker");
  public static final DataComponent<Object> FIREWORK_EXPLOSION = create(44, "firework_explosion");
  public static final DataComponent<Object> FIREWORKS = create(45, "fireworks");
  public static final DataComponent<Object> PROFILE = create(46, "profile");
  public static final DataComponent<Object> NOTE_BLOCK_SOUND = create(47, "note_block_sound");
  public static final DataComponent<Object> BANNER_PATTERNS = create(48, "banner_patterns");
  public static final DataComponent<Object> BASE_COLOR = create(49, "base_color");
  public static final DataComponent<Object> POT_DECORATIONS = create(50, "pot_decorations");
  public static final DataComponent<List<ItemStack>> CONTAINER = create(51, "container");
  public static final DataComponent<Object> BLOCK_STATE = create(52, "block_state");
  public static final DataComponent<Object> BEES = create(53, "bees");
  public static final DataComponent<Object> LOCK = create(54, "lock");
  public static final DataComponent<Object> CONTAINER_LOOT = create(55, "container_loot");

  private final int id;
  private final Key key;

  private DataComponent(final int id, final Key key) {
    this.id = id;
    this.key = key;
  }

  public int id() {
    return this.id;
  }

  @Override
  public @NotNull Key key() {
    return this.key;
  }

  private static <T> DataComponent<T> create(final int id, final @Subst("value") String key) {
    return new DataComponent<>(id, Key.key(Key.MINECRAFT_NAMESPACE, key));
  }
}
