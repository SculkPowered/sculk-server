package de.bauhd.minecraft.server.api.entity;

import java.util.concurrent.atomic.AtomicInteger;

public abstract class AbstractEntity implements Entity {

    private static final AtomicInteger CURRENT_ID = new AtomicInteger(0);

    private final int id = CURRENT_ID.getAndIncrement();

    @Override
    public int getId() {
        return this.id;
    }
}
