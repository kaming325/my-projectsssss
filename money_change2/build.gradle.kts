import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    application
    java
    kotlin("jvm") version "1.8.20-RC2"
    kotlin("plugin.serialization") version "1.8.10"

}

group = "com.example"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = org.gradle.api.JavaVersion.VERSION_17

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.4.1")
}

tasks.withType<Jar> {
    manifest {
        attributes["Main-Class"] = "MainKt"
    }

    duplicatesStrategy = org.gradle.api.file.DuplicatesStrategy.EXCLUDE

    configurations["compileClasspath"].forEach{
        file: File -> from(zipTree(file.absoluteFile))
    }
}

