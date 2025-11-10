import com.github.jengelman.gradle.plugins.shadow.transformers.Log4j2PluginsCacheFileTransformer
import java.io.ByteArrayOutputStream

plugins {
    id("java")
    alias(libs.plugins.shadow)
}

dependencies {
    implementation(project(":api"))
    implementation(project(":server:appender"))

    /*
        We go back to netty v4, because at this point v5 is stick in the Alpha and is not frequently updated.
     */
    implementation(libs.bundles.netty)
    implementation(variantOf(libs.netty.transport.epoll) { classifier("linux-x86_64") })
    implementation(variantOf(libs.netty.transport.epoll) { classifier("linux-aarch_64") })

    // terminal
    implementation(libs.jline)
    runtimeOnly(libs.jansi)

    implementation(libs.bundles.log4j)
    implementation(libs.fastutil)

    implementation(libs.velocity.native)
    implementation(libs.zstd)

    testImplementation(libs.junit.jupiter)
    testRuntimeOnly(libs.junit.platform)
    testRuntimeOnly(libs.junit.engine)
}

repositories {
    maven("https://repo.papermc.io/repository/maven-public/") // for velocity-native
}

tasks {
    jar {
        manifest {
            attributes["Main-Class"] = "eu.sculkpowered.server.Main"
            attributes["Multi-Release"] = true
            attributes["Implementation-Title"] = "Sculk-Server"
            attributes["Implementation-Vendor"] = "SculkPowered Contributors"
            val gitCommit = "" /*ByteArrayOutputStream().use {
                exec {
                    executable = "git"
                    standardOutput = it
                    args = listOf("rev-parse", "HEAD")
                }
                it.toString().trim().substring(0, 8)
            }*/ // TODO
            attributes["Implementation-Version"] = "${project.version} (git-$gitCommit)"
        }
    }

    shadowJar {
        archiveFileName.set("sculk-server.jar")
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
            "it/unimi/dsi/fastutil/objects/*HashSet*",
            "javax/annotation/**",
            "org/checkerframework/**",
            "javax.annotation/**"
        )
    }

    test {
        useJUnitPlatform()
    }
}