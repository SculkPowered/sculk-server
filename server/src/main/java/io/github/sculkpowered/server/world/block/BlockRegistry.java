package io.github.sculkpowered.server.world.block;

import io.github.sculkpowered.server.registry.SimpleRegistry;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import net.kyori.adventure.key.Key;
import org.intellij.lang.annotations.Subst;

public final class BlockRegistry extends SimpleRegistry<BlockState> {

  private BlockRegistry() {
    super("minecraft:block");
  }

  public static void addBlocks() {
    try (final var reader = new BufferedReader(
        new InputStreamReader(Objects.requireNonNull(BlockParent.class.getClassLoader()
            .getResourceAsStream("registries/blocks"))))) {
      final var registry = new BlockRegistry();
      String line;
      final var constructors = new HashMap<String, Constructor<?>>();
      final var entryArray = new Map.Entry<?, ?>[0];
      while ((line = reader.readLine()) != null) {
        @Subst("") final var split = line.split(",");
        final var block = new BlockParent(Key.key(Key.MINECRAFT_NAMESPACE, split[0]));
        var id = Integer.parseInt(split[2]);
        final var defId = Integer.parseInt(split[3]);
        var capacity = split.length - 4;
        capacity = (capacity == 0 ? 1 : capacity);
        final var states = new SculkBlockState[capacity];
        try {
          var clazz = split[1];
          if (clazz.contains("$")) {
            clazz = "SculkBlockState" + clazz;
          }
          clazz = "io.github.sculkpowered.server.world.block." + clazz;
          final var constructor = constructors.computeIfAbsent(clazz, s -> {
            try {
              return Class.forName(s)
                  .getDeclaredConstructor(BlockParent.class, int.class, Map.class);
            } catch (NoSuchMethodException | ClassNotFoundException e) {
              throw new RuntimeException(e);
            }
          });
          if (capacity == 1) {
            states[0] = (SculkBlockState) constructor.newInstance(block, id, Map.of());
            registry.byId.put(id, states[0]);
          } else {
            for (var i = 0; i < capacity; i++) {
              final var map = new HashMap<String, String>(capacity);
              for (final var entries : split[i + 4].split("\\\\")) {
                final var entry = entries.split("/");
                map.put(entry[0], entry[1]);
              }
              final var state = (SculkBlockState) constructor.newInstance(block, id,
                  Map.ofEntries(map.entrySet().toArray(entryArray)));
              states[i] = state;
              registry.byId.put(id, state);
              id++;
            }
          }
        } catch (InvocationTargetException | InstantiationException | IllegalAccessException e) {
          throw new RuntimeException(e);
        }
        block.setStates(states);
        registry.byKey.put(block.key().asString(), states[defId]);
      }
      registry.def = registry.get("minecraft:air");
      Blocks.set(registry);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

}
