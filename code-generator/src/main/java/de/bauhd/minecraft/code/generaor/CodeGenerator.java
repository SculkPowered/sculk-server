package de.bauhd.minecraft.code.generaor;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Consumer;

public final class CodeGenerator {

    private static final Gson GSON = new Gson();

    public static void main(String[] args) throws IOException {
        new CodeGenerator().append();
    }

    private void append() throws IOException {
        this.generateBlocks();
    }

    private void generateBlocks() throws IOException {
        final var path = Path.of("api", "src", "main", "java", "de", "bauhd", "minecraft", "server", "world", "block", "Block.java");
        final var mapStringJsonObject = TypeToken.getParameterized(Map.class, String.class, JsonObject.class).getType();
        this.append(path, list -> {
            final Map<String, JsonObject> map = GSON.fromJson(
                    new InputStreamReader(Objects.requireNonNull(CodeGenerator.class.getClassLoader()
                            .getResourceAsStream("blocks.json"))), mapStringJsonObject);
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
