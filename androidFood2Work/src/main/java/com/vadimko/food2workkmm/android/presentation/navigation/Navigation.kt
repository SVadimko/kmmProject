package com.vadimko.food2workkmm.android.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.vadimko.food2workkmm.android.presentation.recipe_detail.RecipeDetailScreen
import com.vadimko.food2workkmm.android.presentation.recipe_detail.RecipeDetailViewModel
import com.vadimko.food2workkmm.android.presentation.recipe_list.RecipeListScreen
import androidx.hilt.navigation.compose.hiltViewModel
import com.vadimko.food2workkmm.android.presentation.recipe_list.RecipeListViewModel

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screen.RecipeList.route
    ) {
        composable(Screen.RecipeList.route) {
            val viewModel: RecipeListViewModel = hiltViewModel()
            RecipeListScreen(
                state = viewModel.state.value,
                onTriggerEvent = viewModel::onTriggerEvent,
                onSelectRecipe = { recipeId->
                    navController.navigate("${Screen.RecipeDetail.route}/$recipeId")
                }
            )
        }
        composable(Screen.RecipeDetail.route + "/{recipeId}",
            arguments = listOf(navArgument("recipeId") {
                type = NavType.IntType
            }
            )
        ) {
            val viewModel: RecipeDetailViewModel = hiltViewModel()
            RecipeDetailScreen(
                state = viewModel.state.value,
                onTriggerEvent = viewModel::onTriggerEvent
             )
        }
    }
}