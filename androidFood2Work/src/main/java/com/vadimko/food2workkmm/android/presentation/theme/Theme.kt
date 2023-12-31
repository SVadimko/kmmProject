package com.vadimko.food2workkmm.android.presentation.theme

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.vadimko.food2workkmm.android.presentation.components.CircularIndeterminateProgressBar
import com.vadimko.food2workkmm.android.presentation.components.ProcessDialogQueue
import com.vadimko.food2workkmm.domain.model.GenericMessageInfo
import com.vadimko.food2workkmm.domain.util.Queue

@SuppressLint("ConflictingOnColor")
private val LightThemeColors = lightColors(
    primary = Blue600,
    primaryVariant = Blue400,
    onPrimary = Black2,
    secondary = Color.White,
    secondaryVariant = Teal300,
    onSecondary = Color.Black,
    error = RedErrorDark,
    onError = RedErrorLight,
    background = Grey1,
    onBackground = Color.Black,
    surface = Color.White,
    onSurface = Black2,
)

@ExperimentalComposeUiApi
@ExperimentalMaterialApi
@Composable
fun AppTheme(
    displayProgressBar: Boolean,
    dialogQueue: Queue<GenericMessageInfo> = Queue(mutableListOf<GenericMessageInfo>()),
    onRemoveHeadMessageFromQueue: () -> Unit,
    content: @Composable () -> Unit,
) {
    MaterialTheme(
        colors = LightThemeColors,
        typography = QuickSandTypography,
        shapes = AppShapes
    ){
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color =Grey1)
        ){
            // For android we can process the DialogQueue at the Application level
            // on iOS you cannot do this because SwiftUI preloads the views in a List
            ProcessDialogQueue(
                dialogQueue = dialogQueue,
                onRemoveHeadMessageFromQueue = onRemoveHeadMessageFromQueue,
            )
            Column{
                content()
            }
            CircularIndeterminateProgressBar(isDisplayed = displayProgressBar, 0.3f)
        }
    }
}
