package com.vadimko.food2workkmm.android.presentation.components

import androidx.compose.runtime.Composable
import com.vadimko.food2workkmm.domain.model.GenericMessageInfo
import com.vadimko.food2workkmm.domain.util.Queue

@Composable
fun ProcessDialogQueue(
    dialogQueue: Queue<GenericMessageInfo>?,
    onRemoveHeadMessageFromQueue: () -> Unit
) {
    dialogQueue?.peek()?.let { dialogInfo ->
        GenericDialog(
            onDismiss = dialogInfo.onDismiss,
            title = dialogInfo.title,
            description = dialogInfo.description,
            positiveAction = dialogInfo.positiveAction,
            negativeAction = dialogInfo.negativeAction,
            onRemoveHeadFromQueue = onRemoveHeadMessageFromQueue
        )
    }
}