package com.valentinerutto.music.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "albumsList")
data class AlbumsEntity(
    @PrimaryKey(autoGenerate = true) val id: Int?,
    val isFavorite: Boolean,
    val releaseDate: String,
    val albumName: String,
    val artistName: String,
    val genre: String,
    val numberOfSongs: String,
    val labelName: String,
    val albumCover: String
)