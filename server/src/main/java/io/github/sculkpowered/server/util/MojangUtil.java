package io.github.sculkpowered.server.util;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.github.sculkpowered.server.SculkServer;
import io.github.sculkpowered.server.entity.player.GameProfile;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.UUID;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class MojangUtil {

  private static final Logger LOGGER = LogManager.getLogger(MojangUtil.class);

  private static final String SESSION_SERVER_URL = "https://sessionserver.mojang.com";
  private static final String HAS_JOINED_URL =
      SESSION_SERVER_URL.concat("/session/minecraft/hasJoined?username=%s&serverId=%s");

  public static GameProfile hasJoined(final String username, final String serverId) {
    return SculkServer.GSON.fromJson(apiRequest(String.format(HAS_JOINED_URL, username, serverId)),
        GameProfile.class);
  }

  public static UUID fromMojang(String uniqueId) {
    long lo = 0;
    long hi = 0;
    for (int i = 0, j = 0; i < 32; ++j) {
      var current = 0;
      var c = uniqueId.charAt(i);
      if (c >= '0' && c <= '9') {
        current = (c - '0');
      } else if (c >= 'a' && c <= 'f') {
        current = (c - 'a' + 10);
      } else if (c >= 'A' && c <= 'F') {
        current = (c - 'A' + 10);
      } else {
        throw nonHexCharacter(i, c);
      }
      current = (current << 4);
      c = uniqueId.charAt(++i);
      if (c >= '0' && c <= '9') {
        current |= (c - '0');
      } else if (c >= 'a' && c <= 'f') {
        current |= (c - 'a' + 10);
      } else if (c >= 'A' && c <= 'F') {
        current |= (c - 'A' + 10);
      } else {
        throw nonHexCharacter(i, c);
      }
      if (j < 8) {
        hi = (hi << 8) | current;
      } else {
        lo = (lo << 8) | current;
      }
      ++i;
    }
    return new UUID(hi, lo);
  }

  private static NumberFormatException nonHexCharacter(final int i, final char c) {
    return new NumberFormatException(
        "Non-hex character at #" + i + ": '" + c + "' (value 0x" + Integer.toHexString(c) + ")");
  }

  private static JsonObject apiRequest(final String url) {
    try (final var reader = new InputStreamReader(
        URI.create(url).toURL().openConnection().getInputStream())) {
      return JsonParser.parseReader(reader).getAsJsonObject();
    } catch (IOException | IllegalStateException e) {
      LOGGER.error("Exception during api request", e);
      return null;
    }
  }
}
