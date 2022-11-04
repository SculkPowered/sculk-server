package de.bauhd.minecraft.server;

import de.bauhd.minecraft.server.api.entity.Player;
import de.bauhd.minecraft.server.protocol.packet.play.KeepAlive;

import java.util.ArrayList;
import java.util.List;

public final class Worker {

    private static final int TPS = 20;
    private static final int MILLIS_BETWEEN_TICK = 1000 / TPS;

    // TODO
    public static final List<Player> PLAYERS = new ArrayList<>();

    private boolean running = true;

    public void start() {
        while (this.running) {
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

        PLAYERS.forEach(player -> player.send(keepAlive));
    }

}
