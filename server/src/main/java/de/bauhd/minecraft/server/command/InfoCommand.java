package de.bauhd.minecraft.server.command;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import de.bauhd.minecraft.server.AdvancedMinecraftServer;
import net.kyori.adventure.text.Component;

import java.lang.management.ManagementFactory;
import java.text.DecimalFormat;

public final class InfoCommand {

    private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("#.##");

    public BrigadierCommand get() {
        return new BrigadierCommand(LiteralArgumentBuilder.<CommandSender>literal("info")
                .executes(context -> {
                    final var sender = context.getSource();
                    final var systemMXBean = (com.sun.management.OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
                    final var memoryMXBean = ManagementFactory.getMemoryMXBean().getHeapMemoryUsage();

                    sender.sendMessage(Component.text()
                            .append(Component.text("Version: " + AdvancedMinecraftServer.class.getPackage().getImplementationVersion())).appendNewline()
                            .append(Component.text("CPU: " + DECIMAL_FORMAT.format(systemMXBean.getCpuLoad() * 100) + "%")).appendNewline()
                            .append(Component.text("RAM: " +
                                    this.toMB(memoryMXBean.getUsed()) + "/" + this.toMB(memoryMXBean.getMax()))).appendNewline()
                            .append(Component.text("Threads: " + Thread.getAllStackTraces().keySet().size())));
                    return 0;
                })
                .build());
    }

    private int toMB(final long bytes) {
        return (int) (bytes / 1048576);
    }

}
