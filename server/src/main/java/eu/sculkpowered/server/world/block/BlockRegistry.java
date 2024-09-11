package eu.sculkpowered.server.world.block;

import eu.sculkpowered.server.registry.SimpleRegistry;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import net.kyori.adventure.key.Key;
import org.intellij.lang.annotations.Subst;

public final class BlockRegistry {

  public static SimpleRegistry<BlockState> get() {
    final var byId = new Int2ObjectOpenHashMap<BlockState>();
    final var byKey = new HashMap<String, BlockState>();
    try (final var reader = new BufferedReader(
        new InputStreamReader(Objects.requireNonNull(BlockParent.class.getClassLoader()
            .getResourceAsStream("registries/blocks"))))) {
      String line;
      while ((line = reader.readLine()) != null) {
        @Subst("") final var split = line.split(",");
        var id = Integer.parseInt(split[2]);
        final var defId = Integer.parseInt(split[3]);
        var capacity = split.length - 4;
        capacity = (capacity == 0 ? 1 : capacity);
        final var states = new SculkBlockState[capacity];
        final var block = new BlockParent(Key.key(Key.MINECRAFT_NAMESPACE, split[0]),
            Float.parseFloat(split[1]));
        if (capacity == 1) {
          states[0] = new SculkBlockState(block, id, Map.of());
          byId.put(id, states[0]);
        } else {
          for (var i = 0; i < capacity; i++) {
            final var map = new HashMap<String, String>(capacity);
            for (final var entries : split[i + 4].split("\\\\")) {
              final var entry = entries.split("/");
              map.put(entry[0], entry[1]);
            }
            @SuppressWarnings("unchecked") final var state = new SculkBlockState(block, id,
                Map.ofEntries(map.entrySet().toArray(new Map.Entry[0])));
            states[i] = state;
            byId.put(id, state);
            id++;
          }
        }
        block.setStates(states);
        byKey.put(block.key().asString(), states[defId]);
      }
      return new SimpleRegistry<>("minecraft:block", byKey, byId, byKey.get("minecraft:air"));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
