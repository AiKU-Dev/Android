package com.aiku.presentation.util

import android.content.Context
import android.graphics.ImageDecoder
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap

internal fun Int.asImageBitmap(context: Context): ImageBitmap {
    val drawable = context.getDrawable(this) as BitmapDrawable
    return drawable.bitmap.asImageBitmap()
}

internal fun Uri.parseImageBitmap(context: Context): ImageBitmap {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
        val source = ImageDecoder.createSource(context.contentResolver, this)
        ImageDecoder.decodeBitmap(source).asImageBitmap()
    } else {
        MediaStore.Images.Media.getBitmap(context.contentResolver, this).asImageBitmap()
    }
}