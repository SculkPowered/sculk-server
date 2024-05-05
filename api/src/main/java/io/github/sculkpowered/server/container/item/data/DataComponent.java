package io.github.sculkpowered.server.container.item.data;

import io.github.sculkpowered.server.container.item.ItemStack;
import io.github.sculkpowered.server.registry.Registries;
import java.util.List;
import java.util.Map;
import net.kyori.adventure.nbt.CompoundBinaryTag;
import net.kyori.adventure.text.Component;

public final class DataComponent {

  public static final DataComponentType<CompoundBinaryTag> CUSTOM_DATA = get("minecraft:custom_data");
  public static final DataComponentType<Integer> MAX_STACK_SIZE = get("minecraft:max_stack_size");
  public static final DataComponentType<Integer> MAX_DAMAGE = get("minecraft:max_damage");
  public static final DataComponentType<Integer> DAMAGE = get("minecraft:damage");
  public static final DataComponentType<Boolean> UNBREAKABLE = get("minecraft:unbreakable");
  public static final DataComponentType<Component> CUSTOM_NAME = get("minecraft:custom_name");
  public static final DataComponentType<Component> ITEM_NAME = get("minecraft:item_name");
  public static final DataComponentType<List<Component>> LORE = get("minecraft:lore");
  public static final DataComponentType<Rarity> RARITY = get("minecraft:rarity");
  public static final DataComponentType<ItemEnchantments> ENCHANTMENTS = get("minecraft:enchantments");
  public static final DataComponentType<Object> CAN_PLACE_ON = get("minecraft:can_place_on");
  public static final DataComponentType<Object> CAN_BREAK = get("minecraft:can_break");
  public static final DataComponentType<ItemAttributes> ATTRIBUTE_MODIFIERS = get("minecraft:attribute_modifiers");
  public static final DataComponentType<Integer> CUSTOM_MODEL_DATA = get("minecraft:custom_model_data");
  public static final DataComponentType<Void> HIDE_ADDITIONAL_TOOLTIP = get("minecraft:hide_additional_tooltip");
  public static final DataComponentType<Void> HIDE_TOOLTIP = get("minecraft:hide_tooltip");
  public static final DataComponentType<Integer> REPAIR_COST = get("minecraft:repair_cost");
  public static final DataComponentType<Void> CREATIVE_SLOT_LOCK = get("minecraft:creative_slot_lock");
  public static final DataComponentType<Boolean> ENCHANTMENT_GLINT_OVERRIDE = get("minecraft:enchantment_glint_override");
  public static final DataComponentType<Object> INTANGIBLE_PROJECTILE = get("minecraft:intangible_projectile");
  public static final DataComponentType<Object> FOOD = get("minecraft:food");
  public static final DataComponentType<Object> FIRE_RESISTANT = get("minecraft:fire_resistant");
  public static final DataComponentType<Object> TOOL = get("minecraft:tool");
  public static final DataComponentType<ItemEnchantments> STORED_ENCHANTMENTS = get("minecraft:stored_enchantments");
  public static final DataComponentType<Object> DYED_COLOR = get("minecraft:dyed_color");
  public static final DataComponentType<Object> MAP_COLOR = get("minecraft:map_color");
  public static final DataComponentType<Object> MAP_ID = get("minecraft:map_id");
  public static final DataComponentType<Object> MAP_DECORATIONS = get("minecraft:map_decorations");
  public static final DataComponentType<Object> MAP_POST_PROCESSING = get("minecraft:map_post_processing");
  public static final DataComponentType<Object> CHARGED_PROJECTILES = get("minecraft:charged_projectiles");
  public static final DataComponentType<Object> BUNDLE_CONTENTS = get("minecraft:bundle_contents");
  public static final DataComponentType<Object> POTION_CONTENTS = get("minecraft:potion_contents");
  public static final DataComponentType<Object> SUSPICIOUS_STEW_EFFECTS = get("minecraft:suspicious_stew_effects");
  public static final DataComponentType<Object> WRITABLE_BOOK_CONTENT = get("minecraft:writable_book_content");
  public static final DataComponentType<Object> WRITTEN_BOOK_CONTENT = get("minecraft:written_book_content");
  public static final DataComponentType<Object> TRIM = get("minecraft:trim");
  public static final DataComponentType<Object> DEBUG_STICK_STATE = get("minecraft:debug_stick_state");
  public static final DataComponentType<CompoundBinaryTag> ENTITY_DATA = get("minecraft:entity_data");
  public static final DataComponentType<CompoundBinaryTag> BUCKET_ENTITY_DATA = get("minecraft:bucket_entity_data");
  public static final DataComponentType<CompoundBinaryTag> BLOCK_ENTITY_DATA = get("minecraft:block_entity_data");
  public static final DataComponentType<Object> INSTRUMENT = get("minecraft:instrument");
  public static final DataComponentType<Integer> OMINOUS_BOTTLE_AMPLIFIER = get("minecraft:ominous_bottle_amplifier");
  public static final DataComponentType<Object> RECIPES = get("minecraft:recipes");
  public static final DataComponentType<Object> LODESTONE_TRACKER = get("minecraft:lodestone_tracker");
  public static final DataComponentType<Object> FIREWORK_EXPLOSION = get("minecraft:firework_explosion");
  public static final DataComponentType<Object> FIREWORKS = get("minecraft:fireworks");
  public static final DataComponentType<Object> PROFILE = get("minecraft:profile");
  public static final DataComponentType<Object> NOTE_BLOCK_SOUND = get("minecraft:note_block_sound");
  public static final DataComponentType<Object> BANNER_PATTERNS = get("minecraft:banner_patterns");
  public static final DataComponentType<Object> BASE_COLOR = get("minecraft:base_color");
  public static final DataComponentType<Object> POT_DECORATIONS = get("minecraft:pot_decorations");
  public static final DataComponentType<List<ItemStack>> CONTAINER = get("minecraft:container");
  public static final DataComponentType<Map<String, String>> BLOCK_STATE = get("minecraft:block_state");
  public static final DataComponentType<Object> BEES = get("minecraft:bees");
  public static final DataComponentType<Object> LOCK = get("minecraft:lock");
  public static final DataComponentType<Object> CONTAINER_LOOT = get("minecraft:container_loot");
  
  DataComponent() {
    throw new AssertionError();
  }

  @SuppressWarnings("unchecked")
  private static <T> DataComponentType<T> get(final String key) {
    return (DataComponentType<T>) Registries.dataComponentTypes().get(key);
  }
}
