package io.github.sculkpowered.server.code.generator;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

final class BlockGenerator extends Generator {

  private static final List<String> DO_NOT_CREATE = List.of("Bed");
  private static final Map<String, String> PROPERTY_TO_CLASS = Map.of(
      "waterlogged", "Waterloggable",
      "powered", "Powerable",
      "snowy", "Snowy",
      "face", "Face",
      "facing", "Facing",
      "half", "Half",
      "age", "Ageable",
      "rotation", "Rotationable",
      "axis", "Axis"
  );
  private static final String[] GROUPS = {
      "Button",
      "WallSign",
      "WallHangingSign",
      "HangingSign",
      "Sign",
      "Bed",
      "Stairs",
      "Trapdoor",
      "FenceGate",
      "Door",
      "WallFan",
      "Slab",
      "WallBanner",
      "Banner",
      "WallHead",
      "Head",
  };
  private static final Map<String, Integer> BLOCK_ENTITIES = new HashMap<>() { // TODO: find a way to not hardcode this
    {
      this.put("Furnace", 0);
      this.put("Chest", 1);
      this.put("TrappedChest", 2);
      this.put("EnderChest", 3);
      this.put("Jukebox", 4);
      this.put("Dispenser", 5);
      this.put("Dropper", 6);
      this.put("WallSign", 7);
      this.put("Sign", 7);
      this.put("HangingSign", 8);
      this.put("MobSpawner", 9);
      this.put("Piston", 10);
      this.put("BrewingStand", 11);
      this.put("EnchantingTable", 12);
      this.put("EndPortal", 13);
      this.put("Beacon", 14);
      this.put("WallHead", 15);
      this.put("Head", 15);
      this.put("DaylightDetector", 16);
      this.put("Hopper", 17);
      this.put("Comparator", 18);
      this.put("WallBanner", 19);
      this.put("Banner", 19);
      this.put("StructureBlock", 20);
      this.put("EndGateway", 21);
      this.put("CommandBlock", 22);
      this.put("ShulkerBox", 23);
      this.put("Bed", 24);
      this.put("Conduit", 25);
      this.put("Barrel", 26);
      this.put("Smoker", 27);
      this.put("BlastFurnace", 28);
      this.put("Lectern", 29);
      this.put("Bell", 30);
      this.put("Jigsaw", 31);
      this.put("Campfire", 32);
      this.put("Beehive", 33);
      this.put("SculkSensor", 34);
      this.put("CalibratedSculkSensor", 35);
      this.put("SculkCatalyst", 36);
      this.put("SculkShrieker", 37);
      this.put("ChiseledBookshelf", 38);
      this.put("BrushableBlock", 39);
      this.put("DecoratedPot", 40);
    }
  };

  public BlockGenerator(final BufferedReader reader) throws IOException {
    final var blockPackage = Constants.API_PACKAGE.resolve("world").resolve("block");
    final Map<String, JsonObject> objects = Constants.GSON.fromJson(reader, Constants.STRING_JSON_MAP);
    final var blockAdder = new ArrayList<String>();
    final var blocks = new ArrayList<String>();
    objects.forEach((key, json) -> {
      final var clazz = this.classAndCreate(blockPackage, key, json.get("properties"));
      final var states = Constants.GSON.fromJson(json.get("states"), JsonObject[].class);
      final var stringBuilder = new StringBuilder();
      var defId = 0;
      for (var i = 0; i < states.length; i++) {
        final var state = states[i];
        if (state.has("default")) {
          defId = state.get("id").getAsInt();
        }

        final Map<String, String> properties = Constants.GSON.fromJson(state.get("properties"),
            Constants.STRING_STRING_MAP);
        if (properties == null) {
          continue;
        }
        final var entries = new ArrayList<>(properties.entrySet());
        for (int j = 0; j < entries.size(); j++) {
          final var entry = entries.get(j);
          stringBuilder.append(entry.getKey()).append("/").append(entry.getValue());
          if (entries.size() - 1 != j) {
            stringBuilder.append("\\");
          }
        }
        if (states.length - 1 != i) {
          stringBuilder.append(",");
        }
      }

      final var id = states[0].get("id").getAsInt();
      blockAdder.add(key + "," + this.creatorClass(clazz) + "," + id + "," + (defId - id) +
          (stringBuilder.isEmpty() ? "" : "," + stringBuilder) + "\n");

      blocks.add("public static final " + clazz + " " +
          (key.split(":")[1].toUpperCase()) + " = " + (!clazz.equals("BlockState") ? "(" + clazz
          + ") " : "")
          + "get(\"" + key + "\");");
    });
    this.append(blockPackage.resolve("Block.java"), blocks);
    this.createBlockRegistry(blockAdder);
  }

  private String classAndCreate(final Path path, String key, final JsonElement propertyElement) {
    final var needsClass = propertyElement != null;
    var clazz = this.keyToName(key).replace("Skull", "Head");
    var grouped = false;
    for (final var group : GROUPS) {
      if (clazz.endsWith(group)) {
        clazz = group;
        grouped = true;
        break;
      }
    }
    final var entity = BLOCK_ENTITIES.containsKey(clazz);
    if (entity || needsClass) {
      final Map<String, String[]> properties = Constants.GSON.fromJson(propertyElement,
          Constants.STRING_STRING_ARRAY_MAP);
      final List<String> keys =
          properties != null ? new ArrayList<>(properties.keySet()) : List.of();
      if (!entity) {
        if (keys.isEmpty()) {
          return "BlockState";
        }
        if (keys.size() == 1 && !grouped) {
          final var property = PROPERTY_TO_CLASS.get(keys.get(0));
          return "BlockState" + (property != null ? "." + property + "<BlockState>" : "");
        }
      }
      if (!DO_NOT_CREATE.contains(clazz)) {
        final var stringBuilder = new StringBuilder();
        final var iterator = keys.iterator();
        while (iterator.hasNext()) {
          final var property = iterator.next();
          final var propertyClass = PROPERTY_TO_CLASS.get(property);
          if (propertyClass != null) {
            stringBuilder
                .append("BlockState.")
                .append(propertyClass)
                .append("<")
                .append(clazz)
                .append(">, ");
          } else {
            iterator.remove();
          }
        }
        if (!entity) {
          if (stringBuilder.isEmpty()) {
            return "BlockState";
          }
          if (keys.size() == 1) {
            return "BlockState." + PROPERTY_TO_CLASS.get(keys.get(0)) + "<BlockState>";
          }
        }
        this.createClasses(path, clazz, stringBuilder, entity);
      }
    } else {
      clazz = "BlockState";
    }
    return clazz;
  }

  private String creatorClass(String clazz) {
    clazz = "Sculk" + clazz;
    if (clazz.contains("<")) {
      clazz = clazz.substring(0, clazz.indexOf("<"));
    }
    if (clazz.contains("BlockState.")) {
      final var split = clazz.split("BlockState.");
      clazz = "$" + split[1];
    }
    return clazz;
  }

  private void createClasses(final Path path, final String clazz, final StringBuilder stringBuilder,
      final boolean entity) {
    final var empty = stringBuilder.isEmpty();
    new ClassCreator(path.resolve(clazz + ".java"), "io.github.sculkpowered.server.world.block", clazz)
        .type(ClassCreator.Type.INTERFACE)
        .addition(
            "extends " + (entity ? "Block.Entity<" + clazz + ">" + (!empty ? ", " : "") : "") +
                (!empty ? stringBuilder.substring(0, stringBuilder.length() - 2) : ""))
        .create();

    final List<String> implementation = new ArrayList<>(Arrays.asList(
        "  Sculk" + clazz + "(BlockParent block, int id, Map<String, String> properties) {",
        "    super(block, id, properties" + (entity ? ", " + BLOCK_ENTITIES.get(clazz) : "")
            + ");",
        "  }"
    ));
    if (entity) {
      implementation.addAll(Arrays.asList(
          "",
          "  public Sculk" + clazz
              + "(BlockParent block, int id, Map<String, String> properties, int entityId, CompoundBinaryTag nbt) {",
          "    super(block, id, properties, entityId, nbt);",
          "  }",
          "",
          "  @Override",
          "  public @NotNull " + clazz + " nbt(@NotNull CompoundBinaryTag nbt) {",
          "     return new Sculk" + clazz
              + "(this.block, this.id, this.properties, this.entityId, nbt);",
          "  }"
      ));
    }
    new ClassCreator(Constants.SERVER_PACKAGE.resolve("world").resolve("block")
        .resolve("Sculk" + clazz + ".java"), "io.github.sculkpowered.server.world.block",
        "Sculk" + clazz)
        .type(ClassCreator.Type.PROTECTED)
        .addition(
            "extends SculkBlockState" + (entity ? ".Entity<" + clazz + ">" : "") + " implements "
                + clazz)
        .imports((entity ? new String[]{
            "net.kyori.adventure.nbt.CompoundBinaryTag",
            "org.jetbrains.annotations.NotNull",
            "java.util.Map"} :
            new String[]{"java.util.Map"})
        )
        .inner(implementation.toArray(new String[0]))
        .create();
  }

  private void createBlockRegistry(final List<String> list) throws IOException {
    try (final var writer = Files.newBufferedWriter(Path.of("server", "src", "main",
        "resources", "registries", "blocks"))) {
      for (final var s : list) {
        writer.write(s);
      }
    }
  }
}
