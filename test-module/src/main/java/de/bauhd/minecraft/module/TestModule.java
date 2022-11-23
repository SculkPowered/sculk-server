package de.bauhd.minecraft.module;

import de.bauhd.minecraft.server.api.module.Module;
import de.bauhd.minecraft.server.api.module.ModuleDescription;

@ModuleDescription(name = "test", version = "1.0")
public final class TestModule extends Module {

    public TestModule() {
        System.out.println("init");
    }
}
