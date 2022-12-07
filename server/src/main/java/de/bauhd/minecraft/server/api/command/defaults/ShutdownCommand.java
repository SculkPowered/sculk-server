package de.bauhd.minecraft.server.api.command.defaults;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import de.bauhd.minecraft.server.AdvancedMinecraftServer;
import de.bauhd.minecraft.server.api.command.BrigadierCommand;
import de.bauhd.minecraft.server.api.command.CommandSender;

public final class ShutdownCommand extends BrigadierCommand {

    public ShutdownCommand() {
        super(LiteralArgumentBuilder.<CommandSender>literal("shutdown")
                .executes(context -> {
                    AdvancedMinecraftServer.getInstance().shutdown(true);
                    return 0;
                })
                .build());
    }
}
