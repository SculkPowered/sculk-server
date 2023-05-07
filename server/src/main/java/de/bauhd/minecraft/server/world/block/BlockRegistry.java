package de.bauhd.minecraft.server.world.block;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import net.kyori.adventure.key.Key;

import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static de.bauhd.minecraft.server.AdvancedMinecraftServer.GSON;

public final class BlockRegistry {

    private final Map<String, Integer> namespaceToId;

    public BlockRegistry() {
        this.namespaceToId = new HashMap<>();
        final var mapStringJsonObject = TypeToken.getParameterized(Map.class, String.class, JsonObject.class).getType();

        final Map<String, JsonObject> map = GSON.fromJson(
                new InputStreamReader(Objects.requireNonNull(this.getClass().getClassLoader().getResourceAsStream("registries/blocks.json"))), mapStringJsonObject);
        map.forEach((key, json) -> {
            final JsonArray states = GSON.fromJson(json.get("states"), JsonArray.class);
            for (final var state : states) {
                final var object = state.getAsJsonObject();
                final var def = object.get("default");
                if (def != null && def.getAsBoolean()) {
                    this.namespaceToId.put(key, object.get("id").getAsInt());
                }
            }
        });
    }

    public int getId(final Key key) {
        return this.namespaceToId.get(key.asString());
    }

    public int getId(final String key) {
        return this.namespaceToId.get(key);
    }
}
