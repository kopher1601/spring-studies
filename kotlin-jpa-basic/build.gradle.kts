plugins {
    kotlin("jvm") version "2.1.10"
    kotlin("plugin.jpa") version "1.9.25"
}

group = "jp.co.kopher"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("com.h2database:h2:2.3.232")
    implementation("org.hibernate:hibernate-core:6.6.11.Final")
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}