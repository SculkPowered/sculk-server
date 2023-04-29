plugins {
    id("java")
}

defaultTasks("build", "test", "shadowJar")

allprojects {
    group = "de.bauhd.minecraft.server"
    version = "1.0-SNAPSHOT"

    repositories {
        mavenCentral()

        maven("https://libraries.minecraft.net")
    }
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}