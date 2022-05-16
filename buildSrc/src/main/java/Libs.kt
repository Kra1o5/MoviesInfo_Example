object Libs {
    val androidLibs = listOf(
        "androidx.core:core-ktx:${Versions.core}",
        "androidx.compose.ui:ui:${Versions.compose}",
        "androidx.compose.material:material:${Versions.compose}",
        "androidx.compose.ui:ui-tooling-preview:${Versions.compose}",
        "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifeCycle}",
        "androidx.activity:activity-compose:${Versions.activity}",
        "com.google.dagger:hilt-android:${Versions.hilt}"
    )

    val androidKaptLibs = listOf(
        "com.google.dagger:hilt-compiler:${Versions.hilt}"
    )

    val kotlinLibs = listOf(
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"
    )

    val testLibs = listOf(
        "junit:junit:${Versions.junit}"
    )

    val androidTestLibs = listOf(
        "androidx.test.espresso:espresso-core:${Versions.espresso}",
        "androidx.test.ext:junit-ktx:${Versions.junitKtx}",
        "androidx.compose.ui:ui-test-junit4:${Versions.compose}"
    )

    val debugLibs = listOf(
        "androidx.compose.ui:ui-tooling:${Versions.compose}",
        "androidx.compose.ui:ui-test-manifest:${Versions.compose}",
    )
}