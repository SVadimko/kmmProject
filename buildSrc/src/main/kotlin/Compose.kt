object Compose {
//    const val composeVersion = "1.0.0-beta09"
    const val composeVersion = "1.4.3"
    const val runtime = "androidx.compose.runtime:runtime:${composeVersion}"
    const val runtimeLiveData = "androidx.compose.runtime:runtime-livedata:${composeVersion}"
    const val ui = "androidx.compose.ui:ui:${composeVersion}"
    const val material = "androidx.compose.material:material:${composeVersion}"
    const val uiTooling = "androidx.compose.ui:ui-tooling:${composeVersion}"
    const val foundation = "androidx.compose.foundation:foundation:${composeVersion}"
    const val compiler = "androidx.compose.compiler:compiler:${composeVersion}"

    private const val constraintLayoutComposeVersion = "1.0.0-alpha07"
    const val constraintLayout = "androidx.constraintlayout:constraintlayout-compose:${constraintLayoutComposeVersion}"

//    private const val composeActivitiesVersion = "1.3.0-rc01"
    private const val composeActivitiesVersion = "1.7.2"
    const val activity = "androidx.activity:activity-compose:${composeActivitiesVersion}"

//    private const val composeNavigationVerson = "2.4.0-alpha01"
    private const val composeNavigationVerson = "2.6.0"
    const val navigation = "androidx.navigation:navigation-compose:${composeNavigationVerson}"
}