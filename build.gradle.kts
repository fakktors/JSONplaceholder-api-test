plugins {
    id("java")
    id("io.qameta.allure") version "2.11.2"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

val junitVersion = "5.10.0"
val restAssuredVersion = "5.4.0"
val allureVersion = "2.25.0"

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")

    testImplementation("io.rest-assured:rest-assured:$restAssuredVersion")
    testImplementation("io.rest-assured:json-schema-validator:$restAssuredVersion")

    testImplementation(platform("io.qameta.allure:allure-bom:$allureVersion"))
    testImplementation("io.qameta.allure:allure-junit5")
    testImplementation("io.qameta.allure:allure-rest-assured")
}

tasks.test {
    useJUnitPlatform()
    finalizedBy("allureReport")
}

allure {
    adapter {
        frameworks {
            junit5
        }
    }
}