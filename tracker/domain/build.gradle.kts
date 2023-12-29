plugins {
    `android-library`
    `kotlin-android`
}

apply(from = "$rootDir/compose-module.gradle")

android {
    namespace = "br.com.derlandybelchior.tracker.domain"
}

dependencies {
    implementation(project(Modules.core))
}