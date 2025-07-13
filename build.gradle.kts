import org.gradle.kotlin.dsl.testImplementation

plugins {
    kotlin("jvm") version "1.9.10"
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
    testImplementation("junit:junit:4.13.2")
}

tasks.test { useJUnit() }

kotlin {
    jvmToolchain(17)
}