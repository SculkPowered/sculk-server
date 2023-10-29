package io.github.sculkpowered.server.event;

import io.github.sculkpowered.server.plugin.Plugin;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;
import java.util.function.Predicate;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

public final class SculkEventHandler implements EventHandler {

  private static final Logger LOGGER = LogManager.getLogger(SculkEventHandler.class);

  private final Map<Class<?>, Registration[]> registrations = new HashMap<>();

  @Override
  public void register(@NotNull Plugin plugin, @NotNull Object listener) {
    for (final var method : listener.getClass().getMethods()) {
      final var annotation = method.getAnnotation(Subscribe.class);
      if (annotation != null) {
        if (method.getParameterCount() == 1) {
          this.register(plugin, method.getParameters()[0].getType(),
              annotation.order(), annotation.async(), listener,
              object -> {
                try {
                  method.invoke(listener, object);
                } catch (IllegalAccessException | InvocationTargetException e) {
                  throw new RuntimeException(e);
                }
              });
        } else {
          LOGGER.error("Plugin {} tried to register a listener method with multiple parameters. {}",
              plugin.description().name(), method);
        }
      }
    }
  }

  @Override
  public <E> void register(@NotNull Plugin plugin, @NotNull Class<E> event, short eventOrder,
      @NotNull Consumer<E> eventConsumer) {
    this.register(plugin, event, eventOrder, false, eventConsumer, eventConsumer);
  }

  @SuppressWarnings("unchecked")
  private <E> void register(@NotNull Plugin plugin, @NotNull Class<E> event, short order,
      boolean async, @NotNull Object instance, @NotNull Consumer<E> eventConsumer) {
    var registrations = this.registrations.get(event);
    if (registrations != null) {
      registrations = Arrays.copyOf(registrations, registrations.length + 1);
    } else {
      registrations = new Registration[1];
    }
    registrations[registrations.length - 1] = new Registration(plugin, order, async, instance,
        (Consumer<Object>) eventConsumer);
    Arrays.sort(registrations);
    this.registrations.put(event, registrations);
  }

  @Override
  public void unregister(@NotNull Plugin plugin) {
    this.unregisterIf(registration -> registration.plugin == plugin);
  }

  @Override
  public void unregister(@NotNull Plugin plugin, @NotNull Object listener) {
    this.unregisterIf(
        registration -> registration.plugin == plugin && registration.instance == listener);
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
    this.call(event, future);
    return future;
  }

  @Override
  public <E> void justCall(E event) {
    if (this.registrations.containsKey(event.getClass())) {
      this.call(event, null);
    }
  }

  private <E> void call(E event, CompletableFuture<E> future) {
    for (final var registration : this.registrations.get(event.getClass())) {
      if (registration.async) {
        registration.plugin.executorService().execute(() -> registration.consumer.accept(event));
      } else {
        registration.consumer.accept(event);
      }
    }
    if (future != null) {
      future.complete(event);
    }
  }

  private record Registration(Plugin plugin, short order, boolean async, Object instance,
                              Consumer<Object> consumer) implements Comparable<Registration> {

    @Override
    public int compareTo(@NotNull SculkEventHandler.Registration other) {
      return this.order - other.order;
    }
  }
}
