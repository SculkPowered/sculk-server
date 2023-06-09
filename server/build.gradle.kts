import com.github.jengelman.gradle.plugins.shadow.transformers.Log4j2PluginsCacheFileTransformer

plugins {
    id("java")
    alias(libs.plugins.shadow)
}

dependencies {
    implementation(project(":api"))
    implementation(project(":server:appender"))

    // netty
    implementation(libs.bundles.netty)
    implementation(variantOf(libs.netty.transport.epoll) { classifier("linux-x86_64") })
    implementation(variantOf(libs.netty.transport.epoll) { classifier("linux-aarch_64") })

    // terminal
    implementation(libs.jline)
    runtimeOnly(libs.jansi)

    implementation(libs.bundles.log4j)
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
            "it/unimi/dsi/fastutil/*Big*",
            "it/unimi/dsi/fastutil/*Queue*",
            "it/unimi/dsi/fastutil/booleans/**",
            "it/unimi/dsi/fastutil/bytes/**",
            "it/unimi/dsi/fastutil/chars/**",
            "it/unimi/dsi/fastutil/doubles/**",
            "it/unimi/dsi/fastutil/floats/**",
            "it/unimi/dsi/fastutil/io/**",
            "it/unimi/dsi/fastutil/shorts/**",
            "it/unimi/dsi/fastutil/ints/*Byte*",
            "it/unimi/dsi/fastutil/ints/*Char*",
            "it/unimi/dsi/fastutil/ints/*Double*",
            "it/unimi/dsi/fastutil/ints/*Long*",
            "it/unimi/dsi/fastutil/ints/*Float*",
            "it/unimi/dsi/fastutil/ints/*Big*",
            "it/unimi/dsi/fastutil/ints/*Tree*",
            "it/unimi/dsi/fastutil/ints/*HashSet*",
            "it/unimi/dsi/fastutil/longs/*Byte*",
            "it/unimi/dsi/fastutil/longs/*Char*",
            "it/unimi/dsi/fastutil/longs/*Double*",
            "it/unimi/dsi/fastutil/longs/*Int*",
            "it/unimi/dsi/fastutil/longs/*Big*",
            "it/unimi/dsi/fastutil/longs/*Tree*",
            "it/unimi/dsi/fastutil/longs/*HashSet*",
            "it/unimi/dsi/fastutil/longs/*Long2Long*",
            "it/unimi/dsi/fastutil/objects/*Byte*",
            "it/unimi/dsi/fastutil/objects/*Char*",
            "it/unimi/dsi/fastutil/objects/*Double*",
            "it/unimi/dsi/fastutil/objects/*Long*",
            "it/unimi/dsi/fastutil/objects/*Big*",
            "it/unimi/dsi/fastutil/objects/*Tree*",
            "it/unimi/dsi/fastutil/objects/*2Object*",
            "it/unimi/dsi/fastutil/objects/*HashSet*"
        )
    }

    test {
        useJUnitPlatform()
    }
}