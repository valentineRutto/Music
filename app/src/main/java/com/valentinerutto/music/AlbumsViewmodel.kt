package com.valentinerutto.music

import androidx.lifecycle.ViewModel
import com.valentinerutto.music.data.remote.TopAlbumsResponse
import com.valentinerutto.music.repository.AlbumsRepository
import retrofit2.Response

class AlbumsViewmodel(private val albumsRepository: AlbumsRepository) : ViewModel() {
    suspend fun getAlbums(): Response<TopAlbumsResponse> {
        return albumsRepository.getAlbums()
    }

}