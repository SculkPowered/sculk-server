package de.bauhd.minecraft.server.terminal;

import de.bauhd.minecraft.server.command.CommandSender;
import net.kyori.adventure.chat.ChatType;
import net.kyori.adventure.identity.Identity;
import net.kyori.adventure.permission.PermissionChecker;
import net.kyori.adventure.pointer.Pointers;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import net.kyori.adventure.util.TriState;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

public final class TerminalCommandSender implements CommandSender {

    private final Logger logger;
    private final Pointers pointers = Pointers.builder()
            .withStatic(Identity.NAME, "CONSOLE")
            .withStatic(PermissionChecker.POINTER, PermissionChecker.always(TriState.TRUE))
            .build();

    public TerminalCommandSender(Logger logger) {
        this.logger = logger;
    }

    @Override
    public boolean hasPermission(@NotNull String permission) {
        return true;
    }

    @Override
    public void sendMessage(@NotNull Component message) {
        if (message instanceof TextComponent textComponent) {
            this.logger.info(textComponent.content());
        }
        for (final var child : message.children()) {
            this.logger.info(PlainTextComponentSerializer.plainText().serialize(child));
        }
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
