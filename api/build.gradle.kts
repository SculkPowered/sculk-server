plugins {
    id("java")
    id("java-library")
}

dependencies {
    // adventure
    api(platform(libs.adventure.bom))
    api("net.kyori:adventure-api")
    api("net.kyori:adventure-nbt")
    api("net.kyori:adventure-text-serializer-gson")
    api("net.kyori:adventure-text-serializer-legacy")
    api("net.kyori:adventure-text-serializer-plain")
    api("net.kyori:adventure-text-minimessage")

    api(libs.gson)
    api(libs.brigadier)
}

tasks {
    javadoc {
        (options as StandardJavadocDocletOptions).links = listOf(
            "https://docs.oracle.com/en/java/javase/17/docs/api/",
            "https://jd.advntr.dev/api/${libs.adventure.bom.get().version}/"
        )
    }
}