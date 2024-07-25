package uz.elmurod.newsapi.buildSrc

object Constants {
    object App {
        const val applicationId = "uz.elmurod.newsapi"
        const val minSdk = 21
        const val targetSdk = 31
        const val versionCode = 1
        const val versionName = "1.0"
        const val testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    object Build {
        const val compileSdk = 31
        const val buildTools = "30.0.3"
        const val jvmTarget = "1.8"
    }

    object Modules {
        const val app = ":app"
        const val core = ":core"
        const val domain = ":domain"
        const val navigation = ":navigation"
        const val database = ":database"
        const val network = ":network"
        const val main=":main"
        const val search=":search"
    }
}