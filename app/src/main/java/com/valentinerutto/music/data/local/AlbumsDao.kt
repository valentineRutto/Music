package com.valentinerutto.music.data.local

import androidx.room.Dao
import androidx.room.Query
import com.valentinerutto.music.util.BaseDao

@Dao
interface AlbumsDao : BaseDao<AlbumsEntity> {

    @Query("SELECT * FROM albumsList")
    suspend fun getAlbumsList(): List<AlbumsEntity>

    @Query(
        """
    SELECT * FROM albumsList WHERE
    albumName LIKE :queryText
    """
    )
    suspend fun searchAlbum(queryText: String): List<AlbumsEntity>

    @Query("SELECT * FROM albumsList GROUP BY genre  ")
    suspend fun getAlbumsOfSameGenre(): List<AlbumsEntity>

    @Query("SELECT * FROM albumsList WHERE isFavorite=1  ")
    suspend fun getFavouriteAlbumsList(): List<AlbumsEntity>
}