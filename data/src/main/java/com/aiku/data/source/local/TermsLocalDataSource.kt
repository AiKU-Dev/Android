package com.aiku.data.source.local

import android.content.Context
import androidx.annotation.RawRes
import java.io.InputStreamReader
import javax.inject.Inject

class TermsLocalDataSource @Inject constructor(
    private val context: Context
) {
    fun readTermsFromRaw(@RawRes resId: Int): String {
        val inputStream = context.resources.openRawResource(resId)
        val reader = InputStreamReader(inputStream)
        return reader.readText()
    }
}
