package com.vadimko.food2workkmm.presentation.recipe_detail

sealed class RecipeDetailEvents{
    data class GetRecipe(val recipeId:Int):RecipeDetailEvents()
}
