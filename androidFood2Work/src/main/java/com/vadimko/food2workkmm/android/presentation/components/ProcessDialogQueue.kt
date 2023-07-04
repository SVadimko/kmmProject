package com.vadimko.food2workkmm.android.presentation.components

import androidx.compose.runtime.Composable
import com.vadimko.food2workkmm.domain.model.GenericMessageInfo
import com.vadimko.food2workkmm.domain.util.Queue

@Composable
fun ProcessDialogQueue(
    dialogQueue: Queue<GenericMessageInfo>?
) {
    dialogQueue?.peek()?.let { dialogInfo ->
        GenericDialog(
            title = dialogInfo.title,
            description = dialogInfo.description
        )
    }
}