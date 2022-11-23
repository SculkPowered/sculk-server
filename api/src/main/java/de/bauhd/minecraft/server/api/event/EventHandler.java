package de.bauhd.minecraft.server.api.event;

import de.bauhd.minecraft.server.api.module.Module;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

public interface EventHandler {

    void register(@NotNull Module module, @NotNull Object listener);

    void register(@NotNull Module module, @NotNull Object... listeners);

    <E> CompletableFuture<E> call(E event);

}
