plugins {
    id("java")
}

dependencies {
    implementation(project(":api"))
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}