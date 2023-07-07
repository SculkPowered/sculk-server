package de.bauhd.minecraft.server.event;

import de.bauhd.minecraft.server.plugin.Plugin;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.function.Predicate;

public final class MineEventHandler implements EventHandler {

    private static final Logger LOGGER = LogManager.getLogger(MineEventHandler.class);

    private final Map<Class<?>, Registration[]> registrations = new HashMap<>();
    private final ExecutorService executor = Executors.newFixedThreadPool(
            Runtime.getRuntime().availableProcessors(), new EventThreadFactory());
    private final Comparator<Registration> orderComparator = Comparator.comparingInt(Registration::order);

    @Override
    public void register(@NotNull Plugin plugin, @NotNull Object listener) {
        for (final var method : listener.getClass().getMethods()) {
            final var annotation = method.getAnnotation(Subscribe.class);
            if (annotation != null) {
                if (method.getParameterCount() == 1) {
                    this.register(plugin, method.getParameters()[0].getType(), annotation.order(), listener, object -> {
                        try {
                            method.invoke(listener, object);
                        } catch (IllegalAccessException | InvocationTargetException e) {
                            throw new RuntimeException(e);
                        }
                    });
                } else {
                    LOGGER.error("Plugin {} tried to register a listener method with multiple parameters. {}",
                            plugin.getDescription().name(), method);
                }
            }
        }
    }

    @Override
    public void register(@NotNull Plugin plugin, @NotNull Object... listeners) {
        for (final var listener : listeners) {
            this.register(plugin, listener);
        }
    }

    @Override
    public <E> void register(@NotNull Plugin plugin, @NotNull Class<E> event, short eventOrder, @NotNull Consumer<E> eventConsumer) {
        this.register(plugin, event, eventOrder, eventConsumer, eventConsumer);
    }

    @SuppressWarnings("unchecked")
    private <E> void register(@NotNull Plugin plugin, @NotNull Class<E> event, short order,
                              @NotNull Object instance, @NotNull Consumer<E> eventConsumer) {
        final var registrations = new ArrayList<Registration>();
        final var array = this.registrations.get(event);
        if (array != null) {
            Collections.addAll(registrations, array);
        }
        registrations.add(new Registration(plugin, order, instance, (Consumer<Object>) eventConsumer));
        registrations.sort(this.orderComparator);
        this.registrations.put(event, registrations.toArray(new Registration[0]));
    }

    @Override
    public void unregister(@NotNull Plugin plugin) {
        this.unregisterIf(registration -> registration.plugin == plugin);
    }

    @Override
    public void unregister(@NotNull Plugin plugin, @NotNull Object listener) {
        this.unregisterIf(registration -> registration.plugin == plugin && registration.instance == listener);
    }

    private void unregisterIf(final Predicate<Registration> predicate) {
        for (final var entry : this.registrations.entrySet()) {
            final var list = new ArrayList<>(List.of(entry.getValue()));
            list.removeIf(predicate);
            this.registrations.put(entry.getKey(), list.toArray(new Registration[0]));
        }
    }

    @Override
    public <E> CompletableFuture<E> call(E event) {
        if (!this.registrations.containsKey(event.getClass())) {
            return CompletableFuture.completedFuture(event);
        }
        final var future = new CompletableFuture<E>();
        this.executor.execute(() -> this.call(event, future));
        return future;
    }

    @Override
    public <E> void justCall(E event) {
        if (this.registrations.containsKey(event.getClass())) {
            this.executor.execute(() -> this.call(event, null));
        }
    }

    @Override
    public <E> E callSync(E event) {
        if (this.registrations.containsKey(event.getClass())) {
            this.call(event, null);
        }
        return event;
    }

    public boolean shutdown() throws InterruptedException {
        this.executor.shutdown();
        return this.executor.awaitTermination(10, TimeUnit.SECONDS);
    }

    private <E> void call(E event, CompletableFuture<E> future) {
        for (final var registration : this.registrations.get(event.getClass())) {
            registration.consumer.accept(event);
        }
        if (future != null) {
            future.complete(event);
        }
    }

    private record Registration(Plugin plugin, short order, Object instance, Consumer<Object> consumer) {}
}
