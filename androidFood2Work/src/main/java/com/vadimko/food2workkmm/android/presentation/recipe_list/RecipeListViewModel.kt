package com.vadimko.food2workkmm.android.presentation.recipe_list

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vadimko.food2workkmm.domain.model.Recipe
import com.vadimko.food2workkmm.interactors.recipe_list.SearchRecipes
import com.vadimko.food2workkmm.presentation.recipe_list.FoodCategory
import com.vadimko.food2workkmm.presentation.recipe_list.RecipeListEvents
import com.vadimko.food2workkmm.presentation.recipe_list.RecipeListState
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

    val state:MutableState<RecipeListState> = mutableStateOf(RecipeListState())

    init {
        //loadRecipes()
        onTriggerEvent(RecipeListEvents.LoadRecipes)
    }

    fun onTriggerEvent(event: RecipeListEvents){
        when(event){
            is RecipeListEvents.LoadRecipes -> {
                loadRecipes()
            }
            is RecipeListEvents.NextPage -> {
                nextPage()
            }
            is RecipeListEvents.OnUpdateQuery -> {
                state.value = state.value.copy(query = event.query, selectedCategory = null)
            }
            is RecipeListEvents.NewSearch -> {
                newSearch()
            }
            is RecipeListEvents.OnSelectCategory -> {
                onSelectedCategory(event.category)
                state.value = state.value.copy(selectedCategory = event.category, query = event.category.value)
            }
            else -> {
                handleError("ERROR")
            }
        }
    }

    private fun nextPage(){
        state.value = state.value.copy(page = state.value.page + 1)
        loadRecipes()
    }

    private fun loadRecipes(){
        searchRecipes.execute(
            page = state.value.page,
            query = state.value.query,
        ).onEach { dataState ->
            state.value = state.value.copy(isLoading = dataState.isLoading)
        //    Log.wtf("recipeListVM", "${dataState.isLoading}")
            dataState.data?.let{recipes ->
                appendRecipes(recipes)
            }
            dataState.message?.let{message->
                handleError(message)
         //       Log.wtf("recipeListVM", "${message}")
            }
        }.launchIn(viewModelScope)
    }

    private fun newSearch(){
        state.value = state.value.copy(page = 1, recipes = listOf())
        loadRecipes()
    }

    private fun onSelectedCategory(category: FoodCategory){
        state.value = state.value.copy(selectedCategory = category, query = category.value)
        newSearch()
    }

    private fun appendRecipes(recipes:List<Recipe>){
        val curr = ArrayList(state.value.recipes)
        curr.addAll(recipes)
        state.value = state.value.copy(recipes = curr)
    }

    private fun handleError(error:String){
        val queue = state.value.queue
        queue.add(error)
        state.value = state.value.copy(queue = queue)
    }
}