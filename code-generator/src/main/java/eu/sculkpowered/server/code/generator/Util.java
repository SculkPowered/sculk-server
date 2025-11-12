package eu.sculkpowered.server.code.generator;

public final class Util {

  public static String keyToValue(final String key) {
    return key.split(":")[1];
  }

  public static String snakeToCamelCase(final String key) {
    final var stringBuilder = new StringBuilder();
    for (final var s : key.split("_")) {
      stringBuilder.append(Character.toUpperCase(s.charAt(0))).append(s.substring(1));
    }
    return stringBuilder.toString();
  }
}
