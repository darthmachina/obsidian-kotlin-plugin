plugins {
    kotlin("js") version "1.6.0"
}

group = "com.osterlie"
version = "0.1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(npm("obsidian", "0.12.17"))

    testImplementation("org.jetbrains.kotlin:kotlin-test:1.6.0")
}

kotlin {
    js(IR) {
        binaries.executable()
        browser {
            useCommonJs()
            commonWebpackConfig {
                cssSupport.enabled = true
            }
            webpackTask {
                output.libraryTarget = "commonjs"
                output.library = null
                outputFileName = "main.js"
            }
        }
    }
}

// OptIn to JsExport annotation
tasks.withType<org.jetbrains.kotlin.gradle.tasks.Kotlin2JsCompile>().configureEach {
    kotlinOptions.freeCompilerArgs += "-opt-in=kotlin.RequiresOptIn"
}

project.gradle.startParameter.excludedTaskNames.add("browserTest")