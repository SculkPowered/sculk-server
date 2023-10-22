package io.github.sculkpowered.server.scheduler;

import io.github.sculkpowered.server.plugin.Plugin;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.Range;

public interface Scheduler {

  @NotNull Task newTask(@NotNull Plugin plugin, @NotNull Runnable runnable);

  interface Task {

    @NotNull Task delay(@Range(from = 0, to = Integer.MAX_VALUE) int delay, @NotNull TimeUnit unit);

    @NotNull Task repeat(@Range(from = 0, to = Integer.MAX_VALUE) int repeat, @NotNull TimeUnit unit);

    void schedule();

    @Nullable ScheduledFuture<?> getFuture();

    /**
     * Cancels the task.
     */
    void cancel();
  }
}
