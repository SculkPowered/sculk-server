package de.bauhd.minecraft.server;

public final class Main {

    static {
        System.setProperty("java.util.logging.manager", "org.apache.logging.log4j.jul.LogManager");
    }

    public static void main(String[] args) {
        new AdvancedMinecraftServer();
    }
}
