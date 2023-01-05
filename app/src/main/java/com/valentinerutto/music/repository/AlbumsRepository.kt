package com.valentinerutto.music.repository

import com.valentinerutto.music.data.local.AlbumsDao
import com.valentinerutto.music.data.remote.TopAlbumsResponse
import com.valentinerutto.music.network.ApiService
import retrofit2.Response

class AlbumsRepository(private val apiService: ApiService, private val albumsDao: AlbumsDao) {
    suspend fun getAlbums(): Response<TopAlbumsResponse> {
        return apiService.getAlbums()
    }
}