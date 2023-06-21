package com.valentinerutto.music.util

import android.content.Context
import android.view.View
import android.widget.Toast

fun View.show() {
    visibility = View.VISIBLE
}

fun View.hide() {
    visibility = View.GONE
}

fun mToast(context: Context, text: String) {
    Toast.makeText(context, text, Toast.LENGTH_LONG).show()
}

fun String?.isUnknown(): Boolean {
    return this == UNKNOWN_STRING_VALUE
}

private const val UNKNOWN_STRING_VALUE = "---"
