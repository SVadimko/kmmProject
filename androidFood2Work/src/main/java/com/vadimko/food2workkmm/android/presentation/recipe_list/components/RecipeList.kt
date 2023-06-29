package com.vadimko.food2workkmm.android.presentation.recipe_list.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import com.vadimko.food2workkmm.domain.model.Recipe

@Composable
fun RecipeList(
   loading: Boolean,
   recipes: List<Recipe>,
   onClickRecipeListItem:(Int)->Unit
){
    if(loading && recipes.isEmpty()){

    } else if(recipes.isEmpty()){

    } else {
        LazyColumn() {
            /*  items(100) { recipeId ->
                  Row(modifier = Modifier
                      .fillMaxWidth()
                      .clickable { onSelectedRecipe.invoke(recipeId) }
                  ){
                      Text(modifier = Modifier.padding(16.dp), text = "Recipe id = $recipeId")
                  }

              }*/
            itemsIndexed(
                items = recipes
            ){index, recipe ->
                RecipeCard(recipe = recipe, onClick = {onClickRecipeListItem(recipe.id)})
            }
        }
    }
}