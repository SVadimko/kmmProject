package com.vadimko.food2workkmm.datasource.cache

import com.vadimko.food2workkmm.datasource.network.RecipeServiceImpl.Companion.RECIPE_PAGINATION_PAGE_SIZE
import com.vadimko.food2workkmm.domain.model.Recipe
import com.vadimko.food2workkmm.domain.util.DatetimeUtil

class RecipeCacheImpl(
    private val recipeDatabase: RecipeDatabase,
    private val datetimeUtil: DatetimeUtil
): RecipeCache {

    private val queries: RecipeDbQueries = recipeDatabase.recipeDbQueries

    override fun insert(recipe: Recipe) {
       queries.insertRecipe(
           id = recipe.id.toLong(),
           title = recipe.title,
           publisher = recipe.publisher,
           featured_image = recipe.featuredImage,
           rating = recipe.rating.toLong(),
           source_url = recipe.sourceUrl,
           ingredients = recipe.ingredients.convertIngredientListToString(),
           date_updated = datetimeUtil.toEpochMilliseconds(recipe.dateUpdated),
           date_added = datetimeUtil.toEpochMilliseconds(recipe.dateAdded)
       )
    }

    override fun insert(recipes: List<Recipe>) {
        for(recipe in recipes){
            insert(recipe)
        }
    }

    override fun search(query: String, page: Int): List<Recipe> {
        return queries.searchRecipes(
            query = query,
            pageSize = RECIPE_PAGINATION_PAGE_SIZE,
            offset = ((page -1)*RECIPE_PAGINATION_PAGE_SIZE)
        ).executeAsList().toRecipeList()
    }

    override fun getAll(page: Int): List<Recipe> {
        return queries.getAllRecipes(
            pageSize = RECIPE_PAGINATION_PAGE_SIZE,
            offset = ((page -1)*RECIPE_PAGINATION_PAGE_SIZE)
        ).executeAsList().toRecipeList()
    }

    override fun get(recipeId: Int): Recipe? {
        return try{
            queries.getRecipeById(recipeId.toLong()).executeAsOneOrNull()?.toRecipe()
        }catch (e:NullPointerException){
            null
        }
    }
}