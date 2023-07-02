package com.vadimko.food2workkmm.android.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.google.accompanist.coil.rememberCoilPainter
import com.google.accompanist.imageloading.ImageLoadState

const val RECIPE_IMAGE_HEIGHT = 260

@Composable
fun RecipeImage(
    url: String,
    contentDescription: String
) {
    val painter = rememberCoilPainter(
        request = url,
        requestBuilder = {
            crossfade(true)
            error(com.vadimko.food2workkmm.R.drawable.ic_no_image)
            fallback(com.vadimko.food2workkmm.R.drawable.ic_no_image)
//            fallback(R.drawable.ic_no_image)
        },
        fadeIn = true
    )
    Box {
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .height(RECIPE_IMAGE_HEIGHT.dp),
            painter = painter,
            contentDescription = contentDescription,
            contentScale = if (painter.loadState is ImageLoadState.Success) ContentScale.Crop else ContentScale.Fit
        )

        when (painter.loadState) {
            is ImageLoadState.Error -> {

            }
            is ImageLoadState.Success -> {
            }
            is ImageLoadState.Loading -> {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(RECIPE_IMAGE_HEIGHT.dp)
                ) {

                }
            }

            else -> {}
        }
    }
}