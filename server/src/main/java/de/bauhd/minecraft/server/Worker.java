package de.bauhd.minecraft.server;

import de.bauhd.minecraft.server.protocol.packet.play.KeepAlive;

public final class Worker {

    private static final int TPS = 20;
    private static final int MILLIS_BETWEEN_TICK = 1000 / TPS;

    private final AdvancedMinecraftServer server;

    public Worker(final AdvancedMinecraftServer server) {
        this.server = server;
    }

    public void start() {
        while (this.server.isRunning()) {
            this.sendKeepAlive();

            try {
                //noinspection BusyWait
                Thread.sleep(MILLIS_BETWEEN_TICK);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void sendKeepAlive() {
        final var keepAlive = new KeepAlive(System.currentTimeMillis());
        this.server.sendAll(keepAlive);
    }

}
