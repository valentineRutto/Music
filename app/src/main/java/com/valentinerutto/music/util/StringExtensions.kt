package com.valentinerutto.music.util

fun String?.isUnknown(): Boolean {
    return this == UNKNOWN_STRING_VALUE
}

private const val UNKNOWN_STRING_VALUE = "---"
