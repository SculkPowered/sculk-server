package de.bauhd.sculk.code.generator;

import com.google.gson.JsonObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Map;

import static de.bauhd.sculk.code.generator.Constants.*;

final class CodeGenerator extends Generator {

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
            new BlockGenerator(reader);
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
                            .type(ClassCreator.Type.PROTECTED)
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
}
