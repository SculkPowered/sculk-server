package eu.sculkpowered.server.code.generator;

import com.google.gson.JsonObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

final class BlockGenerator extends Generator {

  public BlockGenerator(final BufferedReader reader) throws IOException {
    final var blockPackage = Constants.API_PACKAGE.resolve("world").resolve("block");
    final Map<String, JsonObject> objects = Constants.GSON.fromJson(reader, Constants.STRING_JSON_MAP);
    final var blockAdder = new ArrayList<String>();
    final var blocks = new ArrayList<String>();
    objects.forEach((key, json) -> {
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
      final var value = Util.keyToValue(key);
      blockAdder.add(value + "," + id + "," + (defId - id) +
          (stringBuilder.isEmpty() ? "" : "," + stringBuilder) + "\n");

      blocks.add("public static final BlockState " + (value.toUpperCase(Locale.ENGLISH))
          + " = " + "get(\"" + key + "\");");
    });
    this.append(blockPackage.resolve("Block.java"), blocks);
    this.createBlockRegistry(blockAdder);
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
