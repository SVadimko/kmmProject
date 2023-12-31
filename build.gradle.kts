plugins {
    //trick: for the same plugin versions in all sub-modules
    id("com.android.application").version("7.4.1").apply(false)
    id("com.android.library").version("7.4.1").apply(false)
    kotlin("android").version("1.8.10").apply(false)
    kotlin("multiplatform").version("1.8.10").apply(false)
    id("com.google.dagger.hilt.android").version("2.46.1").apply(false)
    id("com.squareup.sqldelight").version("1.5.5").apply(false)
}

buildscript {
    dependencies {
        classpath(Moko.mokoResGenerator)
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
