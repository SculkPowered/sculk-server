package io.github.sculkpowered.server.command.defaults;

import static net.kyori.adventure.text.Component.text;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.tree.LiteralCommandNode;
import com.sun.management.OperatingSystemMXBean;
import io.github.sculkpowered.server.Server;
import io.github.sculkpowered.server.SculkServer;
import io.github.sculkpowered.server.command.CommandSource;
import io.github.sculkpowered.server.command.Commands;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import net.kyori.adventure.text.Component;

public final class InfoCommand {

  private static final OperatingSystemMXBean OPERATING_SYSTEM_MX_BEAN = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
  private static final MemoryMXBean MEMORY_MX_BEAN = ManagementFactory.getMemoryMXBean();

  public static LiteralCommandNode<CommandSource> get() {
    return Commands.literal("info")
        .requires(commandSource -> commandSource.hasPermission("server.command.info"))
        .executes(context -> {
          final var heapMemoryUsage = MEMORY_MX_BEAN.getHeapMemoryUsage();
          context.getSource().sendMessage(text()
              .append(
                  text("Version: " + Server.class.getPackage().getImplementationVersion()))
              .appendNewline()
              .append(Component.text("CPU: " + SculkServer.DECIMAL_FORMAT.format(
                  OPERATING_SYSTEM_MX_BEAN.getCpuLoad() * 100) + "%")).appendNewline()
              .append(text("RAM (Heap): " +
                  toMB(heapMemoryUsage.getUsed()) + "/" + toMB(heapMemoryUsage.getMax())))
              .appendNewline()
              .append(text("Threads: " + Thread.getAllStackTraces().keySet().size())));
          return Command.SINGLE_SUCCESS;
        })
        .build();
  }

  private static int toMB(final long bytes) {
    return (int) (bytes >> 20);
  }
}
