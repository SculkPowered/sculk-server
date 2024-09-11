package eu.sculkpowered.server.code.generator;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

class Generator {

  protected void append(final Path path, final List<String> list) throws IOException {
    final var oldLines = Files.readAllLines(path);
    try (final var writer = Files.newBufferedWriter(path)) {
      var index = 0;
      String space = null;
      for (final var line : oldLines) {
        writer.write(line + "\n");
        if (line.trim().equals("// START")) {
          space = line.substring(0, line.indexOf("/"));
          index = oldLines.indexOf(line);
          break;
        }
      }
      for (final var s : list) {
        writer.write(space + s + "\n");
      } // append generated code
      var end = false;
      for (var i = index + 1; i < oldLines.size(); i++) {
        final var line = oldLines.get(i);
        if (line.trim().equals("// END")) {
          end = true;
        }
        if (!end) {
          continue;
        }
        writer.write(line + "\n");
      }
    }
  }

  protected String keyToName(final String key) {
    final var stringBuilder = new StringBuilder();
    for (final var s : key.substring(key.indexOf(":") + 1).split("_")) {
      stringBuilder.append(Character.toUpperCase(s.charAt(0))).append(s.substring(1));
    }
    return stringBuilder.toString();
  }
}
