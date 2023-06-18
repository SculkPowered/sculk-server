package de.bauhd.minecraft.server.event;

import de.bauhd.minecraft.server.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

/**
 * Handles the registration and execution of events.
 */
public interface EventHandler {

    /**
     * Registers the event listener.
     * @param plugin the plugin that registers the event listener
     * @param listener the listener
     */
    void register(@NotNull Plugin plugin, @NotNull Object listener);

    /**
     * Registers the event listeners.
     * @param plugin the plugin that registers the event listener
     * @param listeners the listeners
     */
    void register(@NotNull Plugin plugin, @NotNull Object... listeners);

    /**
     * Calls the event.
     * @param event the event to call
     * @return a {@link CompletableFuture} with the called event.
     * @param <E> the event type to call
     */
    <E> CompletableFuture<E> call(E event);

    /**
     * Calls the event.
     * @param event the event to call
     * @param <E> the event type to call
     */
    <E> void justCall(E event);


    /**
     * Calls the event sync.
     * @param event the event to call
     * @return the called event.
     * @param <E> the event type to call
     */
    <E> E callSync(E event);
}
