package com.vadimko.food2workkmm.android.presentation.recipe_detail

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vadimko.food2workkmm.domain.model.GenericMessageInfo
import com.vadimko.food2workkmm.domain.model.UIComponentType
import com.vadimko.food2workkmm.domain.util.GenericMessageInfoQueueUtil
import com.vadimko.food2workkmm.domain.util.Queue
import com.vadimko.food2workkmm.interactors.recipe_detail.GetRecipe
import com.vadimko.food2workkmm.presentation.recipe_detail.RecipeDetailEvents
import com.vadimko.food2workkmm.presentation.recipe_detail.RecipeDetailState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import java.util.*
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
            is RecipeDetailEvents.OnRemoveHeadMessageFromQueue -> {
                removeHeadMessage()
            }
            else -> {
                appendToMessageQueue(GenericMessageInfo.Builder()
                    .id(UUID.randomUUID().toString())
                    .title("Error")
                    .uiComponentType(UIComponentType.Dialog)
                    .description("Invalid Event"))
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
                appendToMessageQueue(message)
            }
        }.launchIn(viewModelScope)
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