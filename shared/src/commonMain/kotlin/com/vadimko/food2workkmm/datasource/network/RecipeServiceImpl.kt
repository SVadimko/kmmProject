package com.vadimko.food2workkmm.datasource.network

import com.vadimko.food2workkmm.datasource.network.model.RecipeDto
import com.vadimko.food2workkmm.datasource.network.model.RecipeSearchResponse
import com.vadimko.food2workkmm.datasource.network.model.toRecipe
import com.vadimko.food2workkmm.datasource.network.model.toRecipeList
import com.vadimko.food2workkmm.domain.model.Recipe
import io.ktor.client.*
import io.ktor.client.request.*

class RecipeServiceImpl(
    private val httpClient: HttpClient,
    private val baseUrl: String
): RecipeService {
    override suspend fun search(page: Int, query: String): List<Recipe> {
       return httpClient.get<RecipeSearchResponse>{
           url("$baseUrl/search?page=$page&query = $query")
           header("Authorization", TOKEN)
       }.results.toRecipeList()
    }

    override suspend fun get(id: Int): Recipe {
       return httpClient.get<RecipeDto>(){
            url("$BASE_URL/get?id=$id")
            header("Authorization", TOKEN)
        }.toRecipe()
    }

    companion object{
        const val TOKEN = "Token 9c8b06d329136da358c2d00e76946b0111ce2c48"
        const val BASE_URL = "https://food2fork.ca/api/recipe"
        const val RECIPE_PAGINATION_PAGE_SIZE = 30L
    }
}