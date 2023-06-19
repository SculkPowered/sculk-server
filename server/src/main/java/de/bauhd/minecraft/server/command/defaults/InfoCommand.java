package de.bauhd.minecraft.server.command.defaults;

import de.bauhd.minecraft.server.MinecraftServer;
import de.bauhd.minecraft.server.command.BrigadierCommand;
import net.kyori.adventure.text.Component;

import java.lang.management.ManagementFactory;
import java.text.DecimalFormat;

public final class InfoCommand extends BrigadierCommand {

    private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("#.##");

    public InfoCommand() {
        super(literal("info")
                .requires(commandSender -> commandSender.hasPermission("server.command.info"))
                .executes(context -> {
                    final var sender = context.getSource();
                    final var systemMXBean = (com.sun.management.OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
                    final var memoryMXBean = ManagementFactory.getMemoryMXBean().getHeapMemoryUsage();

                    sender.sendMessage(Component.text()
                            .append(Component.text("Version: " + MinecraftServer.class.getPackage().getImplementationVersion())).appendNewline()
                            .append(Component.text("CPU: " + DECIMAL_FORMAT.format(systemMXBean.getCpuLoad() * 100) + "%")).appendNewline()
                            .append(Component.text("RAM: " +
                                    toMB(memoryMXBean.getUsed()) + "/" + toMB(memoryMXBean.getMax()))).appendNewline()
                            .append(Component.text("Threads: " + Thread.getAllStackTraces().keySet().size())));
                    return 1;
                })
                .build());
    }

    private static int toMB(final long bytes) {
        return (int) (bytes / 1048576);
    }
}
