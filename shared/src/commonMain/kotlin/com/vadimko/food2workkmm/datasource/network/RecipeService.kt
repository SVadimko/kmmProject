package com.vadimko.food2workkmm.datasource.network

import com.vadimko.food2workkmm.domain.model.Recipe

interface RecipeService {

    suspend fun search(
        page: Int,
        query: String
    ): List<Recipe>

    suspend fun get(
        id: Int
    ): Recipe

}