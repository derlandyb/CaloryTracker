plugins {
    `android-library`
    `kotlin-android`
}

apply(from = "$rootDir/base-module.gradle")

android {
    namespace = "br.com.derlandybelchior.onboarding.domain"
}

dependencies {
    implementation(project(Modules.core))
}