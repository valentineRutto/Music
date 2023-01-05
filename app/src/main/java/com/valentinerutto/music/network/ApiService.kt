package com.valentinerutto.music.network

import com.valentinerutto.music.data.remote.TopAlbumsResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("limit=100/json")
    suspend fun getAlbums(): Response<TopAlbumsResponse>
}