plugins {
    kotlin("jvm") version "1.9.23"
}

group = "com.metric.search.visual.note"
version = "0.1.0"

repositories {
    mavenLocal()
    mavenCentral()
    maven("https://packages.jetbrains.team/maven/p/kds/kotlin-ds-maven")
}

dependencies {
    implementation(files("libs/Metric-space-framework-1.0-20211209.152843-1.jar"))
    implementation("com.metric.search.visualisation:visualisation-library:1.0-SNAPSHOT")
    implementation("org.jetbrains.kotlinx:kandy-lets-plot:0.6.0")
    implementation("org.apache.commons:commons-math3:3.6.1")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(17)
}