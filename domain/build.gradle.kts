plugins {
    kotlin("jvm")
    kotlin("plugin.jpa")
    id("org.springframework.boot")
    id("io.spring.dependency-management")
}

dependencies {

    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.8.8")

}

tasks.register("prepareKotlinBuildScriptModel") {}

tasks.getByName<org.springframework.boot.gradle.tasks.bundling.BootJar>("bootJar") {
    enabled = false
}

tasks.getByName<Jar>("jar") {
    enabled = true
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }
}
