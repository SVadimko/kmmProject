package com.vadimko.food2workkmm.datasource.network

import io.ktor.client.*

expect class KtorClientFactory {
    fun build(): HttpClient
}