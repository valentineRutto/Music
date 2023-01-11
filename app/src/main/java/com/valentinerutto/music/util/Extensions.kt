package com.valentinerutto.music.util

import android.view.View

fun View.show() {
    visibility = View.VISIBLE
}

fun View.hide() {
    visibility = View.GONE
}

fun String?.isUnknown(): Boolean {
    return this == UNKNOWN_STRING_VALUE
}

private const val UNKNOWN_STRING_VALUE = "---"
