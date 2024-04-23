package io.github.sculkpowered.server.world.block;

import java.util.HashMap;
import java.util.Map;
import net.kyori.adventure.nbt.CompoundBinaryTag;
import org.jetbrains.annotations.NotNull;

/**
 * Represent all supported blocks.
 */
@SuppressWarnings({"unused", "unchecked"}) // I know - it looks better
public final class Block {

  // START
  public static final BlockState ACACIA_BUTTON = get("minecraft:acacia_button");
  public static final BlockState ACACIA_DOOR = get("minecraft:acacia_door");
  public static final BlockState ACACIA_FENCE = get("minecraft:acacia_fence");
  public static final BlockState ACACIA_FENCE_GATE = get("minecraft:acacia_fence_gate");
  public static final BlockState ACACIA_HANGING_SIGN = get("minecraft:acacia_hanging_sign");
  public static final BlockState ACACIA_LEAVES = get("minecraft:acacia_leaves");
  public static final BlockState ACACIA_LOG = get("minecraft:acacia_log");
  public static final BlockState ACACIA_PLANKS = get("minecraft:acacia_planks");
  public static final BlockState ACACIA_PRESSURE_PLATE = get("minecraft:acacia_pressure_plate");
  public static final BlockState ACACIA_SAPLING = get("minecraft:acacia_sapling");
  public static final BlockState ACACIA_SIGN = get("minecraft:acacia_sign");
  public static final BlockState ACACIA_SLAB = get("minecraft:acacia_slab");
  public static final BlockState ACACIA_STAIRS = get("minecraft:acacia_stairs");
  public static final BlockState ACACIA_TRAPDOOR = get("minecraft:acacia_trapdoor");
  public static final BlockState ACACIA_WALL_HANGING_SIGN = get("minecraft:acacia_wall_hanging_sign");
  public static final BlockState ACACIA_WALL_SIGN = get("minecraft:acacia_wall_sign");
  public static final BlockState ACACIA_WOOD = get("minecraft:acacia_wood");
  public static final BlockState ACTIVATOR_RAIL = get("minecraft:activator_rail");
  public static final BlockState AIR = get("minecraft:air");
  public static final BlockState ALLIUM = get("minecraft:allium");
  public static final BlockState AMETHYST_BLOCK = get("minecraft:amethyst_block");
  public static final BlockState AMETHYST_CLUSTER = get("minecraft:amethyst_cluster");
  public static final BlockState ANCIENT_DEBRIS = get("minecraft:ancient_debris");
  public static final BlockState ANDESITE = get("minecraft:andesite");
  public static final BlockState ANDESITE_SLAB = get("minecraft:andesite_slab");
  public static final BlockState ANDESITE_STAIRS = get("minecraft:andesite_stairs");
  public static final BlockState ANDESITE_WALL = get("minecraft:andesite_wall");
  public static final BlockState ANVIL = get("minecraft:anvil");
  public static final BlockState ATTACHED_MELON_STEM = get("minecraft:attached_melon_stem");
  public static final BlockState ATTACHED_PUMPKIN_STEM = get("minecraft:attached_pumpkin_stem");
  public static final BlockState AZALEA = get("minecraft:azalea");
  public static final BlockState AZALEA_LEAVES = get("minecraft:azalea_leaves");
  public static final BlockState AZURE_BLUET = get("minecraft:azure_bluet");
  public static final BlockState BAMBOO = get("minecraft:bamboo");
  public static final BlockState BAMBOO_BLOCK = get("minecraft:bamboo_block");
  public static final BlockState BAMBOO_BUTTON = get("minecraft:bamboo_button");
  public static final BlockState BAMBOO_DOOR = get("minecraft:bamboo_door");
  public static final BlockState BAMBOO_FENCE = get("minecraft:bamboo_fence");
  public static final BlockState BAMBOO_FENCE_GATE = get("minecraft:bamboo_fence_gate");
  public static final BlockState BAMBOO_HANGING_SIGN = get("minecraft:bamboo_hanging_sign");
  public static final BlockState BAMBOO_MOSAIC = get("minecraft:bamboo_mosaic");
  public static final BlockState BAMBOO_MOSAIC_SLAB = get("minecraft:bamboo_mosaic_slab");
  public static final BlockState BAMBOO_MOSAIC_STAIRS = get("minecraft:bamboo_mosaic_stairs");
  public static final BlockState BAMBOO_PLANKS = get("minecraft:bamboo_planks");
  public static final BlockState BAMBOO_PRESSURE_PLATE = get("minecraft:bamboo_pressure_plate");
  public static final BlockState BAMBOO_SAPLING = get("minecraft:bamboo_sapling");
  public static final BlockState BAMBOO_SIGN = get("minecraft:bamboo_sign");
  public static final BlockState BAMBOO_SLAB = get("minecraft:bamboo_slab");
  public static final BlockState BAMBOO_STAIRS = get("minecraft:bamboo_stairs");
  public static final BlockState BAMBOO_TRAPDOOR = get("minecraft:bamboo_trapdoor");
  public static final BlockState BAMBOO_WALL_HANGING_SIGN = get("minecraft:bamboo_wall_hanging_sign");
  public static final BlockState BAMBOO_WALL_SIGN = get("minecraft:bamboo_wall_sign");
  public static final BlockState BARREL = get("minecraft:barrel");
  public static final BlockState BARRIER = get("minecraft:barrier");
  public static final BlockState BASALT = get("minecraft:basalt");
  public static final BlockState BEACON = get("minecraft:beacon");
  public static final BlockState BEDROCK = get("minecraft:bedrock");
  public static final BlockState BEE_NEST = get("minecraft:bee_nest");
  public static final BlockState BEEHIVE = get("minecraft:beehive");
  public static final BlockState BEETROOTS = get("minecraft:beetroots");
  public static final BlockState BELL = get("minecraft:bell");
  public static final BlockState BIG_DRIPLEAF = get("minecraft:big_dripleaf");
  public static final BlockState BIG_DRIPLEAF_STEM = get("minecraft:big_dripleaf_stem");
  public static final BlockState BIRCH_BUTTON = get("minecraft:birch_button");
  public static final BlockState BIRCH_DOOR = get("minecraft:birch_door");
  public static final BlockState BIRCH_FENCE = get("minecraft:birch_fence");
  public static final BlockState BIRCH_FENCE_GATE = get("minecraft:birch_fence_gate");
  public static final BlockState BIRCH_HANGING_SIGN = get("minecraft:birch_hanging_sign");
  public static final BlockState BIRCH_LEAVES = get("minecraft:birch_leaves");
  public static final BlockState BIRCH_LOG = get("minecraft:birch_log");
  public static final BlockState BIRCH_PLANKS = get("minecraft:birch_planks");
  public static final BlockState BIRCH_PRESSURE_PLATE = get("minecraft:birch_pressure_plate");
  public static final BlockState BIRCH_SAPLING = get("minecraft:birch_sapling");
  public static final BlockState BIRCH_SIGN = get("minecraft:birch_sign");
  public static final BlockState BIRCH_SLAB = get("minecraft:birch_slab");
  public static final BlockState BIRCH_STAIRS = get("minecraft:birch_stairs");
  public static final BlockState BIRCH_TRAPDOOR = get("minecraft:birch_trapdoor");
  public static final BlockState BIRCH_WALL_HANGING_SIGN = get("minecraft:birch_wall_hanging_sign");
  public static final BlockState BIRCH_WALL_SIGN = get("minecraft:birch_wall_sign");
  public static final BlockState BIRCH_WOOD = get("minecraft:birch_wood");
  public static final BlockState BLACK_BANNER = get("minecraft:black_banner");
  public static final BlockState BLACK_BED = get("minecraft:black_bed");
  public static final BlockState BLACK_CANDLE = get("minecraft:black_candle");
  public static final BlockState BLACK_CANDLE_CAKE = get("minecraft:black_candle_cake");
  public static final BlockState BLACK_CARPET = get("minecraft:black_carpet");
  public static final BlockState BLACK_CONCRETE = get("minecraft:black_concrete");
  public static final BlockState BLACK_CONCRETE_POWDER = get("minecraft:black_concrete_powder");
  public static final BlockState BLACK_GLAZED_TERRACOTTA = get("minecraft:black_glazed_terracotta");
  public static final BlockState BLACK_SHULKER_BOX = get("minecraft:black_shulker_box");
  public static final BlockState BLACK_STAINED_GLASS = get("minecraft:black_stained_glass");
  public static final BlockState BLACK_STAINED_GLASS_PANE = get("minecraft:black_stained_glass_pane");
  public static final BlockState BLACK_TERRACOTTA = get("minecraft:black_terracotta");
  public static final BlockState BLACK_WALL_BANNER = get("minecraft:black_wall_banner");
  public static final BlockState BLACK_WOOL = get("minecraft:black_wool");
  public static final BlockState BLACKSTONE = get("minecraft:blackstone");
  public static final BlockState BLACKSTONE_SLAB = get("minecraft:blackstone_slab");
  public static final BlockState BLACKSTONE_STAIRS = get("minecraft:blackstone_stairs");
  public static final BlockState BLACKSTONE_WALL = get("minecraft:blackstone_wall");
  public static final BlockState BLAST_FURNACE = get("minecraft:blast_furnace");
  public static final BlockState BLUE_BANNER = get("minecraft:blue_banner");
  public static final BlockState BLUE_BED = get("minecraft:blue_bed");
  public static final BlockState BLUE_CANDLE = get("minecraft:blue_candle");
  public static final BlockState BLUE_CANDLE_CAKE = get("minecraft:blue_candle_cake");
  public static final BlockState BLUE_CARPET = get("minecraft:blue_carpet");
  public static final BlockState BLUE_CONCRETE = get("minecraft:blue_concrete");
  public static final BlockState BLUE_CONCRETE_POWDER = get("minecraft:blue_concrete_powder");
  public static final BlockState BLUE_GLAZED_TERRACOTTA = get("minecraft:blue_glazed_terracotta");
  public static final BlockState BLUE_ICE = get("minecraft:blue_ice");
  public static final BlockState BLUE_ORCHID = get("minecraft:blue_orchid");
  public static final BlockState BLUE_SHULKER_BOX = get("minecraft:blue_shulker_box");
  public static final BlockState BLUE_STAINED_GLASS = get("minecraft:blue_stained_glass");
  public static final BlockState BLUE_STAINED_GLASS_PANE = get("minecraft:blue_stained_glass_pane");
  public static final BlockState BLUE_TERRACOTTA = get("minecraft:blue_terracotta");
  public static final BlockState BLUE_WALL_BANNER = get("minecraft:blue_wall_banner");
  public static final BlockState BLUE_WOOL = get("minecraft:blue_wool");
  public static final BlockState BONE_BLOCK = get("minecraft:bone_block");
  public static final BlockState BOOKSHELF = get("minecraft:bookshelf");
  public static final BlockState BRAIN_CORAL = get("minecraft:brain_coral");
  public static final BlockState BRAIN_CORAL_BLOCK = get("minecraft:brain_coral_block");
  public static final BlockState BRAIN_CORAL_FAN = get("minecraft:brain_coral_fan");
  public static final BlockState BRAIN_CORAL_WALL_FAN = get("minecraft:brain_coral_wall_fan");
  public static final BlockState BREWING_STAND = get("minecraft:brewing_stand");
  public static final BlockState BRICK_SLAB = get("minecraft:brick_slab");
  public static final BlockState BRICK_STAIRS = get("minecraft:brick_stairs");
  public static final BlockState BRICK_WALL = get("minecraft:brick_wall");
  public static final BlockState BRICKS = get("minecraft:bricks");
  public static final BlockState BROWN_BANNER = get("minecraft:brown_banner");
  public static final BlockState BROWN_BED = get("minecraft:brown_bed");
  public static final BlockState BROWN_CANDLE = get("minecraft:brown_candle");
  public static final BlockState BROWN_CANDLE_CAKE = get("minecraft:brown_candle_cake");
  public static final BlockState BROWN_CARPET = get("minecraft:brown_carpet");
  public static final BlockState BROWN_CONCRETE = get("minecraft:brown_concrete");
  public static final BlockState BROWN_CONCRETE_POWDER = get("minecraft:brown_concrete_powder");
  public static final BlockState BROWN_GLAZED_TERRACOTTA = get("minecraft:brown_glazed_terracotta");
  public static final BlockState BROWN_MUSHROOM = get("minecraft:brown_mushroom");
  public static final BlockState BROWN_MUSHROOM_BLOCK = get("minecraft:brown_mushroom_block");
  public static final BlockState BROWN_SHULKER_BOX = get("minecraft:brown_shulker_box");
  public static final BlockState BROWN_STAINED_GLASS = get("minecraft:brown_stained_glass");
  public static final BlockState BROWN_STAINED_GLASS_PANE = get("minecraft:brown_stained_glass_pane");
  public static final BlockState BROWN_TERRACOTTA = get("minecraft:brown_terracotta");
  public static final BlockState BROWN_WALL_BANNER = get("minecraft:brown_wall_banner");
  public static final BlockState BROWN_WOOL = get("minecraft:brown_wool");
  public static final BlockState BUBBLE_COLUMN = get("minecraft:bubble_column");
  public static final BlockState BUBBLE_CORAL = get("minecraft:bubble_coral");
  public static final BlockState BUBBLE_CORAL_BLOCK = get("minecraft:bubble_coral_block");
  public static final BlockState BUBBLE_CORAL_FAN = get("minecraft:bubble_coral_fan");
  public static final BlockState BUBBLE_CORAL_WALL_FAN = get("minecraft:bubble_coral_wall_fan");
  public static final BlockState BUDDING_AMETHYST = get("minecraft:budding_amethyst");
  public static final BlockState CACTUS = get("minecraft:cactus");
  public static final BlockState CAKE = get("minecraft:cake");
  public static final BlockState CALCITE = get("minecraft:calcite");
  public static final BlockState CALIBRATED_SCULK_SENSOR = get("minecraft:calibrated_sculk_sensor");
  public static final BlockState CAMPFIRE = get("minecraft:campfire");
  public static final BlockState CANDLE = get("minecraft:candle");
  public static final BlockState CANDLE_CAKE = get("minecraft:candle_cake");
  public static final BlockState CARROTS = get("minecraft:carrots");
  public static final BlockState CARTOGRAPHY_TABLE = get("minecraft:cartography_table");
  public static final BlockState CARVED_PUMPKIN = get("minecraft:carved_pumpkin");
  public static final BlockState CAULDRON = get("minecraft:cauldron");
  public static final BlockState CAVE_AIR = get("minecraft:cave_air");
  public static final BlockState CAVE_VINES = get("minecraft:cave_vines");
  public static final BlockState CAVE_VINES_PLANT = get("minecraft:cave_vines_plant");
  public static final BlockState CHAIN = get("minecraft:chain");
  public static final BlockState CHAIN_COMMAND_BLOCK = get("minecraft:chain_command_block");
  public static final BlockState CHERRY_BUTTON = get("minecraft:cherry_button");
  public static final BlockState CHERRY_DOOR = get("minecraft:cherry_door");
  public static final BlockState CHERRY_FENCE = get("minecraft:cherry_fence");
  public static final BlockState CHERRY_FENCE_GATE = get("minecraft:cherry_fence_gate");
  public static final BlockState CHERRY_HANGING_SIGN = get("minecraft:cherry_hanging_sign");
  public static final BlockState CHERRY_LEAVES = get("minecraft:cherry_leaves");
  public static final BlockState CHERRY_LOG = get("minecraft:cherry_log");
  public static final BlockState CHERRY_PLANKS = get("minecraft:cherry_planks");
  public static final BlockState CHERRY_PRESSURE_PLATE = get("minecraft:cherry_pressure_plate");
  public static final BlockState CHERRY_SAPLING = get("minecraft:cherry_sapling");
  public static final BlockState CHERRY_SIGN = get("minecraft:cherry_sign");
  public static final BlockState CHERRY_SLAB = get("minecraft:cherry_slab");
  public static final BlockState CHERRY_STAIRS = get("minecraft:cherry_stairs");
  public static final BlockState CHERRY_TRAPDOOR = get("minecraft:cherry_trapdoor");
  public static final BlockState CHERRY_WALL_HANGING_SIGN = get("minecraft:cherry_wall_hanging_sign");
  public static final BlockState CHERRY_WALL_SIGN = get("minecraft:cherry_wall_sign");
  public static final BlockState CHERRY_WOOD = get("minecraft:cherry_wood");
  public static final BlockState CHEST = get("minecraft:chest");
  public static final BlockState CHIPPED_ANVIL = get("minecraft:chipped_anvil");
  public static final BlockState CHISELED_BOOKSHELF = get("minecraft:chiseled_bookshelf");
  public static final BlockState CHISELED_COPPER = get("minecraft:chiseled_copper");
  public static final BlockState CHISELED_DEEPSLATE = get("minecraft:chiseled_deepslate");
  public static final BlockState CHISELED_NETHER_BRICKS = get("minecraft:chiseled_nether_bricks");
  public static final BlockState CHISELED_POLISHED_BLACKSTONE = get("minecraft:chiseled_polished_blackstone");
  public static final BlockState CHISELED_QUARTZ_BLOCK = get("minecraft:chiseled_quartz_block");
  public static final BlockState CHISELED_RED_SANDSTONE = get("minecraft:chiseled_red_sandstone");
  public static final BlockState CHISELED_SANDSTONE = get("minecraft:chiseled_sandstone");
  public static final BlockState CHISELED_STONE_BRICKS = get("minecraft:chiseled_stone_bricks");
  public static final BlockState CHISELED_TUFF = get("minecraft:chiseled_tuff");
  public static final BlockState CHISELED_TUFF_BRICKS = get("minecraft:chiseled_tuff_bricks");
  public static final BlockState CHORUS_FLOWER = get("minecraft:chorus_flower");
  public static final BlockState CHORUS_PLANT = get("minecraft:chorus_plant");
  public static final BlockState CLAY = get("minecraft:clay");
  public static final BlockState COAL_BLOCK = get("minecraft:coal_block");
  public static final BlockState COAL_ORE = get("minecraft:coal_ore");
  public static final BlockState COARSE_DIRT = get("minecraft:coarse_dirt");
  public static final BlockState COBBLED_DEEPSLATE = get("minecraft:cobbled_deepslate");
  public static final BlockState COBBLED_DEEPSLATE_SLAB = get("minecraft:cobbled_deepslate_slab");
  public static final BlockState COBBLED_DEEPSLATE_STAIRS = get("minecraft:cobbled_deepslate_stairs");
  public static final BlockState COBBLED_DEEPSLATE_WALL = get("minecraft:cobbled_deepslate_wall");
  public static final BlockState COBBLESTONE = get("minecraft:cobblestone");
  public static final BlockState COBBLESTONE_SLAB = get("minecraft:cobblestone_slab");
  public static final BlockState COBBLESTONE_STAIRS = get("minecraft:cobblestone_stairs");
  public static final BlockState COBBLESTONE_WALL = get("minecraft:cobblestone_wall");
  public static final BlockState COBWEB = get("minecraft:cobweb");
  public static final BlockState COCOA = get("minecraft:cocoa");
  public static final BlockState COMMAND_BLOCK = get("minecraft:command_block");
  public static final BlockState COMPARATOR = get("minecraft:comparator");
  public static final BlockState COMPOSTER = get("minecraft:composter");
  public static final BlockState CONDUIT = get("minecraft:conduit");
  public static final BlockState COPPER_BLOCK = get("minecraft:copper_block");
  public static final BlockState COPPER_BULB = get("minecraft:copper_bulb");
  public static final BlockState COPPER_DOOR = get("minecraft:copper_door");
  public static final BlockState COPPER_GRATE = get("minecraft:copper_grate");
  public static final BlockState COPPER_ORE = get("minecraft:copper_ore");
  public static final BlockState COPPER_TRAPDOOR = get("minecraft:copper_trapdoor");
  public static final BlockState CORNFLOWER = get("minecraft:cornflower");
  public static final BlockState CRACKED_DEEPSLATE_BRICKS = get("minecraft:cracked_deepslate_bricks");
  public static final BlockState CRACKED_DEEPSLATE_TILES = get("minecraft:cracked_deepslate_tiles");
  public static final BlockState CRACKED_NETHER_BRICKS = get("minecraft:cracked_nether_bricks");
  public static final BlockState CRACKED_POLISHED_BLACKSTONE_BRICKS = get("minecraft:cracked_polished_blackstone_bricks");
  public static final BlockState CRACKED_STONE_BRICKS = get("minecraft:cracked_stone_bricks");
  public static final BlockState CRAFTER = get("minecraft:crafter");
  public static final BlockState CRAFTING_TABLE = get("minecraft:crafting_table");
  public static final BlockState CREEPER_HEAD = get("minecraft:creeper_head");
  public static final BlockState CREEPER_WALL_HEAD = get("minecraft:creeper_wall_head");
  public static final BlockState CRIMSON_BUTTON = get("minecraft:crimson_button");
  public static final BlockState CRIMSON_DOOR = get("minecraft:crimson_door");
  public static final BlockState CRIMSON_FENCE = get("minecraft:crimson_fence");
  public static final BlockState CRIMSON_FENCE_GATE = get("minecraft:crimson_fence_gate");
  public static final BlockState CRIMSON_FUNGUS = get("minecraft:crimson_fungus");
  public static final BlockState CRIMSON_HANGING_SIGN = get("minecraft:crimson_hanging_sign");
  public static final BlockState CRIMSON_HYPHAE = get("minecraft:crimson_hyphae");
  public static final BlockState CRIMSON_NYLIUM = get("minecraft:crimson_nylium");
  public static final BlockState CRIMSON_PLANKS = get("minecraft:crimson_planks");
  public static final BlockState CRIMSON_PRESSURE_PLATE = get("minecraft:crimson_pressure_plate");
  public static final BlockState CRIMSON_ROOTS = get("minecraft:crimson_roots");
  public static final BlockState CRIMSON_SIGN = get("minecraft:crimson_sign");
  public static final BlockState CRIMSON_SLAB = get("minecraft:crimson_slab");
  public static final BlockState CRIMSON_STAIRS = get("minecraft:crimson_stairs");
  public static final BlockState CRIMSON_STEM = get("minecraft:crimson_stem");
  public static final BlockState CRIMSON_TRAPDOOR = get("minecraft:crimson_trapdoor");
  public static final BlockState CRIMSON_WALL_HANGING_SIGN = get("minecraft:crimson_wall_hanging_sign");
  public static final BlockState CRIMSON_WALL_SIGN = get("minecraft:crimson_wall_sign");
  public static final BlockState CRYING_OBSIDIAN = get("minecraft:crying_obsidian");
  public static final BlockState CUT_COPPER = get("minecraft:cut_copper");
  public static final BlockState CUT_COPPER_SLAB = get("minecraft:cut_copper_slab");
  public static final BlockState CUT_COPPER_STAIRS = get("minecraft:cut_copper_stairs");
  public static final BlockState CUT_RED_SANDSTONE = get("minecraft:cut_red_sandstone");
  public static final BlockState CUT_RED_SANDSTONE_SLAB = get("minecraft:cut_red_sandstone_slab");
  public static final BlockState CUT_SANDSTONE = get("minecraft:cut_sandstone");
  public static final BlockState CUT_SANDSTONE_SLAB = get("minecraft:cut_sandstone_slab");
  public static final BlockState CYAN_BANNER = get("minecraft:cyan_banner");
  public static final BlockState CYAN_BED = get("minecraft:cyan_bed");
  public static final BlockState CYAN_CANDLE = get("minecraft:cyan_candle");
  public static final BlockState CYAN_CANDLE_CAKE = get("minecraft:cyan_candle_cake");
  public static final BlockState CYAN_CARPET = get("minecraft:cyan_carpet");
  public static final BlockState CYAN_CONCRETE = get("minecraft:cyan_concrete");
  public static final BlockState CYAN_CONCRETE_POWDER = get("minecraft:cyan_concrete_powder");
  public static final BlockState CYAN_GLAZED_TERRACOTTA = get("minecraft:cyan_glazed_terracotta");
  public static final BlockState CYAN_SHULKER_BOX = get("minecraft:cyan_shulker_box");
  public static final BlockState CYAN_STAINED_GLASS = get("minecraft:cyan_stained_glass");
  public static final BlockState CYAN_STAINED_GLASS_PANE = get("minecraft:cyan_stained_glass_pane");
  public static final BlockState CYAN_TERRACOTTA = get("minecraft:cyan_terracotta");
  public static final BlockState CYAN_WALL_BANNER = get("minecraft:cyan_wall_banner");
  public static final BlockState CYAN_WOOL = get("minecraft:cyan_wool");
  public static final BlockState DAMAGED_ANVIL = get("minecraft:damaged_anvil");
  public static final BlockState DANDELION = get("minecraft:dandelion");
  public static final BlockState DARK_OAK_BUTTON = get("minecraft:dark_oak_button");
  public static final BlockState DARK_OAK_DOOR = get("minecraft:dark_oak_door");
  public static final BlockState DARK_OAK_FENCE = get("minecraft:dark_oak_fence");
  public static final BlockState DARK_OAK_FENCE_GATE = get("minecraft:dark_oak_fence_gate");
  public static final BlockState DARK_OAK_HANGING_SIGN = get("minecraft:dark_oak_hanging_sign");
  public static final BlockState DARK_OAK_LEAVES = get("minecraft:dark_oak_leaves");
  public static final BlockState DARK_OAK_LOG = get("minecraft:dark_oak_log");
  public static final BlockState DARK_OAK_PLANKS = get("minecraft:dark_oak_planks");
  public static final BlockState DARK_OAK_PRESSURE_PLATE = get("minecraft:dark_oak_pressure_plate");
  public static final BlockState DARK_OAK_SAPLING = get("minecraft:dark_oak_sapling");
  public static final BlockState DARK_OAK_SIGN = get("minecraft:dark_oak_sign");
  public static final BlockState DARK_OAK_SLAB = get("minecraft:dark_oak_slab");
  public static final BlockState DARK_OAK_STAIRS = get("minecraft:dark_oak_stairs");
  public static final BlockState DARK_OAK_TRAPDOOR = get("minecraft:dark_oak_trapdoor");
  public static final BlockState DARK_OAK_WALL_HANGING_SIGN = get("minecraft:dark_oak_wall_hanging_sign");
  public static final BlockState DARK_OAK_WALL_SIGN = get("minecraft:dark_oak_wall_sign");
  public static final BlockState DARK_OAK_WOOD = get("minecraft:dark_oak_wood");
  public static final BlockState DARK_PRISMARINE = get("minecraft:dark_prismarine");
  public static final BlockState DARK_PRISMARINE_SLAB = get("minecraft:dark_prismarine_slab");
  public static final BlockState DARK_PRISMARINE_STAIRS = get("minecraft:dark_prismarine_stairs");
  public static final BlockState DAYLIGHT_DETECTOR = get("minecraft:daylight_detector");
  public static final BlockState DEAD_BRAIN_CORAL = get("minecraft:dead_brain_coral");
  public static final BlockState DEAD_BRAIN_CORAL_BLOCK = get("minecraft:dead_brain_coral_block");
  public static final BlockState DEAD_BRAIN_CORAL_FAN = get("minecraft:dead_brain_coral_fan");
  public static final BlockState DEAD_BRAIN_CORAL_WALL_FAN = get("minecraft:dead_brain_coral_wall_fan");
  public static final BlockState DEAD_BUBBLE_CORAL = get("minecraft:dead_bubble_coral");
  public static final BlockState DEAD_BUBBLE_CORAL_BLOCK = get("minecraft:dead_bubble_coral_block");
  public static final BlockState DEAD_BUBBLE_CORAL_FAN = get("minecraft:dead_bubble_coral_fan");
  public static final BlockState DEAD_BUBBLE_CORAL_WALL_FAN = get("minecraft:dead_bubble_coral_wall_fan");
  public static final BlockState DEAD_BUSH = get("minecraft:dead_bush");
  public static final BlockState DEAD_FIRE_CORAL = get("minecraft:dead_fire_coral");
  public static final BlockState DEAD_FIRE_CORAL_BLOCK = get("minecraft:dead_fire_coral_block");
  public static final BlockState DEAD_FIRE_CORAL_FAN = get("minecraft:dead_fire_coral_fan");
  public static final BlockState DEAD_FIRE_CORAL_WALL_FAN = get("minecraft:dead_fire_coral_wall_fan");
  public static final BlockState DEAD_HORN_CORAL = get("minecraft:dead_horn_coral");
  public static final BlockState DEAD_HORN_CORAL_BLOCK = get("minecraft:dead_horn_coral_block");
  public static final BlockState DEAD_HORN_CORAL_FAN = get("minecraft:dead_horn_coral_fan");
  public static final BlockState DEAD_HORN_CORAL_WALL_FAN = get("minecraft:dead_horn_coral_wall_fan");
  public static final BlockState DEAD_TUBE_CORAL = get("minecraft:dead_tube_coral");
  public static final BlockState DEAD_TUBE_CORAL_BLOCK = get("minecraft:dead_tube_coral_block");
  public static final BlockState DEAD_TUBE_CORAL_FAN = get("minecraft:dead_tube_coral_fan");
  public static final BlockState DEAD_TUBE_CORAL_WALL_FAN = get("minecraft:dead_tube_coral_wall_fan");
  public static final BlockState DECORATED_POT = get("minecraft:decorated_pot");
  public static final BlockState DEEPSLATE = get("minecraft:deepslate");
  public static final BlockState DEEPSLATE_BRICK_SLAB = get("minecraft:deepslate_brick_slab");
  public static final BlockState DEEPSLATE_BRICK_STAIRS = get("minecraft:deepslate_brick_stairs");
  public static final BlockState DEEPSLATE_BRICK_WALL = get("minecraft:deepslate_brick_wall");
  public static final BlockState DEEPSLATE_BRICKS = get("minecraft:deepslate_bricks");
  public static final BlockState DEEPSLATE_COAL_ORE = get("minecraft:deepslate_coal_ore");
  public static final BlockState DEEPSLATE_COPPER_ORE = get("minecraft:deepslate_copper_ore");
  public static final BlockState DEEPSLATE_DIAMOND_ORE = get("minecraft:deepslate_diamond_ore");
  public static final BlockState DEEPSLATE_EMERALD_ORE = get("minecraft:deepslate_emerald_ore");
  public static final BlockState DEEPSLATE_GOLD_ORE = get("minecraft:deepslate_gold_ore");
  public static final BlockState DEEPSLATE_IRON_ORE = get("minecraft:deepslate_iron_ore");
  public static final BlockState DEEPSLATE_LAPIS_ORE = get("minecraft:deepslate_lapis_ore");
  public static final BlockState DEEPSLATE_REDSTONE_ORE = get("minecraft:deepslate_redstone_ore");
  public static final BlockState DEEPSLATE_TILE_SLAB = get("minecraft:deepslate_tile_slab");
  public static final BlockState DEEPSLATE_TILE_STAIRS = get("minecraft:deepslate_tile_stairs");
  public static final BlockState DEEPSLATE_TILE_WALL = get("minecraft:deepslate_tile_wall");
  public static final BlockState DEEPSLATE_TILES = get("minecraft:deepslate_tiles");
  public static final BlockState DETECTOR_RAIL = get("minecraft:detector_rail");
  public static final BlockState DIAMOND_BLOCK = get("minecraft:diamond_block");
  public static final BlockState DIAMOND_ORE = get("minecraft:diamond_ore");
  public static final BlockState DIORITE = get("minecraft:diorite");
  public static final BlockState DIORITE_SLAB = get("minecraft:diorite_slab");
  public static final BlockState DIORITE_STAIRS = get("minecraft:diorite_stairs");
  public static final BlockState DIORITE_WALL = get("minecraft:diorite_wall");
  public static final BlockState DIRT = get("minecraft:dirt");
  public static final BlockState DIRT_PATH = get("minecraft:dirt_path");
  public static final BlockState DISPENSER = get("minecraft:dispenser");
  public static final BlockState DRAGON_EGG = get("minecraft:dragon_egg");
  public static final BlockState DRAGON_HEAD = get("minecraft:dragon_head");
  public static final BlockState DRAGON_WALL_HEAD = get("minecraft:dragon_wall_head");
  public static final BlockState DRIED_KELP_BLOCK = get("minecraft:dried_kelp_block");
  public static final BlockState DRIPSTONE_BLOCK = get("minecraft:dripstone_block");
  public static final BlockState DROPPER = get("minecraft:dropper");
  public static final BlockState EMERALD_BLOCK = get("minecraft:emerald_block");
  public static final BlockState EMERALD_ORE = get("minecraft:emerald_ore");
  public static final BlockState ENCHANTING_TABLE = get("minecraft:enchanting_table");
  public static final BlockState END_GATEWAY = get("minecraft:end_gateway");
  public static final BlockState END_PORTAL = get("minecraft:end_portal");
  public static final BlockState END_PORTAL_FRAME = get("minecraft:end_portal_frame");
  public static final BlockState END_ROD = get("minecraft:end_rod");
  public static final BlockState END_STONE = get("minecraft:end_stone");
  public static final BlockState END_STONE_BRICK_SLAB = get("minecraft:end_stone_brick_slab");
  public static final BlockState END_STONE_BRICK_STAIRS = get("minecraft:end_stone_brick_stairs");
  public static final BlockState END_STONE_BRICK_WALL = get("minecraft:end_stone_brick_wall");
  public static final BlockState END_STONE_BRICKS = get("minecraft:end_stone_bricks");
  public static final BlockState ENDER_CHEST = get("minecraft:ender_chest");
  public static final BlockState EXPOSED_CHISELED_COPPER = get("minecraft:exposed_chiseled_copper");
  public static final BlockState EXPOSED_COPPER = get("minecraft:exposed_copper");
  public static final BlockState EXPOSED_COPPER_BULB = get("minecraft:exposed_copper_bulb");
  public static final BlockState EXPOSED_COPPER_DOOR = get("minecraft:exposed_copper_door");
  public static final BlockState EXPOSED_COPPER_GRATE = get("minecraft:exposed_copper_grate");
  public static final BlockState EXPOSED_COPPER_TRAPDOOR = get("minecraft:exposed_copper_trapdoor");
  public static final BlockState EXPOSED_CUT_COPPER = get("minecraft:exposed_cut_copper");
  public static final BlockState EXPOSED_CUT_COPPER_SLAB = get("minecraft:exposed_cut_copper_slab");
  public static final BlockState EXPOSED_CUT_COPPER_STAIRS = get("minecraft:exposed_cut_copper_stairs");
  public static final BlockState FARMLAND = get("minecraft:farmland");
  public static final BlockState FERN = get("minecraft:fern");
  public static final BlockState FIRE = get("minecraft:fire");
  public static final BlockState FIRE_CORAL = get("minecraft:fire_coral");
  public static final BlockState FIRE_CORAL_BLOCK = get("minecraft:fire_coral_block");
  public static final BlockState FIRE_CORAL_FAN = get("minecraft:fire_coral_fan");
  public static final BlockState FIRE_CORAL_WALL_FAN = get("minecraft:fire_coral_wall_fan");
  public static final BlockState FLETCHING_TABLE = get("minecraft:fletching_table");
  public static final BlockState FLOWER_POT = get("minecraft:flower_pot");
  public static final BlockState FLOWERING_AZALEA = get("minecraft:flowering_azalea");
  public static final BlockState FLOWERING_AZALEA_LEAVES = get("minecraft:flowering_azalea_leaves");
  public static final BlockState FROGSPAWN = get("minecraft:frogspawn");
  public static final BlockState FROSTED_ICE = get("minecraft:frosted_ice");
  public static final BlockState FURNACE = get("minecraft:furnace");
  public static final BlockState GILDED_BLACKSTONE = get("minecraft:gilded_blackstone");
  public static final BlockState GLASS = get("minecraft:glass");
  public static final BlockState GLASS_PANE = get("minecraft:glass_pane");
  public static final BlockState GLOW_LICHEN = get("minecraft:glow_lichen");
  public static final BlockState GLOWSTONE = get("minecraft:glowstone");
  public static final BlockState GOLD_BLOCK = get("minecraft:gold_block");
  public static final BlockState GOLD_ORE = get("minecraft:gold_ore");
  public static final BlockState GRANITE = get("minecraft:granite");
  public static final BlockState GRANITE_SLAB = get("minecraft:granite_slab");
  public static final BlockState GRANITE_STAIRS = get("minecraft:granite_stairs");
  public static final BlockState GRANITE_WALL = get("minecraft:granite_wall");
  public static final BlockState GRASS_BLOCK = get("minecraft:grass_block");
  public static final BlockState GRAVEL = get("minecraft:gravel");
  public static final BlockState GRAY_BANNER = get("minecraft:gray_banner");
  public static final BlockState GRAY_BED = get("minecraft:gray_bed");
  public static final BlockState GRAY_CANDLE = get("minecraft:gray_candle");
  public static final BlockState GRAY_CANDLE_CAKE = get("minecraft:gray_candle_cake");
  public static final BlockState GRAY_CARPET = get("minecraft:gray_carpet");
  public static final BlockState GRAY_CONCRETE = get("minecraft:gray_concrete");
  public static final BlockState GRAY_CONCRETE_POWDER = get("minecraft:gray_concrete_powder");
  public static final BlockState GRAY_GLAZED_TERRACOTTA = get("minecraft:gray_glazed_terracotta");
  public static final BlockState GRAY_SHULKER_BOX = get("minecraft:gray_shulker_box");
  public static final BlockState GRAY_STAINED_GLASS = get("minecraft:gray_stained_glass");
  public static final BlockState GRAY_STAINED_GLASS_PANE = get("minecraft:gray_stained_glass_pane");
  public static final BlockState GRAY_TERRACOTTA = get("minecraft:gray_terracotta");
  public static final BlockState GRAY_WALL_BANNER = get("minecraft:gray_wall_banner");
  public static final BlockState GRAY_WOOL = get("minecraft:gray_wool");
  public static final BlockState GREEN_BANNER = get("minecraft:green_banner");
  public static final BlockState GREEN_BED = get("minecraft:green_bed");
  public static final BlockState GREEN_CANDLE = get("minecraft:green_candle");
  public static final BlockState GREEN_CANDLE_CAKE = get("minecraft:green_candle_cake");
  public static final BlockState GREEN_CARPET = get("minecraft:green_carpet");
  public static final BlockState GREEN_CONCRETE = get("minecraft:green_concrete");
  public static final BlockState GREEN_CONCRETE_POWDER = get("minecraft:green_concrete_powder");
  public static final BlockState GREEN_GLAZED_TERRACOTTA = get("minecraft:green_glazed_terracotta");
  public static final BlockState GREEN_SHULKER_BOX = get("minecraft:green_shulker_box");
  public static final BlockState GREEN_STAINED_GLASS = get("minecraft:green_stained_glass");
  public static final BlockState GREEN_STAINED_GLASS_PANE = get("minecraft:green_stained_glass_pane");
  public static final BlockState GREEN_TERRACOTTA = get("minecraft:green_terracotta");
  public static final BlockState GREEN_WALL_BANNER = get("minecraft:green_wall_banner");
  public static final BlockState GREEN_WOOL = get("minecraft:green_wool");
  public static final BlockState GRINDSTONE = get("minecraft:grindstone");
  public static final BlockState HANGING_ROOTS = get("minecraft:hanging_roots");
  public static final BlockState HAY_BLOCK = get("minecraft:hay_block");
  public static final BlockState HEAVY_CORE = get("minecraft:heavy_core");
  public static final BlockState HEAVY_WEIGHTED_PRESSURE_PLATE = get("minecraft:heavy_weighted_pressure_plate");
  public static final BlockState HONEY_BLOCK = get("minecraft:honey_block");
  public static final BlockState HONEYCOMB_BLOCK = get("minecraft:honeycomb_block");
  public static final BlockState HOPPER = get("minecraft:hopper");
  public static final BlockState HORN_CORAL = get("minecraft:horn_coral");
  public static final BlockState HORN_CORAL_BLOCK = get("minecraft:horn_coral_block");
  public static final BlockState HORN_CORAL_FAN = get("minecraft:horn_coral_fan");
  public static final BlockState HORN_CORAL_WALL_FAN = get("minecraft:horn_coral_wall_fan");
  public static final BlockState ICE = get("minecraft:ice");
  public static final BlockState INFESTED_CHISELED_STONE_BRICKS = get("minecraft:infested_chiseled_stone_bricks");
  public static final BlockState INFESTED_COBBLESTONE = get("minecraft:infested_cobblestone");
  public static final BlockState INFESTED_CRACKED_STONE_BRICKS = get("minecraft:infested_cracked_stone_bricks");
  public static final BlockState INFESTED_DEEPSLATE = get("minecraft:infested_deepslate");
  public static final BlockState INFESTED_MOSSY_STONE_BRICKS = get("minecraft:infested_mossy_stone_bricks");
  public static final BlockState INFESTED_STONE = get("minecraft:infested_stone");
  public static final BlockState INFESTED_STONE_BRICKS = get("minecraft:infested_stone_bricks");
  public static final BlockState IRON_BARS = get("minecraft:iron_bars");
  public static final BlockState IRON_BLOCK = get("minecraft:iron_block");
  public static final BlockState IRON_DOOR = get("minecraft:iron_door");
  public static final BlockState IRON_ORE = get("minecraft:iron_ore");
  public static final BlockState IRON_TRAPDOOR = get("minecraft:iron_trapdoor");
  public static final BlockState JACK_O_LANTERN = get("minecraft:jack_o_lantern");
  public static final BlockState JIGSAW = get("minecraft:jigsaw");
  public static final BlockState JUKEBOX = get("minecraft:jukebox");
  public static final BlockState JUNGLE_BUTTON = get("minecraft:jungle_button");
  public static final BlockState JUNGLE_DOOR = get("minecraft:jungle_door");
  public static final BlockState JUNGLE_FENCE = get("minecraft:jungle_fence");
  public static final BlockState JUNGLE_FENCE_GATE = get("minecraft:jungle_fence_gate");
  public static final BlockState JUNGLE_HANGING_SIGN = get("minecraft:jungle_hanging_sign");
  public static final BlockState JUNGLE_LEAVES = get("minecraft:jungle_leaves");
  public static final BlockState JUNGLE_LOG = get("minecraft:jungle_log");
  public static final BlockState JUNGLE_PLANKS = get("minecraft:jungle_planks");
  public static final BlockState JUNGLE_PRESSURE_PLATE = get("minecraft:jungle_pressure_plate");
  public static final BlockState JUNGLE_SAPLING = get("minecraft:jungle_sapling");
  public static final BlockState JUNGLE_SIGN = get("minecraft:jungle_sign");
  public static final BlockState JUNGLE_SLAB = get("minecraft:jungle_slab");
  public static final BlockState JUNGLE_STAIRS = get("minecraft:jungle_stairs");
  public static final BlockState JUNGLE_TRAPDOOR = get("minecraft:jungle_trapdoor");
  public static final BlockState JUNGLE_WALL_HANGING_SIGN = get("minecraft:jungle_wall_hanging_sign");
  public static final BlockState JUNGLE_WALL_SIGN = get("minecraft:jungle_wall_sign");
  public static final BlockState JUNGLE_WOOD = get("minecraft:jungle_wood");
  public static final BlockState KELP = get("minecraft:kelp");
  public static final BlockState KELP_PLANT = get("minecraft:kelp_plant");
  public static final BlockState LADDER = get("minecraft:ladder");
  public static final BlockState LANTERN = get("minecraft:lantern");
  public static final BlockState LAPIS_BLOCK = get("minecraft:lapis_block");
  public static final BlockState LAPIS_ORE = get("minecraft:lapis_ore");
  public static final BlockState LARGE_AMETHYST_BUD = get("minecraft:large_amethyst_bud");
  public static final BlockState LARGE_FERN = get("minecraft:large_fern");
  public static final BlockState LAVA = get("minecraft:lava");
  public static final BlockState LAVA_CAULDRON = get("minecraft:lava_cauldron");
  public static final BlockState LECTERN = get("minecraft:lectern");
  public static final BlockState LEVER = get("minecraft:lever");
  public static final BlockState LIGHT = get("minecraft:light");
  public static final BlockState LIGHT_BLUE_BANNER = get("minecraft:light_blue_banner");
  public static final BlockState LIGHT_BLUE_BED = get("minecraft:light_blue_bed");
  public static final BlockState LIGHT_BLUE_CANDLE = get("minecraft:light_blue_candle");
  public static final BlockState LIGHT_BLUE_CANDLE_CAKE = get("minecraft:light_blue_candle_cake");
  public static final BlockState LIGHT_BLUE_CARPET = get("minecraft:light_blue_carpet");
  public static final BlockState LIGHT_BLUE_CONCRETE = get("minecraft:light_blue_concrete");
  public static final BlockState LIGHT_BLUE_CONCRETE_POWDER = get("minecraft:light_blue_concrete_powder");
  public static final BlockState LIGHT_BLUE_GLAZED_TERRACOTTA = get("minecraft:light_blue_glazed_terracotta");
  public static final BlockState LIGHT_BLUE_SHULKER_BOX = get("minecraft:light_blue_shulker_box");
  public static final BlockState LIGHT_BLUE_STAINED_GLASS = get("minecraft:light_blue_stained_glass");
  public static final BlockState LIGHT_BLUE_STAINED_GLASS_PANE = get("minecraft:light_blue_stained_glass_pane");
  public static final BlockState LIGHT_BLUE_TERRACOTTA = get("minecraft:light_blue_terracotta");
  public static final BlockState LIGHT_BLUE_WALL_BANNER = get("minecraft:light_blue_wall_banner");
  public static final BlockState LIGHT_BLUE_WOOL = get("minecraft:light_blue_wool");
  public static final BlockState LIGHT_GRAY_BANNER = get("minecraft:light_gray_banner");
  public static final BlockState LIGHT_GRAY_BED = get("minecraft:light_gray_bed");
  public static final BlockState LIGHT_GRAY_CANDLE = get("minecraft:light_gray_candle");
  public static final BlockState LIGHT_GRAY_CANDLE_CAKE = get("minecraft:light_gray_candle_cake");
  public static final BlockState LIGHT_GRAY_CARPET = get("minecraft:light_gray_carpet");
  public static final BlockState LIGHT_GRAY_CONCRETE = get("minecraft:light_gray_concrete");
  public static final BlockState LIGHT_GRAY_CONCRETE_POWDER = get("minecraft:light_gray_concrete_powder");
  public static final BlockState LIGHT_GRAY_GLAZED_TERRACOTTA = get("minecraft:light_gray_glazed_terracotta");
  public static final BlockState LIGHT_GRAY_SHULKER_BOX = get("minecraft:light_gray_shulker_box");
  public static final BlockState LIGHT_GRAY_STAINED_GLASS = get("minecraft:light_gray_stained_glass");
  public static final BlockState LIGHT_GRAY_STAINED_GLASS_PANE = get("minecraft:light_gray_stained_glass_pane");
  public static final BlockState LIGHT_GRAY_TERRACOTTA = get("minecraft:light_gray_terracotta");
  public static final BlockState LIGHT_GRAY_WALL_BANNER = get("minecraft:light_gray_wall_banner");
  public static final BlockState LIGHT_GRAY_WOOL = get("minecraft:light_gray_wool");
  public static final BlockState LIGHT_WEIGHTED_PRESSURE_PLATE = get("minecraft:light_weighted_pressure_plate");
  public static final BlockState LIGHTNING_ROD = get("minecraft:lightning_rod");
  public static final BlockState LILAC = get("minecraft:lilac");
  public static final BlockState LILY_OF_THE_VALLEY = get("minecraft:lily_of_the_valley");
  public static final BlockState LILY_PAD = get("minecraft:lily_pad");
  public static final BlockState LIME_BANNER = get("minecraft:lime_banner");
  public static final BlockState LIME_BED = get("minecraft:lime_bed");
  public static final BlockState LIME_CANDLE = get("minecraft:lime_candle");
  public static final BlockState LIME_CANDLE_CAKE = get("minecraft:lime_candle_cake");
  public static final BlockState LIME_CARPET = get("minecraft:lime_carpet");
  public static final BlockState LIME_CONCRETE = get("minecraft:lime_concrete");
  public static final BlockState LIME_CONCRETE_POWDER = get("minecraft:lime_concrete_powder");
  public static final BlockState LIME_GLAZED_TERRACOTTA = get("minecraft:lime_glazed_terracotta");
  public static final BlockState LIME_SHULKER_BOX = get("minecraft:lime_shulker_box");
  public static final BlockState LIME_STAINED_GLASS = get("minecraft:lime_stained_glass");
  public static final BlockState LIME_STAINED_GLASS_PANE = get("minecraft:lime_stained_glass_pane");
  public static final BlockState LIME_TERRACOTTA = get("minecraft:lime_terracotta");
  public static final BlockState LIME_WALL_BANNER = get("minecraft:lime_wall_banner");
  public static final BlockState LIME_WOOL = get("minecraft:lime_wool");
  public static final BlockState LODESTONE = get("minecraft:lodestone");
  public static final BlockState LOOM = get("minecraft:loom");
  public static final BlockState MAGENTA_BANNER = get("minecraft:magenta_banner");
  public static final BlockState MAGENTA_BED = get("minecraft:magenta_bed");
  public static final BlockState MAGENTA_CANDLE = get("minecraft:magenta_candle");
  public static final BlockState MAGENTA_CANDLE_CAKE = get("minecraft:magenta_candle_cake");
  public static final BlockState MAGENTA_CARPET = get("minecraft:magenta_carpet");
  public static final BlockState MAGENTA_CONCRETE = get("minecraft:magenta_concrete");
  public static final BlockState MAGENTA_CONCRETE_POWDER = get("minecraft:magenta_concrete_powder");
  public static final BlockState MAGENTA_GLAZED_TERRACOTTA = get("minecraft:magenta_glazed_terracotta");
  public static final BlockState MAGENTA_SHULKER_BOX = get("minecraft:magenta_shulker_box");
  public static final BlockState MAGENTA_STAINED_GLASS = get("minecraft:magenta_stained_glass");
  public static final BlockState MAGENTA_STAINED_GLASS_PANE = get("minecraft:magenta_stained_glass_pane");
  public static final BlockState MAGENTA_TERRACOTTA = get("minecraft:magenta_terracotta");
  public static final BlockState MAGENTA_WALL_BANNER = get("minecraft:magenta_wall_banner");
  public static final BlockState MAGENTA_WOOL = get("minecraft:magenta_wool");
  public static final BlockState MAGMA_BLOCK = get("minecraft:magma_block");
  public static final BlockState MANGROVE_BUTTON = get("minecraft:mangrove_button");
  public static final BlockState MANGROVE_DOOR = get("minecraft:mangrove_door");
  public static final BlockState MANGROVE_FENCE = get("minecraft:mangrove_fence");
  public static final BlockState MANGROVE_FENCE_GATE = get("minecraft:mangrove_fence_gate");
  public static final BlockState MANGROVE_HANGING_SIGN = get("minecraft:mangrove_hanging_sign");
  public static final BlockState MANGROVE_LEAVES = get("minecraft:mangrove_leaves");
  public static final BlockState MANGROVE_LOG = get("minecraft:mangrove_log");
  public static final BlockState MANGROVE_PLANKS = get("minecraft:mangrove_planks");
  public static final BlockState MANGROVE_PRESSURE_PLATE = get("minecraft:mangrove_pressure_plate");
  public static final BlockState MANGROVE_PROPAGULE = get("minecraft:mangrove_propagule");
  public static final BlockState MANGROVE_ROOTS = get("minecraft:mangrove_roots");
  public static final BlockState MANGROVE_SIGN = get("minecraft:mangrove_sign");
  public static final BlockState MANGROVE_SLAB = get("minecraft:mangrove_slab");
  public static final BlockState MANGROVE_STAIRS = get("minecraft:mangrove_stairs");
  public static final BlockState MANGROVE_TRAPDOOR = get("minecraft:mangrove_trapdoor");
  public static final BlockState MANGROVE_WALL_HANGING_SIGN = get("minecraft:mangrove_wall_hanging_sign");
  public static final BlockState MANGROVE_WALL_SIGN = get("minecraft:mangrove_wall_sign");
  public static final BlockState MANGROVE_WOOD = get("minecraft:mangrove_wood");
  public static final BlockState MEDIUM_AMETHYST_BUD = get("minecraft:medium_amethyst_bud");
  public static final BlockState MELON = get("minecraft:melon");
  public static final BlockState MELON_STEM = get("minecraft:melon_stem");
  public static final BlockState MOSS_BLOCK = get("minecraft:moss_block");
  public static final BlockState MOSS_CARPET = get("minecraft:moss_carpet");
  public static final BlockState MOSSY_COBBLESTONE = get("minecraft:mossy_cobblestone");
  public static final BlockState MOSSY_COBBLESTONE_SLAB = get("minecraft:mossy_cobblestone_slab");
  public static final BlockState MOSSY_COBBLESTONE_STAIRS = get("minecraft:mossy_cobblestone_stairs");
  public static final BlockState MOSSY_COBBLESTONE_WALL = get("minecraft:mossy_cobblestone_wall");
  public static final BlockState MOSSY_STONE_BRICK_SLAB = get("minecraft:mossy_stone_brick_slab");
  public static final BlockState MOSSY_STONE_BRICK_STAIRS = get("minecraft:mossy_stone_brick_stairs");
  public static final BlockState MOSSY_STONE_BRICK_WALL = get("minecraft:mossy_stone_brick_wall");
  public static final BlockState MOSSY_STONE_BRICKS = get("minecraft:mossy_stone_bricks");
  public static final BlockState MOVING_PISTON = get("minecraft:moving_piston");
  public static final BlockState MUD = get("minecraft:mud");
  public static final BlockState MUD_BRICK_SLAB = get("minecraft:mud_brick_slab");
  public static final BlockState MUD_BRICK_STAIRS = get("minecraft:mud_brick_stairs");
  public static final BlockState MUD_BRICK_WALL = get("minecraft:mud_brick_wall");
  public static final BlockState MUD_BRICKS = get("minecraft:mud_bricks");
  public static final BlockState MUDDY_MANGROVE_ROOTS = get("minecraft:muddy_mangrove_roots");
  public static final BlockState MUSHROOM_STEM = get("minecraft:mushroom_stem");
  public static final BlockState MYCELIUM = get("minecraft:mycelium");
  public static final BlockState NETHER_BRICK_FENCE = get("minecraft:nether_brick_fence");
  public static final BlockState NETHER_BRICK_SLAB = get("minecraft:nether_brick_slab");
  public static final BlockState NETHER_BRICK_STAIRS = get("minecraft:nether_brick_stairs");
  public static final BlockState NETHER_BRICK_WALL = get("minecraft:nether_brick_wall");
  public static final BlockState NETHER_BRICKS = get("minecraft:nether_bricks");
  public static final BlockState NETHER_GOLD_ORE = get("minecraft:nether_gold_ore");
  public static final BlockState NETHER_PORTAL = get("minecraft:nether_portal");
  public static final BlockState NETHER_QUARTZ_ORE = get("minecraft:nether_quartz_ore");
  public static final BlockState NETHER_SPROUTS = get("minecraft:nether_sprouts");
  public static final BlockState NETHER_WART = get("minecraft:nether_wart");
  public static final BlockState NETHER_WART_BLOCK = get("minecraft:nether_wart_block");
  public static final BlockState NETHERITE_BLOCK = get("minecraft:netherite_block");
  public static final BlockState NETHERRACK = get("minecraft:netherrack");
  public static final BlockState NOTE_BLOCK = get("minecraft:note_block");
  public static final BlockState OAK_BUTTON = get("minecraft:oak_button");
  public static final BlockState OAK_DOOR = get("minecraft:oak_door");
  public static final BlockState OAK_FENCE = get("minecraft:oak_fence");
  public static final BlockState OAK_FENCE_GATE = get("minecraft:oak_fence_gate");
  public static final BlockState OAK_HANGING_SIGN = get("minecraft:oak_hanging_sign");
  public static final BlockState OAK_LEAVES = get("minecraft:oak_leaves");
  public static final BlockState OAK_LOG = get("minecraft:oak_log");
  public static final BlockState OAK_PLANKS = get("minecraft:oak_planks");
  public static final BlockState OAK_PRESSURE_PLATE = get("minecraft:oak_pressure_plate");
  public static final BlockState OAK_SAPLING = get("minecraft:oak_sapling");
  public static final BlockState OAK_SIGN = get("minecraft:oak_sign");
  public static final BlockState OAK_SLAB = get("minecraft:oak_slab");
  public static final BlockState OAK_STAIRS = get("minecraft:oak_stairs");
  public static final BlockState OAK_TRAPDOOR = get("minecraft:oak_trapdoor");
  public static final BlockState OAK_WALL_HANGING_SIGN = get("minecraft:oak_wall_hanging_sign");
  public static final BlockState OAK_WALL_SIGN = get("minecraft:oak_wall_sign");
  public static final BlockState OAK_WOOD = get("minecraft:oak_wood");
  public static final BlockState OBSERVER = get("minecraft:observer");
  public static final BlockState OBSIDIAN = get("minecraft:obsidian");
  public static final BlockState OCHRE_FROGLIGHT = get("minecraft:ochre_froglight");
  public static final BlockState ORANGE_BANNER = get("minecraft:orange_banner");
  public static final BlockState ORANGE_BED = get("minecraft:orange_bed");
  public static final BlockState ORANGE_CANDLE = get("minecraft:orange_candle");
  public static final BlockState ORANGE_CANDLE_CAKE = get("minecraft:orange_candle_cake");
  public static final BlockState ORANGE_CARPET = get("minecraft:orange_carpet");
  public static final BlockState ORANGE_CONCRETE = get("minecraft:orange_concrete");
  public static final BlockState ORANGE_CONCRETE_POWDER = get("minecraft:orange_concrete_powder");
  public static final BlockState ORANGE_GLAZED_TERRACOTTA = get("minecraft:orange_glazed_terracotta");
  public static final BlockState ORANGE_SHULKER_BOX = get("minecraft:orange_shulker_box");
  public static final BlockState ORANGE_STAINED_GLASS = get("minecraft:orange_stained_glass");
  public static final BlockState ORANGE_STAINED_GLASS_PANE = get("minecraft:orange_stained_glass_pane");
  public static final BlockState ORANGE_TERRACOTTA = get("minecraft:orange_terracotta");
  public static final BlockState ORANGE_TULIP = get("minecraft:orange_tulip");
  public static final BlockState ORANGE_WALL_BANNER = get("minecraft:orange_wall_banner");
  public static final BlockState ORANGE_WOOL = get("minecraft:orange_wool");
  public static final BlockState OXEYE_DAISY = get("minecraft:oxeye_daisy");
  public static final BlockState OXIDIZED_CHISELED_COPPER = get("minecraft:oxidized_chiseled_copper");
  public static final BlockState OXIDIZED_COPPER = get("minecraft:oxidized_copper");
  public static final BlockState OXIDIZED_COPPER_BULB = get("minecraft:oxidized_copper_bulb");
  public static final BlockState OXIDIZED_COPPER_DOOR = get("minecraft:oxidized_copper_door");
  public static final BlockState OXIDIZED_COPPER_GRATE = get("minecraft:oxidized_copper_grate");
  public static final BlockState OXIDIZED_COPPER_TRAPDOOR = get("minecraft:oxidized_copper_trapdoor");
  public static final BlockState OXIDIZED_CUT_COPPER = get("minecraft:oxidized_cut_copper");
  public static final BlockState OXIDIZED_CUT_COPPER_SLAB = get("minecraft:oxidized_cut_copper_slab");
  public static final BlockState OXIDIZED_CUT_COPPER_STAIRS = get("minecraft:oxidized_cut_copper_stairs");
  public static final BlockState PACKED_ICE = get("minecraft:packed_ice");
  public static final BlockState PACKED_MUD = get("minecraft:packed_mud");
  public static final BlockState PEARLESCENT_FROGLIGHT = get("minecraft:pearlescent_froglight");
  public static final BlockState PEONY = get("minecraft:peony");
  public static final BlockState PETRIFIED_OAK_SLAB = get("minecraft:petrified_oak_slab");
  public static final BlockState PIGLIN_HEAD = get("minecraft:piglin_head");
  public static final BlockState PIGLIN_WALL_HEAD = get("minecraft:piglin_wall_head");
  public static final BlockState PINK_BANNER = get("minecraft:pink_banner");
  public static final BlockState PINK_BED = get("minecraft:pink_bed");
  public static final BlockState PINK_CANDLE = get("minecraft:pink_candle");
  public static final BlockState PINK_CANDLE_CAKE = get("minecraft:pink_candle_cake");
  public static final BlockState PINK_CARPET = get("minecraft:pink_carpet");
  public static final BlockState PINK_CONCRETE = get("minecraft:pink_concrete");
  public static final BlockState PINK_CONCRETE_POWDER = get("minecraft:pink_concrete_powder");
  public static final BlockState PINK_GLAZED_TERRACOTTA = get("minecraft:pink_glazed_terracotta");
  public static final BlockState PINK_PETALS = get("minecraft:pink_petals");
  public static final BlockState PINK_SHULKER_BOX = get("minecraft:pink_shulker_box");
  public static final BlockState PINK_STAINED_GLASS = get("minecraft:pink_stained_glass");
  public static final BlockState PINK_STAINED_GLASS_PANE = get("minecraft:pink_stained_glass_pane");
  public static final BlockState PINK_TERRACOTTA = get("minecraft:pink_terracotta");
  public static final BlockState PINK_TULIP = get("minecraft:pink_tulip");
  public static final BlockState PINK_WALL_BANNER = get("minecraft:pink_wall_banner");
  public static final BlockState PINK_WOOL = get("minecraft:pink_wool");
  public static final BlockState PISTON = get("minecraft:piston");
  public static final BlockState PISTON_HEAD = get("minecraft:piston_head");
  public static final BlockState PITCHER_CROP = get("minecraft:pitcher_crop");
  public static final BlockState PITCHER_PLANT = get("minecraft:pitcher_plant");
  public static final BlockState PLAYER_HEAD = get("minecraft:player_head");
  public static final BlockState PLAYER_WALL_HEAD = get("minecraft:player_wall_head");
  public static final BlockState PODZOL = get("minecraft:podzol");
  public static final BlockState POINTED_DRIPSTONE = get("minecraft:pointed_dripstone");
  public static final BlockState POLISHED_ANDESITE = get("minecraft:polished_andesite");
  public static final BlockState POLISHED_ANDESITE_SLAB = get("minecraft:polished_andesite_slab");
  public static final BlockState POLISHED_ANDESITE_STAIRS = get("minecraft:polished_andesite_stairs");
  public static final BlockState POLISHED_BASALT = get("minecraft:polished_basalt");
  public static final BlockState POLISHED_BLACKSTONE = get("minecraft:polished_blackstone");
  public static final BlockState POLISHED_BLACKSTONE_BRICK_SLAB = get("minecraft:polished_blackstone_brick_slab");
  public static final BlockState POLISHED_BLACKSTONE_BRICK_STAIRS = get("minecraft:polished_blackstone_brick_stairs");
  public static final BlockState POLISHED_BLACKSTONE_BRICK_WALL = get("minecraft:polished_blackstone_brick_wall");
  public static final BlockState POLISHED_BLACKSTONE_BRICKS = get("minecraft:polished_blackstone_bricks");
  public static final BlockState POLISHED_BLACKSTONE_BUTTON = get("minecraft:polished_blackstone_button");
  public static final BlockState POLISHED_BLACKSTONE_PRESSURE_PLATE = get("minecraft:polished_blackstone_pressure_plate");
  public static final BlockState POLISHED_BLACKSTONE_SLAB = get("minecraft:polished_blackstone_slab");
  public static final BlockState POLISHED_BLACKSTONE_STAIRS = get("minecraft:polished_blackstone_stairs");
  public static final BlockState POLISHED_BLACKSTONE_WALL = get("minecraft:polished_blackstone_wall");
  public static final BlockState POLISHED_DEEPSLATE = get("minecraft:polished_deepslate");
  public static final BlockState POLISHED_DEEPSLATE_SLAB = get("minecraft:polished_deepslate_slab");
  public static final BlockState POLISHED_DEEPSLATE_STAIRS = get("minecraft:polished_deepslate_stairs");
  public static final BlockState POLISHED_DEEPSLATE_WALL = get("minecraft:polished_deepslate_wall");
  public static final BlockState POLISHED_DIORITE = get("minecraft:polished_diorite");
  public static final BlockState POLISHED_DIORITE_SLAB = get("minecraft:polished_diorite_slab");
  public static final BlockState POLISHED_DIORITE_STAIRS = get("minecraft:polished_diorite_stairs");
  public static final BlockState POLISHED_GRANITE = get("minecraft:polished_granite");
  public static final BlockState POLISHED_GRANITE_SLAB = get("minecraft:polished_granite_slab");
  public static final BlockState POLISHED_GRANITE_STAIRS = get("minecraft:polished_granite_stairs");
  public static final BlockState POLISHED_TUFF = get("minecraft:polished_tuff");
  public static final BlockState POLISHED_TUFF_SLAB = get("minecraft:polished_tuff_slab");
  public static final BlockState POLISHED_TUFF_STAIRS = get("minecraft:polished_tuff_stairs");
  public static final BlockState POLISHED_TUFF_WALL = get("minecraft:polished_tuff_wall");
  public static final BlockState POPPY = get("minecraft:poppy");
  public static final BlockState POTATOES = get("minecraft:potatoes");
  public static final BlockState POTTED_ACACIA_SAPLING = get("minecraft:potted_acacia_sapling");
  public static final BlockState POTTED_ALLIUM = get("minecraft:potted_allium");
  public static final BlockState POTTED_AZALEA_BUSH = get("minecraft:potted_azalea_bush");
  public static final BlockState POTTED_AZURE_BLUET = get("minecraft:potted_azure_bluet");
  public static final BlockState POTTED_BAMBOO = get("minecraft:potted_bamboo");
  public static final BlockState POTTED_BIRCH_SAPLING = get("minecraft:potted_birch_sapling");
  public static final BlockState POTTED_BLUE_ORCHID = get("minecraft:potted_blue_orchid");
  public static final BlockState POTTED_BROWN_MUSHROOM = get("minecraft:potted_brown_mushroom");
  public static final BlockState POTTED_CACTUS = get("minecraft:potted_cactus");
  public static final BlockState POTTED_CHERRY_SAPLING = get("minecraft:potted_cherry_sapling");
  public static final BlockState POTTED_CORNFLOWER = get("minecraft:potted_cornflower");
  public static final BlockState POTTED_CRIMSON_FUNGUS = get("minecraft:potted_crimson_fungus");
  public static final BlockState POTTED_CRIMSON_ROOTS = get("minecraft:potted_crimson_roots");
  public static final BlockState POTTED_DANDELION = get("minecraft:potted_dandelion");
  public static final BlockState POTTED_DARK_OAK_SAPLING = get("minecraft:potted_dark_oak_sapling");
  public static final BlockState POTTED_DEAD_BUSH = get("minecraft:potted_dead_bush");
  public static final BlockState POTTED_FERN = get("minecraft:potted_fern");
  public static final BlockState POTTED_FLOWERING_AZALEA_BUSH = get("minecraft:potted_flowering_azalea_bush");
  public static final BlockState POTTED_JUNGLE_SAPLING = get("minecraft:potted_jungle_sapling");
  public static final BlockState POTTED_LILY_OF_THE_VALLEY = get("minecraft:potted_lily_of_the_valley");
  public static final BlockState POTTED_MANGROVE_PROPAGULE = get("minecraft:potted_mangrove_propagule");
  public static final BlockState POTTED_OAK_SAPLING = get("minecraft:potted_oak_sapling");
  public static final BlockState POTTED_ORANGE_TULIP = get("minecraft:potted_orange_tulip");
  public static final BlockState POTTED_OXEYE_DAISY = get("minecraft:potted_oxeye_daisy");
  public static final BlockState POTTED_PINK_TULIP = get("minecraft:potted_pink_tulip");
  public static final BlockState POTTED_POPPY = get("minecraft:potted_poppy");
  public static final BlockState POTTED_RED_MUSHROOM = get("minecraft:potted_red_mushroom");
  public static final BlockState POTTED_RED_TULIP = get("minecraft:potted_red_tulip");
  public static final BlockState POTTED_SPRUCE_SAPLING = get("minecraft:potted_spruce_sapling");
  public static final BlockState POTTED_TORCHFLOWER = get("minecraft:potted_torchflower");
  public static final BlockState POTTED_WARPED_FUNGUS = get("minecraft:potted_warped_fungus");
  public static final BlockState POTTED_WARPED_ROOTS = get("minecraft:potted_warped_roots");
  public static final BlockState POTTED_WHITE_TULIP = get("minecraft:potted_white_tulip");
  public static final BlockState POTTED_WITHER_ROSE = get("minecraft:potted_wither_rose");
  public static final BlockState POWDER_SNOW = get("minecraft:powder_snow");
  public static final BlockState POWDER_SNOW_CAULDRON = get("minecraft:powder_snow_cauldron");
  public static final BlockState POWERED_RAIL = get("minecraft:powered_rail");
  public static final BlockState PRISMARINE = get("minecraft:prismarine");
  public static final BlockState PRISMARINE_BRICK_SLAB = get("minecraft:prismarine_brick_slab");
  public static final BlockState PRISMARINE_BRICK_STAIRS = get("minecraft:prismarine_brick_stairs");
  public static final BlockState PRISMARINE_BRICKS = get("minecraft:prismarine_bricks");
  public static final BlockState PRISMARINE_SLAB = get("minecraft:prismarine_slab");
  public static final BlockState PRISMARINE_STAIRS = get("minecraft:prismarine_stairs");
  public static final BlockState PRISMARINE_WALL = get("minecraft:prismarine_wall");
  public static final BlockState PUMPKIN = get("minecraft:pumpkin");
  public static final BlockState PUMPKIN_STEM = get("minecraft:pumpkin_stem");
  public static final BlockState PURPLE_BANNER = get("minecraft:purple_banner");
  public static final BlockState PURPLE_BED = get("minecraft:purple_bed");
  public static final BlockState PURPLE_CANDLE = get("minecraft:purple_candle");
  public static final BlockState PURPLE_CANDLE_CAKE = get("minecraft:purple_candle_cake");
  public static final BlockState PURPLE_CARPET = get("minecraft:purple_carpet");
  public static final BlockState PURPLE_CONCRETE = get("minecraft:purple_concrete");
  public static final BlockState PURPLE_CONCRETE_POWDER = get("minecraft:purple_concrete_powder");
  public static final BlockState PURPLE_GLAZED_TERRACOTTA = get("minecraft:purple_glazed_terracotta");
  public static final BlockState PURPLE_SHULKER_BOX = get("minecraft:purple_shulker_box");
  public static final BlockState PURPLE_STAINED_GLASS = get("minecraft:purple_stained_glass");
  public static final BlockState PURPLE_STAINED_GLASS_PANE = get("minecraft:purple_stained_glass_pane");
  public static final BlockState PURPLE_TERRACOTTA = get("minecraft:purple_terracotta");
  public static final BlockState PURPLE_WALL_BANNER = get("minecraft:purple_wall_banner");
  public static final BlockState PURPLE_WOOL = get("minecraft:purple_wool");
  public static final BlockState PURPUR_BLOCK = get("minecraft:purpur_block");
  public static final BlockState PURPUR_PILLAR = get("minecraft:purpur_pillar");
  public static final BlockState PURPUR_SLAB = get("minecraft:purpur_slab");
  public static final BlockState PURPUR_STAIRS = get("minecraft:purpur_stairs");
  public static final BlockState QUARTZ_BLOCK = get("minecraft:quartz_block");
  public static final BlockState QUARTZ_BRICKS = get("minecraft:quartz_bricks");
  public static final BlockState QUARTZ_PILLAR = get("minecraft:quartz_pillar");
  public static final BlockState QUARTZ_SLAB = get("minecraft:quartz_slab");
  public static final BlockState QUARTZ_STAIRS = get("minecraft:quartz_stairs");
  public static final BlockState RAIL = get("minecraft:rail");
  public static final BlockState RAW_COPPER_BLOCK = get("minecraft:raw_copper_block");
  public static final BlockState RAW_GOLD_BLOCK = get("minecraft:raw_gold_block");
  public static final BlockState RAW_IRON_BLOCK = get("minecraft:raw_iron_block");
  public static final BlockState RED_BANNER = get("minecraft:red_banner");
  public static final BlockState RED_BED = get("minecraft:red_bed");
  public static final BlockState RED_CANDLE = get("minecraft:red_candle");
  public static final BlockState RED_CANDLE_CAKE = get("minecraft:red_candle_cake");
  public static final BlockState RED_CARPET = get("minecraft:red_carpet");
  public static final BlockState RED_CONCRETE = get("minecraft:red_concrete");
  public static final BlockState RED_CONCRETE_POWDER = get("minecraft:red_concrete_powder");
  public static final BlockState RED_GLAZED_TERRACOTTA = get("minecraft:red_glazed_terracotta");
  public static final BlockState RED_MUSHROOM = get("minecraft:red_mushroom");
  public static final BlockState RED_MUSHROOM_BLOCK = get("minecraft:red_mushroom_block");
  public static final BlockState RED_NETHER_BRICK_SLAB = get("minecraft:red_nether_brick_slab");
  public static final BlockState RED_NETHER_BRICK_STAIRS = get("minecraft:red_nether_brick_stairs");
  public static final BlockState RED_NETHER_BRICK_WALL = get("minecraft:red_nether_brick_wall");
  public static final BlockState RED_NETHER_BRICKS = get("minecraft:red_nether_bricks");
  public static final BlockState RED_SAND = get("minecraft:red_sand");
  public static final BlockState RED_SANDSTONE = get("minecraft:red_sandstone");
  public static final BlockState RED_SANDSTONE_SLAB = get("minecraft:red_sandstone_slab");
  public static final BlockState RED_SANDSTONE_STAIRS = get("minecraft:red_sandstone_stairs");
  public static final BlockState RED_SANDSTONE_WALL = get("minecraft:red_sandstone_wall");
  public static final BlockState RED_SHULKER_BOX = get("minecraft:red_shulker_box");
  public static final BlockState RED_STAINED_GLASS = get("minecraft:red_stained_glass");
  public static final BlockState RED_STAINED_GLASS_PANE = get("minecraft:red_stained_glass_pane");
  public static final BlockState RED_TERRACOTTA = get("minecraft:red_terracotta");
  public static final BlockState RED_TULIP = get("minecraft:red_tulip");
  public static final BlockState RED_WALL_BANNER = get("minecraft:red_wall_banner");
  public static final BlockState RED_WOOL = get("minecraft:red_wool");
  public static final BlockState REDSTONE_BLOCK = get("minecraft:redstone_block");
  public static final BlockState REDSTONE_LAMP = get("minecraft:redstone_lamp");
  public static final BlockState REDSTONE_ORE = get("minecraft:redstone_ore");
  public static final BlockState REDSTONE_TORCH = get("minecraft:redstone_torch");
  public static final BlockState REDSTONE_WALL_TORCH = get("minecraft:redstone_wall_torch");
  public static final BlockState REDSTONE_WIRE = get("minecraft:redstone_wire");
  public static final BlockState REINFORCED_DEEPSLATE = get("minecraft:reinforced_deepslate");
  public static final BlockState REPEATER = get("minecraft:repeater");
  public static final BlockState REPEATING_COMMAND_BLOCK = get("minecraft:repeating_command_block");
  public static final BlockState RESPAWN_ANCHOR = get("minecraft:respawn_anchor");
  public static final BlockState ROOTED_DIRT = get("minecraft:rooted_dirt");
  public static final BlockState ROSE_BUSH = get("minecraft:rose_bush");
  public static final BlockState SAND = get("minecraft:sand");
  public static final BlockState SANDSTONE = get("minecraft:sandstone");
  public static final BlockState SANDSTONE_SLAB = get("minecraft:sandstone_slab");
  public static final BlockState SANDSTONE_STAIRS = get("minecraft:sandstone_stairs");
  public static final BlockState SANDSTONE_WALL = get("minecraft:sandstone_wall");
  public static final BlockState SCAFFOLDING = get("minecraft:scaffolding");
  public static final BlockState SCULK = get("minecraft:sculk");
  public static final BlockState SCULK_CATALYST = get("minecraft:sculk_catalyst");
  public static final BlockState SCULK_SENSOR = get("minecraft:sculk_sensor");
  public static final BlockState SCULK_SHRIEKER = get("minecraft:sculk_shrieker");
  public static final BlockState SCULK_VEIN = get("minecraft:sculk_vein");
  public static final BlockState SEA_LANTERN = get("minecraft:sea_lantern");
  public static final BlockState SEA_PICKLE = get("minecraft:sea_pickle");
  public static final BlockState SEAGRASS = get("minecraft:seagrass");
  public static final BlockState SHORT_GRASS = get("minecraft:short_grass");
  public static final BlockState SHROOMLIGHT = get("minecraft:shroomlight");
  public static final BlockState SHULKER_BOX = get("minecraft:shulker_box");
  public static final BlockState SKELETON_SKULL = get("minecraft:skeleton_skull");
  public static final BlockState SKELETON_WALL_SKULL = get("minecraft:skeleton_wall_skull");
  public static final BlockState SLIME_BLOCK = get("minecraft:slime_block");
  public static final BlockState SMALL_AMETHYST_BUD = get("minecraft:small_amethyst_bud");
  public static final BlockState SMALL_DRIPLEAF = get("minecraft:small_dripleaf");
  public static final BlockState SMITHING_TABLE = get("minecraft:smithing_table");
  public static final BlockState SMOKER = get("minecraft:smoker");
  public static final BlockState SMOOTH_BASALT = get("minecraft:smooth_basalt");
  public static final BlockState SMOOTH_QUARTZ = get("minecraft:smooth_quartz");
  public static final BlockState SMOOTH_QUARTZ_SLAB = get("minecraft:smooth_quartz_slab");
  public static final BlockState SMOOTH_QUARTZ_STAIRS = get("minecraft:smooth_quartz_stairs");
  public static final BlockState SMOOTH_RED_SANDSTONE = get("minecraft:smooth_red_sandstone");
  public static final BlockState SMOOTH_RED_SANDSTONE_SLAB = get("minecraft:smooth_red_sandstone_slab");
  public static final BlockState SMOOTH_RED_SANDSTONE_STAIRS = get("minecraft:smooth_red_sandstone_stairs");
  public static final BlockState SMOOTH_SANDSTONE = get("minecraft:smooth_sandstone");
  public static final BlockState SMOOTH_SANDSTONE_SLAB = get("minecraft:smooth_sandstone_slab");
  public static final BlockState SMOOTH_SANDSTONE_STAIRS = get("minecraft:smooth_sandstone_stairs");
  public static final BlockState SMOOTH_STONE = get("minecraft:smooth_stone");
  public static final BlockState SMOOTH_STONE_SLAB = get("minecraft:smooth_stone_slab");
  public static final BlockState SNIFFER_EGG = get("minecraft:sniffer_egg");
  public static final BlockState SNOW = get("minecraft:snow");
  public static final BlockState SNOW_BLOCK = get("minecraft:snow_block");
  public static final BlockState SOUL_CAMPFIRE = get("minecraft:soul_campfire");
  public static final BlockState SOUL_FIRE = get("minecraft:soul_fire");
  public static final BlockState SOUL_LANTERN = get("minecraft:soul_lantern");
  public static final BlockState SOUL_SAND = get("minecraft:soul_sand");
  public static final BlockState SOUL_SOIL = get("minecraft:soul_soil");
  public static final BlockState SOUL_TORCH = get("minecraft:soul_torch");
  public static final BlockState SOUL_WALL_TORCH = get("minecraft:soul_wall_torch");
  public static final BlockState SPAWNER = get("minecraft:spawner");
  public static final BlockState SPONGE = get("minecraft:sponge");
  public static final BlockState SPORE_BLOSSOM = get("minecraft:spore_blossom");
  public static final BlockState SPRUCE_BUTTON = get("minecraft:spruce_button");
  public static final BlockState SPRUCE_DOOR = get("minecraft:spruce_door");
  public static final BlockState SPRUCE_FENCE = get("minecraft:spruce_fence");
  public static final BlockState SPRUCE_FENCE_GATE = get("minecraft:spruce_fence_gate");
  public static final BlockState SPRUCE_HANGING_SIGN = get("minecraft:spruce_hanging_sign");
  public static final BlockState SPRUCE_LEAVES = get("minecraft:spruce_leaves");
  public static final BlockState SPRUCE_LOG = get("minecraft:spruce_log");
  public static final BlockState SPRUCE_PLANKS = get("minecraft:spruce_planks");
  public static final BlockState SPRUCE_PRESSURE_PLATE = get("minecraft:spruce_pressure_plate");
  public static final BlockState SPRUCE_SAPLING = get("minecraft:spruce_sapling");
  public static final BlockState SPRUCE_SIGN = get("minecraft:spruce_sign");
  public static final BlockState SPRUCE_SLAB = get("minecraft:spruce_slab");
  public static final BlockState SPRUCE_STAIRS = get("minecraft:spruce_stairs");
  public static final BlockState SPRUCE_TRAPDOOR = get("minecraft:spruce_trapdoor");
  public static final BlockState SPRUCE_WALL_HANGING_SIGN = get("minecraft:spruce_wall_hanging_sign");
  public static final BlockState SPRUCE_WALL_SIGN = get("minecraft:spruce_wall_sign");
  public static final BlockState SPRUCE_WOOD = get("minecraft:spruce_wood");
  public static final BlockState STICKY_PISTON = get("minecraft:sticky_piston");
  public static final BlockState STONE = get("minecraft:stone");
  public static final BlockState STONE_BRICK_SLAB = get("minecraft:stone_brick_slab");
  public static final BlockState STONE_BRICK_STAIRS = get("minecraft:stone_brick_stairs");
  public static final BlockState STONE_BRICK_WALL = get("minecraft:stone_brick_wall");
  public static final BlockState STONE_BRICKS = get("minecraft:stone_bricks");
  public static final BlockState STONE_BUTTON = get("minecraft:stone_button");
  public static final BlockState STONE_PRESSURE_PLATE = get("minecraft:stone_pressure_plate");
  public static final BlockState STONE_SLAB = get("minecraft:stone_slab");
  public static final BlockState STONE_STAIRS = get("minecraft:stone_stairs");
  public static final BlockState STONECUTTER = get("minecraft:stonecutter");
  public static final BlockState STRIPPED_ACACIA_LOG = get("minecraft:stripped_acacia_log");
  public static final BlockState STRIPPED_ACACIA_WOOD = get("minecraft:stripped_acacia_wood");
  public static final BlockState STRIPPED_BAMBOO_BLOCK = get("minecraft:stripped_bamboo_block");
  public static final BlockState STRIPPED_BIRCH_LOG = get("minecraft:stripped_birch_log");
  public static final BlockState STRIPPED_BIRCH_WOOD = get("minecraft:stripped_birch_wood");
  public static final BlockState STRIPPED_CHERRY_LOG = get("minecraft:stripped_cherry_log");
  public static final BlockState STRIPPED_CHERRY_WOOD = get("minecraft:stripped_cherry_wood");
  public static final BlockState STRIPPED_CRIMSON_HYPHAE = get("minecraft:stripped_crimson_hyphae");
  public static final BlockState STRIPPED_CRIMSON_STEM = get("minecraft:stripped_crimson_stem");
  public static final BlockState STRIPPED_DARK_OAK_LOG = get("minecraft:stripped_dark_oak_log");
  public static final BlockState STRIPPED_DARK_OAK_WOOD = get("minecraft:stripped_dark_oak_wood");
  public static final BlockState STRIPPED_JUNGLE_LOG = get("minecraft:stripped_jungle_log");
  public static final BlockState STRIPPED_JUNGLE_WOOD = get("minecraft:stripped_jungle_wood");
  public static final BlockState STRIPPED_MANGROVE_LOG = get("minecraft:stripped_mangrove_log");
  public static final BlockState STRIPPED_MANGROVE_WOOD = get("minecraft:stripped_mangrove_wood");
  public static final BlockState STRIPPED_OAK_LOG = get("minecraft:stripped_oak_log");
  public static final BlockState STRIPPED_OAK_WOOD = get("minecraft:stripped_oak_wood");
  public static final BlockState STRIPPED_SPRUCE_LOG = get("minecraft:stripped_spruce_log");
  public static final BlockState STRIPPED_SPRUCE_WOOD = get("minecraft:stripped_spruce_wood");
  public static final BlockState STRIPPED_WARPED_HYPHAE = get("minecraft:stripped_warped_hyphae");
  public static final BlockState STRIPPED_WARPED_STEM = get("minecraft:stripped_warped_stem");
  public static final BlockState STRUCTURE_BLOCK = get("minecraft:structure_block");
  public static final BlockState STRUCTURE_VOID = get("minecraft:structure_void");
  public static final BlockState SUGAR_CANE = get("minecraft:sugar_cane");
  public static final BlockState SUNFLOWER = get("minecraft:sunflower");
  public static final BlockState SUSPICIOUS_GRAVEL = get("minecraft:suspicious_gravel");
  public static final BlockState SUSPICIOUS_SAND = get("minecraft:suspicious_sand");
  public static final BlockState SWEET_BERRY_BUSH = get("minecraft:sweet_berry_bush");
  public static final BlockState TALL_GRASS = get("minecraft:tall_grass");
  public static final BlockState TALL_SEAGRASS = get("minecraft:tall_seagrass");
  public static final BlockState TARGET = get("minecraft:target");
  public static final BlockState TERRACOTTA = get("minecraft:terracotta");
  public static final BlockState TINTED_GLASS = get("minecraft:tinted_glass");
  public static final BlockState TNT = get("minecraft:tnt");
  public static final BlockState TORCH = get("minecraft:torch");
  public static final BlockState TORCHFLOWER = get("minecraft:torchflower");
  public static final BlockState TORCHFLOWER_CROP = get("minecraft:torchflower_crop");
  public static final BlockState TRAPPED_CHEST = get("minecraft:trapped_chest");
  public static final BlockState TRIAL_SPAWNER = get("minecraft:trial_spawner");
  public static final BlockState TRIPWIRE = get("minecraft:tripwire");
  public static final BlockState TRIPWIRE_HOOK = get("minecraft:tripwire_hook");
  public static final BlockState TUBE_CORAL = get("minecraft:tube_coral");
  public static final BlockState TUBE_CORAL_BLOCK = get("minecraft:tube_coral_block");
  public static final BlockState TUBE_CORAL_FAN = get("minecraft:tube_coral_fan");
  public static final BlockState TUBE_CORAL_WALL_FAN = get("minecraft:tube_coral_wall_fan");
  public static final BlockState TUFF = get("minecraft:tuff");
  public static final BlockState TUFF_BRICK_SLAB = get("minecraft:tuff_brick_slab");
  public static final BlockState TUFF_BRICK_STAIRS = get("minecraft:tuff_brick_stairs");
  public static final BlockState TUFF_BRICK_WALL = get("minecraft:tuff_brick_wall");
  public static final BlockState TUFF_BRICKS = get("minecraft:tuff_bricks");
  public static final BlockState TUFF_SLAB = get("minecraft:tuff_slab");
  public static final BlockState TUFF_STAIRS = get("minecraft:tuff_stairs");
  public static final BlockState TUFF_WALL = get("minecraft:tuff_wall");
  public static final BlockState TURTLE_EGG = get("minecraft:turtle_egg");
  public static final BlockState TWISTING_VINES = get("minecraft:twisting_vines");
  public static final BlockState TWISTING_VINES_PLANT = get("minecraft:twisting_vines_plant");
  public static final BlockState VAULT = get("minecraft:vault");
  public static final BlockState VERDANT_FROGLIGHT = get("minecraft:verdant_froglight");
  public static final BlockState VINE = get("minecraft:vine");
  public static final BlockState VOID_AIR = get("minecraft:void_air");
  public static final BlockState WALL_TORCH = get("minecraft:wall_torch");
  public static final BlockState WARPED_BUTTON = get("minecraft:warped_button");
  public static final BlockState WARPED_DOOR = get("minecraft:warped_door");
  public static final BlockState WARPED_FENCE = get("minecraft:warped_fence");
  public static final BlockState WARPED_FENCE_GATE = get("minecraft:warped_fence_gate");
  public static final BlockState WARPED_FUNGUS = get("minecraft:warped_fungus");
  public static final BlockState WARPED_HANGING_SIGN = get("minecraft:warped_hanging_sign");
  public static final BlockState WARPED_HYPHAE = get("minecraft:warped_hyphae");
  public static final BlockState WARPED_NYLIUM = get("minecraft:warped_nylium");
  public static final BlockState WARPED_PLANKS = get("minecraft:warped_planks");
  public static final BlockState WARPED_PRESSURE_PLATE = get("minecraft:warped_pressure_plate");
  public static final BlockState WARPED_ROOTS = get("minecraft:warped_roots");
  public static final BlockState WARPED_SIGN = get("minecraft:warped_sign");
  public static final BlockState WARPED_SLAB = get("minecraft:warped_slab");
  public static final BlockState WARPED_STAIRS = get("minecraft:warped_stairs");
  public static final BlockState WARPED_STEM = get("minecraft:warped_stem");
  public static final BlockState WARPED_TRAPDOOR = get("minecraft:warped_trapdoor");
  public static final BlockState WARPED_WALL_HANGING_SIGN = get("minecraft:warped_wall_hanging_sign");
  public static final BlockState WARPED_WALL_SIGN = get("minecraft:warped_wall_sign");
  public static final BlockState WARPED_WART_BLOCK = get("minecraft:warped_wart_block");
  public static final BlockState WATER = get("minecraft:water");
  public static final BlockState WATER_CAULDRON = get("minecraft:water_cauldron");
  public static final BlockState WAXED_CHISELED_COPPER = get("minecraft:waxed_chiseled_copper");
  public static final BlockState WAXED_COPPER_BLOCK = get("minecraft:waxed_copper_block");
  public static final BlockState WAXED_COPPER_BULB = get("minecraft:waxed_copper_bulb");
  public static final BlockState WAXED_COPPER_DOOR = get("minecraft:waxed_copper_door");
  public static final BlockState WAXED_COPPER_GRATE = get("minecraft:waxed_copper_grate");
  public static final BlockState WAXED_COPPER_TRAPDOOR = get("minecraft:waxed_copper_trapdoor");
  public static final BlockState WAXED_CUT_COPPER = get("minecraft:waxed_cut_copper");
  public static final BlockState WAXED_CUT_COPPER_SLAB = get("minecraft:waxed_cut_copper_slab");
  public static final BlockState WAXED_CUT_COPPER_STAIRS = get("minecraft:waxed_cut_copper_stairs");
  public static final BlockState WAXED_EXPOSED_CHISELED_COPPER = get("minecraft:waxed_exposed_chiseled_copper");
  public static final BlockState WAXED_EXPOSED_COPPER = get("minecraft:waxed_exposed_copper");
  public static final BlockState WAXED_EXPOSED_COPPER_BULB = get("minecraft:waxed_exposed_copper_bulb");
  public static final BlockState WAXED_EXPOSED_COPPER_DOOR = get("minecraft:waxed_exposed_copper_door");
  public static final BlockState WAXED_EXPOSED_COPPER_GRATE = get("minecraft:waxed_exposed_copper_grate");
  public static final BlockState WAXED_EXPOSED_COPPER_TRAPDOOR = get("minecraft:waxed_exposed_copper_trapdoor");
  public static final BlockState WAXED_EXPOSED_CUT_COPPER = get("minecraft:waxed_exposed_cut_copper");
  public static final BlockState WAXED_EXPOSED_CUT_COPPER_SLAB = get("minecraft:waxed_exposed_cut_copper_slab");
  public static final BlockState WAXED_EXPOSED_CUT_COPPER_STAIRS = get("minecraft:waxed_exposed_cut_copper_stairs");
  public static final BlockState WAXED_OXIDIZED_CHISELED_COPPER = get("minecraft:waxed_oxidized_chiseled_copper");
  public static final BlockState WAXED_OXIDIZED_COPPER = get("minecraft:waxed_oxidized_copper");
  public static final BlockState WAXED_OXIDIZED_COPPER_BULB = get("minecraft:waxed_oxidized_copper_bulb");
  public static final BlockState WAXED_OXIDIZED_COPPER_DOOR = get("minecraft:waxed_oxidized_copper_door");
  public static final BlockState WAXED_OXIDIZED_COPPER_GRATE = get("minecraft:waxed_oxidized_copper_grate");
  public static final BlockState WAXED_OXIDIZED_COPPER_TRAPDOOR = get("minecraft:waxed_oxidized_copper_trapdoor");
  public static final BlockState WAXED_OXIDIZED_CUT_COPPER = get("minecraft:waxed_oxidized_cut_copper");
  public static final BlockState WAXED_OXIDIZED_CUT_COPPER_SLAB = get("minecraft:waxed_oxidized_cut_copper_slab");
  public static final BlockState WAXED_OXIDIZED_CUT_COPPER_STAIRS = get("minecraft:waxed_oxidized_cut_copper_stairs");
  public static final BlockState WAXED_WEATHERED_CHISELED_COPPER = get("minecraft:waxed_weathered_chiseled_copper");
  public static final BlockState WAXED_WEATHERED_COPPER = get("minecraft:waxed_weathered_copper");
  public static final BlockState WAXED_WEATHERED_COPPER_BULB = get("minecraft:waxed_weathered_copper_bulb");
  public static final BlockState WAXED_WEATHERED_COPPER_DOOR = get("minecraft:waxed_weathered_copper_door");
  public static final BlockState WAXED_WEATHERED_COPPER_GRATE = get("minecraft:waxed_weathered_copper_grate");
  public static final BlockState WAXED_WEATHERED_COPPER_TRAPDOOR = get("minecraft:waxed_weathered_copper_trapdoor");
  public static final BlockState WAXED_WEATHERED_CUT_COPPER = get("minecraft:waxed_weathered_cut_copper");
  public static final BlockState WAXED_WEATHERED_CUT_COPPER_SLAB = get("minecraft:waxed_weathered_cut_copper_slab");
  public static final BlockState WAXED_WEATHERED_CUT_COPPER_STAIRS = get("minecraft:waxed_weathered_cut_copper_stairs");
  public static final BlockState WEATHERED_CHISELED_COPPER = get("minecraft:weathered_chiseled_copper");
  public static final BlockState WEATHERED_COPPER = get("minecraft:weathered_copper");
  public static final BlockState WEATHERED_COPPER_BULB = get("minecraft:weathered_copper_bulb");
  public static final BlockState WEATHERED_COPPER_DOOR = get("minecraft:weathered_copper_door");
  public static final BlockState WEATHERED_COPPER_GRATE = get("minecraft:weathered_copper_grate");
  public static final BlockState WEATHERED_COPPER_TRAPDOOR = get("minecraft:weathered_copper_trapdoor");
  public static final BlockState WEATHERED_CUT_COPPER = get("minecraft:weathered_cut_copper");
  public static final BlockState WEATHERED_CUT_COPPER_SLAB = get("minecraft:weathered_cut_copper_slab");
  public static final BlockState WEATHERED_CUT_COPPER_STAIRS = get("minecraft:weathered_cut_copper_stairs");
  public static final BlockState WEEPING_VINES = get("minecraft:weeping_vines");
  public static final BlockState WEEPING_VINES_PLANT = get("minecraft:weeping_vines_plant");
  public static final BlockState WET_SPONGE = get("minecraft:wet_sponge");
  public static final BlockState WHEAT = get("minecraft:wheat");
  public static final BlockState WHITE_BANNER = get("minecraft:white_banner");
  public static final BlockState WHITE_BED = get("minecraft:white_bed");
  public static final BlockState WHITE_CANDLE = get("minecraft:white_candle");
  public static final BlockState WHITE_CANDLE_CAKE = get("minecraft:white_candle_cake");
  public static final BlockState WHITE_CARPET = get("minecraft:white_carpet");
  public static final BlockState WHITE_CONCRETE = get("minecraft:white_concrete");
  public static final BlockState WHITE_CONCRETE_POWDER = get("minecraft:white_concrete_powder");
  public static final BlockState WHITE_GLAZED_TERRACOTTA = get("minecraft:white_glazed_terracotta");
  public static final BlockState WHITE_SHULKER_BOX = get("minecraft:white_shulker_box");
  public static final BlockState WHITE_STAINED_GLASS = get("minecraft:white_stained_glass");
  public static final BlockState WHITE_STAINED_GLASS_PANE = get("minecraft:white_stained_glass_pane");
  public static final BlockState WHITE_TERRACOTTA = get("minecraft:white_terracotta");
  public static final BlockState WHITE_TULIP = get("minecraft:white_tulip");
  public static final BlockState WHITE_WALL_BANNER = get("minecraft:white_wall_banner");
  public static final BlockState WHITE_WOOL = get("minecraft:white_wool");
  public static final BlockState WITHER_ROSE = get("minecraft:wither_rose");
  public static final BlockState WITHER_SKELETON_SKULL = get("minecraft:wither_skeleton_skull");
  public static final BlockState WITHER_SKELETON_WALL_SKULL = get("minecraft:wither_skeleton_wall_skull");
  public static final BlockState YELLOW_BANNER = get("minecraft:yellow_banner");
  public static final BlockState YELLOW_BED = get("minecraft:yellow_bed");
  public static final BlockState YELLOW_CANDLE = get("minecraft:yellow_candle");
  public static final BlockState YELLOW_CANDLE_CAKE = get("minecraft:yellow_candle_cake");
  public static final BlockState YELLOW_CARPET = get("minecraft:yellow_carpet");
  public static final BlockState YELLOW_CONCRETE = get("minecraft:yellow_concrete");
  public static final BlockState YELLOW_CONCRETE_POWDER = get("minecraft:yellow_concrete_powder");
  public static final BlockState YELLOW_GLAZED_TERRACOTTA = get("minecraft:yellow_glazed_terracotta");
  public static final BlockState YELLOW_SHULKER_BOX = get("minecraft:yellow_shulker_box");
  public static final BlockState YELLOW_STAINED_GLASS = get("minecraft:yellow_stained_glass");
  public static final BlockState YELLOW_STAINED_GLASS_PANE = get("minecraft:yellow_stained_glass_pane");
  public static final BlockState YELLOW_TERRACOTTA = get("minecraft:yellow_terracotta");
  public static final BlockState YELLOW_WALL_BANNER = get("minecraft:yellow_wall_banner");
  public static final BlockState YELLOW_WOOL = get("minecraft:yellow_wool");
  public static final BlockState ZOMBIE_HEAD = get("minecraft:zombie_head");
  public static final BlockState ZOMBIE_WALL_HEAD = get("minecraft:zombie_wall_head");
  // END

  private Block() {
    throw new AssertionError();
  }

  /**
   * Gets a block by its name.
   *
   * @param name the name of the block
   * @return the block of the name
   */
  public static BlockState get(final @NotNull String name) {
    return Blocks.registry().get(name);
  }

  /**
   * Gets a block by its state id.
   *
   * @param id the id of the block
   * @return the block of the id
   */
  public static BlockState get(final int id) {
    return Blocks.registry().get(id);
  }

  public interface Entity<B extends BlockState> extends BlockState {

    int getEntityId();

    @NotNull CompoundBinaryTag nbt();

    @NotNull B nbt(@NotNull CompoundBinaryTag nbt);
  }

  /**
   * The face a block can have.
   */
  public static final class Face extends PropertyValue<Face> {

    private static final Map<String, Face> INDEX = new HashMap<>(3);

    public static final Face FLOOR = new Face("floor");
    public static final Face WALL = new Face("wall");
    public static final Face CEILING = new Face("ceiling");

    private Face(String value) {
      super(value, INDEX);
    }

    public static Face get(@NotNull String value) {
      return INDEX.get(value);
    }
  }

  /**
   * The facing a block can have.
   */
  public static final class Facing extends PropertyValue<Facing> {

    private static final Map<String, Facing> INDEX = new HashMap<>(6);

    public static final Facing DOWN = new Facing("down");
    public static final Facing UP = new Facing("up");
    public static final Facing NORTH = new Facing("north");
    public static final Facing SOUTH = new Facing("south");
    public static final Facing WEST = new Facing("west");
    public static final Facing EAST = new Facing("east");

    private static final Map<Integer, Facing> INDEX_BY_ID = Map.of(0, DOWN, 1, UP, 2, NORTH, 3,
        SOUTH, 4, WEST, 5, EAST);

    private Facing(String value) {
      super(value, INDEX);
    }

    public static Facing get(@NotNull String value) {
      return INDEX.get(value);
    }

    public static Facing get(int value) {
      return INDEX_BY_ID.get(value);
    }
  }

  /**
   * The half a block can be.
   */
  public static final class Half extends PropertyValue<Half> {

    private static final Map<String, Half> INDEX = new HashMap<>(4);

    public static final Facing BOTTOM = new Facing("bottom");
    public static final Facing TOP = new Facing("top");
    public static final Facing LEFT = new Facing("left");
    public static final Facing RIGHT = new Facing("right");

    private Half(String value) {
      super(value, INDEX);
    }

    public static Half get(@NotNull String value) {
      return INDEX.get(value);
    }
  }

  /**
   * The type of the slab.
   */
  public static final class SlabType extends PropertyValue<SlabType> {

    private static final Map<String, SlabType> INDEX = new HashMap<>(3);

    public static final Facing TOP = new Facing("top");
    public static final Facing BOTTOM = new Facing("bottom");
    public static final Facing DOUBLE = new Facing("double");

    private SlabType(String value) {
      super(value, INDEX);
    }

    public static SlabType get(@NotNull String value) {
      return INDEX.get(value);
    }
  }

  /**
   * The type of the chest.
   */
  public static final class ChestType extends PropertyValue<ChestType> {

    private static final Map<String, ChestType> INDEX = new HashMap<>(3);

    public static final Facing SINGLE = new Facing("single");
    public static final Facing LEFT = new Facing("left");
    public static final Facing RIGHT = new Facing("right");

    private ChestType(String value) {
      super(value, INDEX);
    }

    public static ChestType get(@NotNull String value) {
      return INDEX.get(value);
    }
  }

  public static class PropertyValue<E> {

    private final String value;

    protected PropertyValue(String value, Map<String, E> map) {
      this.value = value;
      map.put(value, (E) this);
    }

    public String value() {
      return this.value;
    }
  }
}
