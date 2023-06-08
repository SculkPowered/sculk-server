package de.bauhd.minecraft.server.event;

import de.bauhd.minecraft.server.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;

public final class MineEventHandler implements EventHandler {

    private final Map<Class<?>, List<Consumer<Object>>> consumers = new HashMap<>();
    private final ExecutorService executor = Executors.newFixedThreadPool(
            Runtime.getRuntime().availableProcessors(), new EventThreadFactory());

    @Override
    public void register(@NotNull Plugin plugin, @NotNull Object listener) {
        for (final var method : this.findMethods(listener.getClass())) {
            final var clazz = method.getParameters()[0].getType();
            final boolean create = !this.consumers.containsKey(clazz);
            if (create) {
                this.consumers.put(clazz, new ArrayList<>());
            }
            this.consumers.get(clazz).add(object -> {
                try {
                    method.invoke(listener, object);
                } catch (IllegalAccessException | InvocationTargetException e) {
                    throw new RuntimeException(e);
                }
            });
        }
    }

    @Override
    public void register(@NotNull Plugin plugin, @NotNull Object... listeners) {
        for (final var listener : listeners) {
            this.register(plugin, listener);
        }
    }

    @Override
    public <E> CompletableFuture<E> call(E event) {
        if (!this.consumers.containsKey(event.getClass())) {
            return CompletableFuture.completedFuture(event);
        }
        final var future = new CompletableFuture<E>();
        this.executor.execute(() -> this.call(event, future));
        return future;
    }

    private <E> void call(E event, CompletableFuture<E> future) {
        for (final var consumer : this.consumers.get(event.getClass())) {
            consumer.accept(event);
        }
        future.complete(event);
    }

    private List<Method> findMethods(final Class<?> clazz) {
        final var list = new ArrayList<Method>();
        for (final var method : clazz.getMethods()) {
            if (method.isAnnotationPresent(Subscribe.class)) {
                if (method.getParameterCount() == 1) {
                    list.add(method);
                }
            }
        }
        return list;
    }
}
