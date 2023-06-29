package com.vadimko.food2workkmm.datasource.cache

import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.drivers.native.NativeSqliteDriver
import com.vadimko.food2forkkmm.datasource.cache.RecipeDatabase

actual class DriverFactory {
    actual fun createDriver():SqlDriver{
        return NativeSqliteDriver(RecipeDatabase.Schema, "recipes.db")
    }
}