package de.bauhd.sculk.code.generator;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public final class CodeGenerator {

    private static final Gson GSON = new Gson();
    private static final Type STRING_JSON_MAP = TypeToken.getParameterized(Map.class, String.class, JsonObject.class).getType();
    private static final Type STRING_STRING_MAP = TypeToken.getParameterized(Map.class, String.class, String.class).getType();
    private static final Type STRING_STRING_ARRAY_MAP = TypeToken.getParameterized(Map.class, String.class, String[].class).getType();
    private static final Type JSON_OBJECT_ARRAY = TypeToken.getArray(JsonObject.class).getType();

    private static final Path API_PACKAGE = Path.of("api", "src", "main", "java", "de", "bauhd", "sculk");
    private static final Path SERVER_PACKAGE = Path.of("server", "src", "main", "java", "de", "bauhd", "sculk");

    public static void main(String[] args) throws IOException {
        final var tmp = Path.of("tmp");
        try {
            Files.createDirectory(tmp);
            new DataGenerator(tmp);

            new CodeGenerator().generate(tmp.resolve("generated"));
        } finally {
            try (final var stream = Files.walk(tmp).sorted(Comparator.reverseOrder())) {
                stream.forEach(path -> {
                    try {
                        Files.delete(path);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
            }
        }
    }

    private void generate(final Path generated) throws IOException {
        final var reports = generated.resolve("reports");
        final var data = generated.resolve("data").resolve("minecraft");

        try (final var reader = Files.newBufferedReader(reports.resolve("blocks.json"))) {
            this.generateBlocks(reader, API_PACKAGE.resolve("world").resolve("block"));
        }
        try (final var reader = Files.newBufferedReader(reports.resolve("registries.json"))) {
            final var json = GSON.fromJson(reader, JsonObject.class);

            this.generateRegistry(json.get("minecraft:item").getAsJsonObject(),
                    API_PACKAGE.resolve("container").resolve("item").resolve("Material.java"));

            this.generateRegistry(json.get("minecraft:enchantment").getAsJsonObject(),
                    API_PACKAGE.resolve("enchantment").resolve("Enchantment.java"));

            this.generateRegistry(json.get("minecraft:potion").getAsJsonObject(),
                    API_PACKAGE.resolve("potion").resolve("PotionEffect.java"));

            // Entities
            final var entityPath = API_PACKAGE.resolve("entity");
            final Map<String, JsonObject> map = GSON.fromJson(json.get("minecraft:entity_type")
                    .getAsJsonObject().get("entries"), STRING_JSON_MAP);
            map.forEach((key, object) -> {
                if (key.equals("minecraft:marker") || key.equals("minecraft:player")) return;
                final var name = this.keyToName(key);
                final var entityTypePath = entityPath.resolve(name + ".java");
                if (Files.notExists(entityTypePath)) {
                    new ClassCreator(entityTypePath, "de.bauhd.sculk.entity", name)
                            .addition("extends Entity")
                            .create();
                }
            });

            this.generateRegistry(map, API_PACKAGE.resolve("entity").resolve("EntityType.java"));
        }

        // Damage Types
        try (final var stream = Files.walk(data.resolve("damage_type"), 1)) {
            final var list = new ArrayList<String>();
            stream.forEach(path -> {
                if (Files.isDirectory(path)) return;
                final var name = path.getFileName().toString().split("\\.")[0];
                try (final var reader = Files.newBufferedReader(path)) {
                    final var damageData = GSON.fromJson(reader, JsonObject.class);
                    final var stringBuilder = new StringBuilder("public static final DamageType " + name.toUpperCase() + " = builder(Key.key(\"" + name + "\"))");
                    if (damageData.has("exhaustion")) {
                        stringBuilder.append(".exhaustion(").append(damageData.get("exhaustion").getAsDouble()).append(")");
                    }
                    if (damageData.has("effects")) {
                        stringBuilder.append(".effects(").append("\"").append(damageData.get("effects").getAsString()).append("\"").append(")");
                    }
                    if (damageData.has("message_id")) {
                        stringBuilder.append(".messageId(").append("\"").append(damageData.get("message_id").getAsString()).append("\"").append(")");
                    }
                    if (damageData.has("scaling")) {
                        stringBuilder.append(".scaling(").append("\"").append(damageData.get("scaling").getAsString()).append("\"").append(")");
                    }
                    if (damageData.has("death_message_type")) {
                        stringBuilder.append(".deathMessageType(").append("\"").append(damageData.get("death_message_type").getAsString()).append("\"").append(")");
                    }
                    stringBuilder.append(".build();");
                    list.add(stringBuilder.toString());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
            this.append(API_PACKAGE.resolve("damage").resolve("DamageType.java"), list);
        }
    }

    private void generateBlocks(final Reader reader, final Path path) throws IOException {
        final Map<String, String> propertyToClass = Map.of(
                "waterlogged", "Waterloggable",
                "powered", "Powerable"
        );
        final Map<String, JsonObject> objects = GSON.fromJson(reader, STRING_JSON_MAP);
        final var blockAdder = new ArrayList<String>();
        final var blocks = new ArrayList<String>();
        objects.forEach((key, json) -> {
            final var needsClass = json.has("properties");
            var clazz = (needsClass ? this.keyToName(key) : "BlockState");
            if (needsClass) {
                final Map<String, String[]> properties = GSON.fromJson(json.get("properties"), STRING_STRING_ARRAY_MAP);
                final var keys = new ArrayList<>(properties.keySet());
                if (properties.size() == 1) {
                    final var property = propertyToClass.get(keys.get(0));
                    clazz = "BlockState" + (property != null ? "." + property + "<BlockState>" : "");
                } else {
                    final var stringBuilder = new StringBuilder();
                    final var iterator = keys.iterator();
                    while (iterator.hasNext()) {
                        final var property = iterator.next();
                        final var propertyClass = propertyToClass.get(property);
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
                        clazz = "BlockState." + propertyToClass.get(keys.get(0)) + "<BlockState>";
                    } else {
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
                }
            }

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
                    stringBuilder
                            .append(entry.getKey())
                            .append("/")
                            .append(entry.getValue());
                    if (entries.size() - 1 != j) {
                        stringBuilder.append("\\");
                    }
                }
                if (states.length - 1 != i) {
                    stringBuilder.append(",");
                }
            }

            var noGenericClass = "Sculk" + clazz;
            if (noGenericClass.contains("<")) {
                noGenericClass = noGenericClass.substring(0, noGenericClass.indexOf("<"));
            }
            if (noGenericClass.contains("BlockState.")) {
                final var split = noGenericClass.split("BlockState.");
                noGenericClass = "$" + split[1];
            }
            final var id = states[0].get("id").getAsInt();
            blockAdder.add(key + "," + noGenericClass + "," + id + "," + (defId - id) + (stringBuilder.isEmpty() ? "" : "," + stringBuilder) + "\n");

            blocks.add("public static final " + clazz + " " +
                    (key.split(":")[1].toUpperCase()) + " = " + (!clazz.equals("BlockState") ? "(" + clazz + ") " : "")
                    + "get(\"" + key + "\");");
        });
        this.append(path.resolve("Block.java"), blocks);
        try (final var writer = Files.newBufferedWriter(Path.of("server", "src", "main",
                "resources", "registries", "blocks"))) {
            for (final var s : blockAdder) {
                writer.write(s);
            }
        }
    }

    private void generateRegistry(final JsonObject object, final Path path) throws IOException {
        final Map<String, JsonObject> map = GSON.fromJson(object.get("entries"), STRING_JSON_MAP);
        this.generateRegistry(map, path);
    }

    private void generateRegistry(final Map<String, JsonObject> map, final Path path) throws IOException {
        final var list = new ArrayList<String>(map.size());
        map.forEach((key, json) ->
                list.add(key.split(":")[1].toUpperCase() + "(\"" + key + "\", " + json.get("protocol_id").getAsInt() + "),"));
        this.append(path, list);
    }

    private void append(final Path path, final List<String> list) throws IOException {
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

    private String keyToName(final String key) {
        final var stringBuilder = new StringBuilder();
        for (final var s : key.split(":")[1].split("_")) {
            stringBuilder.append(s.substring(0, 1).toUpperCase()).append(s.substring(1));
        }
        return stringBuilder.toString();
    }
}
