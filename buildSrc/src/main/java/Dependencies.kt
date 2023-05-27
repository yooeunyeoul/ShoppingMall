object Versions {
    const val core = "1.8.0"
    const val lifecycle = "2.6.1"
    const val activity_compose = "1.5.1"
    const val junit = "4.13.2"
    const val androidTestJunit = "1.1.5"
    const val androidTestEspresso = "3.5.1"
    const val navigationCompose = "2.5.3"
    const val coilCompose = "2.2.2"
    const val hiltAndroid = "2.45"
    const val hiltNavigation = "1.0.0"
    const val accompanistPager = "0.25.0"
    const val accompanistPagerIndicator = "0.25.0"
    const val pagingCompose = "1.0.0-alpha18"
    const val datastorePreferences = "1.0.0"
}


object Deps {
    const val core = "androidx.core:core-ktx:${Versions.core}"
    const val activity_compose = "androidx.activity:activity-compose:${Versions.activity_compose}"
    const val viewModelKtx = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"
    const val runtimeKtx = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycle}"
    const val runtimeCompose = "androidx.lifecycle:lifecycle-runtime-compose:${Versions.lifecycle}"
    const val viewModelCompose = "androidx.lifecycle:lifecycle-viewmodel-compose:${Versions.lifecycle}"
    const val navigationCompose = "androidx.navigation:navigation-compose:${Versions.navigationCompose}"
    const val coilCompose = "io.coil-kt:coil-compose:${Versions.coilCompose}"
    const val hiltAndroid = "com.google.dagger:hilt-android:${Versions.hiltAndroid}"
    const val hiltCompiler = "com.google.dagger:hilt-compiler:${Versions.hiltAndroid}"
    const val hiltNavigation = "androidx.hilt:hilt-navigation-compose:${Versions.hiltNavigation}"
    const val accompanistPager = "com.google.accompanist:accompanist-pager:${Versions.accompanistPager}"
    const val accompanistPagerIndicator = "com.google.accompanist:accompanist-pager-indicators:${Versions.accompanistPagerIndicator}"
    const val pagingCompose = "androidx.paging:paging-compose:${Versions.pagingCompose}"
    const val datastorePreferences = "androidx.datastore:datastore-preferences:${Versions.datastorePreferences}"

}

object TestDeps {
    const val jUnit = "junit:junit:${Versions.junit}"
}

object AndroidTestDeps{
    const val jUnit = "androidx.test.ext:junit:${Versions.androidTestJunit}"
    const val espresso = "androidx.test.espresso:espresso-core:${Versions.androidTestEspresso}"
}