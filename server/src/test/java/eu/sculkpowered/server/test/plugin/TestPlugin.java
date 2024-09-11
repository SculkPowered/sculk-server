package eu.sculkpowered.server.test.plugin;

import eu.sculkpowered.server.plugin.Plugin;
import eu.sculkpowered.server.plugin.PluginDescription;

@PluginDescription(name = "Test", version = "1.0")
public final class TestPlugin extends Plugin {

  public static final Plugin PLUGIN = new TestPlugin();
}
