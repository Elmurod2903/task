package uz.elmurod.newsapi.buildSrc

object Libs {

    const val kotlin_std_lib = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin_version}"
    const val core = "androidx.core:core-ktx:${Versions.core_ktx_version}"
    const val multiDex = "androidx.multidex:multidex:${Versions.multiDex_version}"
    const val reflect = "org.jetbrains.kotlin:kotlin-reflect:${Versions.reflect_version}"

    const val lifecycleRuntime =
        "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycle_version}"

    // ViewModel
    const val viewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle_version}"

    // LiveData
    const val liveData = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycle_version}"
    const val lifecycleSavedState =
        "androidx.lifecycle:lifecycle-viewmodel-savedstate:${Versions.lifecycle_version}"
    const val lifecycle_compiler =
        "androidx.lifecycle:lifecycle-common-java8:${Versions.lifecycle_version}"

    // optional - helpers for implementing LifecycleOwner in a Service
    const val lifecycle_service =
        "androidx.lifecycle:lifecycle-service:${Versions.lifecycle_version}"

    // Kotlin
    const val fragment = "androidx.fragment:fragment-ktx:${Versions.fragment_version}"
    const val glide = "com.github.bumptech.glide:glide:${Versions.glide_version}"

    //network
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit_version}"
    const val moshi = "com.squareup.moshi:moshi-kotlin:${Versions.moshi_version}"
    const val retrofit_moshi = "com.squareup.retrofit2:converter-moshi:${Versions.retrofit_version}"
    const val chucker_debug = "com.github.chuckerteam.chucker:library:${Versions.chucker_version}"
    const val chucker_release =
        "com.github.chuckerteam.chucker:library-no-op:${Versions.chucker_version}"

    const val location =
        "com.google.android.gms:play-services-location:${Versions.location_version}"

    const val paging = "androidx.paging:paging-runtime-ktx:${Versions.paging_version}"

    //shimmer
    const val shimmer = "com.facebook.shimmer:shimmer:${Versions.shimmer_version}"

    const val lottie = "com.airbnb.android:lottie:${Versions.lottie_version}"

    //hilt
    const val hilt = "com.google.dagger:hilt-android:${Versions.hilt_version}"

    //navigation
    const val navigation_fragment =
        "androidx.navigation:navigation-fragment-ktx:${Versions.navigation_version}"
    const val navigation_ui = "androidx.navigation:navigation-ui-ktx:${Versions.navigation_version}"
    const val navigation_dynamic =
        "androidx.navigation:navigation-dynamic-features-fragment:${Versions.navigation_version}"

    const val firebase_bom = "com.google.firebase:firebase-bom:${Versions.firebase_bom_version}"
    const val firebase_analytics = "com.google.firebase:firebase-analytics-ktx"
    const val firebase_crashlytics = "com.google.firebase:firebase-crashlytics-ktx"
    const val firebase_messaging = "com.google.firebase:firebase-messaging-ktx"

    const val room_lib = "androidx.room:room-runtime:${Versions.room_version}"
    const val room_ktx = "androidx.room:room-ktx:${Versions.room_version}"
    const val play_core = "com.google.android.play:core-ktx:${Versions.play_core_version}"
    const val camera_core = "androidx.camera:camera-core:${Versions.camerax_version}"
    const val camera2_lib = "androidx.camera:camera-camera2:${Versions.camerax_version}"
    const val camerax_lifecycle = "androidx.camera:camera-lifecycle:${Versions.camerax_version}"
    const val camera_view = "androidx.camera:camera-view:${Versions.camera_view_version}"

    const val ok_http_lib = "com.squareup.okhttp3:okhttp:${Versions.ok_http_version}"
    const val zelory_compressor_lib = "id.zelory:compressor:${Versions.compressor_version}"

    const val auth_service_lib =
        "com.google.android.gms:play-services-auth:${Versions.auth_service_version}"
    const val auth_api_lib =
        "com.google.android.gms:play-services-auth-api-phone:${Versions.auth_api_version}"

    const val coroutines =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines_version}"

    const val view_binding_delegate =
        "com.github.kirich1409:viewbindingpropertydelegate:${Versions.binding_version}"

    const val circle_image_view =
        "de.hdodenhof:circleimageview:${Versions.circle_image_view_version}"

    const val datastore_typed = "androidx.datastore:datastore:${Versions.data_store_version}"
    const val datastore_preferences =
        "androidx.datastore:datastore-preferences:${Versions.data_store_version}"

    const val map_kit_lite =
        "com.yandex.android:maps.mobile:${Versions.map_kit_lite_version}"

    const val androidGradlePlugin = "com.android.tools.build:gradle:7.0.2"

    object Kotlin {
        private const val version = "1.5.31"
        const val stdlib = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:$version"
        const val gradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$version"
        const val extensions = "org.jetbrains.kotlin:kotlin-android-extensions:$version"
        const val reflect = "org.jetbrains.kotlin:kotlin-reflect:$version"
    }

    object Coroutines {
        private const val version = "1.5.2"
        const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$version"
        const val android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$version"
        const val test = "org.jetbrains.kotlinx:kotlinx-coroutines-test:$version"
    }

    object OkHttp {
        private const val version = "4.9.1"
        const val okhttp = "com.squareup.okhttp3:okhttp:$version"
        const val logging = "com.squareup.okhttp3:logging-interceptor:$version"
    }

    object AndroidX {
        const val appcompat = "androidx.appcompat:appcompat:1.2.0"
        const val coreKtx = "androidx.core:core-ktx:1.7.0"
        const val fragment = "androidx.fragment:fragment-ktx:1.4.0"
        const val window = "androidx.window:window:1.0.0-beta04"


        object Constraint {
            private const val version = "2.1.1"
            const val constraintLayout = "androidx.constraintlayout:constraintlayout:$version"
        }
    }

    object Material {
        private const val version = "1.4.0"
        const val material = "com.google.android.material:material:$version"
    }

    object Room {
        private const val version = "2.4.0"
        const val runtime = "androidx.room:room-runtime:${version}"
        const val ktx = "androidx.room:room-ktx:$version"
        const val compiler = "androidx.room:room-compiler:${version}"
        const val room_paging = "androidx.room:room-paging:${version}"
    }

    object Coil {
        private const val version = "1.4.0"
        const val coil = "io.coil-kt:coil:$version"
    }

    object Multidex {
        private const val version = "2.0.1"
        const val multidex = "androidx.multidex:multidex:$version"
    }

    object Dagger {
        private const val version = "2.40"
        const val dagger = "com.google.dagger:dagger:$version"
        const val daggerKapt = "com.google.dagger:dagger-compiler:$version"
    }

    object Hilt {
        private const val version = "2.40"
        const val daggerHilt = "com.google.dagger:hilt-android:$version"
        const val daggerHiltKapt = "com.google.dagger:hilt-android-compiler:$version"
        const val hilt_plugin = "com.google.dagger:hilt-android-gradle-plugin:$version"

    }


    object ConstraintLayout {
        private const val version = "2.1.2"
        const val constraintLayout = "androidx.constraintlayout:constraintlayout:$version"
    }

    object Lifecycle {
        private const val lifecycle_version = "2.2.0"
        const val lifecycle_viewmodel =
            "androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycle_version"
        const val lifecycle_runtime = "androidx.lifecycle:lifecycle-runtime-ktx:$lifecycle_version"
    }

    object Navigation {
        private const val version = "2.3.5"
        const val navigation_fragment = "androidx.navigation:navigation-fragment-ktx:$version"
        const val navigation_ui = "androidx.navigation:navigation-ui-ktx:$version"
        const val navigation_dynamic =
            "androidx.navigation:navigation-dynamic-features-fragment:$version"
        const val safe_args = "androidx.navigation:navigation-safe-args-gradle-plugin:$version"
        const val common = "androidx.navigation:navigation-common-ktx:$version"
    }

    object Tests {
        private const val junitVersion = "4.13.2"
        private const val junitTestVersion = "1.1.3"
        private const val espressoCoreVersion = "3.4.0"

        const val junit = "junit:junit:$junitVersion"
        const val junitTest = "androidx.test.ext:junit:$junitTestVersion"
        const val espresso = "androidx.test.espresso:espresso-core:$espressoCoreVersion"
    }

    object Network {
        private const val retrofit_version = "2.9.0"
        private const val moshi_version = "1.12.0"

        const val retrofit = "com.squareup.retrofit2:retrofit:$retrofit_version"
        const val moshi = "com.squareup.moshi:moshi-kotlin:$moshi_version"
        const val moshiKapt = "com.squareup.moshi:moshi-kotlin-codegen:$moshi_version"
        const val retrofit_moshi = "com.squareup.retrofit2:converter-moshi:$retrofit_version"
    }

    object Paging {
        private const val paging_version = "3.1.0"
        const val paging = "androidx.paging:paging-runtime-ktx:$paging_version"

    }

    object Glide {
        private const val glide_version = "4.12.0"
        const val glide = "com.github.bumptech.glide:glide:$glide_version"

    }

    object Binding {
        private const val binding_version = "1.4.4"
        const val view_binding_delegate =
            "com.github.kirich1409:viewbindingpropertydelegate:$binding_version"
    }
}