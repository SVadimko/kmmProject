package com.vadimko.food2workkmm.android.presentation.recipe_list

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import com.vadimko.food2workkmm.android.presentation.recipe_list.components.RecipeList
import com.vadimko.food2workkmm.android.presentation.theme.AppTheme
import com.vadimko.food2workkmm.presentation.recipe_list.RecipeListState

@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterialApi::class)
@Composable
fun RecipeListScreen(
    state: RecipeListState,
    onClickRecipeListItem: (Int) -> Unit
) {
    AppTheme(displayProgressBar = false) {
        RecipeList(
            loading = state.isLoading,
            recipes = state.recipes,
            onClickRecipeListItem = onClickRecipeListItem
        )

    }

}
