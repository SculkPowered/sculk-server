package de.bauhd.minecraft.server.plugin;

import de.bauhd.minecraft.server.AdvancedMinecraftServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.jar.JarFile;

public final class MinecraftPluginHandler implements PluginHandler {

    private static final Path PLUGINS_DIRECTORY = Path.of("plugins");

    private final AdvancedMinecraftServer server;
    private final Map<Plugin, Path> plugins = new HashMap<>();

    public MinecraftPluginHandler(final AdvancedMinecraftServer server) {
        this.server = server;
        try {
            if (Files.notExists(PLUGINS_DIRECTORY)) {
                Files.createDirectory(PLUGINS_DIRECTORY);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void loadPlugins() {
        try (final var stream = Files.newDirectoryStream(PLUGINS_DIRECTORY,
                path -> Files.isRegularFile(path) && path.getFileName().toString().endsWith(".jar"))) {
            stream.forEach(this::loadPlugin);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadPlugin(final Path path) {
        try (final var jarFile = new JarFile(path.toFile())) {
            final var jarEntry = jarFile.getEntry("plugin");
            if (jarEntry == null) {
                System.err.println(path.getFileName() + " has no plugin file");
                return;
            }

            try (final var reader = new BufferedReader(new InputStreamReader(jarFile.getInputStream(jarEntry)))) {
                final var main = reader.readLine();
                final var classLoader = new URLClassLoader(new URL[]{path.toUri().toURL()});
                final var plugin = (Plugin) Class.forName(main, true, classLoader).getConstructor().newInstance();
                plugin.init(this.server);
                this.plugins.put(plugin, path);
                this.server.getEventHandler().register(plugin, plugin);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (InvocationTargetException | InstantiationException | IllegalAccessException |
                     NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
