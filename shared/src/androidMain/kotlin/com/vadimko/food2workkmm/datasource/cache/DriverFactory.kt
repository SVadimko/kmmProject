package com.vadimko.food2workkmm.datasource.cache

import android.content.Context
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver
import com.vadimko.food2forkkmm.datasource.cache.RecipeDatabase

actual class DriverFactory(
    private val context: Context
) {
    actual fun createDriver(): SqlDriver{
        return AndroidSqliteDriver(RecipeDatabase.Schema, context, "recipes.dp")
    }
}