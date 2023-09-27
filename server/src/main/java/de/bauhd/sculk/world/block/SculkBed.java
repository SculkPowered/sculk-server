package de.bauhd.sculk.world.block;

import org.jetbrains.annotations.NotNull;

import java.util.Map;

final class SculkBed extends SculkBlockState implements Bed {

    SculkBed(BlockParent block, int id, Map<String, String> properties) {
        super(block, id, properties);
    }

    @Override
    public boolean occupied() {
        return this.properties.get("occupied").equals("true");
    }

    @Override
    public @NotNull Bed occupied(boolean occupied) {
        return (Bed) this.property("occupied", occupied);
    }

    @Override
    public @NotNull Part part() {
        return Part.valueOf(this.properties.get("part"));
    }

    @Override
    public @NotNull Bed part(@NotNull Part part) {
        return (Bed) this.property("part", part.name().toLowerCase());
    }
}
