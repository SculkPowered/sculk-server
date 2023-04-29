plugins {
    id("java")
}

dependencies {
    implementation(libs.jline)
    implementation(libs.log4j.core)
    annotationProcessor(libs.log4j.core)
}