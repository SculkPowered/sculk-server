import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    id("java")
}
apply(plugin = "com.github.johnrengelman.shadow")

tasks.withType<Jar> {
    manifest {
        attributes["Main-Class"] = "de.bauhd.minecraft.server.Main"
        attributes["Multi-Release"] = true
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

    // terminal
    implementation("org.jline:jline-reader:3.21.0")
    runtimeOnly("org.fusesource.jansi:jansi:2.4.0")

    // logging
    val log4jVersion = "2.19.0"
    implementation("org.apache.logging.log4j:log4j-core:$log4jVersion")
    implementation("org.apache.logging.log4j:log4j-iostreams:$log4jVersion")
    implementation("org.apache.logging.log4j:log4j-jul:$log4jVersion") // for jline logger

    implementation("it.unimi.dsi:fastutil-core:8.5.9")
}

tasks.withType<ShadowJar> {
    archiveFileName.set("minecraft-server.jar")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}