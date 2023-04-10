package com.valentinerutto.music.data.local

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.migration.AutoMigrationSpec

@Database(
    exportSchema = true,
    version = 1, entities = [AlbumsEntity::class])
abstract class AlbumsDatabase : RoomDatabase() {

    abstract val albumsDao: AlbumsDao
    class MyAutomigration : AutoMigrationSpec{}
}

