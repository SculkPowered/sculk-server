package de.bauhd.minecraft.server.api.event;

import de.bauhd.minecraft.server.api.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

public interface EventHandler {

    void register(@NotNull Plugin plugin, @NotNull Object listener);

    void register(@NotNull Plugin plugin, @NotNull Object... listeners);

    <E> CompletableFuture<E> call(E event);

}
