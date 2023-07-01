package com.vadimko.food2workkmm.presentation.recipe_detail

import com.vadimko.food2workkmm.domain.model.Recipe

data class RecipeDetailState(
    val isLoading: Boolean = false,
    val recipe: Recipe? = null
)
