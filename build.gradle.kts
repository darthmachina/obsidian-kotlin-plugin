plugins {
    kotlin("multiplatform") version "2.3.0"
}

group = "io.github.darthmachina"
version = "0.2.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven { url = uri("https://maven.pkg.jetbrains.space/public/p/kotlinx-html/maven") }
}

kotlin {
    js(IR) {
        binaries.executable()
        browser {
            useCommonJs()
            commonWebpackConfig {
                cssSupport {
                    enabled.set(true)
                }
            }
            webpackTask {
                output.libraryTarget = "commonjs"
                output.library = null
                mainOutputFileName = "main.js"
            }
            compilerOptions {
                optIn.add("kotlin.js.ExperimentalJsExport")
            }
        }
    }

    sourceSets {
        jsMain.dependencies {
            implementation(npm("obsidian", "1.11.0"))

            val kotlinxHtmlVersion = "0.7.3"
            implementation("org.jetbrains.kotlinx:kotlinx-html-js:$kotlinxHtmlVersion")
            implementation("org.jetbrains.kotlinx:kotlinx-html:$kotlinxHtmlVersion")
        }

        jsTest.dependencies {
            implementation("org.jetbrains.kotlin:kotlin-test:1.6.0")
        }
    }
}

project.gradle.startParameter.excludedTaskNames.add("jsBrowserTest")
