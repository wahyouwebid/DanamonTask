import buildtype.AndroidBuildType.DEBUG
import buildtype.AndroidBuildType.RELEASE
import config.AndroidConfig
import dependencies.Dependencies

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
    id("kotlin-parcelize")
}

@Suppress("UnstableApiUsage")
android {
    compileSdk = AndroidConfig.compileSdk

    defaultConfig {
        namespace = AndroidConfig.appNameSpace
        minSdk = AndroidConfig.minSdk
        versionCode = AndroidConfig.versionCode
        versionName = AndroidConfig.versionName
        testInstrumentationRunner = AndroidConfig.testInstrumentRunner
    }

    buildTypes {
        named(DEBUG) {
            isMinifyEnabled = false
            proguardFiles (getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
            buildConfigField("String", "baseUrl", "\"https://jsonplaceholder.typicode.com/\"")
        }

        named(RELEASE) {
            isMinifyEnabled = true
            proguardFiles (getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
            buildConfigField("String", "baseUrl", "\"https://jsonplaceholder.typicode.com/\"")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    buildFeatures {
        buildConfig = true
        viewBinding = true
    }

    testOptions {
        unitTests.isIncludeAndroidResources = true
    }
}

dependencies {
    Dependencies.AndroidLib.apply {
        implementation(core)
        implementation(appCompat)
        implementation(navigationIu)
        implementation(navigationFragment)
        implementation(constraintLayout)
        implementation(pagingRuntime)
        implementation(pagingRxJava)
        implementation(securityCrypto)
    }

    Dependencies.UiLib.apply {
        implementation(material)
        implementation(glide)
        kapt(glideCompiler)
        implementation(pretty)
    }

    Dependencies.NetworkingLib.apply {
        implementation(retrofit2)
        implementation(rxJava)
        implementation(converterGson)
        implementation(okhttp)
        implementation(okhttpInterceptor)
        debugImplementation(chucker)
        releaseImplementation(chuckerNoop)
    }

    Dependencies.DependencyInjectionLib.apply {
        implementation(hilt)
        kapt(hiltCompiler)
        kapt(hiltAndroidCompiler)
    }

    Dependencies.ReactiveLib.apply {
        implementation(rxAndroid)
        implementation(rxJava)
        implementation(rxBinding)
    }

    Dependencies.RoomLib.apply {
        implementation(room)
        implementation(roomRuntime)
        implementation(roomRxJava)
        implementation(roomPaging)
        kapt(roomCompiler)
    }

    Dependencies.TestingLib.apply {
        testImplementation(androidJunit)
        androidTestImplementation(espresso)
        androidTestImplementation(junit)
    }
}
