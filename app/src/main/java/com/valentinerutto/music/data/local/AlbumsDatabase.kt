package com.valentinerutto.music.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(exportSchema = false, version = 1, entities = [AlbumsEntity::class])
abstract class AlbumsDatabase : RoomDatabase() {
    abstract val albumsDao: AlbumsDao
}