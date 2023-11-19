plugins {
    id("java")
    id("checkstyle")
}

defaultTasks("build", "test", "shadowJar")

allprojects {
    group = "io.github.sculkpowered.server"
    version = "1.0.0-PRE1"

    repositories {
        mavenCentral()

        maven("https://libraries.minecraft.net") // for brigadier
    }
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}