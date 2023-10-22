package io.github.sculkpowered.server.json;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import io.github.sculkpowered.server.entity.player.GameProfile.Property;
import java.lang.reflect.Type;

public final class GameProfilePropertyDeserializer implements JsonDeserializer<Property> {

  @Override
  public Property deserialize(JsonElement jsonElement, Type type,
      JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
    final var json = jsonElement.getAsJsonObject();
    return new Property(
        json.get("name").getAsString(),
        json.get("value").getAsString(),
        json.get("signature") != null ? json.get("signature").getAsString() : null
    );
  }
}
