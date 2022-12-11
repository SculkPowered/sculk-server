plugins {
    id("java")
    id("java-library")
}

dependencies {

    // adventure
    api(platform("net.kyori:adventure-bom:4.12.0"))
    api("net.kyori:adventure-api")
    api("net.kyori:adventure-nbt")
    api("net.kyori:adventure-text-serializer-gson")
    api("net.kyori:adventure-text-serializer-legacy")
    api("net.kyori:adventure-text-serializer-plain")
    api("net.kyori:adventure-text-minimessage")

    api("com.google.code.gson:gson:2.10")

    api("com.mojang:brigadier:1.0.18")

}

tasks.getByName<Javadoc>("javadoc") {
    (options as StandardJavadocDocletOptions).links = listOf(
        "https://docs.oracle.com/en/java/javase/17/docs/api/",
        "https://jd.adventure.kyori.net/api/4.12.0/"
    )
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}