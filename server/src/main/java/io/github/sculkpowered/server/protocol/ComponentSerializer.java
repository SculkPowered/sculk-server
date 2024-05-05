package io.github.sculkpowered.server.protocol;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.internal.LazilyParsedNumber;
import java.util.ArrayList;
import java.util.Objects;
import net.kyori.adventure.nbt.BinaryTag;
import net.kyori.adventure.nbt.BinaryTagType;
import net.kyori.adventure.nbt.BinaryTagTypes;
import net.kyori.adventure.nbt.ByteArrayBinaryTag;
import net.kyori.adventure.nbt.ByteBinaryTag;
import net.kyori.adventure.nbt.CompoundBinaryTag;
import net.kyori.adventure.nbt.DoubleBinaryTag;
import net.kyori.adventure.nbt.EndBinaryTag;
import net.kyori.adventure.nbt.FloatBinaryTag;
import net.kyori.adventure.nbt.IntArrayBinaryTag;
import net.kyori.adventure.nbt.IntBinaryTag;
import net.kyori.adventure.nbt.ListBinaryTag;
import net.kyori.adventure.nbt.LongArrayBinaryTag;
import net.kyori.adventure.nbt.LongBinaryTag;
import net.kyori.adventure.nbt.ShortBinaryTag;
import net.kyori.adventure.nbt.StringBinaryTag;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.gson.GsonComponentSerializer;
import org.jetbrains.annotations.NotNull;

/**
 * Temporary class that will be removed as soon as Adventure releases its own serializer.
 * Serialisation based on <a
 * href="https://github.com/PaperMC/Velocity/blob/dev/3.0.0/proxy/src/main/java/com/velocitypowered/proxy/protocol/packet/chat/ComponentHolder.java#L113">...</a>
 */
public final class ComponentSerializer {

  public static @NotNull BinaryTag serializeToNbt(final Component component) {
    return serializeToNbt(GsonComponentSerializer.gson().serializeToTree(component));
  }

  private static @NotNull BinaryTag serializeToNbt(final JsonElement json) {
    if (json instanceof JsonPrimitive jsonPrimitive) {
      if (jsonPrimitive.isNumber()) {
        final var number = json.getAsNumber();

        if (number instanceof Byte) {
          return ByteBinaryTag.byteBinaryTag((Byte) number);
        } else if (number instanceof Short) {
          return ShortBinaryTag.shortBinaryTag((Short) number);
        } else if (number instanceof Integer) {
          return IntBinaryTag.intBinaryTag((Integer) number);
        } else if (number instanceof Long) {
          return LongBinaryTag.longBinaryTag((Long) number);
        } else if (number instanceof Float) {
          return FloatBinaryTag.floatBinaryTag((Float) number);
        } else if (number instanceof Double) {
          return DoubleBinaryTag.doubleBinaryTag((Double) number);
        } else if (number instanceof LazilyParsedNumber) {
          return IntBinaryTag.intBinaryTag(number.intValue());
        }
      } else if (jsonPrimitive.isString()) {
        return StringBinaryTag.stringBinaryTag(jsonPrimitive.getAsString());
      } else if (jsonPrimitive.isBoolean()) {
        return ByteBinaryTag.byteBinaryTag((byte) (jsonPrimitive.getAsBoolean() ? 1 : 0));
      } else {
        throw new IllegalArgumentException("Unknown JSON primitive: " + jsonPrimitive);
      }
    } else if (json instanceof JsonObject) {
      final var compound = CompoundBinaryTag.builder();

      for (final var property : ((JsonObject) json).entrySet()) {
        compound.put(property.getKey(), serializeToNbt(property.getValue()));
      }
      return compound.build();
    } else if (json instanceof JsonArray) {
      final var jsonArray = ((JsonArray) json).asList();

      if (jsonArray.isEmpty()) {
        return ListBinaryTag.empty();
      }

      final var tagItems = new ArrayList<BinaryTag>(jsonArray.size());
      BinaryTagType<? extends BinaryTag> listType = null;

      for (final var element : jsonArray) {
        final var tag = serializeToNbt(element);
        tagItems.add(tag);

        if (listType == null) {
          listType = tag.type();
        } else if (listType != tag.type()) {
          listType = BinaryTagTypes.COMPOUND;
        }
      }

      switch (listType.id()) {
        case 1://BinaryTagTypes.BYTE:
          final var bytes = new byte[jsonArray.size()];
          for (int i = 0; i < bytes.length; i++) {
            bytes[i] = (Byte) jsonArray.get(i).getAsNumber();
          }
          return ByteArrayBinaryTag.byteArrayBinaryTag(bytes);
        case 3://BinaryTagTypes.INT:
          final var ints = new int[jsonArray.size()];
          for (int i = 0; i < ints.length; i++) {
            ints[i] = (Integer) jsonArray.get(i).getAsNumber();
          }
          return IntArrayBinaryTag.intArrayBinaryTag(ints);
        case 4://BinaryTagTypes.LONG:
          final var longs = new long[jsonArray.size()];
          for (int i = 0; i < longs.length; i++) {
            longs[i] = (Long) jsonArray.get(i).getAsNumber();
          }
          return LongArrayBinaryTag.longArrayBinaryTag(longs);
        case 10://BinaryTagTypes.COMPOUND:
          tagItems.replaceAll(tag -> {
            if (tag.type() == BinaryTagTypes.COMPOUND) {
              return tag;
            } else {
              return CompoundBinaryTag.builder().put("", tag).build();
            }
          });
          break;
      }
      return ListBinaryTag.listBinaryTag(listType, tagItems);
    }
    return EndBinaryTag.endBinaryTag();
  }

  public static Component deserialize(final @NotNull BinaryTag tag) {
    return GsonComponentSerializer.gson().deserializeFromTree(deserialize0(tag));
  }

  private static JsonElement deserialize0(final @NotNull BinaryTag tag) {
    switch (tag.type().id()) {
      case 1://BinaryTagTypes.BYTE:
        return new JsonPrimitive(((ByteBinaryTag) tag).value());
      case 2://BinaryTagTypes.SHORT:
        return new JsonPrimitive(((ShortBinaryTag) tag).value());
      case 3://BinaryTagTypes.INT:
        return new JsonPrimitive(((IntBinaryTag) tag).value());
      case 4://BinaryTagTypes.LONG:
        return new JsonPrimitive(((LongBinaryTag) tag).value());
      case 5://BinaryTagTypes.FLOAT:
        return new JsonPrimitive(((FloatBinaryTag) tag).value());
      case 6://BinaryTagTypes.DOUBLE:
        return new JsonPrimitive(((DoubleBinaryTag) tag).value());
      case 7://BinaryTagTypes.BYTE_ARRAY:
        final var byteArray = ((ByteArrayBinaryTag) tag).value();
        final var jsonByteArray = new JsonArray(byteArray.length);
        for (var b : byteArray) {
          jsonByteArray.add(new JsonPrimitive(b));
        }
        return jsonByteArray;
      case 8://BinaryTagTypes.STRING:
        return new JsonPrimitive(((StringBinaryTag) tag).value());
      case 9://BinaryTagTypes.LIST:
        final var items = (ListBinaryTag) tag;
        final var jsonList = new JsonArray(items.size());
        for (final var subTag : items) {
          jsonList.add(deserialize0(subTag));
        }
        return jsonList;
      case 10://BinaryTagTypes.COMPOUND:
        final var compound = (CompoundBinaryTag) tag;
        final var jsonObject = new JsonObject();
        for (final var key : compound.keySet()) {
          jsonObject.add(key.isEmpty() ? "text" : key, deserialize0(
              Objects.requireNonNull(compound.get(key))));
        };
        return jsonObject;
      case 11://BinaryTagTypes.INT_ARRAY:
        final var intArray = ((IntArrayBinaryTag) tag).value();
        final var jsonIntArray = new JsonArray(intArray.length);
        for (int i : intArray) {
          jsonIntArray.add(new JsonPrimitive(i));
        }
        return jsonIntArray;
      case 12://BinaryTagTypes.LONG_ARRAY:
        final var longArray = ((LongArrayBinaryTag) tag).value();
        final var jsonLongArray = new JsonArray(longArray.length);
        for (var l : longArray) {
          jsonLongArray.add(new JsonPrimitive(l));
        }
        return jsonLongArray;
      default:
        throw new IllegalArgumentException("Unknown tag: " + tag);
    }
  }
}
