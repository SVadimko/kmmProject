package com.vadimko.food2workkmm.android.presentation.recipe_list

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vadimko.food2workkmm.SharedRes
import com.vadimko.food2workkmm.android.presentation.support.stringResource
import com.vadimko.food2workkmm.android.presentation.support.stringResourceCommon
import com.vadimko.food2workkmm.domain.model.*
import com.vadimko.food2workkmm.domain.util.GenericMessageInfoQueueUtil
import com.vadimko.food2workkmm.domain.util.Queue
import com.vadimko.food2workkmm.interactors.recipe_list.SearchRecipes
import com.vadimko.food2workkmm.presentation.recipe_list.FoodCategory
import com.vadimko.food2workkmm.presentation.recipe_list.RecipeListEvents
import com.vadimko.food2workkmm.presentation.recipe_list.RecipeListState
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList

@HiltViewModel
class RecipeListViewModel @Inject constructor(
    @ApplicationContext private val ctx: Context,
    private val savedStateHandle: SavedStateHandle,
    private val searchRecipes: SearchRecipes
): ViewModel() {

    val state:MutableState<RecipeListState> = mutableStateOf(RecipeListState())

    init {
        //loadRecipes()
        onTriggerEvent(RecipeListEvents.LoadRecipes)
        val messageInfoBuilder = GenericMessageInfo.Builder()
            .id(UUID.randomUUID().toString())
            .title("FKITA")
            .uiComponentType(UIComponentType.Dialog)
            .description("That's something weird happens")
            .positive(
                PositiveAction(
                    positiveBtnTxt = "YEP",
                    onPositiveAction = {
                        state.value = state.value.copy(query = "Whoa")
                        onTriggerEvent(RecipeListEvents.NewSearch)
                    }
                )
            )
            .negative(
                NegativeAction(
                    negativeBtnTxt = "NO",
                    onNegativeAction = {
                        state.value = state.value.copy(query = "NO")
                        onTriggerEvent(RecipeListEvents.NewSearch)
                    }
                )
            )
        appendToMessageQueue(messageInfoBuilder)
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
            is RecipeListEvents.OnRemoveHeadMessageFromQueue -> {
                removeHeadMessage()
            }
            else -> {
//                appendToMessageQueue(stringResourceCommon(id = SharedRes.strings.hello_world, context = ctx))
                appendToMessageQueue(GenericMessageInfo.Builder()
                    .id(UUID.randomUUID().toString())
                    .title("Error")
                    .uiComponentType(UIComponentType.Dialog)
                    .description("Invalid Event"))
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
                appendToMessageQueue(message)
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

    private fun appendToMessageQueue(messageInfo:GenericMessageInfo.Builder){
        if(!GenericMessageInfoQueueUtil().doesMessageAlreadyExistInQueue(
                queue = state.value.queue,
                messageInfo = messageInfo.build()
        )) {
            val queue = state.value.queue
            queue.add(messageInfo.build())
            state.value = state.value.copy(queue = queue)
        }
    }

    private fun removeHeadMessage(){
        try {
           val queue = state.value.queue
           queue.remove()
            state.value = state.value.copy(queue = Queue(mutableListOf()))
            state.value = state.value.copy(queue = queue)
        } catch (e: Exception){

        }
    }
}