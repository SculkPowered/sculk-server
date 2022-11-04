package de.bauhd.minecraft.server.util;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import it.unimi.dsi.fastutil.Pair;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public final class MojangUtil {

    private static final String SESSION_SERVER = "https://sessionserver.mojang.com/session/minecraft/profile/%s?unsigned=false";
    private static final String MOJANG_API = "https://api.mojang.com/users/profiles/minecraft/%s";

    public static String getUniqueIdString(final String name) {
        final var object = apiRequest(String.format(MOJANG_API, name));
        if (object == null) return null;
        return object.get("id").getAsString();
    }

    public static Pair<String, String> getSkin(final String uniqueId) {
        final var object = apiRequest(String.format(SESSION_SERVER, uniqueId));
        if (object == null) return null;
        for (final var element : object.getAsJsonArray("properties")) {
            final var property = element.getAsJsonObject();
            final var name = property.get("name").getAsString();
            if (name.equals("textures")) {
                return new Pair<>() {
                    @Override
                    public String left() {
                        return property.get("value").getAsString();
                    }

                    @Override
                    public String right() {
                        return property.get("signature").getAsString();
                    }
                };
            }
        }
        return null;
    }

    public static Pair<String, String> getSkinFromName(final String name) {
        return getSkin(getUniqueIdString(name));
    }

    private static JsonObject apiRequest(final String url) {
        try (final var reader = new InputStreamReader(new URL(url).openConnection().getInputStream())) {
            return JsonParser.parseReader(reader).getAsJsonObject();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
