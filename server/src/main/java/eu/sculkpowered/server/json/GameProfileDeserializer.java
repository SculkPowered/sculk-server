package eu.sculkpowered.server.json;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import eu.sculkpowered.server.SculkServer;
import eu.sculkpowered.server.entity.player.GameProfile;
import eu.sculkpowered.server.entity.player.GameProfile.Property;
import eu.sculkpowered.server.util.MojangUtil;
import java.lang.reflect.Type;
import java.util.List;

public final class GameProfileDeserializer implements JsonDeserializer<GameProfile> {

  @Override
  public GameProfile deserialize(JsonElement jsonElement, Type type,
      JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
    final var json = jsonElement.getAsJsonObject();
    return new GameProfile(
        MojangUtil.fromMojang(json.get("id").getAsString()),
        json.get("name").getAsString(),
        List.of(SculkServer.GSON.fromJson(json.get("properties"), Property[].class))
    );
  }
}
