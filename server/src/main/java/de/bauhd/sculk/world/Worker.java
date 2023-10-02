package de.bauhd.sculk.world;

final class Worker extends Thread {

    private static final int TPS = 20;
    private static final int MILLIS_BETWEEN_TICK = 1000 / TPS;

    private final SculkWorld world;

    public Worker(final SculkWorld world) {
        super("Sculk World " + world.getName() + " Worker");
        this.world = world;
    }

    @Override
    public void run() {
        while (this.world.isAlive()) {
            final var time = System.currentTimeMillis();

            // tick over chunks -> and entities
            for (final var chunk : this.world.chunks().values()) {
                chunk.tick();
            }

            final var elapsed = System.currentTimeMillis() - time;
            if (elapsed < MILLIS_BETWEEN_TICK) {
                try {
                    //noinspection BusyWait
                    Thread.sleep(MILLIS_BETWEEN_TICK - elapsed);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
