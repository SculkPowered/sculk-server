package io.github.sculkpowered.server.scheduler;

import io.github.sculkpowered.server.plugin.Plugin;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class SculkScheduler implements Scheduler {

  private final ScheduledExecutorService executorService;

  public SculkScheduler() {
    this.executorService = Executors.newSingleThreadScheduledExecutor(runnable -> {
      final var thread = new Thread(runnable, "Sculk Scheduler");
      thread.setDaemon(true);
      return thread;
    });
  }

  @Override
  public @NotNull Scheduler.Task newTask(@NotNull Plugin plugin, @NotNull Runnable runnable) {
    return new Task(plugin, runnable);
  }

  private final class Task implements Scheduler.Task, Runnable {

    private final Plugin plugin;
    private final Runnable runnable;
    private int delay;
    private TimeUnit delayUnit;
    private int repeat;
    private TimeUnit repeatUnit;
    private ScheduledFuture<?> future;

    private Task(final Plugin plugin, final Runnable runnable) {
      this.plugin = plugin;
      this.runnable = runnable;
    }

    @Override
    public @NotNull Scheduler.Task delay(int delay, @NotNull TimeUnit unit) {
      this.delay = delay;
      this.delayUnit = unit;
      return this;
    }

    @Override
    public @NotNull Scheduler.Task repeat(int repeat, @NotNull TimeUnit unit) {
      this.repeat = repeat;
      this.repeatUnit = unit;
      return this;
    }

    @Override
    public void schedule() {
      if (this.repeatUnit == null) { // no repeat
        this.future = SculkScheduler.this.executorService.schedule(this, this.delay,
            this.delayUnit);
      } else {
        this.future = SculkScheduler.this.executorService
            .scheduleAtFixedRate(this, this.delay, this.repeat, this.repeatUnit);
      }
    }

    @Override
    public @Nullable ScheduledFuture<?> getFuture() {
      return this.future;
    }

    @Override
    public void cancel() {
      this.future.cancel(false);
    }

    @Override
    public void run() {
      this.plugin.getExecutorService().execute(this.runnable);
    }
  }
}
