import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.4.20"
    application
}

group = "me.david"
version = "1.0"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test-junit"))
}

tasks.test {
    useJUnit()
}

tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = "11"
}

application {
    mainClassName = "MainKt"
}