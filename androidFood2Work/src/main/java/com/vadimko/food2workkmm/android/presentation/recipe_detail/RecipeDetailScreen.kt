package com.vadimko.food2workkmm.android.presentation.recipe_detail

import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.vadimko.food2workkmm.android.presentation.components.RECIPE_IMAGE_HEIGHT
import com.vadimko.food2workkmm.android.presentation.recipe_detail.components.LoadingRecipeShimmer
import com.vadimko.food2workkmm.android.presentation.recipe_detail.components.RecipeView
import com.vadimko.food2workkmm.android.presentation.theme.AppTheme
import com.vadimko.food2workkmm.presentation.recipe_detail.RecipeDetailEvents
import com.vadimko.food2workkmm.presentation.recipe_detail.RecipeDetailState

@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterialApi::class)
@Composable
fun RecipeDetailScreen(
    state: RecipeDetailState,
    onTriggerEvent: (RecipeDetailEvents) -> Unit
) {
    AppTheme(
        displayProgressBar = state.isLoading,
        dialogQueue = state.queue
    ) {
        if (state.recipe == null && state.isLoading) {
            LoadingRecipeShimmer(imageHeight = RECIPE_IMAGE_HEIGHT.dp)
        } else if (state.recipe == null) {
            Text(
                text = "We were unable to retrieve the details for this recipe.\n Try resetting the app",
                modifier = Modifier.padding(16.dp),
                style = MaterialTheme.typography.body1
            )

        } else {
            state.recipe?.let {
                RecipeView(it)
            }
        }
    }

}
