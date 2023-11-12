package io.github.sculkpowered.server;

final class Worker extends Thread {

  private static final int TPS = 20;
  private static final int MILLIS_BETWEEN_TICK = 1000 / TPS;

  private final SculkServer server;

  public Worker(final SculkServer server) {
    super("Sculk Worker");
    this.server = server;
  }

  @Override
  public void run() {
    while (this.server.isRunning()) {
      final var time = System.currentTimeMillis();

      for (final var world : this.server.worlds()) {
        // tick over chunks -> and entities
        for (final var chunk : world.chunks().values()) {
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
}
