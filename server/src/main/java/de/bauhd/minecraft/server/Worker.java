package de.bauhd.minecraft.server;

import de.bauhd.minecraft.server.protocol.packet.play.KeepAlive;
import net.kyori.adventure.text.Component;

public final class Worker {

    private static final int TPS = 20;
    private static final int MILLIS_BETWEEN_TICK = 1000 / TPS;

    private final AdvancedMinecraftServer server;

    public Worker(final AdvancedMinecraftServer server) {
        this.server = server;
    }

    public void start() {
        while (this.server.isRunning()) {
            final var time = System.currentTimeMillis();

            final var keepAlive = new KeepAlive(time);
            this.server.getMinecraftPlayers().forEach(player -> {
                final var elapsedTime = System.currentTimeMillis() - player.lastSendKeepAlive();

                if (player.keepAlivePending()) {
                    if (elapsedTime > 30000) { // disconnect after 30 seconds
                        player.disconnect(Component.translatable("disconnect.timeout"));
                    }
                } else {
                    if (elapsedTime > 15000) { // send all 15 seconds
                        player.setKeepAlivePending(true);
                        player.setLastSendKeepAlive(time);
                        player.send(keepAlive);
                    }
                }
            });

            try {
                //noinspection BusyWait
                Thread.sleep(MILLIS_BETWEEN_TICK);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
