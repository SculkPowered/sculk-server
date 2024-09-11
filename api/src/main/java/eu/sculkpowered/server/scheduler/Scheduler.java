package eu.sculkpowered.server.scheduler;

import eu.sculkpowered.server.plugin.Plugin;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.Range;

public interface Scheduler {

  /**
   * Creates a new scheduler task.
   *
   * @param plugin   the plugin
   * @param runnable the runnable
   * @return the new created task
   * @since 1.0.0
   */
  @NotNull Task newTask(@NotNull Plugin plugin, @NotNull Runnable runnable);

  interface Task {

    /**
     * Delays the task with the specified time.
     *
     * @param delay the delay in the specified unit
     * @param unit  the time unit
     * @return the current task
     * @since 1.0.0
     */
    @NotNull Task delay(@Range(from = 0, to = Integer.MAX_VALUE) int delay, @NotNull TimeUnit unit);

    /**
     * Repeats the task with the specified time.
     *
     * @param repeat the repetition in the specified unit
     * @param unit   the time unit
     * @return the current task
     * @since 1.0.0
     */
    @NotNull Task repeat(@Range(from = 0, to = Integer.MAX_VALUE) int repeat,
        @NotNull TimeUnit unit);

    /**
     * Schedules the task.
     *
     * @since 1.0.0
     */
    void schedule();

    /**
     * The future of the executed task.
     *
     * @return the future of the executed task
     * @since 1.0.0
     */
    @Nullable ScheduledFuture<?> future();

    /**
     * Cancels the task.
     *
     * @since 1.0.0
     */
    void cancel();
  }
}
