package de.bauhd.sculk.adventure;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.logger.slf4j.ComponentLogger;
import net.kyori.adventure.text.logger.slf4j.ComponentLoggerProvider;
import net.kyori.adventure.text.serializer.ansi.ANSIComponentSerializer;
import org.jetbrains.annotations.NotNull;
import org.slf4j.LoggerFactory;

import java.util.function.Function;

@SuppressWarnings("UnstableApiUsage") // this is a platform
public final class AdventureLoggerProvider implements ComponentLoggerProvider {

    private static final Function<Component, String> SERIALIZER =
            component -> ANSIComponentSerializer.ansi().serialize(component);

    @Override
    public @NotNull ComponentLogger logger(@NotNull LoggerHelper helper, @NotNull String name) {
        return helper.delegating(LoggerFactory.getLogger(name), SERIALIZER);
    }
}
