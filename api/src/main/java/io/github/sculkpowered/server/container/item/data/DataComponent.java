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

  public static final DataComponent<CompoundBinaryTag> CUSTOM_DATA = create("custom_data");
  public static final DataComponent<Integer> MAX_STACK_SIZE = create("max_stack_size");
  public static final DataComponent<Integer> MAX_DAMAGE = create("max_damage");
  public static final DataComponent<Integer> DAMAGE = create("damage");
  public static final DataComponent<Object> UNBREAKABLE = create("unbreakable");
  public static final DataComponent<Component> CUSTOM_NAME = create("custom_name");
  public static final DataComponent<Component> ITEM_NAME = create("item_name");
  public static final DataComponent<Object> LORE = create("lore");
  public static final DataComponent<Object> RARITY = create("rarity");
  public static final DataComponent<Object> ENCHANTMENTS = create("enchantments");
  public static final DataComponent<Object> CAN_PLACE_ON = create("can_place_on");
  public static final DataComponent<Object> CAN_BREAK = create("can_break");
  public static final DataComponent<Object> ATTRIBUTE_MODIFIERS = create("attribute_modifiers");
  public static final DataComponent<Object> CUSTOM_MODEL_DATA = create("custom_model_data");
  public static final DataComponent<Void> HIDE_ADDITIONAL_TOOLTIP = create(
      "hide_additional_tooltip");
  public static final DataComponent<Void> HIDE_TOOLTIP = create("hide_tooltip");
  public static final DataComponent<Integer> REPAIR_COST = create("rapair_cost");
  public static final DataComponent<Void> CREATIVE_SLOT_LOCK = create("creative_slot_lock");
  public static final DataComponent<Boolean> ENCHANTMENT_GLINT_OVERRIDE = create(
      "enchantment_glint_override");
  public static final DataComponent<Object> INTANGIBLE_PROJECTILE = create("intangible_projectile");
  public static final DataComponent<Object> FOOD = create("food");
  public static final DataComponent<Object> FIRE_RESISTANT = create("fire_resistant");
  public static final DataComponent<Object> TOOL = create("tool");
  public static final DataComponent<Object> STORED_ENCHANTMENTS = create("stored_enchantments");
  public static final DataComponent<Object> DYED_COLOR = create("dyed_color");
  public static final DataComponent<Object> MAP_COLOR = create("map_color");
  public static final DataComponent<Object> MAP_ID = create("map_id");
  public static final DataComponent<Object> MAP_DECORATIONS = create("map_decorations");
  public static final DataComponent<Object> MAP_POST_PROCESSING = create("map_post_processing");
  public static final DataComponent<Object> CHARGED_PROJECTILES = create("charged_projectiles");
  public static final DataComponent<Object> BUNDLE_CONTENTS = create("bundle_contents");
  public static final DataComponent<Object> POTION_CONTENTS = create("potion_contents");
  public static final DataComponent<Object> SUSPICIOUS_STEW_EFFECTS = create(
      "suspicious_stew_effects");
  public static final DataComponent<Object> WRITABLE_BOOK_CONTENT = create("writable_book_content");
  public static final DataComponent<Object> WRITTEN_BOOK_CONTENT = create("written_book_content");
  public static final DataComponent<Object> TRIM = create("trim");
  public static final DataComponent<Object> DEBUG_STICK_STATE = create("debug_stick_state");
  public static final DataComponent<CompoundBinaryTag> ENTITY_DATA = create("entity_data");
  public static final DataComponent<CompoundBinaryTag> BUCKET_ENTITY_DATA = create("bucket_entity_data");
  public static final DataComponent<CompoundBinaryTag> BLOCK_ENTITY_DATA = create("block_entity_data");
  public static final DataComponent<Object> INSTRUMENT = create("instrument");
  public static final DataComponent<Integer> OMINOUS_BOTTLE_AMPLIFIER = create(
      "ominous_bottle_amplifier");
  public static final DataComponent<Object> RECIPES = create("recipes");
  public static final DataComponent<Object> LODESTONE_TRACKER = create("lodestone_tracker");
  public static final DataComponent<Object> FIREWORK_EXPLOSION = create("firework_explosion");
  public static final DataComponent<Object> FIREWORKS = create("fireworks");
  public static final DataComponent<Object> PROFILE = create("profile");
  public static final DataComponent<Object> NOTE_BLOCK_SOUND = create("note_block_sound");
  public static final DataComponent<Object> BANNER_PATTERNS = create("banner_patterns");
  public static final DataComponent<Object> BASE_COLOR = create("base_color");
  public static final DataComponent<Object> POT_DECORATIONS = create("pot_decorations");
  public static final DataComponent<List<ItemStack>> CONTAINER = create("container");
  public static final DataComponent<Object> BLOCK_STATE = create("block_state");
  public static final DataComponent<Object> BEES = create("bees");
  public static final DataComponent<Object> LOCK = create("lock");
  public static final DataComponent<Object> CONTAINER_LOOT = create("container_loot");

  private final Key key;

  private DataComponent(final Key key) {
    this.key = key;
  }

  @Override
  public @NotNull Key key() {
    return this.key;
  }

  private static <T> DataComponent<T> create(final @Subst("value") String key) {
    return new DataComponent<>(Key.key(Key.MINECRAFT_NAMESPACE, key));
  }
}
