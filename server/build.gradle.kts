import com.github.jengelman.gradle.plugins.shadow.transformers.Log4j2PluginsCacheFileTransformer

plugins {
    id("java")
    alias(libs.plugins.shadow)
}

dependencies {
    implementation(project(":api"))
    implementation(project(":server:appender"))

    // netty
    implementation(platform(libs.netty.bom))
    implementation("io.netty:netty5-transport")
    implementation("io.netty:netty5-codec")
    implementation("io.netty:netty5-transport-native-epoll")
    implementation("io.netty:netty5-transport-native-epoll:linux-x86_64")
    implementation("io.netty:netty5-transport-native-epoll:linux-aarch_64")

    // terminal
    implementation(libs.jline)
    runtimeOnly(libs.jansi)

    implementation(libs.bundles.log4j)
    annotationProcessor(libs.log4j.core)
    implementation(libs.fastutil)
}

tasks {
    jar {
        manifest {
            attributes["Main-Class"] = "de.bauhd.minecraft.server.Main"
            attributes["Multi-Release"] = true
            attributes["Implementation-Title"] = "Minecraft-Server"
            attributes["Implementation-Version"] = project.version
        }
    }

    shadowJar {
        archiveFileName.set("minecraft-server.jar")
        transform(Log4j2PluginsCacheFileTransformer::class.java)

        // exclude some things we don't need to save space
        exclude(
            "it/unimi/dsi/fastutil/booleans/**",
            "it/unimi/dsi/fastutil/bytes/**",
            "it/unimi/dsi/fastutil/chars/**",
            "it/unimi/dsi/fastutil/doubles/**",
            "it/unimi/dsi/fastutil/floats/**",
            "it/unimi/dsi/fastutil/io/**",
            "it/unimi/dsi/fastutil/shorts/**"
        )
    }

    test {
        useJUnitPlatform()
    }
}