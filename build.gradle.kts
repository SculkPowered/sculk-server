plugins {
    id("java")
    id("com.github.johnrengelman.shadow").version("7.1.2").apply(false)
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