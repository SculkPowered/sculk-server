package de.bauhd.sculk.code.generator;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static de.bauhd.sculk.code.generator.Constants.*;
import static de.bauhd.sculk.code.generator.Constants.STRING_STRING_MAP;

final class BlockGenerator extends Generator {

    private static final Map<String, String> PROPERTY_TO_CLASS = Map.of(
            "waterlogged", "Waterloggable",
            "powered", "Powerable",
            "snowy", "Snowy",
            "face", "Face",
            "facing", "Facing",
            "half", "Half",
            "age", "Ageable"
    );
    private static final String[] GROUPS = {
            "Button",
            "WallSign",
            "WallHangingSign",
            "Bed",
            "Stairs",
            "Trapdoor",
            "FenceGate",
            "Door",
            "Bed",
            "WallFan"
    };

    public BlockGenerator(final BufferedReader reader) throws IOException {
        final var blockPackage = API_PACKAGE.resolve("world").resolve("block");
        final Map<String, JsonObject> objects = GSON.fromJson(reader, STRING_JSON_MAP);
        final var blockAdder = new ArrayList<String>();
        final var blocks = new ArrayList<String>();
        objects.forEach((key, json) -> {
            final var clazz = this.classAndCreate(blockPackage, key, json.get("properties"));
            final JsonObject[] states = GSON.fromJson(json.get("states"), JSON_OBJECT_ARRAY);
            final var stringBuilder = new StringBuilder();
            var defId = 0;
            for (var i = 0; i < states.length; i++) {
                final var state = states[i];
                if (state.has("default")) {
                    defId = state.get("id").getAsInt();
                }

                final Map<String, String> properties = GSON.fromJson(state.get("properties"), STRING_STRING_MAP);
                if (properties == null) continue;
                final var entries = new ArrayList<>(properties.entrySet());
                for (int j = 0; j < entries.size(); j++) {
                    final var entry = entries.get(j);
                    stringBuilder.append(entry.getKey()).append("/").append(entry.getValue());
                    if (entries.size() - 1 != j) stringBuilder.append("\\");
                }
                if (states.length - 1 != i) stringBuilder.append(",");
            }

            final var id = states[0].get("id").getAsInt();
            blockAdder.add(key + "," + this.creatorClass(clazz) + "," + id + "," + (defId - id) +
                    (stringBuilder.isEmpty() ? "" : "," + stringBuilder) + "\n");

            blocks.add("public static final " + clazz + " " +
                    (key.split(":")[1].toUpperCase()) + " = " + (!clazz.equals("BlockState") ? "(" + clazz + ") " : "")
                    + "get(\"" + key + "\");");
        });
        this.append(blockPackage.resolve("Block.java"), blocks);
        this.createBlockRegistry(blockAdder);
    }

    private String classAndCreate(final Path path, final String key, final JsonElement propertyElement) {
        final var needsClass = propertyElement != null;
        var clazz = (needsClass ? this.keyToName(key) : "BlockState");
        if (needsClass) {
            final Map<String, String[]> properties = GSON.fromJson(propertyElement, STRING_STRING_ARRAY_MAP);
            final var keys = new ArrayList<>(properties.keySet());
            if (properties.size() == 1) {
                final var property = PROPERTY_TO_CLASS.get(keys.get(0));
                clazz = "BlockState" + (property != null ? "." + property + "<BlockState>" : "");
            } else {
                for (final var group : GROUPS) {
                    if (clazz.endsWith(group)) {
                        clazz = group;
                        break;
                    }
                }
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
                if (keys.isEmpty()) {
                    clazz = "BlockState";
                } else if (keys.size() == 1) {
                    clazz = "BlockState." + PROPERTY_TO_CLASS.get(keys.get(0)) + "<BlockState>";
                } else {
                    this.createClasses(path, clazz, stringBuilder);
                }
            }
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

    private void createClasses(final Path path, final String clazz, final StringBuilder stringBuilder) {
        new ClassCreator(path.resolve(clazz + ".java"), "de.bauhd.sculk.world.block", clazz)
                .addition("extends " + stringBuilder.substring(0, stringBuilder.length() - 2))
                .type(ClassCreator.Type.INTERFACE)
                .create();
        new ClassCreator(SERVER_PACKAGE.resolve("world").resolve("block")
                .resolve("Sculk" + clazz + ".java"), "de.bauhd.sculk.world.block",
                "Sculk" + clazz)
                .addition("extends SculkBlockState implements " + clazz)
                .imports("java.util.Map")
                .inner(
                        "    Sculk" + clazz + "(BlockParent block, int id, Map<String, String> properties) {",
                        "        super(block, id, properties);",
                        "    }"
                )
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
