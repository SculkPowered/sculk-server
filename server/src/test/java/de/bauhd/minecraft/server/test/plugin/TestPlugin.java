package de.bauhd.minecraft.server.test.plugin;

import de.bauhd.minecraft.server.plugin.Plugin;
import de.bauhd.minecraft.server.plugin.PluginDescription;

@PluginDescription(name = "Test", version = "1.0")
public final class TestPlugin extends Plugin {

    public static final Plugin PLUGIN = new TestPlugin();

}
