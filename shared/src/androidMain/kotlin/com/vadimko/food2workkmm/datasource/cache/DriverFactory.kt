package com.vadimko.food2workkmm.datasource.cache

import android.content.Context
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver

actual class DriverFactory(
    private val context: Context
) {
    actual fun createDriver(): SqlDriver{
        return AndroidSqliteDriver(RecipeDatabase.Schema, context, "recipes.dp")
    }
}