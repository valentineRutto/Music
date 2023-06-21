package com.valentinerutto.music.ui.compossables

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.valentinerutto.music.R

@Composable
fun Images(url: String, modifier: Modifier) {

    AsyncImage(
        ImageRequest
            .Builder(LocalContext.current)
            .data(url)
            .crossfade(true)
            .build(),
        contentDescription = stringResource(R.string.album),
        contentScale = ContentScale.Crop,
        modifier = modifier
    )
}