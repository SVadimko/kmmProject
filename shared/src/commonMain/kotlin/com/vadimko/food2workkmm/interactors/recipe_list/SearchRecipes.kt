package com.vadimko.food2workkmm.interactors.recipe_list

import com.vadimko.food2workkmm.datasource.cache.RecipeCache
import com.vadimko.food2workkmm.datasource.network.RecipeService
import com.vadimko.food2workkmm.domain.model.Recipe
import com.vadimko.food2workkmm.domain.util.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SearchRecipes(
    private val recipeService: RecipeService,
    private val recipeCache: RecipeCache
) {

    fun execute(
        page: Int,
        query: String
    ): Flow<DataState<List<Recipe>>>  = flow {
        emit(DataState.loading())
        try{
            val recipes = recipeService.search(page, query)
            recipeCache.insert(recipes)
            val cacheResults = if(query.isBlank()){
                recipeCache.getAll(page = page)
            } else {
               recipeCache.search(
                   query = query,
                   page = page
               )
            }
            emit(DataState.data(message = null, data = cacheResults))
        } catch (e:Exception){
            emit(DataState.error(e.message?:"Unknown Error"))
        }
    }
}