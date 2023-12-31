package com.vadimko.food2workkmm.android.presentation.recipe_list.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.vadimko.food2workkmm.android.presentation.components.RECIPE_IMAGE_HEIGHT
import com.vadimko.food2workkmm.datasource.network.RecipeServiceImpl.Companion.RECIPE_PAGINATION_PAGE_SIZE
import com.vadimko.food2workkmm.domain.model.Recipe
import com.vadimko.food2workkmm.domain.util.DataState.Companion.loading

@Composable
fun RecipeList(
    loading: Boolean,
    recipes: List<Recipe>,
    padding: PaddingValues,
    page: Int,
    onTriggerNextPage:()->Unit,
    onClickRecipeListItem: (Int) -> Unit
) {
    if (loading && recipes.isEmpty()) {
        LoadingRecipeListShimmer(imageHeight = RECIPE_IMAGE_HEIGHT.dp)
    } else if (recipes.isEmpty()) {

    } else {
        LazyColumn() {
            itemsIndexed(
                items = recipes
            ) { index, recipe ->
                if(((index +1) >= page*RECIPE_PAGINATION_PAGE_SIZE) && !loading){
                  onTriggerNextPage()
                }
                RecipeCard(recipe = recipe, onClick = { onClickRecipeListItem(recipe.id) })
            }
        }
    }
}