package com.valentinerutto.music.data.local

import androidx.room.Dao
import androidx.room.Query
import com.valentinerutto.music.util.BaseDao

@Dao
interface AlbumsDao : BaseDao<AlbumsEntity> {

    @Query("SELECT * FROM albumsList")
    fun getAlbumsList(): List<AlbumsEntity>


}