package de.bauhd.minecraft.server.terminal;

import de.bauhd.minecraft.server.command.CommandSender;
import net.kyori.adventure.chat.ChatType;
import net.kyori.adventure.identity.Identity;
import net.kyori.adventure.permission.PermissionChecker;
import net.kyori.adventure.pointer.Pointers;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.logger.slf4j.ComponentLogger;
import net.kyori.adventure.util.TriState;
import org.jetbrains.annotations.NotNull;

public final class TerminalCommandSender implements CommandSender {

    private static final ComponentLogger LOGGER = ComponentLogger.logger(TerminalCommandSender.class);
    private final Pointers pointers = Pointers.builder()
            .withStatic(Identity.NAME, "CONSOLE")
            .withStatic(PermissionChecker.POINTER, PermissionChecker.always(TriState.TRUE))
            .build();

    @Override
    public boolean hasPermission(@NotNull String permission) {
        return true;
    }

    @Override
    public void sendMessage(@NotNull Component message) {
        LOGGER.info(message);
    }

    @Override
    public void sendMessage(@NotNull Component message, ChatType.@NotNull Bound boundChatType) {
        this.sendMessage(message);
    }

    @Override
    public @NotNull Pointers pointers() {
        return this.pointers;
    }
}
