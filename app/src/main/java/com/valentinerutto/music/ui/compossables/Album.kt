package com.valentinerutto.music.ui.compossables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.valentinerutto.music.AlbumsViewModel
import com.valentinerutto.music.data.local.AlbumsEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun Album(albumsViewModel: AlbumsViewModel, album: AlbumsEntity, modifier: Modifier) {

    Box(
        Modifier
            .aspectRatio(1f)
            .fillMaxWidth()
            .padding(10.dp)
            .clip(RoundedCornerShape(8.dp))
    ) {
        Images(
            album.albumCover,
            Modifier
                .aspectRatio(1f)
                .fillMaxWidth()
        )
        Box(
            Modifier
                .align(Alignment.TopCenter)
                .fillMaxWidth()
                .height(64.dp)
                .background(
                    Brush.verticalGradient(
                        listOf(
                            Color.Black.copy(alpha = 0.33f),
                            Color.Transparent,
                        )
                    )
                )
        )
        Box(
            Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .height(64.dp)
                .background(
                    Brush.verticalGradient(
                        listOf(
                            Color.Transparent,
                            Color.Black.copy(alpha = 0.33f),
                        )
                    )
                )
        )
        Text(
            album.albumTitle,
            style = MaterialTheme.typography.titleMedium,
            color = Color.White,
            modifier = Modifier
                .padding(10.dp)
                .align(Alignment.BottomStart)
        )
        Button(
            onClick = {

            },
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(8.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.surface,
                contentColor = MaterialTheme.colorScheme.onSurface
            )
        ) {
            IconButton(onClick = {

                CoroutineScope(Dispatchers.Default).launch {
                    var newAlbum = album

                    if (album.isFavorite) {
                        newAlbum = album.copy(isFavorite = false)

                    } else {
                        newAlbum = album.copy(isFavorite = true)
                    }

                    albumsViewModel.updateAlbum(newAlbum)
                }
            }) {
                Icon(Icons.Outlined.Favorite, contentDescription = "Favorite")

            }
        }
    }
}
