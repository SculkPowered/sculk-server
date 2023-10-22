package io.github.sculkpowered.server.event;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;
import org.jetbrains.annotations.NotNull;

public final class EventThreadFactory implements ThreadFactory {

  private final AtomicInteger count = new AtomicInteger(1);

  @Override
  public Thread newThread(@NotNull Runnable runnable) {
    final var thread = new Thread(runnable,
        "Sculk Event Executor - " + this.count.getAndIncrement());
    thread.setDaemon(true);
    return thread;
  }
}
