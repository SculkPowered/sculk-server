package io.github.sculkpowered.server.test.event;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import io.github.sculkpowered.server.event.EventOrder;
import io.github.sculkpowered.server.event.SculkEventHandler;
import io.github.sculkpowered.server.event.lifecycle.ServerInitializeEvent;
import io.github.sculkpowered.server.test.plugin.TestPlugin;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Consumer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

public final class EventTest {

  private static final SculkEventHandler EVENT_HANDLER = new SculkEventHandler();

  @Test
  void test() {
    final var calls = new AtomicInteger();
    final var listener1 = new AtomicLong();
    final var listener2 = new AtomicLong();
    final var initEvent = new ServerInitializeEvent();

    EVENT_HANDLER.register(TestPlugin.PLUGIN, ServerInitializeEvent.class, EventOrder.LAST,
        event -> {
          calls.getAndIncrement();
          listener1.set(System.nanoTime());
        });
    final Consumer<ServerInitializeEvent> consumer = event -> {
      calls.getAndIncrement();
      listener2.set(System.nanoTime());
    };
    EVENT_HANDLER.register(TestPlugin.PLUGIN, ServerInitializeEvent.class, consumer);
    EVENT_HANDLER.call(initEvent).join();
    EVENT_HANDLER.unregister(TestPlugin.PLUGIN, consumer);
    EVENT_HANDLER.call(initEvent).join();
    EVENT_HANDLER.unregister(TestPlugin.PLUGIN);
    EVENT_HANDLER.call(initEvent).join();

    assertEquals(3, calls.get());
    assertTrue(listener1.get() > listener2.get());
  }

  @AfterAll
  static void shutdown() throws InterruptedException {
    EVENT_HANDLER.shutdown();
  }
}
