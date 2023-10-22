package io.github.sculkpowered.server.plugin;

import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public final class PluginClassLoader extends URLClassLoader {

  private static final List<PluginClassLoader> CLASS_LOADERS = new CopyOnWriteArrayList<>();

  static {
    registerAsParallelCapable();
  }

  public PluginClassLoader(URL[] urls, ClassLoader parent) {
    super(urls, parent);
    CLASS_LOADERS.add(this);
  }

  @Override
  public void addURL(URL url) {
    super.addURL(url);
  }

  @Override
  protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
    return this.loadClass(name, resolve, true);
  }

  private Class<?> loadClass(String name, boolean resolve, boolean deep)
      throws ClassNotFoundException {
    try {
      return super.loadClass(name, resolve);
    } catch (ClassNotFoundException ignored) {
    }
    if (deep) {
      for (final var classLoader : CLASS_LOADERS) {
        if (classLoader != this) {
          try {
            return classLoader.loadClass(name, resolve, false);
          } catch (ClassNotFoundException ignored) {
          }
        }
      }
    }
    throw new ClassNotFoundException(name);
  }

  @Override
  public void close() throws IOException {
    CLASS_LOADERS.remove(this);
    super.close();
  }
}
