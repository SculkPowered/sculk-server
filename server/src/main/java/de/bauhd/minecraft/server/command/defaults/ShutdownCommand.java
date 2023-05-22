package de.bauhd.minecraft.server.command.defaults;

import de.bauhd.minecraft.server.AdvancedMinecraftServer;
import de.bauhd.minecraft.server.command.BrigadierCommand;

public final class ShutdownCommand extends BrigadierCommand {

    public ShutdownCommand(final AdvancedMinecraftServer server) {
        super(literal("shutdown")
                .executes(context -> {
                    server.shutdown(true);
                    return 0;
                })
                .build());
    }
}
