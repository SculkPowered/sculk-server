package de.bauhd.sculk.code.generator;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.nio.file.Path;
import java.util.Map;

final class Constants {

    public static final Gson GSON = new Gson();

    public static final Path API_PACKAGE = Path.of("api", "src", "main", "java", "de", "bauhd", "sculk");
    public static final Path SERVER_PACKAGE = Path.of("server", "src", "main", "java", "de", "bauhd", "sculk");

    public static final Type STRING_JSON_MAP = TypeToken.getParameterized(Map.class, String.class, JsonObject.class).getType();
    public static final Type STRING_STRING_MAP = TypeToken.getParameterized(Map.class, String.class, String.class).getType();
    public static final Type STRING_STRING_ARRAY_MAP = TypeToken.getParameterized(Map.class, String.class, String[].class).getType();
    public static final Type JSON_OBJECT_ARRAY = TypeToken.getArray(JsonObject.class).getType();
}
