plugins {
    id("java")
}

defaultTasks("build", "test", "shadowJar")

allprojects {
    group = "de.bauhd.sculk"
    version = "1.0-SNAPSHOT"

    repositories {
        mavenCentral()
        mavenLocal()
        maven("https://libraries.minecraft.net") // for brigadier
    }
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}