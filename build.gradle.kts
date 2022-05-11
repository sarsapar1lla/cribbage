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

val kotlinxVersion = "1.6.1"
val kotlinVersion = "1.6.20"
val assertJVersion = "3.22.0"

dependencies {
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:${kotlinxVersion}")
    implementation("org.jetbrains.kotlin:kotlin-stdlib:${kotlinVersion}")
    testImplementation(kotlin("test"))
    testImplementation("org.assertj:assertj-core:${assertJVersion}")
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}