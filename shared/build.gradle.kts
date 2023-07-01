plugins {
//    kotlin("multiplatform")
//    kotlin("native.cocoapods")
//    id("com.android.library")
    kotlin(KotlinPlugins.multiplatform)
    kotlin(KotlinPlugins.cocoapods)
    kotlin(KotlinPlugins.serialization) version Kotlin.version
    id(Plugins.androidLibrary)
    id(Plugins.sqlDelight)
}

version = "1.5.5"

kotlin {
    android {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }
    iosX64()
    iosArm64()
    iosSimulatorArm64()

    cocoapods {
        summary = "Some description for the Shared Module"
        homepage = "Link to the Shared Module homepage"
        version = "1.0"
        ios.deploymentTarget = "14.1"
        podfile = project.file("../iosFood2Work/Podfile")
        framework {
            baseName = "shared"
        }
    }

    sourceSets {
        /*   val commonMain by getting {
               dependencies{
                   implementation(Kotlinx.datetime)
                   implementation(Ktor.core)
                   implementation(Ktor.clientSerialization)
               }
           }
           val androidMain by getting {
               dependencies{
                   implementation(Ktor.android)
               }
           }
           val iosMain by getting{
               dependencies {
                   implementation(Ktor.ios)
               }
           }*/
        val commonMain by getting {
            dependencies {
                implementation(Kotlinx.datetime)
                implementation(Ktor.core)
                implementation(Ktor.clientSerialization)
                implementation(Ktor.logging)
                implementation(SQLDelight.runtime)
                //implementation(Ktor.negotiation)
                //implementation(Ktor.json)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        val androidMain by getting {
            //dependsOn(commonMain)
            dependencies {
                implementation(Ktor.android)
                implementation(SQLDelight.androidDriver)

            }
        }
        val androidUnitTest by getting
        val iosX64Main by getting {
            dependencies {
            }
        }
        val iosArm64Main by getting {
            dependencies {
            }
        }
        val iosSimulatorArm64Main by getting
        val iosMain by creating {
            dependencies {
                //implementation(Ktor.core)
                //implementation(Ktor.clientSerialization)
                //implementation(Ktor.negotiation)
                // implementation(Ktor.json)
                implementation(Ktor.ios)
                implementation(SQLDelight.nativeDriver)
            }
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)

        }
        val iosX64Test by getting
        val iosArm64Test by getting
        val iosSimulatorArm64Test by getting
        val iosTest by creating {
            dependsOn(commonTest)
            iosX64Test.dependsOn(this)
            iosArm64Test.dependsOn(this)
            iosSimulatorArm64Test.dependsOn(this)
        }
    }
}

sqldelight {
    database("RecipeDatabase") {
        packageName = "com.vadimko.food2forkkmm.datasource.cache"
        sourceFolders = listOf("sqldelight")
    }
}

android {
    namespace = "com.vadimko.food2workkmm"
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    compileSdk = Application.targetSdk
    defaultConfig {
        minSdk = Application.minSdk
    }
    configurations {
        create("androidTestApi")
        create("androidTestDebugApi")
        create("androidTestReleaseApi")
        create("testApi")
        create("testDebugApi")
        create("testReleaseApi")
    }
}

