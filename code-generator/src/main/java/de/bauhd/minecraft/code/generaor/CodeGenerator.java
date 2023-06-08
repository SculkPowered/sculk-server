package de.bauhd.minecraft.code.generaor;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.function.Consumer;

public final class CodeGenerator {

    private static final Gson GSON = new Gson();
    private static final Type STRING_JSON_MAP = TypeToken.getParameterized(Map.class, String.class, JsonObject.class).getType();

    public static void main(String[] args) throws IOException {
        final var path = Path.of("generated");
        if (Files.notExists(path)) {
            System.err.println("generated directory not found.");
            return;
        }

        new CodeGenerator().generate(path);
    }

    private void generate(final Path generated) throws IOException {
        final var reports = generated.resolve("reports");
        final var data = generated.resolve("data").resolve("minecraft");
        final var apiPackage = Path.of("api", "src", "main", "java", "de", "bauhd", "minecraft", "server");

        try (final var reader = Files.newBufferedReader(reports.resolve("blocks.json"))) {
            this.generateBlocks(reader, apiPackage.resolve("world").resolve("block").resolve("Block.java"));
        }
        try (final var reader = Files.newBufferedReader(reports.resolve("registries.json"))) {
            final var json = GSON.fromJson(reader, JsonObject.class);

            this.generateRegistry(json.get("minecraft:item").getAsJsonObject(),
                    apiPackage.resolve("container").resolve("item").resolve("Material.java"));

            this.generateRegistry(json.get("minecraft:enchantment").getAsJsonObject(),
                    apiPackage.resolve("enchantment").resolve("Enchantment.java"));

            this.generateRegistry(json.get("minecraft:potion").getAsJsonObject(),
                    apiPackage.resolve("potion").resolve("PotionEffect.java"));

            final var entityPath = apiPackage.resolve("entity");
            final Map<String, JsonObject> map = GSON.fromJson(json.get("minecraft:entity_type").getAsJsonObject().get("entries"), STRING_JSON_MAP);
            map.forEach((key, object) -> {
                if (key.equals("minecraft:marker") || key.equals("minecraft:player")) return;
                final var stringBuilder = new StringBuilder();
                for (final var s : key.split(":")[1].split("_")) {
                    stringBuilder.append(s.substring(0, 1).toUpperCase()).append(s.substring(1));
                }
                final var name = stringBuilder.toString();
                final var entityTypePath = entityPath.resolve(name + ".java");
                if (Files.notExists(entityTypePath)) {
                    try (final var writer = Files.newBufferedWriter(entityTypePath)) {
                        writer.write("package de.bauhd.minecraft.server.entity;\n");
                        writer.write("\n");
                        writer.write("public interface " + name + " extends Entity {}");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            });

            this.generateRegistry(json.get("minecraft:entity_type").getAsJsonObject(),
                    apiPackage.resolve("entity").resolve("EntityType.java"));
        }
    }

    private void generateBlocks(final Reader reader, final Path path) throws IOException {
        this.append(path, list -> {
            final Map<String, JsonObject> map = GSON.fromJson(reader, STRING_JSON_MAP);
            map.forEach((key, json) -> {
                final var states = GSON.fromJson(json.get("states"), JsonArray.class);
                for (final var state : states) {
                    final var object = state.getAsJsonObject();
                    final var def = object.get("default");
                    if (def != null && def.getAsBoolean()) {
                        list.add("    public static final Block " + (key.split(":")[1].toUpperCase()) +
                                " = new Block(\"" + key + "\", " + object.get("id") + ");");
                    }
                }
            });
        });
    }

    private void generateRegistry(final JsonObject object, final Path path) throws IOException {
        this.append(path, list -> {
            final Map<String, JsonObject> map = GSON.fromJson(object.get("entries"), STRING_JSON_MAP);
            map.forEach((key, json) ->
                    list.add("    " + key.split(":")[1].toUpperCase() + "(" + json.get("protocol_id").getAsInt() + "),"));
        });
    }

    private void append(final Path path, final Consumer<List<String>> appender) throws IOException {
        final var oldLines = Files.readAllLines(path);
        final var newLines = new ArrayList<String>();
        var index = 0;
        for (final var line : oldLines) {
            newLines.add(line);
            if (line.equals("    // START")) {
                index = oldLines.indexOf(line);
                break;
            }
        }
        appender.accept(newLines); // append generated code
        var end = false;
        for (var i = index + 1; i < oldLines.size(); i++) {
            final var line = oldLines.get(i);
            if (line.equals("    // END")) {
                end = true;
            }
            if (!end) continue;
            newLines.add(line);
        }
        try (final var writer = Files.newBufferedWriter(path)) {
            for (final var line : newLines) {
                writer.write(line + "\n");
            }
        }
    }
}
