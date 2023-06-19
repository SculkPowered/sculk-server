package de.bauhd.minecraft.server.event;

import de.bauhd.minecraft.server.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

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
     * Registers an event listener consumer.
     * @param plugin the plugin that registers the event listener
     * @param event the event class
     * @param eventConsumer the consumer that consumes the event on call
     * @param <E> the event class type
     */
    default <E> void register(@NotNull Plugin plugin, @NotNull Class<E> event, @NotNull Consumer<E> eventConsumer) {
        this.register(plugin, event, EventOrder.NORMAL, eventConsumer);
    }

    /**
     * Registers an event listener consumer.
     * @param plugin the plugin that registers the event listener
     * @param event the event class
     * @param eventOrder the order
     * @param eventConsumer the consumer that consumes the event on call
     * @param <E> the event class type
     */
    <E> void register(@NotNull Plugin plugin, @NotNull Class<E> event, @NotNull EventOrder eventOrder, @NotNull Consumer<E> eventConsumer);

    /**
     * Unregisters all listeners of a specific plugin
     * @param plugin the plugin of the listeners
     */
    void unregister(@NotNull Plugin plugin);

    /**
     * Unregisters the specified listener.
     * @param plugin the plugin of the listener
     * @param listener the listener to unregister
     */
    void unregister(@NotNull Plugin plugin, @NotNull Object listener);

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
