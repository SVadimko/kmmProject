package com.vadimko.food2workkmm.android.presentation.recipe_detail

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.vadimko.food2workkmm.domain.model.Recipe

@Composable
fun RecipeDetailScreen(
    recipe: Recipe?,
){
    if(recipe == null){
        Text("ERROR")
    } else {
        Text("${recipe.title}")
    }
}