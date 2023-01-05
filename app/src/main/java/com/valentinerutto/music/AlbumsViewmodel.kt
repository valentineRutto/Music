package com.valentinerutto.music

import androidx.lifecycle.ViewModel
import com.valentinerutto.music.data.remote.TopAlbumsResponse
import com.valentinerutto.music.network.ApiService
import retrofit2.Response

class AlbumsViewmodel(private val apiService: ApiService) : ViewModel() {

    suspend fun getAlbums(): Response<TopAlbumsResponse> {
        return apiService.getAlbums()
    }
}