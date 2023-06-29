package com.vadimko.food2workkmm.android.presentation.recipe_detail

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.hilt.navigation.compose.hiltViewModel
import com.vadimko.food2workkmm.android.presentation.components.RecipeImage
import com.vadimko.food2workkmm.android.presentation.recipe_list.components.RecipeCard
import com.vadimko.food2workkmm.android.presentation.theme.AppTheme
import com.vadimko.food2workkmm.domain.model.Recipe

@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterialApi::class)
@Composable
fun RecipeDetailScreen(
    recipe: Recipe?,
){
    AppTheme(displayProgressBar = false) {
        if(recipe == null){
            Text("ERROR")
        } else {
//           RecipeImage(url = recipe.featuredImage, contentDescription = "Image")
            RecipeCard(recipe = recipe, ) {
                
            }
        }
    }

}