package com.vadimko.food2workkmm.android.presentation.recipe_list

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import com.vadimko.food2workkmm.android.presentation.recipe_list.components.RecipeList
import com.vadimko.food2workkmm.android.presentation.recipe_list.components.SearchAppbar
import com.vadimko.food2workkmm.android.presentation.theme.AppTheme
import com.vadimko.food2workkmm.presentation.recipe_list.FoodCategoryUtil
import com.vadimko.food2workkmm.presentation.recipe_list.RecipeListEvents
import com.vadimko.food2workkmm.presentation.recipe_list.RecipeListState

@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterialApi::class)
@Composable
fun RecipeListScreen(
    state: RecipeListState,
    onTriggerEvent: (RecipeListEvents) -> Unit,
    onSelectRecipe: (Int) -> Unit
) {
    AppTheme(
        displayProgressBar = state.isLoading,
        dialogQueue = state.queue,
        onRemoveHeadMessageFromQueue = {
            onTriggerEvent(RecipeListEvents.OnRemoveHeadMessageFromQueue)
        }
    ) {
        val foodCategories = remember { FoodCategoryUtil().getAllFoodCategory() }
        Scaffold(
            topBar = {
                SearchAppbar(
                    query = state.query,
                    selectedCategory = state.selectedCategory,
                    categories = foodCategories,
                    onSelectedCategoryChanged = {
                        onTriggerEvent(
                            RecipeListEvents.OnSelectCategory(
                                it
                            )
                        )
                    },
                    onQueryChange = {
                        onTriggerEvent(RecipeListEvents.OnUpdateQuery(it))
                    },
                ) {
                    onTriggerEvent(RecipeListEvents.NewSearch)
                }
            }
        )
        { padding ->
            RecipeList(
                loading = state.isLoading,
                recipes = state.recipes,
                page = state.page,
                padding = padding,
                onTriggerNextPage = { onTriggerEvent(RecipeListEvents.NextPage) },
                onClickRecipeListItem = onSelectRecipe,
            )
        }

    }

}
