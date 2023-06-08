package de.bauhd.minecraft.code.generaor;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.atomic.AtomicInteger;

// for now
public final class DamageTypeGenerator {

    public static void main(String[] args) {
        final var gson = new Gson();
        final var array = new JsonArray();
        try (final var stream = Files.walk(Path.of("generated", "data", "minecraft", "damage_type"), 1)) {
            final var i = new AtomicInteger();
            stream.forEach(path -> {
                if (Files.isDirectory(path)) return;
                final var name = path.getFileName().toString().split("\\.")[0];
                System.out.println(path + " " + name);
                try (final var reader = Files.newBufferedReader(path)) {
                    final var json = new JsonObject();
                    json.addProperty("name", "minecraft:" + name);
                    json.addProperty("id", i);
                    json.add("element", gson.fromJson(reader, JsonElement.class));
                    array.add(json);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                i.getAndIncrement();
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        final var json = new JsonObject();
        json.addProperty("type", "minecraft:damage_type");
        json.add("value", array);
        System.out.println(json);
    }
}
