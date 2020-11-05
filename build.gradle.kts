import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    kotlin("jvm") version "1.4.10"
    id("com.github.johnrengelman.shadow") version "6.1.0"
    application
}

group = "com.dkanen"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    // Align versions of all Kotlin components
    implementation(platform("org.jetbrains.kotlin:kotlin-bom"))

    implementation("com.opencsv:opencsv:5.3")
    testImplementation(kotlin("test-junit5"))

    testImplementation("org.junit.jupiter:junit-jupiter-api:5.6.2")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.6.2")
}

tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = "11"
}

tasks.withType<Test> {
    useJUnitPlatform()
}

application {
    mainClassName = "MainKt"
}

tasks.withType<ShadowJar> {
    archiveBaseName.set("normalizer")
    archiveClassifier.set("")
    archiveVersion.set("")
}
