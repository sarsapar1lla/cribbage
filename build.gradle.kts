import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.6.20"
    java
    application
    id("com.github.johnrengelman.shadow") version "7.1.2"
}

group = "org.sarsaparilla.cribbage"
version = "1.0-SNAPSHOT"

application {
    mainClass.set("engine.MainKt")
}

tasks.shadowJar {
    archiveBaseName.set(project.name)
    archiveClassifier.set("")
    archiveVersion.set("")
    manifest {
        attributes(mapOf(
            "Main-Class" to "engine.MainKt",
            "Implementation-Title" to project.name
        ))
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.1")
    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.6.20")
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}