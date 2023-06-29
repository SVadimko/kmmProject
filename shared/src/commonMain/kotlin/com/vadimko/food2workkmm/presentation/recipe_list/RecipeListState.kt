package com.vadimko.food2workkmm.presentation.recipe_list

import com.vadimko.food2workkmm.domain.model.Recipe

data class RecipeListState(
    val isLoading: Boolean = false,
    val page: Int = 1,
    val query: String = "",
    val recipes: List<Recipe> = listOf()
)