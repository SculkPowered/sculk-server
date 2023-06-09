package de.bauhd.minecraft.server.world;

import de.bauhd.minecraft.server.world.chunk.MinecraftChunk;

public final class Worker extends Thread {

    private static final int TPS = 20;
    private static final int MILLIS_BETWEEN_TICK = 1000 / TPS;

    private final MinecraftWorld world;

    public Worker(final MinecraftWorld world) {
        super("Minecraft World " + world.getName() + " Worker");
        this.world = world;
    }

    @Override
    public void run() {
        while (this.world.isAlive()) {
            final var time = System.currentTimeMillis();

            this.world.chunks().values().forEach(MinecraftChunk::tick);

            try {
                //noinspection BusyWait
                Thread.sleep(MILLIS_BETWEEN_TICK - (System.currentTimeMillis() - time));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
