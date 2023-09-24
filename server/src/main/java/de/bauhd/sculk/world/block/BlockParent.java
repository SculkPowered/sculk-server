package de.bauhd.sculk.world.block;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public final class BlockParent {

    private final String name;
    private SculkBlockState[] states;

    private BlockParent(final String name) {
        this.name = name;
    }

    public static void addBlocks() {
        final var byName = new HashMap<String, BlockState>();
        final var byId = new HashMap<Integer, BlockState>();
        try (final var reader = new BufferedReader(new InputStreamReader(Objects.requireNonNull(BlockParent.class.getClassLoader()
                .getResourceAsStream("registries/blocks"))))) {
            String line;
            final var constructors = new HashMap<String, Constructor<?>>();
            while ((line = reader.readLine()) != null) {
                final var split = line.split(",");
                final var block = new BlockParent(split[0]);
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
                    clazz = "de.bauhd.sculk.world.block." + clazz;
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
                    } else {
                        for (var i = 0; i < capacity; i++) {
                            final var map = new HashMap<String, String>(capacity);
                            for (final var entries : split[i + 4].split("\\\\")) {
                                final var entry = entries.split("/");
                                map.put(entry[0], entry[1]);
                            }
                            final var state = (SculkBlockState) constructor.newInstance(block, id, Map.copyOf(map));
                            states[i] = state;
                            byId.put(id, state);
                            id++;
                        }
                    }
                } catch (InvocationTargetException | InstantiationException | IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
                block.setStates(states);
                byName.put(block.name, states[defId]);
            }
            Blocks.set(byName, byId);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void setStates(SculkBlockState[] states) {
        this.states = states;
    }

    public BlockState state(Map<String, String> properties) {
        for (final var state : this.states) {
            if (state.getProperties().equals(properties)) {
                return state;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "BlockParent{" +
                "name=" + this.name +
                '}';
    }
}
