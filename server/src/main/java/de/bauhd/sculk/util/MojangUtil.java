package de.bauhd.sculk.util;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import de.bauhd.sculk.SculkServer;
import de.bauhd.sculk.entity.player.GameProfile;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.UUID;

public final class MojangUtil {

    private static final Logger LOGGER = LogManager.getLogger(MojangUtil.class);

    private static final String SESSION_SERVER_URL = "https://sessionserver.mojang.com";
    private static final String SESSION_SERVER_PROFILE_URL = SESSION_SERVER_URL + "/session/minecraft/profile/%s?unsigned=false";
    private static final String HAS_JOINED_URL = SESSION_SERVER_URL + "/session/minecraft/hasJoined?username=%s&serverId=%s";
    private static final String MOJANG_API_PROFILE_URL = "https://api.mojang.com/users/profiles/minecraft/%s";

    public static String getUniqueIdString(final String name) {
        final var json = apiRequest(String.format(MOJANG_API_PROFILE_URL, name));
        if (json == null) return null;
        return json.get("id").getAsString();
    }

    public static GameProfile.Property getSkin(final String uniqueId) {
        final var json = apiRequest(String.format(SESSION_SERVER_PROFILE_URL, uniqueId));
        if (json == null) return null;
        for (final var element : json.getAsJsonArray("properties")) {
            final var property = element.getAsJsonObject();
            final var name = property.get("name").getAsString();
            if (name.equals("textures")) {
                return new GameProfile.Property("textures", property.get("value").getAsString(), property.get("signature").getAsString());
            }
        }
        return null;
    }

    public static GameProfile.Property getSkinFromName(final String name) {
        return getSkin(getUniqueIdString(name));
    }

    public static GameProfile hasJoined(final String username, final String serverId) {
        return SculkServer.GSON.fromJson(apiRequest(String.format(HAS_JOINED_URL, username, serverId)), GameProfile.class);
    }

    public static UUID fromMojang(String uniqueId) {
        return UUID.fromString(uniqueId
                .replaceFirst("(\\p{XDigit}{8})(\\p{XDigit}{4})(\\p{XDigit}{4})(\\p{XDigit}{4})(\\p{XDigit}+)",
                        "$1-$2-$3-$4-$5"));
    }

    private static JsonObject apiRequest(final String url) {
        try (final var reader = new InputStreamReader(URI.create(url).toURL().openConnection().getInputStream())) {
            return JsonParser.parseReader(reader).getAsJsonObject();
        } catch (IOException | IllegalStateException e) {
            LOGGER.error("Exception during api request", e);
            return null;
        }
    }
}
