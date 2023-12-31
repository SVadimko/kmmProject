package com.vadimko.food2workkmm.android.presentation.navigation

sealed class Screen(
    val route: String
){
    object RecipeList: Screen("recipeList")
    object RecipeDetail: Screen("recipeDetail")
}
