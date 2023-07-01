object Ktor {
    private const val ktorVersion = "1.6.8"
//    private const val ktorVersion = "2.3.1"
    const val core = "io.ktor:ktor-client-core:${ktorVersion}"
    const val clientSerialization = "io.ktor:ktor-client-serialization:${ktorVersion}"
    const val logging = "io.ktor:ktor-client-logging:$ktorVersion"
   // const val negotiation = "io.ktor:ktor-client-content-negotiation:$ktorVersion"
    //const val json = "io.ktor:ktor-serialization-gson:$ktorVersion"
    const val android = "io.ktor:ktor-client-android:${ktorVersion}"
    const val ios = "io.ktor:ktor-client-ios:${ktorVersion}"
}