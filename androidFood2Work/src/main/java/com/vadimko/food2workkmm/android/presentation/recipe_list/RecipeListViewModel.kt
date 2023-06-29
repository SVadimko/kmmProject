package com.vadimko.food2workkmm.android.presentation.recipe_list

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vadimko.food2workkmm.interactors.recipe_list.SearchRecipes
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class RecipeListViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val searchRecipes: SearchRecipes
): ViewModel() {

    init {
        loadRecipes()
    }

    private fun loadRecipes(){
        searchRecipes.execute(
            page = 1,
            query = "chicken",
        ).onEach { dataState ->
        //    Log.wtf("recipeListVM", "${dataState.isLoading}")
            dataState.data?.let{recipes ->
         //       Log.wtf("recipeListVM", "${recipes}")
            }
            dataState.message?.let{message->
         //       Log.wtf("recipeListVM", "${message}")
            }
        }.launchIn(viewModelScope)
    }
}