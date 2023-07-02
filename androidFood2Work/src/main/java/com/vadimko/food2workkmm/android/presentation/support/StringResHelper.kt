package com.vadimko.food2workkmm.android.presentation.support

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.vadimko.food2workkmm.Strings
import dev.icerock.moko.resources.AssetResource
import dev.icerock.moko.resources.FileResource
import dev.icerock.moko.resources.ImageResource
import dev.icerock.moko.resources.StringResource

@Composable
fun stringResource(id: StringResource, vararg args: Any): String {
    return Strings(LocalContext.current).get(id, args.toList())
}

fun stringResourceCommon(id: StringResource, vararg args: Any, context: Context): String {
    return Strings(context).get(id, args.toList())
}

fun AssetResource.toAndroidAssetUri() = "file:///android_asset/$originalPath"

