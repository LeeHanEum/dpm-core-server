plugins {
    kotlin("jvm")
    kotlin("plugin.jpa")
    id("org.springframework.boot")
    id("io.spring.dependency-management")
}

dependencies {
    implementation(project(":application"))
    implementation(project(":codegen"))

    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-jooq")
    implementation("org.springframework.boot:spring-boot-starter-web")

    runtimeOnly("com.mysql:mysql-connector-j")

    val jdslVersion = "2.2.1.RELEASE"
    implementation("com.linecorp.kotlin-jdsl:spring-data-kotlin-jdsl-starter-jakarta:$jdslVersion")
}

tasks.register("prepareKotlinBuildScriptModel") {}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }
}
