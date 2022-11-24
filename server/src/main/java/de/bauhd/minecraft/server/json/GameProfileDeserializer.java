package de.bauhd.minecraft.server.json;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;
import de.bauhd.minecraft.server.AdvancedMinecraftServer;
import de.bauhd.minecraft.server.api.entity.player.GameProfile;
import de.bauhd.minecraft.server.util.MojangUtil;

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
                List.of(AdvancedMinecraftServer.GSON.fromJson(json.get("properties"), PROPERTY_ARRAY))
        );
    }
}
