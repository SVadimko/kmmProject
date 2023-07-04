package com.vadimko.food2workkmm.domain.model

sealed class UIComponentType {
    object Dialog:UIComponentType()
    object SnackBar:UIComponentType()
    object None: UIComponentType()
}