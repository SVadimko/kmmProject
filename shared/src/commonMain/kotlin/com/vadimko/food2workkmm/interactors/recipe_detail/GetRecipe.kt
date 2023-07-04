package com.vadimko.food2workkmm.interactors.recipe_detail

import com.vadimko.food2workkmm.datasource.cache.RecipeCache
import com.vadimko.food2workkmm.datasource.network.RecipeService
import com.vadimko.food2workkmm.domain.model.GenericMessageInfo
import com.vadimko.food2workkmm.domain.model.Recipe
import com.vadimko.food2workkmm.domain.model.UIComponentType
import com.vadimko.food2workkmm.domain.util.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetRecipe(
    private val recipeCache: RecipeCache,
    private val recipeService: RecipeService
) {

    fun execute(
        recipeId: Int
    ): Flow<DataState<Recipe>> = flow {
    emit(DataState.loading())
        try{
            val recipe = recipeCache.get(recipeId)
            emit(DataState.data(message = null, data = recipe))

        } catch (e: Exception){
            emit(DataState.error(GenericMessageInfo.Builder()
                .id("GetRecipes.Error")
                .title("Error")
                .uiComponentType(UIComponentType.Dialog)
                .description(e.message?:"Unknown Error")))
        }
    }
}