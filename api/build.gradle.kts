plugins {
    id("java")
    id("java-library")
    id("maven-publish")
}

dependencies {
    api(libs.bundles.adventure)
    api(libs.slf4j)
    api(libs.gson)
    api(libs.guava)
    api(libs.brigadier)
}

publishing {
    repositories {
        maven {
            name = "ossrhSnapshot"
            url = uri("https://s01.oss.sonatype.org/content/repositories/snapshots/")
            credentials {
                username = System.getenv("SONATYPE_USER")
                password = System.getenv("SONATYPE_PASSWORD")
            }
        }
    }

    publications {
        create<MavenPublication>("maven") {
            pom {
                name = "Sculk-Server"
                url = "https://github.com/SculkPowered/sculk-server"
                groupId = groupId
                artifactId = artifactId
                version = version
            }

            from(components["java"])
        }
    }
}

tasks {
    javadoc {
        (options as StandardJavadocDocletOptions).links = listOf(
                "https://docs.oracle.com/en/java/javase/17/docs/api/",
                "https://jd.advntr.dev/api/${libs.versions.adventure.get()}/",
                "https://jd.advntr.dev/nbt/${libs.versions.adventure.get()}/",
                "https://jd.advntr.dev/text-logger-slf4j/${libs.versions.adventure.get()}/",
                "https://guava.dev/releases/${libs.guava.get().version}/api/docs/"
        )
    }
}
