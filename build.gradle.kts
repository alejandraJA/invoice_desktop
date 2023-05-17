import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    kotlin("multiplatform")
    id("org.jetbrains.compose")
}

group = "com.example"
version = "1.0-SNAPSHOT"

repositories {
    google()
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
}
dependencies {
}

kotlin {
    jvm {
        jvmToolchain(11)
        withJava()
    }
    sourceSets {
        val jvmMain by getting {
            dependencies {
                implementation(compose.desktop.currentOs)

                // Dependencies injection
                implementation("io.insert-koin:koin-core:3.4.0")

                // Material 3
                implementation("org.jetbrains.compose.material3:material3-desktop:1.5.0-dev1043")

                // Pager
                implementation ("com.google.accompanist:accompanist-pager:0.25.0") // Pager
                implementation ("com.google.accompanist:accompanist-pager-indicators:0.25.0") // Pager Indicators

                // Retrofit
                implementation("com.squareup.retrofit2:retrofit:2.9.0")
                implementation("com.squareup.retrofit2:converter-gson:2.9.0")
                implementation("com.squareup.okhttp3:logging-interceptor:5.0.0-alpha.4")
                implementation("com.squareup.okhttp3:okhttp:5.0.0-alpha.4")
                implementation("org.jbundle.util.osgi.wrapped:org.jbundle.util.osgi.wrapped.org.apache.http.client:4.1.2")

                // Corrutinas
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-swing:1.6.4")
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-jdk8:1.6.4")
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-slf4j:1.6.4")
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-debug:1.6.4")

            }
        }
        val jvmTest by getting
    }
}

compose.desktop {
    application {
        mainClass = "MainKt"
        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "compose_desktop"
            packageVersion = "1.0.0"
        }
    }
}