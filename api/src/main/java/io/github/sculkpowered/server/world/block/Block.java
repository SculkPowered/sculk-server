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
  public static final Button ACACIA_BUTTON = (Button) get("minecraft:acacia_button");
  public static final Door ACACIA_DOOR = (Door) get("minecraft:acacia_door");
  public static final BlockState.Waterloggable<BlockState> ACACIA_FENCE = (BlockState.Waterloggable<BlockState>) get("minecraft:acacia_fence");
  public static final FenceGate ACACIA_FENCE_GATE = (FenceGate) get("minecraft:acacia_fence_gate");
  public static final HangingSign ACACIA_HANGING_SIGN = (HangingSign) get("minecraft:acacia_hanging_sign");
  public static final BlockState.Waterloggable<BlockState> ACACIA_LEAVES = (BlockState.Waterloggable<BlockState>) get("minecraft:acacia_leaves");
  public static final BlockState.Axis<BlockState> ACACIA_LOG = (BlockState.Axis<BlockState>) get("minecraft:acacia_log");
  public static final BlockState ACACIA_PLANKS = get("minecraft:acacia_planks");
  public static final BlockState.Powerable<BlockState> ACACIA_PRESSURE_PLATE = (BlockState.Powerable<BlockState>) get("minecraft:acacia_pressure_plate");
  public static final BlockState ACACIA_SAPLING = get("minecraft:acacia_sapling");
  public static final Sign ACACIA_SIGN = (Sign) get("minecraft:acacia_sign");
  public static final BlockState.Waterloggable<BlockState> ACACIA_SLAB = (BlockState.Waterloggable<BlockState>) get("minecraft:acacia_slab");
  public static final Stairs ACACIA_STAIRS = (Stairs) get("minecraft:acacia_stairs");
  public static final Trapdoor ACACIA_TRAPDOOR = (Trapdoor) get("minecraft:acacia_trapdoor");
  public static final WallHangingSign ACACIA_WALL_HANGING_SIGN = (WallHangingSign) get("minecraft:acacia_wall_hanging_sign");
  public static final WallSign ACACIA_WALL_SIGN = (WallSign) get("minecraft:acacia_wall_sign");
  public static final BlockState.Axis<BlockState> ACACIA_WOOD = (BlockState.Axis<BlockState>) get("minecraft:acacia_wood");
  public static final ActivatorRail ACTIVATOR_RAIL = (ActivatorRail) get("minecraft:activator_rail");
  public static final BlockState AIR = get("minecraft:air");
  public static final BlockState ALLIUM = get("minecraft:allium");
  public static final BlockState AMETHYST_BLOCK = get("minecraft:amethyst_block");
  public static final AmethystCluster AMETHYST_CLUSTER = (AmethystCluster) get("minecraft:amethyst_cluster");
  public static final BlockState ANCIENT_DEBRIS = get("minecraft:ancient_debris");
  public static final BlockState ANDESITE = get("minecraft:andesite");
  public static final BlockState.Waterloggable<BlockState> ANDESITE_SLAB = (BlockState.Waterloggable<BlockState>) get("minecraft:andesite_slab");
  public static final Stairs ANDESITE_STAIRS = (Stairs) get("minecraft:andesite_stairs");
  public static final BlockState.Waterloggable<BlockState> ANDESITE_WALL = (BlockState.Waterloggable<BlockState>) get("minecraft:andesite_wall");
  public static final BlockState.Facing<BlockState> ANVIL = (BlockState.Facing<BlockState>) get("minecraft:anvil");
  public static final BlockState.Facing<BlockState> ATTACHED_MELON_STEM = (BlockState.Facing<BlockState>) get("minecraft:attached_melon_stem");
  public static final BlockState.Facing<BlockState> ATTACHED_PUMPKIN_STEM = (BlockState.Facing<BlockState>) get("minecraft:attached_pumpkin_stem");
  public static final BlockState AZALEA = get("minecraft:azalea");
  public static final BlockState.Waterloggable<BlockState> AZALEA_LEAVES = (BlockState.Waterloggable<BlockState>) get("minecraft:azalea_leaves");
  public static final BlockState AZURE_BLUET = get("minecraft:azure_bluet");
  public static final BlockState.Ageable<BlockState> BAMBOO = (BlockState.Ageable<BlockState>) get("minecraft:bamboo");
  public static final BlockState.Axis<BlockState> BAMBOO_BLOCK = (BlockState.Axis<BlockState>) get("minecraft:bamboo_block");
  public static final Button BAMBOO_BUTTON = (Button) get("minecraft:bamboo_button");
  public static final Door BAMBOO_DOOR = (Door) get("minecraft:bamboo_door");
  public static final BlockState.Waterloggable<BlockState> BAMBOO_FENCE = (BlockState.Waterloggable<BlockState>) get("minecraft:bamboo_fence");
  public static final FenceGate BAMBOO_FENCE_GATE = (FenceGate) get("minecraft:bamboo_fence_gate");
  public static final HangingSign BAMBOO_HANGING_SIGN = (HangingSign) get("minecraft:bamboo_hanging_sign");
  public static final BlockState BAMBOO_MOSAIC = get("minecraft:bamboo_mosaic");
  public static final BlockState.Waterloggable<BlockState> BAMBOO_MOSAIC_SLAB = (BlockState.Waterloggable<BlockState>) get("minecraft:bamboo_mosaic_slab");
  public static final Stairs BAMBOO_MOSAIC_STAIRS = (Stairs) get("minecraft:bamboo_mosaic_stairs");
  public static final BlockState BAMBOO_PLANKS = get("minecraft:bamboo_planks");
  public static final BlockState.Powerable<BlockState> BAMBOO_PRESSURE_PLATE = (BlockState.Powerable<BlockState>) get("minecraft:bamboo_pressure_plate");
  public static final BlockState BAMBOO_SAPLING = get("minecraft:bamboo_sapling");
  public static final Sign BAMBOO_SIGN = (Sign) get("minecraft:bamboo_sign");
  public static final BlockState.Waterloggable<BlockState> BAMBOO_SLAB = (BlockState.Waterloggable<BlockState>) get("minecraft:bamboo_slab");
  public static final Stairs BAMBOO_STAIRS = (Stairs) get("minecraft:bamboo_stairs");
  public static final Trapdoor BAMBOO_TRAPDOOR = (Trapdoor) get("minecraft:bamboo_trapdoor");
  public static final WallHangingSign BAMBOO_WALL_HANGING_SIGN = (WallHangingSign) get("minecraft:bamboo_wall_hanging_sign");
  public static final WallSign BAMBOO_WALL_SIGN = (WallSign) get("minecraft:bamboo_wall_sign");
  public static final Barrel BARREL = (Barrel) get("minecraft:barrel");
  public static final BlockState.Waterloggable<BlockState> BARRIER = (BlockState.Waterloggable<BlockState>) get("minecraft:barrier");
  public static final BlockState.Axis<BlockState> BASALT = (BlockState.Axis<BlockState>) get("minecraft:basalt");
  public static final Beacon BEACON = (Beacon) get("minecraft:beacon");
  public static final BlockState BEDROCK = get("minecraft:bedrock");
  public static final BlockState.Facing<BlockState> BEE_NEST = (BlockState.Facing<BlockState>) get("minecraft:bee_nest");
  public static final Beehive BEEHIVE = (Beehive) get("minecraft:beehive");
  public static final BlockState.Ageable<BlockState> BEETROOTS = (BlockState.Ageable<BlockState>) get("minecraft:beetroots");
  public static final Bell BELL = (Bell) get("minecraft:bell");
  public static final BigDripleaf BIG_DRIPLEAF = (BigDripleaf) get("minecraft:big_dripleaf");
  public static final BigDripleafStem BIG_DRIPLEAF_STEM = (BigDripleafStem) get("minecraft:big_dripleaf_stem");
  public static final Button BIRCH_BUTTON = (Button) get("minecraft:birch_button");
  public static final Door BIRCH_DOOR = (Door) get("minecraft:birch_door");
  public static final BlockState.Waterloggable<BlockState> BIRCH_FENCE = (BlockState.Waterloggable<BlockState>) get("minecraft:birch_fence");
  public static final FenceGate BIRCH_FENCE_GATE = (FenceGate) get("minecraft:birch_fence_gate");
  public static final HangingSign BIRCH_HANGING_SIGN = (HangingSign) get("minecraft:birch_hanging_sign");
  public static final BlockState.Waterloggable<BlockState> BIRCH_LEAVES = (BlockState.Waterloggable<BlockState>) get("minecraft:birch_leaves");
  public static final BlockState.Axis<BlockState> BIRCH_LOG = (BlockState.Axis<BlockState>) get("minecraft:birch_log");
  public static final BlockState BIRCH_PLANKS = get("minecraft:birch_planks");
  public static final BlockState.Powerable<BlockState> BIRCH_PRESSURE_PLATE = (BlockState.Powerable<BlockState>) get("minecraft:birch_pressure_plate");
  public static final BlockState BIRCH_SAPLING = get("minecraft:birch_sapling");
  public static final Sign BIRCH_SIGN = (Sign) get("minecraft:birch_sign");
  public static final BlockState.Waterloggable<BlockState> BIRCH_SLAB = (BlockState.Waterloggable<BlockState>) get("minecraft:birch_slab");
  public static final Stairs BIRCH_STAIRS = (Stairs) get("minecraft:birch_stairs");
  public static final Trapdoor BIRCH_TRAPDOOR = (Trapdoor) get("minecraft:birch_trapdoor");
  public static final WallHangingSign BIRCH_WALL_HANGING_SIGN = (WallHangingSign) get("minecraft:birch_wall_hanging_sign");
  public static final WallSign BIRCH_WALL_SIGN = (WallSign) get("minecraft:birch_wall_sign");
  public static final BlockState.Axis<BlockState> BIRCH_WOOD = (BlockState.Axis<BlockState>) get("minecraft:birch_wood");
  public static final Banner BLACK_BANNER = (Banner) get("minecraft:black_banner");
  public static final Bed BLACK_BED = (Bed) get("minecraft:black_bed");
  public static final BlockState.Waterloggable<BlockState> BLACK_CANDLE = (BlockState.Waterloggable<BlockState>) get("minecraft:black_candle");
  public static final BlockState BLACK_CANDLE_CAKE = get("minecraft:black_candle_cake");
  public static final BlockState BLACK_CARPET = get("minecraft:black_carpet");
  public static final BlockState BLACK_CONCRETE = get("minecraft:black_concrete");
  public static final BlockState BLACK_CONCRETE_POWDER = get("minecraft:black_concrete_powder");
  public static final BlockState.Facing<BlockState> BLACK_GLAZED_TERRACOTTA = (BlockState.Facing<BlockState>) get("minecraft:black_glazed_terracotta");
  public static final BlockState.Facing<BlockState> BLACK_SHULKER_BOX = (BlockState.Facing<BlockState>) get("minecraft:black_shulker_box");
  public static final BlockState BLACK_STAINED_GLASS = get("minecraft:black_stained_glass");
  public static final BlockState.Waterloggable<BlockState> BLACK_STAINED_GLASS_PANE = (BlockState.Waterloggable<BlockState>) get("minecraft:black_stained_glass_pane");
  public static final BlockState BLACK_TERRACOTTA = get("minecraft:black_terracotta");
  public static final WallBanner BLACK_WALL_BANNER = (WallBanner) get("minecraft:black_wall_banner");
  public static final BlockState BLACK_WOOL = get("minecraft:black_wool");
  public static final BlockState BLACKSTONE = get("minecraft:blackstone");
  public static final BlockState.Waterloggable<BlockState> BLACKSTONE_SLAB = (BlockState.Waterloggable<BlockState>) get("minecraft:blackstone_slab");
  public static final Stairs BLACKSTONE_STAIRS = (Stairs) get("minecraft:blackstone_stairs");
  public static final BlockState.Waterloggable<BlockState> BLACKSTONE_WALL = (BlockState.Waterloggable<BlockState>) get("minecraft:blackstone_wall");
  public static final BlastFurnace BLAST_FURNACE = (BlastFurnace) get("minecraft:blast_furnace");
  public static final Banner BLUE_BANNER = (Banner) get("minecraft:blue_banner");
  public static final Bed BLUE_BED = (Bed) get("minecraft:blue_bed");
  public static final BlockState.Waterloggable<BlockState> BLUE_CANDLE = (BlockState.Waterloggable<BlockState>) get("minecraft:blue_candle");
  public static final BlockState BLUE_CANDLE_CAKE = get("minecraft:blue_candle_cake");
  public static final BlockState BLUE_CARPET = get("minecraft:blue_carpet");
  public static final BlockState BLUE_CONCRETE = get("minecraft:blue_concrete");
  public static final BlockState BLUE_CONCRETE_POWDER = get("minecraft:blue_concrete_powder");
  public static final BlockState.Facing<BlockState> BLUE_GLAZED_TERRACOTTA = (BlockState.Facing<BlockState>) get("minecraft:blue_glazed_terracotta");
  public static final BlockState BLUE_ICE = get("minecraft:blue_ice");
  public static final BlockState BLUE_ORCHID = get("minecraft:blue_orchid");
  public static final BlockState.Facing<BlockState> BLUE_SHULKER_BOX = (BlockState.Facing<BlockState>) get("minecraft:blue_shulker_box");
  public static final BlockState BLUE_STAINED_GLASS = get("minecraft:blue_stained_glass");
  public static final BlockState.Waterloggable<BlockState> BLUE_STAINED_GLASS_PANE = (BlockState.Waterloggable<BlockState>) get("minecraft:blue_stained_glass_pane");
  public static final BlockState BLUE_TERRACOTTA = get("minecraft:blue_terracotta");
  public static final WallBanner BLUE_WALL_BANNER = (WallBanner) get("minecraft:blue_wall_banner");
  public static final BlockState BLUE_WOOL = get("minecraft:blue_wool");
  public static final BlockState.Axis<BlockState> BONE_BLOCK = (BlockState.Axis<BlockState>) get("minecraft:bone_block");
  public static final BlockState BOOKSHELF = get("minecraft:bookshelf");
  public static final BlockState.Waterloggable<BlockState> BRAIN_CORAL = (BlockState.Waterloggable<BlockState>) get("minecraft:brain_coral");
  public static final BlockState BRAIN_CORAL_BLOCK = get("minecraft:brain_coral_block");
  public static final BlockState.Waterloggable<BlockState> BRAIN_CORAL_FAN = (BlockState.Waterloggable<BlockState>) get("minecraft:brain_coral_fan");
  public static final WallFan BRAIN_CORAL_WALL_FAN = (WallFan) get("minecraft:brain_coral_wall_fan");
  public static final BrewingStand BREWING_STAND = (BrewingStand) get("minecraft:brewing_stand");
  public static final BlockState.Waterloggable<BlockState> BRICK_SLAB = (BlockState.Waterloggable<BlockState>) get("minecraft:brick_slab");
  public static final Stairs BRICK_STAIRS = (Stairs) get("minecraft:brick_stairs");
  public static final BlockState.Waterloggable<BlockState> BRICK_WALL = (BlockState.Waterloggable<BlockState>) get("minecraft:brick_wall");
  public static final BlockState BRICKS = get("minecraft:bricks");
  public static final Banner BROWN_BANNER = (Banner) get("minecraft:brown_banner");
  public static final Bed BROWN_BED = (Bed) get("minecraft:brown_bed");
  public static final BlockState.Waterloggable<BlockState> BROWN_CANDLE = (BlockState.Waterloggable<BlockState>) get("minecraft:brown_candle");
  public static final BlockState BROWN_CANDLE_CAKE = get("minecraft:brown_candle_cake");
  public static final BlockState BROWN_CARPET = get("minecraft:brown_carpet");
  public static final BlockState BROWN_CONCRETE = get("minecraft:brown_concrete");
  public static final BlockState BROWN_CONCRETE_POWDER = get("minecraft:brown_concrete_powder");
  public static final BlockState.Facing<BlockState> BROWN_GLAZED_TERRACOTTA = (BlockState.Facing<BlockState>) get("minecraft:brown_glazed_terracotta");
  public static final BlockState BROWN_MUSHROOM = get("minecraft:brown_mushroom");
  public static final BlockState BROWN_MUSHROOM_BLOCK = get("minecraft:brown_mushroom_block");
  public static final BlockState.Facing<BlockState> BROWN_SHULKER_BOX = (BlockState.Facing<BlockState>) get("minecraft:brown_shulker_box");
  public static final BlockState BROWN_STAINED_GLASS = get("minecraft:brown_stained_glass");
  public static final BlockState.Waterloggable<BlockState> BROWN_STAINED_GLASS_PANE = (BlockState.Waterloggable<BlockState>) get("minecraft:brown_stained_glass_pane");
  public static final BlockState BROWN_TERRACOTTA = get("minecraft:brown_terracotta");
  public static final WallBanner BROWN_WALL_BANNER = (WallBanner) get("minecraft:brown_wall_banner");
  public static final BlockState BROWN_WOOL = get("minecraft:brown_wool");
  public static final BlockState BUBBLE_COLUMN = get("minecraft:bubble_column");
  public static final BlockState.Waterloggable<BlockState> BUBBLE_CORAL = (BlockState.Waterloggable<BlockState>) get("minecraft:bubble_coral");
  public static final BlockState BUBBLE_CORAL_BLOCK = get("minecraft:bubble_coral_block");
  public static final BlockState.Waterloggable<BlockState> BUBBLE_CORAL_FAN = (BlockState.Waterloggable<BlockState>) get("minecraft:bubble_coral_fan");
  public static final WallFan BUBBLE_CORAL_WALL_FAN = (WallFan) get("minecraft:bubble_coral_wall_fan");
  public static final BlockState BUDDING_AMETHYST = get("minecraft:budding_amethyst");
  public static final BlockState.Ageable<BlockState> CACTUS = (BlockState.Ageable<BlockState>) get("minecraft:cactus");
  public static final BlockState CAKE = get("minecraft:cake");
  public static final BlockState CALCITE = get("minecraft:calcite");
  public static final CalibratedSculkSensor CALIBRATED_SCULK_SENSOR = (CalibratedSculkSensor) get("minecraft:calibrated_sculk_sensor");
  public static final Campfire CAMPFIRE = (Campfire) get("minecraft:campfire");
  public static final BlockState.Waterloggable<BlockState> CANDLE = (BlockState.Waterloggable<BlockState>) get("minecraft:candle");
  public static final BlockState CANDLE_CAKE = get("minecraft:candle_cake");
  public static final BlockState.Ageable<BlockState> CARROTS = (BlockState.Ageable<BlockState>) get("minecraft:carrots");
  public static final BlockState CARTOGRAPHY_TABLE = get("minecraft:cartography_table");
  public static final BlockState.Facing<BlockState> CARVED_PUMPKIN = (BlockState.Facing<BlockState>) get("minecraft:carved_pumpkin");
  public static final BlockState CAULDRON = get("minecraft:cauldron");
  public static final BlockState CAVE_AIR = get("minecraft:cave_air");
  public static final BlockState.Ageable<BlockState> CAVE_VINES = (BlockState.Ageable<BlockState>) get("minecraft:cave_vines");
  public static final BlockState CAVE_VINES_PLANT = get("minecraft:cave_vines_plant");
  public static final Chain CHAIN = (Chain) get("minecraft:chain");
  public static final BlockState.Facing<BlockState> CHAIN_COMMAND_BLOCK = (BlockState.Facing<BlockState>) get("minecraft:chain_command_block");
  public static final Button CHERRY_BUTTON = (Button) get("minecraft:cherry_button");
  public static final Door CHERRY_DOOR = (Door) get("minecraft:cherry_door");
  public static final BlockState.Waterloggable<BlockState> CHERRY_FENCE = (BlockState.Waterloggable<BlockState>) get("minecraft:cherry_fence");
  public static final FenceGate CHERRY_FENCE_GATE = (FenceGate) get("minecraft:cherry_fence_gate");
  public static final HangingSign CHERRY_HANGING_SIGN = (HangingSign) get("minecraft:cherry_hanging_sign");
  public static final BlockState.Waterloggable<BlockState> CHERRY_LEAVES = (BlockState.Waterloggable<BlockState>) get("minecraft:cherry_leaves");
  public static final BlockState.Axis<BlockState> CHERRY_LOG = (BlockState.Axis<BlockState>) get("minecraft:cherry_log");
  public static final BlockState CHERRY_PLANKS = get("minecraft:cherry_planks");
  public static final BlockState.Powerable<BlockState> CHERRY_PRESSURE_PLATE = (BlockState.Powerable<BlockState>) get("minecraft:cherry_pressure_plate");
  public static final BlockState CHERRY_SAPLING = get("minecraft:cherry_sapling");
  public static final Sign CHERRY_SIGN = (Sign) get("minecraft:cherry_sign");
  public static final BlockState.Waterloggable<BlockState> CHERRY_SLAB = (BlockState.Waterloggable<BlockState>) get("minecraft:cherry_slab");
  public static final Stairs CHERRY_STAIRS = (Stairs) get("minecraft:cherry_stairs");
  public static final Trapdoor CHERRY_TRAPDOOR = (Trapdoor) get("minecraft:cherry_trapdoor");
  public static final WallHangingSign CHERRY_WALL_HANGING_SIGN = (WallHangingSign) get("minecraft:cherry_wall_hanging_sign");
  public static final WallSign CHERRY_WALL_SIGN = (WallSign) get("minecraft:cherry_wall_sign");
  public static final BlockState.Axis<BlockState> CHERRY_WOOD = (BlockState.Axis<BlockState>) get("minecraft:cherry_wood");
  public static final Chest CHEST = (Chest) get("minecraft:chest");
  public static final BlockState.Facing<BlockState> CHIPPED_ANVIL = (BlockState.Facing<BlockState>) get("minecraft:chipped_anvil");
  public static final ChiseledBookshelf CHISELED_BOOKSHELF = (ChiseledBookshelf) get("minecraft:chiseled_bookshelf");
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
  public static final BlockState.Ageable<BlockState> CHORUS_FLOWER = (BlockState.Ageable<BlockState>) get("minecraft:chorus_flower");
  public static final BlockState CHORUS_PLANT = get("minecraft:chorus_plant");
  public static final BlockState CLAY = get("minecraft:clay");
  public static final BlockState COAL_BLOCK = get("minecraft:coal_block");
  public static final BlockState COAL_ORE = get("minecraft:coal_ore");
  public static final BlockState COARSE_DIRT = get("minecraft:coarse_dirt");
  public static final BlockState COBBLED_DEEPSLATE = get("minecraft:cobbled_deepslate");
  public static final BlockState.Waterloggable<BlockState> COBBLED_DEEPSLATE_SLAB = (BlockState.Waterloggable<BlockState>) get("minecraft:cobbled_deepslate_slab");
  public static final Stairs COBBLED_DEEPSLATE_STAIRS = (Stairs) get("minecraft:cobbled_deepslate_stairs");
  public static final BlockState.Waterloggable<BlockState> COBBLED_DEEPSLATE_WALL = (BlockState.Waterloggable<BlockState>) get("minecraft:cobbled_deepslate_wall");
  public static final BlockState COBBLESTONE = get("minecraft:cobblestone");
  public static final BlockState.Waterloggable<BlockState> COBBLESTONE_SLAB = (BlockState.Waterloggable<BlockState>) get("minecraft:cobblestone_slab");
  public static final Stairs COBBLESTONE_STAIRS = (Stairs) get("minecraft:cobblestone_stairs");
  public static final BlockState.Waterloggable<BlockState> COBBLESTONE_WALL = (BlockState.Waterloggable<BlockState>) get("minecraft:cobblestone_wall");
  public static final BlockState COBWEB = get("minecraft:cobweb");
  public static final Cocoa COCOA = (Cocoa) get("minecraft:cocoa");
  public static final CommandBlock COMMAND_BLOCK = (CommandBlock) get("minecraft:command_block");
  public static final Comparator COMPARATOR = (Comparator) get("minecraft:comparator");
  public static final BlockState COMPOSTER = get("minecraft:composter");
  public static final Conduit CONDUIT = (Conduit) get("minecraft:conduit");
  public static final BlockState COPPER_BLOCK = get("minecraft:copper_block");
  public static final BlockState.Powerable<BlockState> COPPER_BULB = (BlockState.Powerable<BlockState>) get("minecraft:copper_bulb");
  public static final Door COPPER_DOOR = (Door) get("minecraft:copper_door");
  public static final BlockState.Waterloggable<BlockState> COPPER_GRATE = (BlockState.Waterloggable<BlockState>) get("minecraft:copper_grate");
  public static final BlockState COPPER_ORE = get("minecraft:copper_ore");
  public static final Trapdoor COPPER_TRAPDOOR = (Trapdoor) get("minecraft:copper_trapdoor");
  public static final BlockState CORNFLOWER = get("minecraft:cornflower");
  public static final BlockState CRACKED_DEEPSLATE_BRICKS = get("minecraft:cracked_deepslate_bricks");
  public static final BlockState CRACKED_DEEPSLATE_TILES = get("minecraft:cracked_deepslate_tiles");
  public static final BlockState CRACKED_NETHER_BRICKS = get("minecraft:cracked_nether_bricks");
  public static final BlockState CRACKED_POLISHED_BLACKSTONE_BRICKS = get("minecraft:cracked_polished_blackstone_bricks");
  public static final BlockState CRACKED_STONE_BRICKS = get("minecraft:cracked_stone_bricks");
  public static final BlockState CRAFTER = get("minecraft:crafter");
  public static final BlockState CRAFTING_TABLE = get("minecraft:crafting_table");
  public static final Head CREEPER_HEAD = (Head) get("minecraft:creeper_head");
  public static final WallHead CREEPER_WALL_HEAD = (WallHead) get("minecraft:creeper_wall_head");
  public static final Button CRIMSON_BUTTON = (Button) get("minecraft:crimson_button");
  public static final Door CRIMSON_DOOR = (Door) get("minecraft:crimson_door");
  public static final BlockState.Waterloggable<BlockState> CRIMSON_FENCE = (BlockState.Waterloggable<BlockState>) get("minecraft:crimson_fence");
  public static final FenceGate CRIMSON_FENCE_GATE = (FenceGate) get("minecraft:crimson_fence_gate");
  public static final BlockState CRIMSON_FUNGUS = get("minecraft:crimson_fungus");
  public static final HangingSign CRIMSON_HANGING_SIGN = (HangingSign) get("minecraft:crimson_hanging_sign");
  public static final BlockState.Axis<BlockState> CRIMSON_HYPHAE = (BlockState.Axis<BlockState>) get("minecraft:crimson_hyphae");
  public static final BlockState CRIMSON_NYLIUM = get("minecraft:crimson_nylium");
  public static final BlockState CRIMSON_PLANKS = get("minecraft:crimson_planks");
  public static final BlockState.Powerable<BlockState> CRIMSON_PRESSURE_PLATE = (BlockState.Powerable<BlockState>) get("minecraft:crimson_pressure_plate");
  public static final BlockState CRIMSON_ROOTS = get("minecraft:crimson_roots");
  public static final Sign CRIMSON_SIGN = (Sign) get("minecraft:crimson_sign");
  public static final BlockState.Waterloggable<BlockState> CRIMSON_SLAB = (BlockState.Waterloggable<BlockState>) get("minecraft:crimson_slab");
  public static final Stairs CRIMSON_STAIRS = (Stairs) get("minecraft:crimson_stairs");
  public static final BlockState.Axis<BlockState> CRIMSON_STEM = (BlockState.Axis<BlockState>) get("minecraft:crimson_stem");
  public static final Trapdoor CRIMSON_TRAPDOOR = (Trapdoor) get("minecraft:crimson_trapdoor");
  public static final WallHangingSign CRIMSON_WALL_HANGING_SIGN = (WallHangingSign) get("minecraft:crimson_wall_hanging_sign");
  public static final WallSign CRIMSON_WALL_SIGN = (WallSign) get("minecraft:crimson_wall_sign");
  public static final BlockState CRYING_OBSIDIAN = get("minecraft:crying_obsidian");
  public static final BlockState CUT_COPPER = get("minecraft:cut_copper");
  public static final BlockState.Waterloggable<BlockState> CUT_COPPER_SLAB = (BlockState.Waterloggable<BlockState>) get("minecraft:cut_copper_slab");
  public static final Stairs CUT_COPPER_STAIRS = (Stairs) get("minecraft:cut_copper_stairs");
  public static final BlockState CUT_RED_SANDSTONE = get("minecraft:cut_red_sandstone");
  public static final BlockState.Waterloggable<BlockState> CUT_RED_SANDSTONE_SLAB = (BlockState.Waterloggable<BlockState>) get("minecraft:cut_red_sandstone_slab");
  public static final BlockState CUT_SANDSTONE = get("minecraft:cut_sandstone");
  public static final BlockState.Waterloggable<BlockState> CUT_SANDSTONE_SLAB = (BlockState.Waterloggable<BlockState>) get("minecraft:cut_sandstone_slab");
  public static final Banner CYAN_BANNER = (Banner) get("minecraft:cyan_banner");
  public static final Bed CYAN_BED = (Bed) get("minecraft:cyan_bed");
  public static final BlockState.Waterloggable<BlockState> CYAN_CANDLE = (BlockState.Waterloggable<BlockState>) get("minecraft:cyan_candle");
  public static final BlockState CYAN_CANDLE_CAKE = get("minecraft:cyan_candle_cake");
  public static final BlockState CYAN_CARPET = get("minecraft:cyan_carpet");
  public static final BlockState CYAN_CONCRETE = get("minecraft:cyan_concrete");
  public static final BlockState CYAN_CONCRETE_POWDER = get("minecraft:cyan_concrete_powder");
  public static final BlockState.Facing<BlockState> CYAN_GLAZED_TERRACOTTA = (BlockState.Facing<BlockState>) get("minecraft:cyan_glazed_terracotta");
  public static final BlockState.Facing<BlockState> CYAN_SHULKER_BOX = (BlockState.Facing<BlockState>) get("minecraft:cyan_shulker_box");
  public static final BlockState CYAN_STAINED_GLASS = get("minecraft:cyan_stained_glass");
  public static final BlockState.Waterloggable<BlockState> CYAN_STAINED_GLASS_PANE = (BlockState.Waterloggable<BlockState>) get("minecraft:cyan_stained_glass_pane");
  public static final BlockState CYAN_TERRACOTTA = get("minecraft:cyan_terracotta");
  public static final WallBanner CYAN_WALL_BANNER = (WallBanner) get("minecraft:cyan_wall_banner");
  public static final BlockState CYAN_WOOL = get("minecraft:cyan_wool");
  public static final BlockState.Facing<BlockState> DAMAGED_ANVIL = (BlockState.Facing<BlockState>) get("minecraft:damaged_anvil");
  public static final BlockState DANDELION = get("minecraft:dandelion");
  public static final Button DARK_OAK_BUTTON = (Button) get("minecraft:dark_oak_button");
  public static final Door DARK_OAK_DOOR = (Door) get("minecraft:dark_oak_door");
  public static final BlockState.Waterloggable<BlockState> DARK_OAK_FENCE = (BlockState.Waterloggable<BlockState>) get("minecraft:dark_oak_fence");
  public static final FenceGate DARK_OAK_FENCE_GATE = (FenceGate) get("minecraft:dark_oak_fence_gate");
  public static final HangingSign DARK_OAK_HANGING_SIGN = (HangingSign) get("minecraft:dark_oak_hanging_sign");
  public static final BlockState.Waterloggable<BlockState> DARK_OAK_LEAVES = (BlockState.Waterloggable<BlockState>) get("minecraft:dark_oak_leaves");
  public static final BlockState.Axis<BlockState> DARK_OAK_LOG = (BlockState.Axis<BlockState>) get("minecraft:dark_oak_log");
  public static final BlockState DARK_OAK_PLANKS = get("minecraft:dark_oak_planks");
  public static final BlockState.Powerable<BlockState> DARK_OAK_PRESSURE_PLATE = (BlockState.Powerable<BlockState>) get("minecraft:dark_oak_pressure_plate");
  public static final BlockState DARK_OAK_SAPLING = get("minecraft:dark_oak_sapling");
  public static final Sign DARK_OAK_SIGN = (Sign) get("minecraft:dark_oak_sign");
  public static final BlockState.Waterloggable<BlockState> DARK_OAK_SLAB = (BlockState.Waterloggable<BlockState>) get("minecraft:dark_oak_slab");
  public static final Stairs DARK_OAK_STAIRS = (Stairs) get("minecraft:dark_oak_stairs");
  public static final Trapdoor DARK_OAK_TRAPDOOR = (Trapdoor) get("minecraft:dark_oak_trapdoor");
  public static final WallHangingSign DARK_OAK_WALL_HANGING_SIGN = (WallHangingSign) get("minecraft:dark_oak_wall_hanging_sign");
  public static final WallSign DARK_OAK_WALL_SIGN = (WallSign) get("minecraft:dark_oak_wall_sign");
  public static final BlockState.Axis<BlockState> DARK_OAK_WOOD = (BlockState.Axis<BlockState>) get("minecraft:dark_oak_wood");
  public static final BlockState DARK_PRISMARINE = get("minecraft:dark_prismarine");
  public static final BlockState.Waterloggable<BlockState> DARK_PRISMARINE_SLAB = (BlockState.Waterloggable<BlockState>) get("minecraft:dark_prismarine_slab");
  public static final Stairs DARK_PRISMARINE_STAIRS = (Stairs) get("minecraft:dark_prismarine_stairs");
  public static final DaylightDetector DAYLIGHT_DETECTOR = (DaylightDetector) get("minecraft:daylight_detector");
  public static final BlockState.Waterloggable<BlockState> DEAD_BRAIN_CORAL = (BlockState.Waterloggable<BlockState>) get("minecraft:dead_brain_coral");
  public static final BlockState DEAD_BRAIN_CORAL_BLOCK = get("minecraft:dead_brain_coral_block");
  public static final BlockState.Waterloggable<BlockState> DEAD_BRAIN_CORAL_FAN = (BlockState.Waterloggable<BlockState>) get("minecraft:dead_brain_coral_fan");
  public static final WallFan DEAD_BRAIN_CORAL_WALL_FAN = (WallFan) get("minecraft:dead_brain_coral_wall_fan");
  public static final BlockState.Waterloggable<BlockState> DEAD_BUBBLE_CORAL = (BlockState.Waterloggable<BlockState>) get("minecraft:dead_bubble_coral");
  public static final BlockState DEAD_BUBBLE_CORAL_BLOCK = get("minecraft:dead_bubble_coral_block");
  public static final BlockState.Waterloggable<BlockState> DEAD_BUBBLE_CORAL_FAN = (BlockState.Waterloggable<BlockState>) get("minecraft:dead_bubble_coral_fan");
  public static final WallFan DEAD_BUBBLE_CORAL_WALL_FAN = (WallFan) get("minecraft:dead_bubble_coral_wall_fan");
  public static final BlockState DEAD_BUSH = get("minecraft:dead_bush");
  public static final BlockState.Waterloggable<BlockState> DEAD_FIRE_CORAL = (BlockState.Waterloggable<BlockState>) get("minecraft:dead_fire_coral");
  public static final BlockState DEAD_FIRE_CORAL_BLOCK = get("minecraft:dead_fire_coral_block");
  public static final BlockState.Waterloggable<BlockState> DEAD_FIRE_CORAL_FAN = (BlockState.Waterloggable<BlockState>) get("minecraft:dead_fire_coral_fan");
  public static final WallFan DEAD_FIRE_CORAL_WALL_FAN = (WallFan) get("minecraft:dead_fire_coral_wall_fan");
  public static final BlockState.Waterloggable<BlockState> DEAD_HORN_CORAL = (BlockState.Waterloggable<BlockState>) get("minecraft:dead_horn_coral");
  public static final BlockState DEAD_HORN_CORAL_BLOCK = get("minecraft:dead_horn_coral_block");
  public static final BlockState.Waterloggable<BlockState> DEAD_HORN_CORAL_FAN = (BlockState.Waterloggable<BlockState>) get("minecraft:dead_horn_coral_fan");
  public static final WallFan DEAD_HORN_CORAL_WALL_FAN = (WallFan) get("minecraft:dead_horn_coral_wall_fan");
  public static final BlockState.Waterloggable<BlockState> DEAD_TUBE_CORAL = (BlockState.Waterloggable<BlockState>) get("minecraft:dead_tube_coral");
  public static final BlockState DEAD_TUBE_CORAL_BLOCK = get("minecraft:dead_tube_coral_block");
  public static final BlockState.Waterloggable<BlockState> DEAD_TUBE_CORAL_FAN = (BlockState.Waterloggable<BlockState>) get("minecraft:dead_tube_coral_fan");
  public static final WallFan DEAD_TUBE_CORAL_WALL_FAN = (WallFan) get("minecraft:dead_tube_coral_wall_fan");
  public static final DecoratedPot DECORATED_POT = (DecoratedPot) get("minecraft:decorated_pot");
  public static final BlockState.Axis<BlockState> DEEPSLATE = (BlockState.Axis<BlockState>) get("minecraft:deepslate");
  public static final BlockState.Waterloggable<BlockState> DEEPSLATE_BRICK_SLAB = (BlockState.Waterloggable<BlockState>) get("minecraft:deepslate_brick_slab");
  public static final Stairs DEEPSLATE_BRICK_STAIRS = (Stairs) get("minecraft:deepslate_brick_stairs");
  public static final BlockState.Waterloggable<BlockState> DEEPSLATE_BRICK_WALL = (BlockState.Waterloggable<BlockState>) get("minecraft:deepslate_brick_wall");
  public static final BlockState DEEPSLATE_BRICKS = get("minecraft:deepslate_bricks");
  public static final BlockState DEEPSLATE_COAL_ORE = get("minecraft:deepslate_coal_ore");
  public static final BlockState DEEPSLATE_COPPER_ORE = get("minecraft:deepslate_copper_ore");
  public static final BlockState DEEPSLATE_DIAMOND_ORE = get("minecraft:deepslate_diamond_ore");
  public static final BlockState DEEPSLATE_EMERALD_ORE = get("minecraft:deepslate_emerald_ore");
  public static final BlockState DEEPSLATE_GOLD_ORE = get("minecraft:deepslate_gold_ore");
  public static final BlockState DEEPSLATE_IRON_ORE = get("minecraft:deepslate_iron_ore");
  public static final BlockState DEEPSLATE_LAPIS_ORE = get("minecraft:deepslate_lapis_ore");
  public static final BlockState DEEPSLATE_REDSTONE_ORE = get("minecraft:deepslate_redstone_ore");
  public static final BlockState.Waterloggable<BlockState> DEEPSLATE_TILE_SLAB = (BlockState.Waterloggable<BlockState>) get("minecraft:deepslate_tile_slab");
  public static final Stairs DEEPSLATE_TILE_STAIRS = (Stairs) get("minecraft:deepslate_tile_stairs");
  public static final BlockState.Waterloggable<BlockState> DEEPSLATE_TILE_WALL = (BlockState.Waterloggable<BlockState>) get("minecraft:deepslate_tile_wall");
  public static final BlockState DEEPSLATE_TILES = get("minecraft:deepslate_tiles");
  public static final DetectorRail DETECTOR_RAIL = (DetectorRail) get("minecraft:detector_rail");
  public static final BlockState DIAMOND_BLOCK = get("minecraft:diamond_block");
  public static final BlockState DIAMOND_ORE = get("minecraft:diamond_ore");
  public static final BlockState DIORITE = get("minecraft:diorite");
  public static final BlockState.Waterloggable<BlockState> DIORITE_SLAB = (BlockState.Waterloggable<BlockState>) get("minecraft:diorite_slab");
  public static final Stairs DIORITE_STAIRS = (Stairs) get("minecraft:diorite_stairs");
  public static final BlockState.Waterloggable<BlockState> DIORITE_WALL = (BlockState.Waterloggable<BlockState>) get("minecraft:diorite_wall");
  public static final BlockState DIRT = get("minecraft:dirt");
  public static final BlockState DIRT_PATH = get("minecraft:dirt_path");
  public static final Dispenser DISPENSER = (Dispenser) get("minecraft:dispenser");
  public static final BlockState DRAGON_EGG = get("minecraft:dragon_egg");
  public static final Head DRAGON_HEAD = (Head) get("minecraft:dragon_head");
  public static final WallHead DRAGON_WALL_HEAD = (WallHead) get("minecraft:dragon_wall_head");
  public static final BlockState DRIED_KELP_BLOCK = get("minecraft:dried_kelp_block");
  public static final BlockState DRIPSTONE_BLOCK = get("minecraft:dripstone_block");
  public static final Dropper DROPPER = (Dropper) get("minecraft:dropper");
  public static final BlockState EMERALD_BLOCK = get("minecraft:emerald_block");
  public static final BlockState EMERALD_ORE = get("minecraft:emerald_ore");
  public static final EnchantingTable ENCHANTING_TABLE = (EnchantingTable) get("minecraft:enchanting_table");
  public static final EndGateway END_GATEWAY = (EndGateway) get("minecraft:end_gateway");
  public static final EndPortal END_PORTAL = (EndPortal) get("minecraft:end_portal");
  public static final BlockState.Facing<BlockState> END_PORTAL_FRAME = (BlockState.Facing<BlockState>) get("minecraft:end_portal_frame");
  public static final BlockState.Facing<BlockState> END_ROD = (BlockState.Facing<BlockState>) get("minecraft:end_rod");
  public static final BlockState END_STONE = get("minecraft:end_stone");
  public static final BlockState.Waterloggable<BlockState> END_STONE_BRICK_SLAB = (BlockState.Waterloggable<BlockState>) get("minecraft:end_stone_brick_slab");
  public static final Stairs END_STONE_BRICK_STAIRS = (Stairs) get("minecraft:end_stone_brick_stairs");
  public static final BlockState.Waterloggable<BlockState> END_STONE_BRICK_WALL = (BlockState.Waterloggable<BlockState>) get("minecraft:end_stone_brick_wall");
  public static final BlockState END_STONE_BRICKS = get("minecraft:end_stone_bricks");
  public static final EnderChest ENDER_CHEST = (EnderChest) get("minecraft:ender_chest");
  public static final BlockState EXPOSED_CHISELED_COPPER = get("minecraft:exposed_chiseled_copper");
  public static final BlockState EXPOSED_COPPER = get("minecraft:exposed_copper");
  public static final BlockState.Powerable<BlockState> EXPOSED_COPPER_BULB = (BlockState.Powerable<BlockState>) get("minecraft:exposed_copper_bulb");
  public static final Door EXPOSED_COPPER_DOOR = (Door) get("minecraft:exposed_copper_door");
  public static final BlockState.Waterloggable<BlockState> EXPOSED_COPPER_GRATE = (BlockState.Waterloggable<BlockState>) get("minecraft:exposed_copper_grate");
  public static final Trapdoor EXPOSED_COPPER_TRAPDOOR = (Trapdoor) get("minecraft:exposed_copper_trapdoor");
  public static final BlockState EXPOSED_CUT_COPPER = get("minecraft:exposed_cut_copper");
  public static final BlockState.Waterloggable<BlockState> EXPOSED_CUT_COPPER_SLAB = (BlockState.Waterloggable<BlockState>) get("minecraft:exposed_cut_copper_slab");
  public static final Stairs EXPOSED_CUT_COPPER_STAIRS = (Stairs) get("minecraft:exposed_cut_copper_stairs");
  public static final BlockState FARMLAND = get("minecraft:farmland");
  public static final BlockState FERN = get("minecraft:fern");
  public static final BlockState.Ageable<BlockState> FIRE = (BlockState.Ageable<BlockState>) get("minecraft:fire");
  public static final BlockState.Waterloggable<BlockState> FIRE_CORAL = (BlockState.Waterloggable<BlockState>) get("minecraft:fire_coral");
  public static final BlockState FIRE_CORAL_BLOCK = get("minecraft:fire_coral_block");
  public static final BlockState.Waterloggable<BlockState> FIRE_CORAL_FAN = (BlockState.Waterloggable<BlockState>) get("minecraft:fire_coral_fan");
  public static final WallFan FIRE_CORAL_WALL_FAN = (WallFan) get("minecraft:fire_coral_wall_fan");
  public static final BlockState FLETCHING_TABLE = get("minecraft:fletching_table");
  public static final BlockState FLOWER_POT = get("minecraft:flower_pot");
  public static final BlockState FLOWERING_AZALEA = get("minecraft:flowering_azalea");
  public static final BlockState.Waterloggable<BlockState> FLOWERING_AZALEA_LEAVES = (BlockState.Waterloggable<BlockState>) get("minecraft:flowering_azalea_leaves");
  public static final BlockState FROGSPAWN = get("minecraft:frogspawn");
  public static final BlockState.Ageable<BlockState> FROSTED_ICE = (BlockState.Ageable<BlockState>) get("minecraft:frosted_ice");
  public static final Furnace FURNACE = (Furnace) get("minecraft:furnace");
  public static final BlockState GILDED_BLACKSTONE = get("minecraft:gilded_blackstone");
  public static final BlockState GLASS = get("minecraft:glass");
  public static final BlockState.Waterloggable<BlockState> GLASS_PANE = (BlockState.Waterloggable<BlockState>) get("minecraft:glass_pane");
  public static final BlockState.Waterloggable<BlockState> GLOW_LICHEN = (BlockState.Waterloggable<BlockState>) get("minecraft:glow_lichen");
  public static final BlockState GLOWSTONE = get("minecraft:glowstone");
  public static final BlockState GOLD_BLOCK = get("minecraft:gold_block");
  public static final BlockState GOLD_ORE = get("minecraft:gold_ore");
  public static final BlockState GRANITE = get("minecraft:granite");
  public static final BlockState.Waterloggable<BlockState> GRANITE_SLAB = (BlockState.Waterloggable<BlockState>) get("minecraft:granite_slab");
  public static final Stairs GRANITE_STAIRS = (Stairs) get("minecraft:granite_stairs");
  public static final BlockState.Waterloggable<BlockState> GRANITE_WALL = (BlockState.Waterloggable<BlockState>) get("minecraft:granite_wall");
  public static final BlockState.Snowy<BlockState> GRASS_BLOCK = (BlockState.Snowy<BlockState>) get("minecraft:grass_block");
  public static final BlockState GRAVEL = get("minecraft:gravel");
  public static final Banner GRAY_BANNER = (Banner) get("minecraft:gray_banner");
  public static final Bed GRAY_BED = (Bed) get("minecraft:gray_bed");
  public static final BlockState.Waterloggable<BlockState> GRAY_CANDLE = (BlockState.Waterloggable<BlockState>) get("minecraft:gray_candle");
  public static final BlockState GRAY_CANDLE_CAKE = get("minecraft:gray_candle_cake");
  public static final BlockState GRAY_CARPET = get("minecraft:gray_carpet");
  public static final BlockState GRAY_CONCRETE = get("minecraft:gray_concrete");
  public static final BlockState GRAY_CONCRETE_POWDER = get("minecraft:gray_concrete_powder");
  public static final BlockState.Facing<BlockState> GRAY_GLAZED_TERRACOTTA = (BlockState.Facing<BlockState>) get("minecraft:gray_glazed_terracotta");
  public static final BlockState.Facing<BlockState> GRAY_SHULKER_BOX = (BlockState.Facing<BlockState>) get("minecraft:gray_shulker_box");
  public static final BlockState GRAY_STAINED_GLASS = get("minecraft:gray_stained_glass");
  public static final BlockState.Waterloggable<BlockState> GRAY_STAINED_GLASS_PANE = (BlockState.Waterloggable<BlockState>) get("minecraft:gray_stained_glass_pane");
  public static final BlockState GRAY_TERRACOTTA = get("minecraft:gray_terracotta");
  public static final WallBanner GRAY_WALL_BANNER = (WallBanner) get("minecraft:gray_wall_banner");
  public static final BlockState GRAY_WOOL = get("minecraft:gray_wool");
  public static final Banner GREEN_BANNER = (Banner) get("minecraft:green_banner");
  public static final Bed GREEN_BED = (Bed) get("minecraft:green_bed");
  public static final BlockState.Waterloggable<BlockState> GREEN_CANDLE = (BlockState.Waterloggable<BlockState>) get("minecraft:green_candle");
  public static final BlockState GREEN_CANDLE_CAKE = get("minecraft:green_candle_cake");
  public static final BlockState GREEN_CARPET = get("minecraft:green_carpet");
  public static final BlockState GREEN_CONCRETE = get("minecraft:green_concrete");
  public static final BlockState GREEN_CONCRETE_POWDER = get("minecraft:green_concrete_powder");
  public static final BlockState.Facing<BlockState> GREEN_GLAZED_TERRACOTTA = (BlockState.Facing<BlockState>) get("minecraft:green_glazed_terracotta");
  public static final BlockState.Facing<BlockState> GREEN_SHULKER_BOX = (BlockState.Facing<BlockState>) get("minecraft:green_shulker_box");
  public static final BlockState GREEN_STAINED_GLASS = get("minecraft:green_stained_glass");
  public static final BlockState.Waterloggable<BlockState> GREEN_STAINED_GLASS_PANE = (BlockState.Waterloggable<BlockState>) get("minecraft:green_stained_glass_pane");
  public static final BlockState GREEN_TERRACOTTA = get("minecraft:green_terracotta");
  public static final WallBanner GREEN_WALL_BANNER = (WallBanner) get("minecraft:green_wall_banner");
  public static final BlockState GREEN_WOOL = get("minecraft:green_wool");
  public static final Grindstone GRINDSTONE = (Grindstone) get("minecraft:grindstone");
  public static final BlockState.Waterloggable<BlockState> HANGING_ROOTS = (BlockState.Waterloggable<BlockState>) get("minecraft:hanging_roots");
  public static final BlockState.Axis<BlockState> HAY_BLOCK = (BlockState.Axis<BlockState>) get("minecraft:hay_block");
  public static final BlockState HEAVY_WEIGHTED_PRESSURE_PLATE = get("minecraft:heavy_weighted_pressure_plate");
  public static final BlockState HONEY_BLOCK = get("minecraft:honey_block");
  public static final BlockState HONEYCOMB_BLOCK = get("minecraft:honeycomb_block");
  public static final Hopper HOPPER = (Hopper) get("minecraft:hopper");
  public static final BlockState.Waterloggable<BlockState> HORN_CORAL = (BlockState.Waterloggable<BlockState>) get("minecraft:horn_coral");
  public static final BlockState HORN_CORAL_BLOCK = get("minecraft:horn_coral_block");
  public static final BlockState.Waterloggable<BlockState> HORN_CORAL_FAN = (BlockState.Waterloggable<BlockState>) get("minecraft:horn_coral_fan");
  public static final WallFan HORN_CORAL_WALL_FAN = (WallFan) get("minecraft:horn_coral_wall_fan");
  public static final BlockState ICE = get("minecraft:ice");
  public static final BlockState INFESTED_CHISELED_STONE_BRICKS = get("minecraft:infested_chiseled_stone_bricks");
  public static final BlockState INFESTED_COBBLESTONE = get("minecraft:infested_cobblestone");
  public static final BlockState INFESTED_CRACKED_STONE_BRICKS = get("minecraft:infested_cracked_stone_bricks");
  public static final BlockState.Axis<BlockState> INFESTED_DEEPSLATE = (BlockState.Axis<BlockState>) get("minecraft:infested_deepslate");
  public static final BlockState INFESTED_MOSSY_STONE_BRICKS = get("minecraft:infested_mossy_stone_bricks");
  public static final BlockState INFESTED_STONE = get("minecraft:infested_stone");
  public static final BlockState INFESTED_STONE_BRICKS = get("minecraft:infested_stone_bricks");
  public static final BlockState.Waterloggable<BlockState> IRON_BARS = (BlockState.Waterloggable<BlockState>) get("minecraft:iron_bars");
  public static final BlockState IRON_BLOCK = get("minecraft:iron_block");
  public static final Door IRON_DOOR = (Door) get("minecraft:iron_door");
  public static final BlockState IRON_ORE = get("minecraft:iron_ore");
  public static final Trapdoor IRON_TRAPDOOR = (Trapdoor) get("minecraft:iron_trapdoor");
  public static final BlockState.Facing<BlockState> JACK_O_LANTERN = (BlockState.Facing<BlockState>) get("minecraft:jack_o_lantern");
  public static final Jigsaw JIGSAW = (Jigsaw) get("minecraft:jigsaw");
  public static final Jukebox JUKEBOX = (Jukebox) get("minecraft:jukebox");
  public static final Button JUNGLE_BUTTON = (Button) get("minecraft:jungle_button");
  public static final Door JUNGLE_DOOR = (Door) get("minecraft:jungle_door");
  public static final BlockState.Waterloggable<BlockState> JUNGLE_FENCE = (BlockState.Waterloggable<BlockState>) get("minecraft:jungle_fence");
  public static final FenceGate JUNGLE_FENCE_GATE = (FenceGate) get("minecraft:jungle_fence_gate");
  public static final HangingSign JUNGLE_HANGING_SIGN = (HangingSign) get("minecraft:jungle_hanging_sign");
  public static final BlockState.Waterloggable<BlockState> JUNGLE_LEAVES = (BlockState.Waterloggable<BlockState>) get("minecraft:jungle_leaves");
  public static final BlockState.Axis<BlockState> JUNGLE_LOG = (BlockState.Axis<BlockState>) get("minecraft:jungle_log");
  public static final BlockState JUNGLE_PLANKS = get("minecraft:jungle_planks");
  public static final BlockState.Powerable<BlockState> JUNGLE_PRESSURE_PLATE = (BlockState.Powerable<BlockState>) get("minecraft:jungle_pressure_plate");
  public static final BlockState JUNGLE_SAPLING = get("minecraft:jungle_sapling");
  public static final Sign JUNGLE_SIGN = (Sign) get("minecraft:jungle_sign");
  public static final BlockState.Waterloggable<BlockState> JUNGLE_SLAB = (BlockState.Waterloggable<BlockState>) get("minecraft:jungle_slab");
  public static final Stairs JUNGLE_STAIRS = (Stairs) get("minecraft:jungle_stairs");
  public static final Trapdoor JUNGLE_TRAPDOOR = (Trapdoor) get("minecraft:jungle_trapdoor");
  public static final WallHangingSign JUNGLE_WALL_HANGING_SIGN = (WallHangingSign) get("minecraft:jungle_wall_hanging_sign");
  public static final WallSign JUNGLE_WALL_SIGN = (WallSign) get("minecraft:jungle_wall_sign");
  public static final BlockState.Axis<BlockState> JUNGLE_WOOD = (BlockState.Axis<BlockState>) get("minecraft:jungle_wood");
  public static final BlockState.Ageable<BlockState> KELP = (BlockState.Ageable<BlockState>) get("minecraft:kelp");
  public static final BlockState KELP_PLANT = get("minecraft:kelp_plant");
  public static final Ladder LADDER = (Ladder) get("minecraft:ladder");
  public static final BlockState.Waterloggable<BlockState> LANTERN = (BlockState.Waterloggable<BlockState>) get("minecraft:lantern");
  public static final BlockState LAPIS_BLOCK = get("minecraft:lapis_block");
  public static final BlockState LAPIS_ORE = get("minecraft:lapis_ore");
  public static final LargeAmethystBud LARGE_AMETHYST_BUD = (LargeAmethystBud) get("minecraft:large_amethyst_bud");
  public static final BlockState.Half<BlockState> LARGE_FERN = (BlockState.Half<BlockState>) get("minecraft:large_fern");
  public static final BlockState LAVA = get("minecraft:lava");
  public static final BlockState LAVA_CAULDRON = get("minecraft:lava_cauldron");
  public static final Lectern LECTERN = (Lectern) get("minecraft:lectern");
  public static final Lever LEVER = (Lever) get("minecraft:lever");
  public static final BlockState.Waterloggable<BlockState> LIGHT = (BlockState.Waterloggable<BlockState>) get("minecraft:light");
  public static final Banner LIGHT_BLUE_BANNER = (Banner) get("minecraft:light_blue_banner");
  public static final Bed LIGHT_BLUE_BED = (Bed) get("minecraft:light_blue_bed");
  public static final BlockState.Waterloggable<BlockState> LIGHT_BLUE_CANDLE = (BlockState.Waterloggable<BlockState>) get("minecraft:light_blue_candle");
  public static final BlockState LIGHT_BLUE_CANDLE_CAKE = get("minecraft:light_blue_candle_cake");
  public static final BlockState LIGHT_BLUE_CARPET = get("minecraft:light_blue_carpet");
  public static final BlockState LIGHT_BLUE_CONCRETE = get("minecraft:light_blue_concrete");
  public static final BlockState LIGHT_BLUE_CONCRETE_POWDER = get("minecraft:light_blue_concrete_powder");
  public static final BlockState.Facing<BlockState> LIGHT_BLUE_GLAZED_TERRACOTTA = (BlockState.Facing<BlockState>) get("minecraft:light_blue_glazed_terracotta");
  public static final BlockState.Facing<BlockState> LIGHT_BLUE_SHULKER_BOX = (BlockState.Facing<BlockState>) get("minecraft:light_blue_shulker_box");
  public static final BlockState LIGHT_BLUE_STAINED_GLASS = get("minecraft:light_blue_stained_glass");
  public static final BlockState.Waterloggable<BlockState> LIGHT_BLUE_STAINED_GLASS_PANE = (BlockState.Waterloggable<BlockState>) get("minecraft:light_blue_stained_glass_pane");
  public static final BlockState LIGHT_BLUE_TERRACOTTA = get("minecraft:light_blue_terracotta");
  public static final WallBanner LIGHT_BLUE_WALL_BANNER = (WallBanner) get("minecraft:light_blue_wall_banner");
  public static final BlockState LIGHT_BLUE_WOOL = get("minecraft:light_blue_wool");
  public static final Banner LIGHT_GRAY_BANNER = (Banner) get("minecraft:light_gray_banner");
  public static final Bed LIGHT_GRAY_BED = (Bed) get("minecraft:light_gray_bed");
  public static final BlockState.Waterloggable<BlockState> LIGHT_GRAY_CANDLE = (BlockState.Waterloggable<BlockState>) get("minecraft:light_gray_candle");
  public static final BlockState LIGHT_GRAY_CANDLE_CAKE = get("minecraft:light_gray_candle_cake");
  public static final BlockState LIGHT_GRAY_CARPET = get("minecraft:light_gray_carpet");
  public static final BlockState LIGHT_GRAY_CONCRETE = get("minecraft:light_gray_concrete");
  public static final BlockState LIGHT_GRAY_CONCRETE_POWDER = get("minecraft:light_gray_concrete_powder");
  public static final BlockState.Facing<BlockState> LIGHT_GRAY_GLAZED_TERRACOTTA = (BlockState.Facing<BlockState>) get("minecraft:light_gray_glazed_terracotta");
  public static final BlockState.Facing<BlockState> LIGHT_GRAY_SHULKER_BOX = (BlockState.Facing<BlockState>) get("minecraft:light_gray_shulker_box");
  public static final BlockState LIGHT_GRAY_STAINED_GLASS = get("minecraft:light_gray_stained_glass");
  public static final BlockState.Waterloggable<BlockState> LIGHT_GRAY_STAINED_GLASS_PANE = (BlockState.Waterloggable<BlockState>) get("minecraft:light_gray_stained_glass_pane");
  public static final BlockState LIGHT_GRAY_TERRACOTTA = get("minecraft:light_gray_terracotta");
  public static final WallBanner LIGHT_GRAY_WALL_BANNER = (WallBanner) get("minecraft:light_gray_wall_banner");
  public static final BlockState LIGHT_GRAY_WOOL = get("minecraft:light_gray_wool");
  public static final BlockState LIGHT_WEIGHTED_PRESSURE_PLATE = get("minecraft:light_weighted_pressure_plate");
  public static final LightningRod LIGHTNING_ROD = (LightningRod) get("minecraft:lightning_rod");
  public static final BlockState.Half<BlockState> LILAC = (BlockState.Half<BlockState>) get("minecraft:lilac");
  public static final BlockState LILY_OF_THE_VALLEY = get("minecraft:lily_of_the_valley");
  public static final BlockState LILY_PAD = get("minecraft:lily_pad");
  public static final Banner LIME_BANNER = (Banner) get("minecraft:lime_banner");
  public static final Bed LIME_BED = (Bed) get("minecraft:lime_bed");
  public static final BlockState.Waterloggable<BlockState> LIME_CANDLE = (BlockState.Waterloggable<BlockState>) get("minecraft:lime_candle");
  public static final BlockState LIME_CANDLE_CAKE = get("minecraft:lime_candle_cake");
  public static final BlockState LIME_CARPET = get("minecraft:lime_carpet");
  public static final BlockState LIME_CONCRETE = get("minecraft:lime_concrete");
  public static final BlockState LIME_CONCRETE_POWDER = get("minecraft:lime_concrete_powder");
  public static final BlockState.Facing<BlockState> LIME_GLAZED_TERRACOTTA = (BlockState.Facing<BlockState>) get("minecraft:lime_glazed_terracotta");
  public static final BlockState.Facing<BlockState> LIME_SHULKER_BOX = (BlockState.Facing<BlockState>) get("minecraft:lime_shulker_box");
  public static final BlockState LIME_STAINED_GLASS = get("minecraft:lime_stained_glass");
  public static final BlockState.Waterloggable<BlockState> LIME_STAINED_GLASS_PANE = (BlockState.Waterloggable<BlockState>) get("minecraft:lime_stained_glass_pane");
  public static final BlockState LIME_TERRACOTTA = get("minecraft:lime_terracotta");
  public static final WallBanner LIME_WALL_BANNER = (WallBanner) get("minecraft:lime_wall_banner");
  public static final BlockState LIME_WOOL = get("minecraft:lime_wool");
  public static final BlockState LODESTONE = get("minecraft:lodestone");
  public static final BlockState.Facing<BlockState> LOOM = (BlockState.Facing<BlockState>) get("minecraft:loom");
  public static final Banner MAGENTA_BANNER = (Banner) get("minecraft:magenta_banner");
  public static final Bed MAGENTA_BED = (Bed) get("minecraft:magenta_bed");
  public static final BlockState.Waterloggable<BlockState> MAGENTA_CANDLE = (BlockState.Waterloggable<BlockState>) get("minecraft:magenta_candle");
  public static final BlockState MAGENTA_CANDLE_CAKE = get("minecraft:magenta_candle_cake");
  public static final BlockState MAGENTA_CARPET = get("minecraft:magenta_carpet");
  public static final BlockState MAGENTA_CONCRETE = get("minecraft:magenta_concrete");
  public static final BlockState MAGENTA_CONCRETE_POWDER = get("minecraft:magenta_concrete_powder");
  public static final BlockState.Facing<BlockState> MAGENTA_GLAZED_TERRACOTTA = (BlockState.Facing<BlockState>) get("minecraft:magenta_glazed_terracotta");
  public static final BlockState.Facing<BlockState> MAGENTA_SHULKER_BOX = (BlockState.Facing<BlockState>) get("minecraft:magenta_shulker_box");
  public static final BlockState MAGENTA_STAINED_GLASS = get("minecraft:magenta_stained_glass");
  public static final BlockState.Waterloggable<BlockState> MAGENTA_STAINED_GLASS_PANE = (BlockState.Waterloggable<BlockState>) get("minecraft:magenta_stained_glass_pane");
  public static final BlockState MAGENTA_TERRACOTTA = get("minecraft:magenta_terracotta");
  public static final WallBanner MAGENTA_WALL_BANNER = (WallBanner) get("minecraft:magenta_wall_banner");
  public static final BlockState MAGENTA_WOOL = get("minecraft:magenta_wool");
  public static final BlockState MAGMA_BLOCK = get("minecraft:magma_block");
  public static final Button MANGROVE_BUTTON = (Button) get("minecraft:mangrove_button");
  public static final Door MANGROVE_DOOR = (Door) get("minecraft:mangrove_door");
  public static final BlockState.Waterloggable<BlockState> MANGROVE_FENCE = (BlockState.Waterloggable<BlockState>) get("minecraft:mangrove_fence");
  public static final FenceGate MANGROVE_FENCE_GATE = (FenceGate) get("minecraft:mangrove_fence_gate");
  public static final HangingSign MANGROVE_HANGING_SIGN = (HangingSign) get("minecraft:mangrove_hanging_sign");
  public static final BlockState.Waterloggable<BlockState> MANGROVE_LEAVES = (BlockState.Waterloggable<BlockState>) get("minecraft:mangrove_leaves");
  public static final BlockState.Axis<BlockState> MANGROVE_LOG = (BlockState.Axis<BlockState>) get("minecraft:mangrove_log");
  public static final BlockState MANGROVE_PLANKS = get("minecraft:mangrove_planks");
  public static final BlockState.Powerable<BlockState> MANGROVE_PRESSURE_PLATE = (BlockState.Powerable<BlockState>) get("minecraft:mangrove_pressure_plate");
  public static final MangrovePropagule MANGROVE_PROPAGULE = (MangrovePropagule) get("minecraft:mangrove_propagule");
  public static final BlockState.Waterloggable<BlockState> MANGROVE_ROOTS = (BlockState.Waterloggable<BlockState>) get("minecraft:mangrove_roots");
  public static final Sign MANGROVE_SIGN = (Sign) get("minecraft:mangrove_sign");
  public static final BlockState.Waterloggable<BlockState> MANGROVE_SLAB = (BlockState.Waterloggable<BlockState>) get("minecraft:mangrove_slab");
  public static final Stairs MANGROVE_STAIRS = (Stairs) get("minecraft:mangrove_stairs");
  public static final Trapdoor MANGROVE_TRAPDOOR = (Trapdoor) get("minecraft:mangrove_trapdoor");
  public static final WallHangingSign MANGROVE_WALL_HANGING_SIGN = (WallHangingSign) get("minecraft:mangrove_wall_hanging_sign");
  public static final WallSign MANGROVE_WALL_SIGN = (WallSign) get("minecraft:mangrove_wall_sign");
  public static final BlockState.Axis<BlockState> MANGROVE_WOOD = (BlockState.Axis<BlockState>) get("minecraft:mangrove_wood");
  public static final MediumAmethystBud MEDIUM_AMETHYST_BUD = (MediumAmethystBud) get("minecraft:medium_amethyst_bud");
  public static final BlockState MELON = get("minecraft:melon");
  public static final BlockState.Ageable<BlockState> MELON_STEM = (BlockState.Ageable<BlockState>) get("minecraft:melon_stem");
  public static final BlockState MOSS_BLOCK = get("minecraft:moss_block");
  public static final BlockState MOSS_CARPET = get("minecraft:moss_carpet");
  public static final BlockState MOSSY_COBBLESTONE = get("minecraft:mossy_cobblestone");
  public static final BlockState.Waterloggable<BlockState> MOSSY_COBBLESTONE_SLAB = (BlockState.Waterloggable<BlockState>) get("minecraft:mossy_cobblestone_slab");
  public static final Stairs MOSSY_COBBLESTONE_STAIRS = (Stairs) get("minecraft:mossy_cobblestone_stairs");
  public static final BlockState.Waterloggable<BlockState> MOSSY_COBBLESTONE_WALL = (BlockState.Waterloggable<BlockState>) get("minecraft:mossy_cobblestone_wall");
  public static final BlockState.Waterloggable<BlockState> MOSSY_STONE_BRICK_SLAB = (BlockState.Waterloggable<BlockState>) get("minecraft:mossy_stone_brick_slab");
  public static final Stairs MOSSY_STONE_BRICK_STAIRS = (Stairs) get("minecraft:mossy_stone_brick_stairs");
  public static final BlockState.Waterloggable<BlockState> MOSSY_STONE_BRICK_WALL = (BlockState.Waterloggable<BlockState>) get("minecraft:mossy_stone_brick_wall");
  public static final BlockState MOSSY_STONE_BRICKS = get("minecraft:mossy_stone_bricks");
  public static final BlockState.Facing<BlockState> MOVING_PISTON = (BlockState.Facing<BlockState>) get("minecraft:moving_piston");
  public static final BlockState MUD = get("minecraft:mud");
  public static final BlockState.Waterloggable<BlockState> MUD_BRICK_SLAB = (BlockState.Waterloggable<BlockState>) get("minecraft:mud_brick_slab");
  public static final Stairs MUD_BRICK_STAIRS = (Stairs) get("minecraft:mud_brick_stairs");
  public static final BlockState.Waterloggable<BlockState> MUD_BRICK_WALL = (BlockState.Waterloggable<BlockState>) get("minecraft:mud_brick_wall");
  public static final BlockState MUD_BRICKS = get("minecraft:mud_bricks");
  public static final BlockState.Axis<BlockState> MUDDY_MANGROVE_ROOTS = (BlockState.Axis<BlockState>) get("minecraft:muddy_mangrove_roots");
  public static final BlockState MUSHROOM_STEM = get("minecraft:mushroom_stem");
  public static final BlockState.Snowy<BlockState> MYCELIUM = (BlockState.Snowy<BlockState>) get("minecraft:mycelium");
  public static final BlockState.Waterloggable<BlockState> NETHER_BRICK_FENCE = (BlockState.Waterloggable<BlockState>) get("minecraft:nether_brick_fence");
  public static final BlockState.Waterloggable<BlockState> NETHER_BRICK_SLAB = (BlockState.Waterloggable<BlockState>) get("minecraft:nether_brick_slab");
  public static final Stairs NETHER_BRICK_STAIRS = (Stairs) get("minecraft:nether_brick_stairs");
  public static final BlockState.Waterloggable<BlockState> NETHER_BRICK_WALL = (BlockState.Waterloggable<BlockState>) get("minecraft:nether_brick_wall");
  public static final BlockState NETHER_BRICKS = get("minecraft:nether_bricks");
  public static final BlockState NETHER_GOLD_ORE = get("minecraft:nether_gold_ore");
  public static final BlockState.Axis<BlockState> NETHER_PORTAL = (BlockState.Axis<BlockState>) get("minecraft:nether_portal");
  public static final BlockState NETHER_QUARTZ_ORE = get("minecraft:nether_quartz_ore");
  public static final BlockState NETHER_SPROUTS = get("minecraft:nether_sprouts");
  public static final BlockState.Ageable<BlockState> NETHER_WART = (BlockState.Ageable<BlockState>) get("minecraft:nether_wart");
  public static final BlockState NETHER_WART_BLOCK = get("minecraft:nether_wart_block");
  public static final BlockState NETHERITE_BLOCK = get("minecraft:netherite_block");
  public static final BlockState NETHERRACK = get("minecraft:netherrack");
  public static final BlockState.Powerable<BlockState> NOTE_BLOCK = (BlockState.Powerable<BlockState>) get("minecraft:note_block");
  public static final Button OAK_BUTTON = (Button) get("minecraft:oak_button");
  public static final Door OAK_DOOR = (Door) get("minecraft:oak_door");
  public static final BlockState.Waterloggable<BlockState> OAK_FENCE = (BlockState.Waterloggable<BlockState>) get("minecraft:oak_fence");
  public static final FenceGate OAK_FENCE_GATE = (FenceGate) get("minecraft:oak_fence_gate");
  public static final HangingSign OAK_HANGING_SIGN = (HangingSign) get("minecraft:oak_hanging_sign");
  public static final BlockState.Waterloggable<BlockState> OAK_LEAVES = (BlockState.Waterloggable<BlockState>) get("minecraft:oak_leaves");
  public static final BlockState.Axis<BlockState> OAK_LOG = (BlockState.Axis<BlockState>) get("minecraft:oak_log");
  public static final BlockState OAK_PLANKS = get("minecraft:oak_planks");
  public static final BlockState.Powerable<BlockState> OAK_PRESSURE_PLATE = (BlockState.Powerable<BlockState>) get("minecraft:oak_pressure_plate");
  public static final BlockState OAK_SAPLING = get("minecraft:oak_sapling");
  public static final Sign OAK_SIGN = (Sign) get("minecraft:oak_sign");
  public static final BlockState.Waterloggable<BlockState> OAK_SLAB = (BlockState.Waterloggable<BlockState>) get("minecraft:oak_slab");
  public static final Stairs OAK_STAIRS = (Stairs) get("minecraft:oak_stairs");
  public static final Trapdoor OAK_TRAPDOOR = (Trapdoor) get("minecraft:oak_trapdoor");
  public static final WallHangingSign OAK_WALL_HANGING_SIGN = (WallHangingSign) get("minecraft:oak_wall_hanging_sign");
  public static final WallSign OAK_WALL_SIGN = (WallSign) get("minecraft:oak_wall_sign");
  public static final BlockState.Axis<BlockState> OAK_WOOD = (BlockState.Axis<BlockState>) get("minecraft:oak_wood");
  public static final Observer OBSERVER = (Observer) get("minecraft:observer");
  public static final BlockState OBSIDIAN = get("minecraft:obsidian");
  public static final BlockState.Axis<BlockState> OCHRE_FROGLIGHT = (BlockState.Axis<BlockState>) get("minecraft:ochre_froglight");
  public static final Banner ORANGE_BANNER = (Banner) get("minecraft:orange_banner");
  public static final Bed ORANGE_BED = (Bed) get("minecraft:orange_bed");
  public static final BlockState.Waterloggable<BlockState> ORANGE_CANDLE = (BlockState.Waterloggable<BlockState>) get("minecraft:orange_candle");
  public static final BlockState ORANGE_CANDLE_CAKE = get("minecraft:orange_candle_cake");
  public static final BlockState ORANGE_CARPET = get("minecraft:orange_carpet");
  public static final BlockState ORANGE_CONCRETE = get("minecraft:orange_concrete");
  public static final BlockState ORANGE_CONCRETE_POWDER = get("minecraft:orange_concrete_powder");
  public static final BlockState.Facing<BlockState> ORANGE_GLAZED_TERRACOTTA = (BlockState.Facing<BlockState>) get("minecraft:orange_glazed_terracotta");
  public static final BlockState.Facing<BlockState> ORANGE_SHULKER_BOX = (BlockState.Facing<BlockState>) get("minecraft:orange_shulker_box");
  public static final BlockState ORANGE_STAINED_GLASS = get("minecraft:orange_stained_glass");
  public static final BlockState.Waterloggable<BlockState> ORANGE_STAINED_GLASS_PANE = (BlockState.Waterloggable<BlockState>) get("minecraft:orange_stained_glass_pane");
  public static final BlockState ORANGE_TERRACOTTA = get("minecraft:orange_terracotta");
  public static final BlockState ORANGE_TULIP = get("minecraft:orange_tulip");
  public static final WallBanner ORANGE_WALL_BANNER = (WallBanner) get("minecraft:orange_wall_banner");
  public static final BlockState ORANGE_WOOL = get("minecraft:orange_wool");
  public static final BlockState OXEYE_DAISY = get("minecraft:oxeye_daisy");
  public static final BlockState OXIDIZED_CHISELED_COPPER = get("minecraft:oxidized_chiseled_copper");
  public static final BlockState OXIDIZED_COPPER = get("minecraft:oxidized_copper");
  public static final BlockState.Powerable<BlockState> OXIDIZED_COPPER_BULB = (BlockState.Powerable<BlockState>) get("minecraft:oxidized_copper_bulb");
  public static final Door OXIDIZED_COPPER_DOOR = (Door) get("minecraft:oxidized_copper_door");
  public static final BlockState.Waterloggable<BlockState> OXIDIZED_COPPER_GRATE = (BlockState.Waterloggable<BlockState>) get("minecraft:oxidized_copper_grate");
  public static final Trapdoor OXIDIZED_COPPER_TRAPDOOR = (Trapdoor) get("minecraft:oxidized_copper_trapdoor");
  public static final BlockState OXIDIZED_CUT_COPPER = get("minecraft:oxidized_cut_copper");
  public static final BlockState.Waterloggable<BlockState> OXIDIZED_CUT_COPPER_SLAB = (BlockState.Waterloggable<BlockState>) get("minecraft:oxidized_cut_copper_slab");
  public static final Stairs OXIDIZED_CUT_COPPER_STAIRS = (Stairs) get("minecraft:oxidized_cut_copper_stairs");
  public static final BlockState PACKED_ICE = get("minecraft:packed_ice");
  public static final BlockState PACKED_MUD = get("minecraft:packed_mud");
  public static final BlockState.Axis<BlockState> PEARLESCENT_FROGLIGHT = (BlockState.Axis<BlockState>) get("minecraft:pearlescent_froglight");
  public static final BlockState.Half<BlockState> PEONY = (BlockState.Half<BlockState>) get("minecraft:peony");
  public static final BlockState.Waterloggable<BlockState> PETRIFIED_OAK_SLAB = (BlockState.Waterloggable<BlockState>) get("minecraft:petrified_oak_slab");
  public static final Head PIGLIN_HEAD = (Head) get("minecraft:piglin_head");
  public static final WallHead PIGLIN_WALL_HEAD = (WallHead) get("minecraft:piglin_wall_head");
  public static final Banner PINK_BANNER = (Banner) get("minecraft:pink_banner");
  public static final Bed PINK_BED = (Bed) get("minecraft:pink_bed");
  public static final BlockState.Waterloggable<BlockState> PINK_CANDLE = (BlockState.Waterloggable<BlockState>) get("minecraft:pink_candle");
  public static final BlockState PINK_CANDLE_CAKE = get("minecraft:pink_candle_cake");
  public static final BlockState PINK_CARPET = get("minecraft:pink_carpet");
  public static final BlockState PINK_CONCRETE = get("minecraft:pink_concrete");
  public static final BlockState PINK_CONCRETE_POWDER = get("minecraft:pink_concrete_powder");
  public static final BlockState.Facing<BlockState> PINK_GLAZED_TERRACOTTA = (BlockState.Facing<BlockState>) get("minecraft:pink_glazed_terracotta");
  public static final BlockState.Facing<BlockState> PINK_PETALS = (BlockState.Facing<BlockState>) get("minecraft:pink_petals");
  public static final BlockState.Facing<BlockState> PINK_SHULKER_BOX = (BlockState.Facing<BlockState>) get("minecraft:pink_shulker_box");
  public static final BlockState PINK_STAINED_GLASS = get("minecraft:pink_stained_glass");
  public static final BlockState.Waterloggable<BlockState> PINK_STAINED_GLASS_PANE = (BlockState.Waterloggable<BlockState>) get("minecraft:pink_stained_glass_pane");
  public static final BlockState PINK_TERRACOTTA = get("minecraft:pink_terracotta");
  public static final BlockState PINK_TULIP = get("minecraft:pink_tulip");
  public static final WallBanner PINK_WALL_BANNER = (WallBanner) get("minecraft:pink_wall_banner");
  public static final BlockState PINK_WOOL = get("minecraft:pink_wool");
  public static final Piston PISTON = (Piston) get("minecraft:piston");
  public static final Head PISTON_HEAD = (Head) get("minecraft:piston_head");
  public static final PitcherCrop PITCHER_CROP = (PitcherCrop) get("minecraft:pitcher_crop");
  public static final BlockState.Half<BlockState> PITCHER_PLANT = (BlockState.Half<BlockState>) get("minecraft:pitcher_plant");
  public static final Head PLAYER_HEAD = (Head) get("minecraft:player_head");
  public static final WallHead PLAYER_WALL_HEAD = (WallHead) get("minecraft:player_wall_head");
  public static final BlockState.Snowy<BlockState> PODZOL = (BlockState.Snowy<BlockState>) get("minecraft:podzol");
  public static final BlockState.Waterloggable<BlockState> POINTED_DRIPSTONE = (BlockState.Waterloggable<BlockState>) get("minecraft:pointed_dripstone");
  public static final BlockState POLISHED_ANDESITE = get("minecraft:polished_andesite");
  public static final BlockState.Waterloggable<BlockState> POLISHED_ANDESITE_SLAB = (BlockState.Waterloggable<BlockState>) get("minecraft:polished_andesite_slab");
  public static final Stairs POLISHED_ANDESITE_STAIRS = (Stairs) get("minecraft:polished_andesite_stairs");
  public static final BlockState.Axis<BlockState> POLISHED_BASALT = (BlockState.Axis<BlockState>) get("minecraft:polished_basalt");
  public static final BlockState POLISHED_BLACKSTONE = get("minecraft:polished_blackstone");
  public static final BlockState.Waterloggable<BlockState> POLISHED_BLACKSTONE_BRICK_SLAB = (BlockState.Waterloggable<BlockState>) get("minecraft:polished_blackstone_brick_slab");
  public static final Stairs POLISHED_BLACKSTONE_BRICK_STAIRS = (Stairs) get("minecraft:polished_blackstone_brick_stairs");
  public static final BlockState.Waterloggable<BlockState> POLISHED_BLACKSTONE_BRICK_WALL = (BlockState.Waterloggable<BlockState>) get("minecraft:polished_blackstone_brick_wall");
  public static final BlockState POLISHED_BLACKSTONE_BRICKS = get("minecraft:polished_blackstone_bricks");
  public static final Button POLISHED_BLACKSTONE_BUTTON = (Button) get("minecraft:polished_blackstone_button");
  public static final BlockState.Powerable<BlockState> POLISHED_BLACKSTONE_PRESSURE_PLATE = (BlockState.Powerable<BlockState>) get("minecraft:polished_blackstone_pressure_plate");
  public static final BlockState.Waterloggable<BlockState> POLISHED_BLACKSTONE_SLAB = (BlockState.Waterloggable<BlockState>) get("minecraft:polished_blackstone_slab");
  public static final Stairs POLISHED_BLACKSTONE_STAIRS = (Stairs) get("minecraft:polished_blackstone_stairs");
  public static final BlockState.Waterloggable<BlockState> POLISHED_BLACKSTONE_WALL = (BlockState.Waterloggable<BlockState>) get("minecraft:polished_blackstone_wall");
  public static final BlockState POLISHED_DEEPSLATE = get("minecraft:polished_deepslate");
  public static final BlockState.Waterloggable<BlockState> POLISHED_DEEPSLATE_SLAB = (BlockState.Waterloggable<BlockState>) get("minecraft:polished_deepslate_slab");
  public static final Stairs POLISHED_DEEPSLATE_STAIRS = (Stairs) get("minecraft:polished_deepslate_stairs");
  public static final BlockState.Waterloggable<BlockState> POLISHED_DEEPSLATE_WALL = (BlockState.Waterloggable<BlockState>) get("minecraft:polished_deepslate_wall");
  public static final BlockState POLISHED_DIORITE = get("minecraft:polished_diorite");
  public static final BlockState.Waterloggable<BlockState> POLISHED_DIORITE_SLAB = (BlockState.Waterloggable<BlockState>) get("minecraft:polished_diorite_slab");
  public static final Stairs POLISHED_DIORITE_STAIRS = (Stairs) get("minecraft:polished_diorite_stairs");
  public static final BlockState POLISHED_GRANITE = get("minecraft:polished_granite");
  public static final BlockState.Waterloggable<BlockState> POLISHED_GRANITE_SLAB = (BlockState.Waterloggable<BlockState>) get("minecraft:polished_granite_slab");
  public static final Stairs POLISHED_GRANITE_STAIRS = (Stairs) get("minecraft:polished_granite_stairs");
  public static final BlockState POLISHED_TUFF = get("minecraft:polished_tuff");
  public static final BlockState.Waterloggable<BlockState> POLISHED_TUFF_SLAB = (BlockState.Waterloggable<BlockState>) get("minecraft:polished_tuff_slab");
  public static final Stairs POLISHED_TUFF_STAIRS = (Stairs) get("minecraft:polished_tuff_stairs");
  public static final BlockState.Waterloggable<BlockState> POLISHED_TUFF_WALL = (BlockState.Waterloggable<BlockState>) get("minecraft:polished_tuff_wall");
  public static final BlockState POPPY = get("minecraft:poppy");
  public static final BlockState.Ageable<BlockState> POTATOES = (BlockState.Ageable<BlockState>) get("minecraft:potatoes");
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
  public static final PoweredRail POWERED_RAIL = (PoweredRail) get("minecraft:powered_rail");
  public static final BlockState PRISMARINE = get("minecraft:prismarine");
  public static final BlockState.Waterloggable<BlockState> PRISMARINE_BRICK_SLAB = (BlockState.Waterloggable<BlockState>) get("minecraft:prismarine_brick_slab");
  public static final Stairs PRISMARINE_BRICK_STAIRS = (Stairs) get("minecraft:prismarine_brick_stairs");
  public static final BlockState PRISMARINE_BRICKS = get("minecraft:prismarine_bricks");
  public static final BlockState.Waterloggable<BlockState> PRISMARINE_SLAB = (BlockState.Waterloggable<BlockState>) get("minecraft:prismarine_slab");
  public static final Stairs PRISMARINE_STAIRS = (Stairs) get("minecraft:prismarine_stairs");
  public static final BlockState.Waterloggable<BlockState> PRISMARINE_WALL = (BlockState.Waterloggable<BlockState>) get("minecraft:prismarine_wall");
  public static final BlockState PUMPKIN = get("minecraft:pumpkin");
  public static final BlockState.Ageable<BlockState> PUMPKIN_STEM = (BlockState.Ageable<BlockState>) get("minecraft:pumpkin_stem");
  public static final Banner PURPLE_BANNER = (Banner) get("minecraft:purple_banner");
  public static final Bed PURPLE_BED = (Bed) get("minecraft:purple_bed");
  public static final BlockState.Waterloggable<BlockState> PURPLE_CANDLE = (BlockState.Waterloggable<BlockState>) get("minecraft:purple_candle");
  public static final BlockState PURPLE_CANDLE_CAKE = get("minecraft:purple_candle_cake");
  public static final BlockState PURPLE_CARPET = get("minecraft:purple_carpet");
  public static final BlockState PURPLE_CONCRETE = get("minecraft:purple_concrete");
  public static final BlockState PURPLE_CONCRETE_POWDER = get("minecraft:purple_concrete_powder");
  public static final BlockState.Facing<BlockState> PURPLE_GLAZED_TERRACOTTA = (BlockState.Facing<BlockState>) get("minecraft:purple_glazed_terracotta");
  public static final BlockState.Facing<BlockState> PURPLE_SHULKER_BOX = (BlockState.Facing<BlockState>) get("minecraft:purple_shulker_box");
  public static final BlockState PURPLE_STAINED_GLASS = get("minecraft:purple_stained_glass");
  public static final BlockState.Waterloggable<BlockState> PURPLE_STAINED_GLASS_PANE = (BlockState.Waterloggable<BlockState>) get("minecraft:purple_stained_glass_pane");
  public static final BlockState PURPLE_TERRACOTTA = get("minecraft:purple_terracotta");
  public static final WallBanner PURPLE_WALL_BANNER = (WallBanner) get("minecraft:purple_wall_banner");
  public static final BlockState PURPLE_WOOL = get("minecraft:purple_wool");
  public static final BlockState PURPUR_BLOCK = get("minecraft:purpur_block");
  public static final BlockState.Axis<BlockState> PURPUR_PILLAR = (BlockState.Axis<BlockState>) get("minecraft:purpur_pillar");
  public static final BlockState.Waterloggable<BlockState> PURPUR_SLAB = (BlockState.Waterloggable<BlockState>) get("minecraft:purpur_slab");
  public static final Stairs PURPUR_STAIRS = (Stairs) get("minecraft:purpur_stairs");
  public static final BlockState QUARTZ_BLOCK = get("minecraft:quartz_block");
  public static final BlockState QUARTZ_BRICKS = get("minecraft:quartz_bricks");
  public static final BlockState.Axis<BlockState> QUARTZ_PILLAR = (BlockState.Axis<BlockState>) get("minecraft:quartz_pillar");
  public static final BlockState.Waterloggable<BlockState> QUARTZ_SLAB = (BlockState.Waterloggable<BlockState>) get("minecraft:quartz_slab");
  public static final Stairs QUARTZ_STAIRS = (Stairs) get("minecraft:quartz_stairs");
  public static final BlockState.Waterloggable<BlockState> RAIL = (BlockState.Waterloggable<BlockState>) get("minecraft:rail");
  public static final BlockState RAW_COPPER_BLOCK = get("minecraft:raw_copper_block");
  public static final BlockState RAW_GOLD_BLOCK = get("minecraft:raw_gold_block");
  public static final BlockState RAW_IRON_BLOCK = get("minecraft:raw_iron_block");
  public static final Banner RED_BANNER = (Banner) get("minecraft:red_banner");
  public static final Bed RED_BED = (Bed) get("minecraft:red_bed");
  public static final BlockState.Waterloggable<BlockState> RED_CANDLE = (BlockState.Waterloggable<BlockState>) get("minecraft:red_candle");
  public static final BlockState RED_CANDLE_CAKE = get("minecraft:red_candle_cake");
  public static final BlockState RED_CARPET = get("minecraft:red_carpet");
  public static final BlockState RED_CONCRETE = get("minecraft:red_concrete");
  public static final BlockState RED_CONCRETE_POWDER = get("minecraft:red_concrete_powder");
  public static final BlockState.Facing<BlockState> RED_GLAZED_TERRACOTTA = (BlockState.Facing<BlockState>) get("minecraft:red_glazed_terracotta");
  public static final BlockState RED_MUSHROOM = get("minecraft:red_mushroom");
  public static final BlockState RED_MUSHROOM_BLOCK = get("minecraft:red_mushroom_block");
  public static final BlockState.Waterloggable<BlockState> RED_NETHER_BRICK_SLAB = (BlockState.Waterloggable<BlockState>) get("minecraft:red_nether_brick_slab");
  public static final Stairs RED_NETHER_BRICK_STAIRS = (Stairs) get("minecraft:red_nether_brick_stairs");
  public static final BlockState.Waterloggable<BlockState> RED_NETHER_BRICK_WALL = (BlockState.Waterloggable<BlockState>) get("minecraft:red_nether_brick_wall");
  public static final BlockState RED_NETHER_BRICKS = get("minecraft:red_nether_bricks");
  public static final BlockState RED_SAND = get("minecraft:red_sand");
  public static final BlockState RED_SANDSTONE = get("minecraft:red_sandstone");
  public static final BlockState.Waterloggable<BlockState> RED_SANDSTONE_SLAB = (BlockState.Waterloggable<BlockState>) get("minecraft:red_sandstone_slab");
  public static final Stairs RED_SANDSTONE_STAIRS = (Stairs) get("minecraft:red_sandstone_stairs");
  public static final BlockState.Waterloggable<BlockState> RED_SANDSTONE_WALL = (BlockState.Waterloggable<BlockState>) get("minecraft:red_sandstone_wall");
  public static final BlockState.Facing<BlockState> RED_SHULKER_BOX = (BlockState.Facing<BlockState>) get("minecraft:red_shulker_box");
  public static final BlockState RED_STAINED_GLASS = get("minecraft:red_stained_glass");
  public static final BlockState.Waterloggable<BlockState> RED_STAINED_GLASS_PANE = (BlockState.Waterloggable<BlockState>) get("minecraft:red_stained_glass_pane");
  public static final BlockState RED_TERRACOTTA = get("minecraft:red_terracotta");
  public static final BlockState RED_TULIP = get("minecraft:red_tulip");
  public static final WallBanner RED_WALL_BANNER = (WallBanner) get("minecraft:red_wall_banner");
  public static final BlockState RED_WOOL = get("minecraft:red_wool");
  public static final BlockState REDSTONE_BLOCK = get("minecraft:redstone_block");
  public static final BlockState REDSTONE_LAMP = get("minecraft:redstone_lamp");
  public static final BlockState REDSTONE_ORE = get("minecraft:redstone_ore");
  public static final BlockState REDSTONE_TORCH = get("minecraft:redstone_torch");
  public static final BlockState.Facing<BlockState> REDSTONE_WALL_TORCH = (BlockState.Facing<BlockState>) get("minecraft:redstone_wall_torch");
  public static final BlockState REDSTONE_WIRE = get("minecraft:redstone_wire");
  public static final BlockState REINFORCED_DEEPSLATE = get("minecraft:reinforced_deepslate");
  public static final Repeater REPEATER = (Repeater) get("minecraft:repeater");
  public static final BlockState.Facing<BlockState> REPEATING_COMMAND_BLOCK = (BlockState.Facing<BlockState>) get("minecraft:repeating_command_block");
  public static final BlockState RESPAWN_ANCHOR = get("minecraft:respawn_anchor");
  public static final BlockState ROOTED_DIRT = get("minecraft:rooted_dirt");
  public static final BlockState.Half<BlockState> ROSE_BUSH = (BlockState.Half<BlockState>) get("minecraft:rose_bush");
  public static final BlockState SAND = get("minecraft:sand");
  public static final BlockState SANDSTONE = get("minecraft:sandstone");
  public static final BlockState.Waterloggable<BlockState> SANDSTONE_SLAB = (BlockState.Waterloggable<BlockState>) get("minecraft:sandstone_slab");
  public static final Stairs SANDSTONE_STAIRS = (Stairs) get("minecraft:sandstone_stairs");
  public static final BlockState.Waterloggable<BlockState> SANDSTONE_WALL = (BlockState.Waterloggable<BlockState>) get("minecraft:sandstone_wall");
  public static final BlockState.Waterloggable<BlockState> SCAFFOLDING = (BlockState.Waterloggable<BlockState>) get("minecraft:scaffolding");
  public static final BlockState SCULK = get("minecraft:sculk");
  public static final SculkCatalyst SCULK_CATALYST = (SculkCatalyst) get("minecraft:sculk_catalyst");
  public static final SculkSensor SCULK_SENSOR = (SculkSensor) get("minecraft:sculk_sensor");
  public static final SculkShrieker SCULK_SHRIEKER = (SculkShrieker) get("minecraft:sculk_shrieker");
  public static final BlockState.Waterloggable<BlockState> SCULK_VEIN = (BlockState.Waterloggable<BlockState>) get("minecraft:sculk_vein");
  public static final BlockState SEA_LANTERN = get("minecraft:sea_lantern");
  public static final BlockState.Waterloggable<BlockState> SEA_PICKLE = (BlockState.Waterloggable<BlockState>) get("minecraft:sea_pickle");
  public static final BlockState SEAGRASS = get("minecraft:seagrass");
  public static final BlockState SHORT_GRASS = get("minecraft:short_grass");
  public static final BlockState SHROOMLIGHT = get("minecraft:shroomlight");
  public static final ShulkerBox SHULKER_BOX = (ShulkerBox) get("minecraft:shulker_box");
  public static final Head SKELETON_SKULL = (Head) get("minecraft:skeleton_skull");
  public static final WallHead SKELETON_WALL_SKULL = (WallHead) get("minecraft:skeleton_wall_skull");
  public static final BlockState SLIME_BLOCK = get("minecraft:slime_block");
  public static final SmallAmethystBud SMALL_AMETHYST_BUD = (SmallAmethystBud) get("minecraft:small_amethyst_bud");
  public static final SmallDripleaf SMALL_DRIPLEAF = (SmallDripleaf) get("minecraft:small_dripleaf");
  public static final BlockState SMITHING_TABLE = get("minecraft:smithing_table");
  public static final Smoker SMOKER = (Smoker) get("minecraft:smoker");
  public static final BlockState SMOOTH_BASALT = get("minecraft:smooth_basalt");
  public static final BlockState SMOOTH_QUARTZ = get("minecraft:smooth_quartz");
  public static final BlockState.Waterloggable<BlockState> SMOOTH_QUARTZ_SLAB = (BlockState.Waterloggable<BlockState>) get("minecraft:smooth_quartz_slab");
  public static final Stairs SMOOTH_QUARTZ_STAIRS = (Stairs) get("minecraft:smooth_quartz_stairs");
  public static final BlockState SMOOTH_RED_SANDSTONE = get("minecraft:smooth_red_sandstone");
  public static final BlockState.Waterloggable<BlockState> SMOOTH_RED_SANDSTONE_SLAB = (BlockState.Waterloggable<BlockState>) get("minecraft:smooth_red_sandstone_slab");
  public static final Stairs SMOOTH_RED_SANDSTONE_STAIRS = (Stairs) get("minecraft:smooth_red_sandstone_stairs");
  public static final BlockState SMOOTH_SANDSTONE = get("minecraft:smooth_sandstone");
  public static final BlockState.Waterloggable<BlockState> SMOOTH_SANDSTONE_SLAB = (BlockState.Waterloggable<BlockState>) get("minecraft:smooth_sandstone_slab");
  public static final Stairs SMOOTH_SANDSTONE_STAIRS = (Stairs) get("minecraft:smooth_sandstone_stairs");
  public static final BlockState SMOOTH_STONE = get("minecraft:smooth_stone");
  public static final BlockState.Waterloggable<BlockState> SMOOTH_STONE_SLAB = (BlockState.Waterloggable<BlockState>) get("minecraft:smooth_stone_slab");
  public static final BlockState SNIFFER_EGG = get("minecraft:sniffer_egg");
  public static final BlockState SNOW = get("minecraft:snow");
  public static final BlockState SNOW_BLOCK = get("minecraft:snow_block");
  public static final SoulCampfire SOUL_CAMPFIRE = (SoulCampfire) get("minecraft:soul_campfire");
  public static final BlockState SOUL_FIRE = get("minecraft:soul_fire");
  public static final BlockState.Waterloggable<BlockState> SOUL_LANTERN = (BlockState.Waterloggable<BlockState>) get("minecraft:soul_lantern");
  public static final BlockState SOUL_SAND = get("minecraft:soul_sand");
  public static final BlockState SOUL_SOIL = get("minecraft:soul_soil");
  public static final BlockState SOUL_TORCH = get("minecraft:soul_torch");
  public static final BlockState.Facing<BlockState> SOUL_WALL_TORCH = (BlockState.Facing<BlockState>) get("minecraft:soul_wall_torch");
  public static final BlockState SPAWNER = get("minecraft:spawner");
  public static final BlockState SPONGE = get("minecraft:sponge");
  public static final BlockState SPORE_BLOSSOM = get("minecraft:spore_blossom");
  public static final Button SPRUCE_BUTTON = (Button) get("minecraft:spruce_button");
  public static final Door SPRUCE_DOOR = (Door) get("minecraft:spruce_door");
  public static final BlockState.Waterloggable<BlockState> SPRUCE_FENCE = (BlockState.Waterloggable<BlockState>) get("minecraft:spruce_fence");
  public static final FenceGate SPRUCE_FENCE_GATE = (FenceGate) get("minecraft:spruce_fence_gate");
  public static final HangingSign SPRUCE_HANGING_SIGN = (HangingSign) get("minecraft:spruce_hanging_sign");
  public static final BlockState.Waterloggable<BlockState> SPRUCE_LEAVES = (BlockState.Waterloggable<BlockState>) get("minecraft:spruce_leaves");
  public static final BlockState.Axis<BlockState> SPRUCE_LOG = (BlockState.Axis<BlockState>) get("minecraft:spruce_log");
  public static final BlockState SPRUCE_PLANKS = get("minecraft:spruce_planks");
  public static final BlockState.Powerable<BlockState> SPRUCE_PRESSURE_PLATE = (BlockState.Powerable<BlockState>) get("minecraft:spruce_pressure_plate");
  public static final BlockState SPRUCE_SAPLING = get("minecraft:spruce_sapling");
  public static final Sign SPRUCE_SIGN = (Sign) get("minecraft:spruce_sign");
  public static final BlockState.Waterloggable<BlockState> SPRUCE_SLAB = (BlockState.Waterloggable<BlockState>) get("minecraft:spruce_slab");
  public static final Stairs SPRUCE_STAIRS = (Stairs) get("minecraft:spruce_stairs");
  public static final Trapdoor SPRUCE_TRAPDOOR = (Trapdoor) get("minecraft:spruce_trapdoor");
  public static final WallHangingSign SPRUCE_WALL_HANGING_SIGN = (WallHangingSign) get("minecraft:spruce_wall_hanging_sign");
  public static final WallSign SPRUCE_WALL_SIGN = (WallSign) get("minecraft:spruce_wall_sign");
  public static final BlockState.Axis<BlockState> SPRUCE_WOOD = (BlockState.Axis<BlockState>) get("minecraft:spruce_wood");
  public static final BlockState.Facing<BlockState> STICKY_PISTON = (BlockState.Facing<BlockState>) get("minecraft:sticky_piston");
  public static final BlockState STONE = get("minecraft:stone");
  public static final BlockState.Waterloggable<BlockState> STONE_BRICK_SLAB = (BlockState.Waterloggable<BlockState>) get("minecraft:stone_brick_slab");
  public static final Stairs STONE_BRICK_STAIRS = (Stairs) get("minecraft:stone_brick_stairs");
  public static final BlockState.Waterloggable<BlockState> STONE_BRICK_WALL = (BlockState.Waterloggable<BlockState>) get("minecraft:stone_brick_wall");
  public static final BlockState STONE_BRICKS = get("minecraft:stone_bricks");
  public static final Button STONE_BUTTON = (Button) get("minecraft:stone_button");
  public static final BlockState.Powerable<BlockState> STONE_PRESSURE_PLATE = (BlockState.Powerable<BlockState>) get("minecraft:stone_pressure_plate");
  public static final BlockState.Waterloggable<BlockState> STONE_SLAB = (BlockState.Waterloggable<BlockState>) get("minecraft:stone_slab");
  public static final Stairs STONE_STAIRS = (Stairs) get("minecraft:stone_stairs");
  public static final BlockState.Facing<BlockState> STONECUTTER = (BlockState.Facing<BlockState>) get("minecraft:stonecutter");
  public static final BlockState.Axis<BlockState> STRIPPED_ACACIA_LOG = (BlockState.Axis<BlockState>) get("minecraft:stripped_acacia_log");
  public static final BlockState.Axis<BlockState> STRIPPED_ACACIA_WOOD = (BlockState.Axis<BlockState>) get("minecraft:stripped_acacia_wood");
  public static final BlockState.Axis<BlockState> STRIPPED_BAMBOO_BLOCK = (BlockState.Axis<BlockState>) get("minecraft:stripped_bamboo_block");
  public static final BlockState.Axis<BlockState> STRIPPED_BIRCH_LOG = (BlockState.Axis<BlockState>) get("minecraft:stripped_birch_log");
  public static final BlockState.Axis<BlockState> STRIPPED_BIRCH_WOOD = (BlockState.Axis<BlockState>) get("minecraft:stripped_birch_wood");
  public static final BlockState.Axis<BlockState> STRIPPED_CHERRY_LOG = (BlockState.Axis<BlockState>) get("minecraft:stripped_cherry_log");
  public static final BlockState.Axis<BlockState> STRIPPED_CHERRY_WOOD = (BlockState.Axis<BlockState>) get("minecraft:stripped_cherry_wood");
  public static final BlockState.Axis<BlockState> STRIPPED_CRIMSON_HYPHAE = (BlockState.Axis<BlockState>) get("minecraft:stripped_crimson_hyphae");
  public static final BlockState.Axis<BlockState> STRIPPED_CRIMSON_STEM = (BlockState.Axis<BlockState>) get("minecraft:stripped_crimson_stem");
  public static final BlockState.Axis<BlockState> STRIPPED_DARK_OAK_LOG = (BlockState.Axis<BlockState>) get("minecraft:stripped_dark_oak_log");
  public static final BlockState.Axis<BlockState> STRIPPED_DARK_OAK_WOOD = (BlockState.Axis<BlockState>) get("minecraft:stripped_dark_oak_wood");
  public static final BlockState.Axis<BlockState> STRIPPED_JUNGLE_LOG = (BlockState.Axis<BlockState>) get("minecraft:stripped_jungle_log");
  public static final BlockState.Axis<BlockState> STRIPPED_JUNGLE_WOOD = (BlockState.Axis<BlockState>) get("minecraft:stripped_jungle_wood");
  public static final BlockState.Axis<BlockState> STRIPPED_MANGROVE_LOG = (BlockState.Axis<BlockState>) get("minecraft:stripped_mangrove_log");
  public static final BlockState.Axis<BlockState> STRIPPED_MANGROVE_WOOD = (BlockState.Axis<BlockState>) get("minecraft:stripped_mangrove_wood");
  public static final BlockState.Axis<BlockState> STRIPPED_OAK_LOG = (BlockState.Axis<BlockState>) get("minecraft:stripped_oak_log");
  public static final BlockState.Axis<BlockState> STRIPPED_OAK_WOOD = (BlockState.Axis<BlockState>) get("minecraft:stripped_oak_wood");
  public static final BlockState.Axis<BlockState> STRIPPED_SPRUCE_LOG = (BlockState.Axis<BlockState>) get("minecraft:stripped_spruce_log");
  public static final BlockState.Axis<BlockState> STRIPPED_SPRUCE_WOOD = (BlockState.Axis<BlockState>) get("minecraft:stripped_spruce_wood");
  public static final BlockState.Axis<BlockState> STRIPPED_WARPED_HYPHAE = (BlockState.Axis<BlockState>) get("minecraft:stripped_warped_hyphae");
  public static final BlockState.Axis<BlockState> STRIPPED_WARPED_STEM = (BlockState.Axis<BlockState>) get("minecraft:stripped_warped_stem");
  public static final StructureBlock STRUCTURE_BLOCK = (StructureBlock) get("minecraft:structure_block");
  public static final BlockState STRUCTURE_VOID = get("minecraft:structure_void");
  public static final BlockState.Ageable<BlockState> SUGAR_CANE = (BlockState.Ageable<BlockState>) get("minecraft:sugar_cane");
  public static final BlockState.Half<BlockState> SUNFLOWER = (BlockState.Half<BlockState>) get("minecraft:sunflower");
  public static final BlockState SUSPICIOUS_GRAVEL = get("minecraft:suspicious_gravel");
  public static final BlockState SUSPICIOUS_SAND = get("minecraft:suspicious_sand");
  public static final BlockState.Ageable<BlockState> SWEET_BERRY_BUSH = (BlockState.Ageable<BlockState>) get("minecraft:sweet_berry_bush");
  public static final BlockState.Half<BlockState> TALL_GRASS = (BlockState.Half<BlockState>) get("minecraft:tall_grass");
  public static final BlockState.Half<BlockState> TALL_SEAGRASS = (BlockState.Half<BlockState>) get("minecraft:tall_seagrass");
  public static final BlockState TARGET = get("minecraft:target");
  public static final BlockState TERRACOTTA = get("minecraft:terracotta");
  public static final BlockState TINTED_GLASS = get("minecraft:tinted_glass");
  public static final BlockState TNT = get("minecraft:tnt");
  public static final BlockState TORCH = get("minecraft:torch");
  public static final BlockState TORCHFLOWER = get("minecraft:torchflower");
  public static final BlockState.Ageable<BlockState> TORCHFLOWER_CROP = (BlockState.Ageable<BlockState>) get("minecraft:torchflower_crop");
  public static final TrappedChest TRAPPED_CHEST = (TrappedChest) get("minecraft:trapped_chest");
  public static final BlockState TRIAL_SPAWNER = get("minecraft:trial_spawner");
  public static final BlockState.Powerable<BlockState> TRIPWIRE = (BlockState.Powerable<BlockState>) get("minecraft:tripwire");
  public static final TripwireHook TRIPWIRE_HOOK = (TripwireHook) get("minecraft:tripwire_hook");
  public static final BlockState.Waterloggable<BlockState> TUBE_CORAL = (BlockState.Waterloggable<BlockState>) get("minecraft:tube_coral");
  public static final BlockState TUBE_CORAL_BLOCK = get("minecraft:tube_coral_block");
  public static final BlockState.Waterloggable<BlockState> TUBE_CORAL_FAN = (BlockState.Waterloggable<BlockState>) get("minecraft:tube_coral_fan");
  public static final WallFan TUBE_CORAL_WALL_FAN = (WallFan) get("minecraft:tube_coral_wall_fan");
  public static final BlockState TUFF = get("minecraft:tuff");
  public static final BlockState.Waterloggable<BlockState> TUFF_BRICK_SLAB = (BlockState.Waterloggable<BlockState>) get("minecraft:tuff_brick_slab");
  public static final Stairs TUFF_BRICK_STAIRS = (Stairs) get("minecraft:tuff_brick_stairs");
  public static final BlockState.Waterloggable<BlockState> TUFF_BRICK_WALL = (BlockState.Waterloggable<BlockState>) get("minecraft:tuff_brick_wall");
  public static final BlockState TUFF_BRICKS = get("minecraft:tuff_bricks");
  public static final BlockState.Waterloggable<BlockState> TUFF_SLAB = (BlockState.Waterloggable<BlockState>) get("minecraft:tuff_slab");
  public static final Stairs TUFF_STAIRS = (Stairs) get("minecraft:tuff_stairs");
  public static final BlockState.Waterloggable<BlockState> TUFF_WALL = (BlockState.Waterloggable<BlockState>) get("minecraft:tuff_wall");
  public static final BlockState TURTLE_EGG = get("minecraft:turtle_egg");
  public static final BlockState.Ageable<BlockState> TWISTING_VINES = (BlockState.Ageable<BlockState>) get("minecraft:twisting_vines");
  public static final BlockState TWISTING_VINES_PLANT = get("minecraft:twisting_vines_plant");
  public static final BlockState.Axis<BlockState> VERDANT_FROGLIGHT = (BlockState.Axis<BlockState>) get("minecraft:verdant_froglight");
  public static final BlockState VINE = get("minecraft:vine");
  public static final BlockState VOID_AIR = get("minecraft:void_air");
  public static final BlockState.Facing<BlockState> WALL_TORCH = (BlockState.Facing<BlockState>) get("minecraft:wall_torch");
  public static final Button WARPED_BUTTON = (Button) get("minecraft:warped_button");
  public static final Door WARPED_DOOR = (Door) get("minecraft:warped_door");
  public static final BlockState.Waterloggable<BlockState> WARPED_FENCE = (BlockState.Waterloggable<BlockState>) get("minecraft:warped_fence");
  public static final FenceGate WARPED_FENCE_GATE = (FenceGate) get("minecraft:warped_fence_gate");
  public static final BlockState WARPED_FUNGUS = get("minecraft:warped_fungus");
  public static final HangingSign WARPED_HANGING_SIGN = (HangingSign) get("minecraft:warped_hanging_sign");
  public static final BlockState.Axis<BlockState> WARPED_HYPHAE = (BlockState.Axis<BlockState>) get("minecraft:warped_hyphae");
  public static final BlockState WARPED_NYLIUM = get("minecraft:warped_nylium");
  public static final BlockState WARPED_PLANKS = get("minecraft:warped_planks");
  public static final BlockState.Powerable<BlockState> WARPED_PRESSURE_PLATE = (BlockState.Powerable<BlockState>) get("minecraft:warped_pressure_plate");
  public static final BlockState WARPED_ROOTS = get("minecraft:warped_roots");
  public static final Sign WARPED_SIGN = (Sign) get("minecraft:warped_sign");
  public static final BlockState.Waterloggable<BlockState> WARPED_SLAB = (BlockState.Waterloggable<BlockState>) get("minecraft:warped_slab");
  public static final Stairs WARPED_STAIRS = (Stairs) get("minecraft:warped_stairs");
  public static final BlockState.Axis<BlockState> WARPED_STEM = (BlockState.Axis<BlockState>) get("minecraft:warped_stem");
  public static final Trapdoor WARPED_TRAPDOOR = (Trapdoor) get("minecraft:warped_trapdoor");
  public static final WallHangingSign WARPED_WALL_HANGING_SIGN = (WallHangingSign) get("minecraft:warped_wall_hanging_sign");
  public static final WallSign WARPED_WALL_SIGN = (WallSign) get("minecraft:warped_wall_sign");
  public static final BlockState WARPED_WART_BLOCK = get("minecraft:warped_wart_block");
  public static final BlockState WATER = get("minecraft:water");
  public static final BlockState WATER_CAULDRON = get("minecraft:water_cauldron");
  public static final BlockState WAXED_CHISELED_COPPER = get("minecraft:waxed_chiseled_copper");
  public static final BlockState WAXED_COPPER_BLOCK = get("minecraft:waxed_copper_block");
  public static final BlockState.Powerable<BlockState> WAXED_COPPER_BULB = (BlockState.Powerable<BlockState>) get("minecraft:waxed_copper_bulb");
  public static final Door WAXED_COPPER_DOOR = (Door) get("minecraft:waxed_copper_door");
  public static final BlockState.Waterloggable<BlockState> WAXED_COPPER_GRATE = (BlockState.Waterloggable<BlockState>) get("minecraft:waxed_copper_grate");
  public static final Trapdoor WAXED_COPPER_TRAPDOOR = (Trapdoor) get("minecraft:waxed_copper_trapdoor");
  public static final BlockState WAXED_CUT_COPPER = get("minecraft:waxed_cut_copper");
  public static final BlockState.Waterloggable<BlockState> WAXED_CUT_COPPER_SLAB = (BlockState.Waterloggable<BlockState>) get("minecraft:waxed_cut_copper_slab");
  public static final Stairs WAXED_CUT_COPPER_STAIRS = (Stairs) get("minecraft:waxed_cut_copper_stairs");
  public static final BlockState WAXED_EXPOSED_CHISELED_COPPER = get("minecraft:waxed_exposed_chiseled_copper");
  public static final BlockState WAXED_EXPOSED_COPPER = get("minecraft:waxed_exposed_copper");
  public static final BlockState.Powerable<BlockState> WAXED_EXPOSED_COPPER_BULB = (BlockState.Powerable<BlockState>) get("minecraft:waxed_exposed_copper_bulb");
  public static final Door WAXED_EXPOSED_COPPER_DOOR = (Door) get("minecraft:waxed_exposed_copper_door");
  public static final BlockState.Waterloggable<BlockState> WAXED_EXPOSED_COPPER_GRATE = (BlockState.Waterloggable<BlockState>) get("minecraft:waxed_exposed_copper_grate");
  public static final Trapdoor WAXED_EXPOSED_COPPER_TRAPDOOR = (Trapdoor) get("minecraft:waxed_exposed_copper_trapdoor");
  public static final BlockState WAXED_EXPOSED_CUT_COPPER = get("minecraft:waxed_exposed_cut_copper");
  public static final BlockState.Waterloggable<BlockState> WAXED_EXPOSED_CUT_COPPER_SLAB = (BlockState.Waterloggable<BlockState>) get("minecraft:waxed_exposed_cut_copper_slab");
  public static final Stairs WAXED_EXPOSED_CUT_COPPER_STAIRS = (Stairs) get("minecraft:waxed_exposed_cut_copper_stairs");
  public static final BlockState WAXED_OXIDIZED_CHISELED_COPPER = get("minecraft:waxed_oxidized_chiseled_copper");
  public static final BlockState WAXED_OXIDIZED_COPPER = get("minecraft:waxed_oxidized_copper");
  public static final BlockState.Powerable<BlockState> WAXED_OXIDIZED_COPPER_BULB = (BlockState.Powerable<BlockState>) get("minecraft:waxed_oxidized_copper_bulb");
  public static final Door WAXED_OXIDIZED_COPPER_DOOR = (Door) get("minecraft:waxed_oxidized_copper_door");
  public static final BlockState.Waterloggable<BlockState> WAXED_OXIDIZED_COPPER_GRATE = (BlockState.Waterloggable<BlockState>) get("minecraft:waxed_oxidized_copper_grate");
  public static final Trapdoor WAXED_OXIDIZED_COPPER_TRAPDOOR = (Trapdoor) get("minecraft:waxed_oxidized_copper_trapdoor");
  public static final BlockState WAXED_OXIDIZED_CUT_COPPER = get("minecraft:waxed_oxidized_cut_copper");
  public static final BlockState.Waterloggable<BlockState> WAXED_OXIDIZED_CUT_COPPER_SLAB = (BlockState.Waterloggable<BlockState>) get("minecraft:waxed_oxidized_cut_copper_slab");
  public static final Stairs WAXED_OXIDIZED_CUT_COPPER_STAIRS = (Stairs) get("minecraft:waxed_oxidized_cut_copper_stairs");
  public static final BlockState WAXED_WEATHERED_CHISELED_COPPER = get("minecraft:waxed_weathered_chiseled_copper");
  public static final BlockState WAXED_WEATHERED_COPPER = get("minecraft:waxed_weathered_copper");
  public static final BlockState.Powerable<BlockState> WAXED_WEATHERED_COPPER_BULB = (BlockState.Powerable<BlockState>) get("minecraft:waxed_weathered_copper_bulb");
  public static final Door WAXED_WEATHERED_COPPER_DOOR = (Door) get("minecraft:waxed_weathered_copper_door");
  public static final BlockState.Waterloggable<BlockState> WAXED_WEATHERED_COPPER_GRATE = (BlockState.Waterloggable<BlockState>) get("minecraft:waxed_weathered_copper_grate");
  public static final Trapdoor WAXED_WEATHERED_COPPER_TRAPDOOR = (Trapdoor) get("minecraft:waxed_weathered_copper_trapdoor");
  public static final BlockState WAXED_WEATHERED_CUT_COPPER = get("minecraft:waxed_weathered_cut_copper");
  public static final BlockState.Waterloggable<BlockState> WAXED_WEATHERED_CUT_COPPER_SLAB = (BlockState.Waterloggable<BlockState>) get("minecraft:waxed_weathered_cut_copper_slab");
  public static final Stairs WAXED_WEATHERED_CUT_COPPER_STAIRS = (Stairs) get("minecraft:waxed_weathered_cut_copper_stairs");
  public static final BlockState WEATHERED_CHISELED_COPPER = get("minecraft:weathered_chiseled_copper");
  public static final BlockState WEATHERED_COPPER = get("minecraft:weathered_copper");
  public static final BlockState.Powerable<BlockState> WEATHERED_COPPER_BULB = (BlockState.Powerable<BlockState>) get("minecraft:weathered_copper_bulb");
  public static final Door WEATHERED_COPPER_DOOR = (Door) get("minecraft:weathered_copper_door");
  public static final BlockState.Waterloggable<BlockState> WEATHERED_COPPER_GRATE = (BlockState.Waterloggable<BlockState>) get("minecraft:weathered_copper_grate");
  public static final Trapdoor WEATHERED_COPPER_TRAPDOOR = (Trapdoor) get("minecraft:weathered_copper_trapdoor");
  public static final BlockState WEATHERED_CUT_COPPER = get("minecraft:weathered_cut_copper");
  public static final BlockState.Waterloggable<BlockState> WEATHERED_CUT_COPPER_SLAB = (BlockState.Waterloggable<BlockState>) get("minecraft:weathered_cut_copper_slab");
  public static final Stairs WEATHERED_CUT_COPPER_STAIRS = (Stairs) get("minecraft:weathered_cut_copper_stairs");
  public static final BlockState.Ageable<BlockState> WEEPING_VINES = (BlockState.Ageable<BlockState>) get("minecraft:weeping_vines");
  public static final BlockState WEEPING_VINES_PLANT = get("minecraft:weeping_vines_plant");
  public static final BlockState WET_SPONGE = get("minecraft:wet_sponge");
  public static final BlockState.Ageable<BlockState> WHEAT = (BlockState.Ageable<BlockState>) get("minecraft:wheat");
  public static final Banner WHITE_BANNER = (Banner) get("minecraft:white_banner");
  public static final Bed WHITE_BED = (Bed) get("minecraft:white_bed");
  public static final BlockState.Waterloggable<BlockState> WHITE_CANDLE = (BlockState.Waterloggable<BlockState>) get("minecraft:white_candle");
  public static final BlockState WHITE_CANDLE_CAKE = get("minecraft:white_candle_cake");
  public static final BlockState WHITE_CARPET = get("minecraft:white_carpet");
  public static final BlockState WHITE_CONCRETE = get("minecraft:white_concrete");
  public static final BlockState WHITE_CONCRETE_POWDER = get("minecraft:white_concrete_powder");
  public static final BlockState.Facing<BlockState> WHITE_GLAZED_TERRACOTTA = (BlockState.Facing<BlockState>) get("minecraft:white_glazed_terracotta");
  public static final BlockState.Facing<BlockState> WHITE_SHULKER_BOX = (BlockState.Facing<BlockState>) get("minecraft:white_shulker_box");
  public static final BlockState WHITE_STAINED_GLASS = get("minecraft:white_stained_glass");
  public static final BlockState.Waterloggable<BlockState> WHITE_STAINED_GLASS_PANE = (BlockState.Waterloggable<BlockState>) get("minecraft:white_stained_glass_pane");
  public static final BlockState WHITE_TERRACOTTA = get("minecraft:white_terracotta");
  public static final BlockState WHITE_TULIP = get("minecraft:white_tulip");
  public static final WallBanner WHITE_WALL_BANNER = (WallBanner) get("minecraft:white_wall_banner");
  public static final BlockState WHITE_WOOL = get("minecraft:white_wool");
  public static final BlockState WITHER_ROSE = get("minecraft:wither_rose");
  public static final Head WITHER_SKELETON_SKULL = (Head) get("minecraft:wither_skeleton_skull");
  public static final WallHead WITHER_SKELETON_WALL_SKULL = (WallHead) get("minecraft:wither_skeleton_wall_skull");
  public static final Banner YELLOW_BANNER = (Banner) get("minecraft:yellow_banner");
  public static final Bed YELLOW_BED = (Bed) get("minecraft:yellow_bed");
  public static final BlockState.Waterloggable<BlockState> YELLOW_CANDLE = (BlockState.Waterloggable<BlockState>) get("minecraft:yellow_candle");
  public static final BlockState YELLOW_CANDLE_CAKE = get("minecraft:yellow_candle_cake");
  public static final BlockState YELLOW_CARPET = get("minecraft:yellow_carpet");
  public static final BlockState YELLOW_CONCRETE = get("minecraft:yellow_concrete");
  public static final BlockState YELLOW_CONCRETE_POWDER = get("minecraft:yellow_concrete_powder");
  public static final BlockState.Facing<BlockState> YELLOW_GLAZED_TERRACOTTA = (BlockState.Facing<BlockState>) get("minecraft:yellow_glazed_terracotta");
  public static final BlockState.Facing<BlockState> YELLOW_SHULKER_BOX = (BlockState.Facing<BlockState>) get("minecraft:yellow_shulker_box");
  public static final BlockState YELLOW_STAINED_GLASS = get("minecraft:yellow_stained_glass");
  public static final BlockState.Waterloggable<BlockState> YELLOW_STAINED_GLASS_PANE = (BlockState.Waterloggable<BlockState>) get("minecraft:yellow_stained_glass_pane");
  public static final BlockState YELLOW_TERRACOTTA = get("minecraft:yellow_terracotta");
  public static final WallBanner YELLOW_WALL_BANNER = (WallBanner) get("minecraft:yellow_wall_banner");
  public static final BlockState YELLOW_WOOL = get("minecraft:yellow_wool");
  public static final Head ZOMBIE_HEAD = (Head) get("minecraft:zombie_head");
  public static final WallHead ZOMBIE_WALL_HEAD = (WallHead) get("minecraft:zombie_wall_head");
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
