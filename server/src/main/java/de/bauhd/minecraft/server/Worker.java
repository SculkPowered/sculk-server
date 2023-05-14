package de.bauhd.minecraft.server;

import de.bauhd.minecraft.server.entity.AbstractEntity;
import de.bauhd.minecraft.server.protocol.packet.play.KeepAlive;
import de.bauhd.minecraft.server.world.MinecraftWorld;
import net.kyori.adventure.text.Component;

public final class Worker extends Thread {

    private static final int TPS = 20;
    private static final int MILLIS_BETWEEN_TICK = 1000 / TPS;

    private final MinecraftWorld world;

    public Worker(final MinecraftWorld world) {
        super("Minecraft World " + world.getName() + " Worker");
        this.world = world;
    }

    @Override
    public void run() {
        while (this.world.isAlive()) {
            this.world.entities().forEach(AbstractEntity::tick);

            try {
                //noinspection BusyWait
                Thread.sleep(MILLIS_BETWEEN_TICK);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
