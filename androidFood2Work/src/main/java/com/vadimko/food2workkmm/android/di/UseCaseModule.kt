package com.vadimko.food2workkmm.android.di

import com.vadimko.food2workkmm.datasource.cache.RecipeCache
import com.vadimko.food2workkmm.datasource.network.RecipeService
import com.vadimko.food2workkmm.interactors.recipe_detail.GetRecipe
import com.vadimko.food2workkmm.interactors.recipe_list.SearchRecipes
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Singleton
    @Provides
    fun provideSearchRecipes(
        recipeService: RecipeService,
        recipeCache: RecipeCache
    ): SearchRecipes {
        return SearchRecipes(recipeService = recipeService, recipeCache = recipeCache)
    }

    @Singleton
    @Provides
    fun provideGetRecipe(
        recipeCache: RecipeCache,
        recipeService: RecipeService,
    ): GetRecipe {
        return GetRecipe(recipeCache = recipeCache, recipeService = recipeService)
    }
}