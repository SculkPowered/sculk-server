package de.bauhd.minecraft.server.plugin;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface PluginDescription {

    String name();

    String version();

    String[] dependencies() default {};

}