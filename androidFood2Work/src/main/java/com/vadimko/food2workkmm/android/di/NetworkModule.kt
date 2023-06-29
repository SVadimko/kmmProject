package com.vadimko.food2workkmm.android.di

import com.vadimko.food2workkmm.datasource.network.KtorClientFactory
import com.vadimko.food2workkmm.datasource.network.RecipeService
import com.vadimko.food2workkmm.datasource.network.RecipeServiceImpl
import com.vadimko.food2workkmm.datasource.network.RecipeServiceImpl.Companion.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.*
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideHttpClient():HttpClient{
        return KtorClientFactory().build()
    }

    @Singleton
    @Provides
    fun provideRecipeService(
        httpClient: HttpClient
    ): RecipeService {
       return RecipeServiceImpl(
           httpClient = httpClient,
           baseUrl = BASE_URL
       )
    }
}