package de.bauhd.sculk.json;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;
import de.bauhd.sculk.SculkMinecraftServer;
import de.bauhd.sculk.entity.player.GameProfile;
import de.bauhd.sculk.util.MojangUtil;

import java.lang.reflect.Type;
import java.util.List;

public final class GameProfileDeserializer implements JsonDeserializer<GameProfile> {

    private static final Type PROPERTY_ARRAY = TypeToken.getArray(GameProfile.Property.class).getType();

    @Override
    public GameProfile deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        final var json = jsonElement.getAsJsonObject();
        return new GameProfile(
                MojangUtil.fromMojang(json.get("id").getAsString()),
                json.get("name").getAsString(),
                List.of(SculkMinecraftServer.GSON.fromJson(json.get("properties"), PROPERTY_ARRAY))
        );
    }
}
