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
    const val retrofit = "2.9.0"
    const val retrofitConverterGson = "2.9.0"
    const val retrofitSerialization = "0.8.0"
    const val okhttp = "5.0.0-alpha.1"
    const val okhttpLoggingInterceptor = "5.0.0-alpha.3"
    const val roomVersion = "2.5.1"
    const val coroutineTest = "1.6.4"

    const val javaInject = "1"
    const val gson = "2.9.0"

    const val appCompat = "1.6.1"
}


object Deps {
    const val core = "androidx.core:core-ktx:${Versions.core}"
    const val activity_compose = "androidx.activity:activity-compose:${Versions.activity_compose}"

    const val appCompat = "androidx.appcompat:appcompat:${Versions.appCompat}"



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

    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val retrofitConverterGson = "com.squareup.retrofit2:converter-gson:${Versions.retrofitConverterGson}"
    const val retrofitSerialization = "com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:${Versions.retrofitSerialization}"

    const val okhttp = "com.squareup.okhttp3:okhttp:${Versions.okhttp}"
    const val okhttpLoggingInterceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.okhttpLoggingInterceptor}"

    const val roomRuntime = "androidx.room:room-runtime:${Versions.roomVersion}"
    const val roomCompiler = "androidx.room:room-compiler:${Versions.roomVersion}"
    const val roomKtx = "androidx.room:room-ktx:${Versions.roomVersion}"
    const val roomPaging = "androidx.room:room-paging:${Versions.roomVersion}"

    const val javaInject = "javax.inject:javax.inject:${Versions.javaInject}"
    const val gson = "com.google.code.gson:gson:${Versions.gson}"






}

object TestDeps {
    const val jUnit = "junit:junit:${Versions.junit}"
}

object AndroidTestDeps{
    const val jUnit = "androidx.test.ext:junit:${Versions.androidTestJunit}"
    const val espresso = "androidx.test.espresso:espresso-core:${Versions.androidTestEspresso}"
    const val coroutinesTest = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutineTest}"
}