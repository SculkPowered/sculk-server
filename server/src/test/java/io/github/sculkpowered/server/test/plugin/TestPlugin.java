package io.github.sculkpowered.server.test.plugin;

import io.github.sculkpowered.server.plugin.Plugin;
import io.github.sculkpowered.server.plugin.PluginDescription;

@PluginDescription(name = "Test", version = "1.0")
public final class TestPlugin extends Plugin {

  public static final Plugin PLUGIN = new TestPlugin();
}
