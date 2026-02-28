plugins {
    java
    id("org.springframework.boot") version "3.4.1"
    id("io.spring.dependency-management") version "1.1.7"
}

group = "com.legacy.bridge"
version = "1.0-SNAPSHOT"
description = ""

java{
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(23))
    }
}

configurations{
    compileOnly{
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    mavenCentral()
}

dependencies {
    // SPRING
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    // DATABASE
    implementation("org.postgresql:postgresql")
    implementation("org.flywaydb:flyway-core")
    implementation("org.flywaydb:flyway-database-postgresql")
    // MAPSTRUCT
    implementation("org.mapstruct:mapstruct:1.5.5.Final")
    annotationProcessor("org.mapstruct:mapstruct-processor:1.5.5.Final")
    // DOCUMENTATION
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.8.0")
    // MAIL
    implementation("org.simplejavamail:simple-java-mail:8.12.5")
    implementation("org.simplejavamail:batch-module:8.12.5")
    // LOMBOK
    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")
    // TEST
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    //
    annotationProcessor("org.projectlombok:lombok-mapstruct-binding:0.2.0")
    //
    implementation("org.springframework.boot:spring-boot-starter-security");
}

tasks.test {
    useJUnitPlatform()
}