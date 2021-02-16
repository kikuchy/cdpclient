plugins {
    kotlin("multiplatform")
    application
}

group = "net.kikuchy"
version = "1.0-SNAPSHOT"

val ktor_version = "1.5.1"

repositories {
    mavenCentral()
}

kotlin {
    /* Targets configuration omitted. 
    *  To find out how to configure the targets, please follow the link:
    *  https://kotlinlang.org/docs/reference/building-mpp-with-gradle.html#setting-up-targets */
    jvm {
        withJava()
        application {
            mainClassName = "net.kikuchy.MainKt"
        }
        compilations.all {
            kotlinOptions.jvmTarget = "1.8"
        }
        testRuns["test"].executionTask.configure {
            useJUnit()
        }
    }
    js(IR) {
        nodejs {
            binaries.executable()
        }
    }
    val hostOs = System.getProperty("os.name")
    val isMingwX64 = hostOs.startsWith("Windows")
    val nativeTarget = when {
        hostOs == "Mac OS X" -> macosX64("native")
        hostOs == "Linux" -> linuxX64("native")
        isMingwX64 -> mingwX64("native")
        else -> throw GradleException("Host OS is not supported in Kotlin/Native.")
    }.apply {
        binaries {
            executable()
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(kotlin("stdlib-common"))
                implementation(parent!!)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }
        val jsMain by getting {
            dependencies {
                implementation("io.ktor:ktor-client-js:$ktor_version")
            }
        }
        val jvmMain by getting {
            dependencies {
                implementation("io.ktor:ktor-client-okhttp:$ktor_version")
            }
        }
        val nativeMain by getting {
            dependencies {
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.4.2-native-mt") {
                    version {
                        strictly("1.4.2-native-mt")
                    }
                }
                implementation("io.ktor:ktor-client-curl:$ktor_version")
            }
        }
    }
}