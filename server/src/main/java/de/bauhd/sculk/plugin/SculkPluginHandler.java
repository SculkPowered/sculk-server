package de.bauhd.sculk.plugin;

import de.bauhd.sculk.Main;
import de.bauhd.sculk.SculkServer;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.jar.JarFile;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class SculkPluginHandler implements PluginHandler {

  private static final Logger LOGGER = LogManager.getLogger(PluginHandler.class);
  private static final Path PLUGINS_DIRECTORY = Path.of("plugins");

  private final SculkServer server;
  private final Map<String, Plugin> plugins = new HashMap<>();

  public SculkPluginHandler(final SculkServer server) {
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
      for (final var path : stream) {
        this.loadPlugin(path);
      }
    } catch (IOException e) {
      LOGGER.error("Error during loading of plugins", e);
    }
  }

  public void loadPlugin(final Path path) {
    try (final var jarFile = new JarFile(path.toFile())) {
      final var jarEntry = jarFile.getEntry("plugin");
      if (jarEntry == null) {
        LOGGER.error(path.getFileName() + " has no plugin file!");
        return;
      }

      try (final var reader = new BufferedReader(
          new InputStreamReader(jarFile.getInputStream(jarEntry)))) {
        final var main = reader.readLine();
        @SuppressWarnings("resource") final var classLoader = new PluginClassLoader(
            new URL[]{path.toUri().toURL()}, Main.class.getClassLoader());
        final var plugin = (Plugin) classLoader.loadClass(main).getConstructor().newInstance();
        plugin.init(this.server);
        this.plugins.put(plugin.getDescription().name(), plugin);
        this.server.getEventHandler().register(plugin, plugin);
      } catch (ClassNotFoundException e) {
        LOGGER.error("Main class could not be found", e);
      } catch (InvocationTargetException | InstantiationException | IllegalAccessException |
               NoSuchMethodException e) {
        throw new RuntimeException(e);
      }
    } catch (IOException e) {
      LOGGER.error(e);
    }
  }

  @Override
  public @Nullable Plugin getPlugin(@NotNull String name) {
    return this.plugins.get(name);
  }

  @Override
  public boolean isLoaded(@NotNull String name) {
    return this.plugins.containsKey(name);
  }

  @Override
  public @NotNull Collection<Plugin> getPlugins() {
    return this.plugins.values();
  }

  @Override
  public void addToClassPath(@NotNull Plugin plugin, @NotNull Path path) {
    final var classLoader = plugin.getClass().getClassLoader();
    if (classLoader instanceof PluginClassLoader pluginClassLoader) {
      try {
        pluginClassLoader.addURL(path.toUri().toURL());
      } catch (MalformedURLException e) {
        throw new AssertionError(e);
      }
    }
  }
}
