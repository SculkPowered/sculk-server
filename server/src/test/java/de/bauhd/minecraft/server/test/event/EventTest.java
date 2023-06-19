package de.bauhd.minecraft.server.test.event;

import de.bauhd.minecraft.server.event.EventOrder;
import de.bauhd.minecraft.server.event.MineEventHandler;
import de.bauhd.minecraft.server.event.lifecycle.ServerInitializeEvent;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Consumer;

import static de.bauhd.minecraft.server.test.plugin.TestPlugin.PLUGIN;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public final class EventTest {

    private static final MineEventHandler EVENT_HANDLER = new MineEventHandler();

    @Test
    void test() {
        final var calls = new AtomicInteger();
        final var listener1 = new AtomicLong();
        final var listener2 = new AtomicLong();
        final var initEvent = new ServerInitializeEvent();

        EVENT_HANDLER.register(PLUGIN, ServerInitializeEvent.class, EventOrder.LAST, event -> {
            calls.getAndIncrement();
            listener1.set(System.nanoTime());
        });
        final Consumer<ServerInitializeEvent> consumer = event -> {
            calls.getAndIncrement();
            listener2.set(System.nanoTime());
        };
        EVENT_HANDLER.register(PLUGIN, ServerInitializeEvent.class, consumer);
        EVENT_HANDLER.call(initEvent);
        EVENT_HANDLER.unregister(PLUGIN, consumer);
        EVENT_HANDLER.call(initEvent);
        EVENT_HANDLER.unregister(PLUGIN);
        EVENT_HANDLER.call(initEvent);

        assertEquals(3, calls.get());
        assertTrue(listener1.get() > listener2.get());
    }

    @AfterAll
    static void shutdown() throws InterruptedException {
        EVENT_HANDLER.shutdown();
    }
}
