package de.bauhd.minecraft.server.api.module;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface ModuleDescription {

    String name();

    String version();

    String[] dependencies() default {};

}
