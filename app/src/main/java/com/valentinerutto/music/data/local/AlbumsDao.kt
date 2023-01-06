package com.valentinerutto.music.data.local

import androidx.room.Dao
import androidx.room.Query
import com.valentinerutto.music.util.BaseDao

@Dao
interface AlbumsDao : BaseDao<AlbumsEntity> {

    @Query("SELECT * FROM albumsList")
    fun getAlbumsList(): List<AlbumsEntity>

    @Query(
        """
    SELECT * FROM albumsList WHERE
    albumName LIKE :queryText OR
    genre LIKE :queryText OR
    artistName LIKE :queryText OR
    labelName LIKE :queryText OR
    numberOfSongs LIKE :queryText OR
    releaseDate LIKE :queryText
    """
    )
    suspend fun searchAlbum(queryText: String): List<AlbumsEntity>


}