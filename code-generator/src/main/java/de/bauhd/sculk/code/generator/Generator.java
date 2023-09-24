package de.bauhd.sculk.code.generator;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

class Generator {

    protected void append(final Path path, final List<String> list) throws IOException {
        final var oldLines = Files.readAllLines(path);
        final var newLines = new ArrayList<String>(oldLines.size());
        var index = 0;
        String space = null;
        for (final var line : oldLines) {
            newLines.add(line);
            if (line.trim().equals("// START")) {
                space = line.substring(0, line.indexOf("/"));
                index = oldLines.indexOf(line);
                break;
            }
        }
        for (final var s : list) {
            newLines.add(space + s);
        } // append generated code
        var end = false;
        for (var i = index + 1; i < oldLines.size(); i++) {
            final var line = oldLines.get(i);
            if (line.trim().equals("// END")) {
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

    protected String keyToName(final String key) {
        final var stringBuilder = new StringBuilder();
        for (final var s : key.split(":")[1].split("_")) {
            stringBuilder.append(s.substring(0, 1).toUpperCase()).append(s.substring(1));
        }
        return stringBuilder.toString();
    }
}
