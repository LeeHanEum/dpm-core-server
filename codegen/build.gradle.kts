import org.jooq.meta.jaxb.ForcedType
import org.jooq.meta.jaxb.Logging
import org.jooq.meta.jaxb.Property

plugins {
    kotlin("jvm")
    id("nu.studer.jooq")
}

val mysqlVersion = "8.0.33"

dependencies {
    implementation("org.jooq:jooq:3.19.1")
    jooqGenerator("org.jooq:jooq-meta:3.19.1")
    jooqGenerator("org.jooq:jooq-codegen:3.19.1")
    jooqGenerator("org.jooq:jooq-meta-extensions:3.19.1")
    jooqGenerator("com.mysql:mysql-connector-j:$mysqlVersion")

    runtimeOnly("com.mysql:mysql-connector-j:$mysqlVersion")
}

jooq {
    configurations {
        create("main") {
            // name of the jOOQ configuration
            generateSchemaSourceOnCompilation.set(true)

            jooqConfiguration.apply {
                logging = Logging.WARN
                jdbc = null

                generator.apply {
                    name = "org.jooq.codegen.KotlinGenerator"
                    database.apply {
                        name = "org.jooq.meta.extensions.ddl.DDLDatabase"
                        properties.addAll(
                            listOf(
                                Property().apply {
                                    key = "scripts"
                                    value = "src/main/resources/db/schema.sql"
                                },
                                Property().apply {
                                    key = "sort"
                                    value = "semantic"
                                },
                                Property().apply {
                                    key = "unqualifiedSchema"
                                    value = "none"
                                },
                                Property().apply {
                                    key = "defaultNameCase"
                                    value = "lower"
                                },
                            ),
                        )
                        forcedTypes.addAll(
                            listOf(
                                ForcedType().apply {
                                    name = "INSTANT"
                                    includeExpression = ".*\\.date"
                                    includeTypes = "timestamp.*"
                                },
                                ForcedType().apply {
                                    name = "INSTANT"
                                    includeExpression = ".*\\.attendance_start"
                                    includeTypes = "timestamp.*"
                                },
                                ForcedType().apply {
                                    name = "INSTANT"
                                    includeExpression = ".*\\.attended_at"
                                    includeTypes = "timestamp.*"
                                },
                            ),
                        )
                    }
                    generate.apply {
                        isDaos = true
                        isRecords = true
                        isFluentSetters = true
                        isJavaTimeTypes = true
                        isDeprecated = false
                    }
                    target.apply {
                        directory = "build/generated/jooq"
                    }
                    strategy.name = "org.jooq.codegen.DefaultGeneratorStrategy"
                }
            }
        }
    }
}

sourceSets {
    main {
        kotlin {
            srcDirs("src/main/kotlin", "build/generated/jooq")
        }
    }
}

tasks.register("prepareKotlinBuildScriptModel") {}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }
}

ktlint {
    verbose.set(true)
    outputToConsole.set(true)
    coloredOutput.set(true)
    reporters {
        reporter(org.jlleitschuh.gradle.ktlint.reporter.ReporterType.CHECKSTYLE)
        reporter(org.jlleitschuh.gradle.ktlint.reporter.ReporterType.JSON)
        reporter(org.jlleitschuh.gradle.ktlint.reporter.ReporterType.HTML)
    }
    filter {
        exclude("**/generated/**")
        include("**/*.kt, **/*.kts")
    }
}
