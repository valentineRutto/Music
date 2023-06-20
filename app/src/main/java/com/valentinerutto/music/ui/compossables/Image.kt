package com.valentinerutto.music.ui.compossables

import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.compose.AsyncImage
import coil.request.ImageRequest

@Composable
fun Images(url: String, modifier: Modifier) {

    AsyncImage(
        ImageRequest
            .Builder(LocalContext.current)
            .data(url)
            .crossfade(true)
            .build(),
        contentDescription = "",
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .aspectRatio(1f)
            .fillMaxWidth()
    )
}