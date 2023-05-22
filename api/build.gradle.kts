plugins {
    id("java")
    id("java-library")
}

dependencies {
    api(libs.bundles.adventure)
    api(libs.bundles.configurate)
    api(libs.slf4j)
    api(libs.gson)
    api(libs.brigadier)
}

tasks {
    javadoc {
        (options as StandardJavadocDocletOptions).links = listOf(
            "https://docs.oracle.com/en/java/javase/17/docs/api/",
            "https://jd.advntr.dev/api/${libs.versions.adventure}/"
        )
    }
}