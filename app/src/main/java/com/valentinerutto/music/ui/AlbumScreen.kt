package com.valentinerutto.music.ui

import android.content.res.Configuration.ORIENTATION_LANDSCAPE
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import com.valentinerutto.music.AlbumsViewModel
import com.valentinerutto.music.ui.compossables.Album

@Composable
fun AlbumScreen(
    albumViewModel: AlbumsViewModel,
    onAlbumSelected: (albumItemPosition: Int) -> Unit,
    modifier: Modifier
) {
    val cellConfiguration = if (LocalConfiguration.current.orientation == ORIENTATION_LANDSCAPE) {
        GridCells.Adaptive(minSize = 175.dp)
    } else GridCells.Fixed(2)

    val albums = albumViewModel.successfulAlbumListResponse.collectAsState().value
    LazyVerticalGrid(
        columns = cellConfiguration,
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {

        itemsIndexed(albums) { index, album ->
            Album(album = album, modifier = modifier.clickable {
                onAlbumSelected.invoke(index)
            })
        }


    }
}