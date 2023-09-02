package de.bauhd.sculk.json;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import de.bauhd.sculk.entity.player.GameProfile;

import java.lang.reflect.Type;

public final class GameProfilePropertyDeserializer implements JsonDeserializer<GameProfile.Property> {

    @Override
    public GameProfile.Property deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        final var json = jsonElement.getAsJsonObject();
        return new GameProfile.Property(
                json.get("name").getAsString(),
                json.get("value").getAsString(),
                json.get("signature") != null ? json.get("signature").getAsString() : null
        );
    }
}
