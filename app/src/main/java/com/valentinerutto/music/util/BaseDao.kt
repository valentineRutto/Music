package com.valentinerutto.music.util

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Update

interface BaseDao<T> {
    @Insert(onConflict = REPLACE)
    suspend fun insert(obj: T)

    @Insert(onConflict = REPLACE)
    suspend fun insert(vararg obj: T)

    @Insert(onConflict = REPLACE)
    suspend fun insert(items: List<T>)

    @Update(onConflict = REPLACE)
    suspend fun update(obj: T)

    @Delete
    suspend fun delete(obj: T)
}