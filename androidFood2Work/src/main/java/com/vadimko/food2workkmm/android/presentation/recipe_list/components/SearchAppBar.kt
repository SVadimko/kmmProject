package com.vadimko.food2workkmm.android.presentation.recipe_list.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.vadimko.food2workkmm.SharedRes
import com.vadimko.food2workkmm.android.presentation.support.stringResource
import com.vadimko.food2workkmm.presentation.recipe_list.FoodCategory
import com.vadimko.food2workkmm.presentation.recipe_list.FoodCategoryUtil

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchAppbar(
    query: String,
    selectedCategory: FoodCategory? = null,
    categories: List<FoodCategory>,
    onSelectedCategoryChanged: (FoodCategory) -> Unit,
    onQueryChange: (String) -> Unit,
    onExecuteSearch: () -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        color = MaterialTheme.colors.secondary,
        elevation = 8.dp
    ) {
        var isTextFieldFocused by remember { mutableStateOf(false) }
        val searchHistory = remember { mutableStateListOf<String>() }
        val keyboardController = LocalSoftwareKeyboardController.current
        Column {
            Row(modifier = Modifier.fillMaxWidth()) {
                TextField(
                    value = query,
                    onValueChange = onQueryChange,
                    label = {
                        Text(text = stringResource(id = SharedRes.strings.search))
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Search
                    ),
                    keyboardActions = KeyboardActions(onSearch = {
                        if (!searchHistory.contains(query)) {
                            if (searchHistory.size >= 3) {
                                searchHistory.removeFirst()
                            }
                            searchHistory.add(query)
                        }
                        onExecuteSearch()
                        keyboardController?.hide()

                    }
                    ),
                    leadingIcon = {
                        Icon(
                            Icons.Filled.Search,
                            contentDescription = null
                        )
                    },
                    trailingIcon = {
                        if (isTextFieldFocused && query.isNotEmpty()) {
                            IconButton(
                                onClick = {
                                    onQueryChange("")
                                    keyboardController?.hide()
                                },
                                modifier = Modifier.padding(4.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Clear,
                                    contentDescription = "Clear",
                                    //tint = Color.Gray
                                )
                            }
                        }
                    },
                    textStyle = TextStyle(color = MaterialTheme.colors.onSurface),
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = MaterialTheme.colors.surface
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .onFocusChanged { isTextFieldFocused = it.isFocused }
                )
            }
            if (isTextFieldFocused) {
                LazyColumn(modifier = Modifier.fillMaxWidth()) {
                    items(searchHistory.reversed()) { query ->
                        Text(
                            text = query,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp, horizontal = 4.dp)
                                .clickable {
                                    onQueryChange(query)
                                    onExecuteSearch()
                                    keyboardController?.hide()
                                }
                        )
                    }
                }
            }
            LazyRow(
                modifier = Modifier.padding(start = 8.dp, top = 8.dp, bottom = 8.dp),
            ) {
                items(categories) {
                    FoodCategoryChip(
                        category = it.value,
                        isSelected = selectedCategory == it,
                        onSelectedCategoryChanged = { categoryName ->
                            FoodCategoryUtil().getFoodCategory(categoryName)?.let { newCategory ->
                                onSelectedCategoryChanged(newCategory)
                            }
                        })
                }
            }
        }
    }
}