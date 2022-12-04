import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    id("java")
}
apply(plugin = "com.github.johnrengelman.shadow")

tasks.withType<Jar> {
    manifest {
        attributes["Main-Class"] = "de.bauhd.minecraft.server.Main"
    }
}

dependencies {
    implementation(project(":api"))

    // netty
    implementation(platform("io.netty:netty5-bom:5.0.0.Alpha5"))
    implementation("io.netty:netty5-transport")
    implementation("io.netty:netty5-codec")
    implementation("io.netty:netty5-transport-native-epoll")
    implementation("io.netty:netty5-transport-native-epoll:linux-x86_64")
    implementation("io.netty:netty5-transport-native-epoll:linux-aarch_64")

    implementation("it.unimi.dsi:fastutil-core:8.5.9")
}

tasks.withType<ShadowJar> {
    archiveFileName.set("minecraft-server.jar")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}