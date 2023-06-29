package com.vadimko.food2workkmm.android.presentation.recipe_detail

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vadimko.food2workkmm.domain.model.Recipe
import com.vadimko.food2workkmm.interactors.recipe_detail.GetRecipe
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipeDetailViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val getRecipe: GetRecipe
): ViewModel() {

    val recipe: MutableState<Recipe?> = mutableStateOf(null)

    init {
        savedStateHandle.get<Int>("recipeId")?.let { recipeId ->
            viewModelScope.launch {
                getRecipe(recipeId)
//                recipe.value = recipeService.get(recipeId)
//                Log.wtf("response", recipe.value.toString())
            }
        }
    }

    private fun getRecipe(recipeId: Int){
        getRecipe.execute(recipeId).onEach {dataState ->
            Log.wtf("recipeGetVM", "${dataState.isLoading}")
            dataState.data?.let{recipe ->
                this.recipe.value = recipe
                Log.wtf("recipeGetVM", "${recipe}")
            }
            dataState.message?.let{message->
                Log.wtf("recipeGetVM", "${message}")
            }
        }.launchIn(viewModelScope)
    }
}