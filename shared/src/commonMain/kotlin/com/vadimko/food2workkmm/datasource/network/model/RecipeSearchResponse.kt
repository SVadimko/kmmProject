package com.vadimko.food2workkmm.datasource.network.model

import com.vadimko.food2workkmm.domain.model.Recipe
import com.vadimko.food2workkmm.domain.util.DatetimeUtil
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class RecipeSearchResponse(
    @SerialName("count")
    var count: Int?,

    @SerialName("next")
    var next: String?,

    @SerialName("previous")
    var previous: String?,

    @SerialName("results")
    var results: List<RecipeDto>,
)

fun RecipeDto.toRecipe(): Recipe {
    val datetimeUtil = DatetimeUtil()
    return Recipe(
        id = pk,
        title = title,
        featuredImage = featuredImage,
        rating = rating,
        publisher = publisher,
        sourceUrl = sourceUrl,
        ingredients = ingredients,
        dateAdded = datetimeUtil.toLocalDate(longDateAdded.toDouble()),
        dateUpdated = datetimeUtil.toLocalDate(longDateUpdated.toDouble()),
    )
}

fun List<RecipeDto>.toRecipeList(): List<Recipe>{
    return map{it.toRecipe()}
}