package com.valentinerutto.music.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "albumsList")
data class AlbumsEntity(
    @PrimaryKey
    val id: Int,
    val isFavorite: Boolean
)