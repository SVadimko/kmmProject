package com.vadimko.food2workkmm.datasource.network

import android.util.Log
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*

actual class KtorClientFactory {
    /* actual fun build(): HttpClient {
        return HttpClient(Android) {
            install(ContentNegotiation) {
                gson(){
                    excludeFieldsWithoutExposeAnnotation()
                }

            }
        }
    }*/
    actual fun build(): HttpClient {
        return HttpClient(Android) {
            install(JsonFeature) {
                serializer = KotlinxSerializer(
                    kotlinx.serialization.json.Json {
                        ignoreUnknownKeys = true
                    }
                )

            }
            install(Logging) {
                logger = object : Logger {
                    override fun log(message: String) {
                        Log.wtf("HTTP_LOG", message)
                    }
                }
                level = LogLevel.ALL
            }
        }
    }
}