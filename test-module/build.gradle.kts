plugins {
    id("java")
}

dependencies {
    implementation(project(":api"))
    annotationProcessor(project(":api"))
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}