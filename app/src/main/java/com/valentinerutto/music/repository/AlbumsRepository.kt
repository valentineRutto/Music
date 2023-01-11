package com.valentinerutto.music.repository

import com.valentinerutto.music.data.local.AlbumsDao
import com.valentinerutto.music.data.local.AlbumsEntity
import com.valentinerutto.music.data.remote.TopAlbumsListResponse
import com.valentinerutto.music.network.ApiService
import com.valentinerutto.music.util.Resource

class AlbumsRepository(private val apiService: ApiService, private val albumsDao: AlbumsDao) {

    suspend fun getSaveAlbums(): Resource<List<AlbumsEntity>> {

        val albumsList = albumsDao.getAlbumsList()

        if (albumsList.isNotEmpty()) return Resource.Success(albumsList)

        val response = apiService.getAlbums()

        if (!response.isSuccessful) return Resource.Error(errorMessage = response.message())

        val albumsEntity = mapResponseToEntity(response.body())

        albumsDao.insert(albumsEntity)

        return Resource.Success(data = albumsDao.getAlbumsList())

    }

    private fun mapResponseToEntity(albumsListResponse: TopAlbumsListResponse?): List<AlbumsEntity> {
        return albumsListResponse?.feed?.entry?.map {
            AlbumsEntity(
                albumName = it.imName.label,
                artistName = it.imArtist.label,
                genre = it.category.attributes.label,
                releaseDate = it.imReleaseDate.attributes.label,
                numberOfSongs = it.imItemCount.label,
                labelName = it.rights.label,
                albumCover = it.imImage[2].label,
                isFavorite = false
            )
        } ?: emptyList()
    }

    suspend fun searchAlbum(queryText: String): List<AlbumsEntity> {
        return albumsDao.searchAlbum("%$queryText")
    }


    suspend fun getFavouriteAlbums(): Resource<List<AlbumsEntity>> {
        val favouriteAlbums = albumsDao.getFavouriteAlbumsList()
        return Resource.Success(data = favouriteAlbums)
    }

    suspend fun updateAlbum(album: AlbumsEntity) {
        albumsDao.update(album)
    }
}

