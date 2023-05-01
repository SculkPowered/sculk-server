package de.bauhd.minecraft.server.world.block;

import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;

import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

import static de.bauhd.minecraft.server.AdvancedMinecraftServer.GSON;

public final class BlockRegistry {

    private Int2ObjectMap<BlockState> states;

    public BlockRegistry() {
        this.states = new Int2ObjectOpenHashMap<>();

        final var mapStringJsonObject = TypeToken.getParameterized(Map.class, String.class, JsonObject.class).getType();

        final Map<String, JsonObject> map = GSON.fromJson(
                new InputStreamReader(Objects.requireNonNull(this.getClass().getClassLoader().getResourceAsStream("registries/blocks.json"))), mapStringJsonObject);
        map.forEach((key, json) -> {
            final Map<String, JsonObject> properties = GSON.fromJson(json.get("properties"), mapStringJsonObject);
            properties.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, entry -> {
                final var propertyMap = new HashMap<>();
                entry.getValue().getAsJsonArray().forEach(element -> propertyMap.put(entry.getKey(), new Property<>() {
                    @Override
                    public Collection<Object> values() {
                        return List.of();
                    }
                }));
                return propertyMap;
            })).forEach((key1, properties1) -> {
                final var states = json.getAsJsonArray("states");
                states.forEach(element -> {
                    /*final var object = element.getAsJsonObject();
                    final var stateProperties = GSON.fromJson(object.get("properties"), mapStringJsonObject);
                    final var blockInfo = new BlockInfo(key1, properties1, propert);
                    new BlockState();*/
                });
            });
        });
    }
}
