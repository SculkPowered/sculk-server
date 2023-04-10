package de.bauhd.minecraft.server.api.module;

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

public final class MinecraftModuleHandler implements ModuleHandler {

    private static final Path MODULES_DIRECTORY = Path.of("modules");

    private final AdvancedMinecraftServer server;
    private final Map<Module, Path> modules = new HashMap<>();

    public MinecraftModuleHandler(final AdvancedMinecraftServer server) {
        this.server = server;
        try {
            if (Files.notExists(MODULES_DIRECTORY)) {
                Files.createDirectory(MODULES_DIRECTORY);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void loadModules() {
        try (final var stream = Files.newDirectoryStream(MODULES_DIRECTORY,
                path -> Files.isRegularFile(path) && path.getFileName().toString().endsWith(".jar"))) {
            stream.forEach(this::loadModule);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadModule(final Path path) {
        try (final var jarFile = new JarFile(path.toFile())) {
            final var jarEntry = jarFile.getEntry("module");
            if (jarEntry == null) {
                System.err.println(path.getFileName() + " has no module file");
                return;
            }

            try (final var reader = new BufferedReader(new InputStreamReader(jarFile.getInputStream(jarEntry)))) {
                final var main = reader.readLine();
                final var classLoader = new URLClassLoader(new URL[]{path.toUri().toURL()});
                final var module = (Module) Class.forName(main, true, classLoader).getConstructor().newInstance();
                module.init(this.server);
                this.modules.put(module, path);
                this.server.getEventHandler().register(module, module);
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
