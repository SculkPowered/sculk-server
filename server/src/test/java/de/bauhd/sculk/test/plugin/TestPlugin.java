package de.bauhd.sculk.test.plugin;

import de.bauhd.sculk.plugin.Plugin;
import de.bauhd.sculk.plugin.PluginDescription;

@PluginDescription(name = "Test", version = "1.0")
public final class TestPlugin extends Plugin {

  public static final Plugin PLUGIN = new TestPlugin();
}
