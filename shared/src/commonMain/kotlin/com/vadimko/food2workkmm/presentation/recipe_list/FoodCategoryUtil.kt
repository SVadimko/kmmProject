package com.vadimko.food2workkmm.presentation.recipe_list

import com.vadimko.food2workkmm.presentation.recipe_list.FoodCategory.*

class FoodCategoryUtil {
    fun getAllFoodCategory():List<FoodCategory>{
        return listOf(
            ERROR,
            CHICKEN,
            BEEF,
            SOUP,
            DESSERT,
            VEGETARIAN,
            MILK,
            VEGAN,
            PIZZA,
            DONUT,
        )
    }

    fun getFoodCategory(value: String): FoodCategory?{
        val map = FoodCategory.values().associateBy (FoodCategory::value )
        return map[value]
    }
}