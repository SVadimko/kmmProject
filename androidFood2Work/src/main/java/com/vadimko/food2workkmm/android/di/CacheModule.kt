package com.vadimko.food2workkmm.android.di

import com.vadimko.food2workkmm.android.BaseApplication
import com.vadimko.food2workkmm.datasource.cache.*
import com.vadimko.food2workkmm.domain.util.DatetimeUtil
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CacheModule {

    @Singleton
    @Provides
    fun provideRecipeDatabase(context:BaseApplication): RecipeDatabase {
        return RecipeDatabaseFactory(driverFactory = DriverFactory(context)).createDataBase()
    }

    @Singleton
    @Provides
    fun provideRecipeCache(recipeDatabase: RecipeDatabase):RecipeCache{
        return RecipeCacheImpl(
            recipeDatabase,
            DatetimeUtil()
        )
    }
}