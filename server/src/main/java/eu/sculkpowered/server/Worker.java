package eu.sculkpowered.server;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

final class Worker extends Thread {

  private static final int TPS = 20;
  private static final int MILLIS_BETWEEN_TICK = 1000 / TPS;

  private final SculkServer server;
  private final Queue<Runnable> tasks = new ConcurrentLinkedQueue<>();

  public Worker(final SculkServer server) {
    super("Sculk Worker");
    this.server = server;
  }

  @Override
  public void run() {
    while (this.server.isRunning()) {
      final var time = System.currentTimeMillis();

      final var tasks = this.tasks.iterator();
      while (tasks.hasNext()) {
        tasks.next().run();
        tasks.remove();
      }

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

  public void addTask(final Runnable task) {
    this.tasks.add(task);
  }
}
