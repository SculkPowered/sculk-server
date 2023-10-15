package de.bauhd.sculk.plugin;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * The annotation that describe a plugin.
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface PluginDescription {

  String name();

  String version();

  String[] dependencies() default {};

}
