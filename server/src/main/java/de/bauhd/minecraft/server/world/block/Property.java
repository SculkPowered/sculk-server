package de.bauhd.minecraft.server.world.block;

import java.util.Collection;
import java.util.List;

public abstract class Property<T> {

    public abstract Collection<T> values();

    public static final class BooleanProperty extends Property<Boolean> {

        private static final List<Boolean> VALUES = List.of(true, false);

        @Override
        public Collection<Boolean> values() {
            return VALUES;
        }
    }

}
