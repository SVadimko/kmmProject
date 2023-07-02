package com.vadimko.food2workkmm.presentation.recipe_list

import com.vadimko.food2workkmm.domain.model.Recipe
import com.vadimko.food2workkmm.domain.util.Queue

data class RecipeListState(
    val isLoading: Boolean = false,
    val page: Int = 1,
    val query: String = "",
    val selectedCategory: FoodCategory? = null,
    val recipes: List<Recipe> = listOf(),
    val queue: Queue<String> = Queue(mutableListOf())
)