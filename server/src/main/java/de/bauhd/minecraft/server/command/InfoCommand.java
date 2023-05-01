package de.bauhd.minecraft.server.command;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
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

                    sender.sendMessage(Component.text("CPU: " + DECIMAL_FORMAT.format(systemMXBean.getCpuLoad() * 100) + "%"));
                    sender.sendMessage(Component.text("RAM: " +
                            this.toMB(memoryMXBean.getUsed()) + "/" + this.toMB(memoryMXBean.getMax())));
                    context.getSource().sendMessage(Component.text("Threads: " + Thread.getAllStackTraces().keySet().size()));
                    return 0;
                })
                .build());
    }

    private int toMB(final long bytes) {
        return (int) (bytes / 1048576);
    }

}
