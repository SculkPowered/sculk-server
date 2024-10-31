package eu.sculkpowered.server.code.generator;

import static eu.sculkpowered.server.code.generator.Constants.API_PACKAGE;
import static eu.sculkpowered.server.code.generator.Constants.GSON;
import static eu.sculkpowered.server.code.generator.Constants.SERVER_PACKAGE;
import static eu.sculkpowered.server.code.generator.Constants.STRING_JSON_MAP;

import com.google.gson.JsonObject;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Locale;
import java.util.Map;

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
          /*try {
            Files.delete(path);
          } catch (IOException e) {
            throw new RuntimeException(e);
          }*/
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

      this.generateRegistry(json.get("minecraft:potion").getAsJsonObject(),
          API_PACKAGE.resolve("potion").resolve("PotionType.java"));

      this.generateRegistry(json.get("minecraft:mob_effect").getAsJsonObject(),
          API_PACKAGE.resolve("potion").resolve("MobEffectType.java"));

      // Entities
      final var entityPath = API_PACKAGE.resolve("entity");
      final Map<String, JsonObject> map = GSON.fromJson(json.get("minecraft:entity_type")
          .getAsJsonObject().get("entries"), STRING_JSON_MAP);
      final var entityToClass = new ArrayList<String>();
      for (final var entry : map.entrySet()) {
        final var key = entry.getKey();
        if (key.equals("minecraft:marker") || key.equals("minecraft:player")) {
          continue;
        }
        final var name = this.keyToName(key);
        final var entityTypePath = entityPath.resolve(name + ".java");
        if (Files.notExists(entityTypePath)) {
          new ClassCreator(entityTypePath, "io.github.sculkpowered.server.entity", name)
              .type(ClassCreator.Type.PROTECTED)
              .addition("extends Entity")
              .create();
        }

        entityToClass.add("map.put(" + name + ".class, Sculk" + name + "::new);");
      }
      this.generateRegistry(map, API_PACKAGE.resolve("entity").resolve("EntityType.java"));
      this.append(SERVER_PACKAGE.resolve("entity").resolve("EntityClassToSupplierMap.java"),
          entityToClass);
    }

    // Damage Types
    try (final var stream = Files.walk(data.resolve("damage_type"), 1)) {
      final var list = new ArrayList<String>();
      final var registry = new ArrayList<String>();
      stream.forEach(path -> {
        if (Files.isDirectory(path)) {
          return;
        }
        final var name = path.getFileName().toString().split("\\.")[0];
        final var upperCaseName = name.toUpperCase(Locale.ENGLISH);
        try (final var reader = Files.newBufferedReader(path)) {
          final var damageData = GSON.fromJson(reader, JsonObject.class);
          final var stringBuilder = new StringBuilder(
              "public static final DamageType " + upperCaseName +
                  " = builder(Key.key(MINECRAFT_NAMESPACE, \"" + name + "\"))");
          if (damageData.has("exhaustion")) {
            stringBuilder.append(".exhaustion(").append(damageData.get("exhaustion").getAsDouble())
                .append(")");
          }
          if (damageData.has("effects")) {
            stringBuilder.append(".effects(").append('"')
                .append(damageData.get("effects").getAsString()).append('"').append(")");
          }
          if (damageData.has("message_id")) {
            stringBuilder.append(".messageId(").append('"')
                .append(damageData.get("message_id").getAsString()).append('"').append(")");
          }
          if (damageData.has("scaling")) {
            stringBuilder.append(".scaling(").append('"')
                .append(damageData.get("scaling").getAsString()).append('"').append(")");
          }
          if (damageData.has("death_message_type")) {
            stringBuilder.append(".deathMessageType(").append('"')
                .append(damageData.get("death_message_type").getAsString()).append('"')
                .append(")");
          }
          stringBuilder.append(".build();");
          list.add(stringBuilder.toString());
          registry.add("registry.register(DamageType." + upperCaseName + ");");
        } catch (IOException e) {
          throw new RuntimeException(e);
        }
      });
      this.append(API_PACKAGE.resolve("damage").resolve("DamageType.java"), list);
      this.append(SERVER_PACKAGE.resolve("damage").resolve("DamageTypeRegistry.java"), registry);
    }
  }

  private void generateRegistry(final JsonObject object, final Path path) throws IOException {
    final Map<String, JsonObject> map = GSON.fromJson(object.get("entries"), STRING_JSON_MAP);
    this.generateRegistry(map, path);
  }

  private void generateRegistry(final Map<String, JsonObject> map, final Path path)
      throws IOException {
    final var list = new ArrayList<Entry>(map.size());
    map.forEach((key, json) -> {
      final var keyValue = key.split(":")[1];
      list.add(new Entry(keyValue.toUpperCase(Locale.ENGLISH) + "(\"" + keyValue + "\"),",
          json.get("protocol_id").getAsInt()));
    });
    list.sort(Comparator.comparingInt(entry -> entry.id));
    this.append(path, list.stream().map(Entry::name).toList());
  }

  private record Entry(String name, int id) {}
}
