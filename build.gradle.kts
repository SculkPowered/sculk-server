plugins {
    id("java")
    id("checkstyle")
}

defaultTasks("build", "test", "shadowJar")

allprojects {
    group = "io.github.sculkpowered.server"
    version = "1.0.0-SNAPSHOT"

    repositories {
        mavenCentral()

        maven("https://s01.oss.sonatype.org/content/repositories/snapshots")
        maven("https://libraries.minecraft.net") // for brigadier
    }
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}