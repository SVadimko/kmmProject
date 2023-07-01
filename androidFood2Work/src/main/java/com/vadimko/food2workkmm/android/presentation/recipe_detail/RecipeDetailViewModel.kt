package com.vadimko.food2workkmm.android.presentation.recipe_detail

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vadimko.food2workkmm.interactors.recipe_detail.GetRecipe
import com.vadimko.food2workkmm.presentation.recipe_detail.RecipeDetailEvents
import com.vadimko.food2workkmm.presentation.recipe_detail.RecipeDetailState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipeDetailViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val getRecipe: GetRecipe
) : ViewModel() {

//    val recipe: MutableState<Recipe?> = mutableStateOf(null)

    val state: MutableState<RecipeDetailState> = mutableStateOf(RecipeDetailState())

    fun onTriggerEvent(event: RecipeDetailEvents) {
        when (event) {
            is RecipeDetailEvents.GetRecipe -> {
                getRecipe(event.recipeId)
            }
            else -> {
                handleError("Error")
            }
        }
    }

    init {
        savedStateHandle.get<Int>("recipeId")?.let { recipeId ->
            viewModelScope.launch {
                onTriggerEvent(RecipeDetailEvents.GetRecipe(recipeId))
//                getRecipe(recipeId)
//                recipe.value = recipeService.get(recipeId)
//                Log.wtf("response", recipe.value.toString())
            }
        }
    }

    private fun getRecipe(recipeId: Int) {
        getRecipe.execute(recipeId).onEach { dataState ->
            state.value = state.value.copy(isLoading = dataState.isLoading)
            dataState.data?.let { recipe ->
                state.value = state.value.copy(recipe = recipe)
            }
            dataState.message?.let { message ->
                handleError(message)
            }
        }.launchIn(viewModelScope)
    }

    private fun handleError(message:String){

    }
}