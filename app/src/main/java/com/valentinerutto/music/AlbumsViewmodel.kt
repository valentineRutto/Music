package com.valentinerutto.music

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.valentinerutto.music.data.local.AlbumsEntity
import com.valentinerutto.music.repository.AlbumsRepository
import com.valentinerutto.music.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AlbumsViewmodel(private val albumsRepository: AlbumsRepository) : ViewModel() {
    private val _successfulAlbumListResponse = MutableLiveData<List<AlbumsEntity>?>()
    val successfulAlbumListResponse: LiveData<List<AlbumsEntity>?>
        get() = _successfulAlbumListResponse

    private val _favouriteAlbumList = MutableLiveData<List<AlbumsEntity>?>()
    val favouriteAlbumList: LiveData<List<AlbumsEntity>?>
        get() = _favouriteAlbumList

    val _filteredAlbumList = MutableLiveData<List<AlbumsEntity>?>()
    val filteredAlbumList: LiveData<List<AlbumsEntity>?>
        get() = _filteredAlbumList


    val _album = MutableLiveData<AlbumsEntity>()
    val album: LiveData<AlbumsEntity>
        get() = _album

    private val _errorAlbumsListResponse = MutableLiveData<String>()
    val errorAlbumsListResponse: LiveData<String>
        get() = _errorAlbumsListResponse

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    private suspend fun getAlbums() {
        _isLoading.postValue(true)
        when (val response = albumsRepository.getSaveAlbums()) {
            is Resource.Success -> {
                _isLoading.postValue(false)
                _successfulAlbumListResponse.postValue(response.data)
            }

            is Resource.Error -> {
                _isLoading.postValue(false)
                _errorAlbumsListResponse.postValue(response.errorMessage)
            }
        }
    }

    fun fetchAlbumsList() {
        viewModelScope.launch(Dispatchers.IO) {
            getAlbums()
        }
    }

    fun fetchFavouriteAlbumsList() {
        viewModelScope.launch(Dispatchers.IO) {
            getFavouriteAlbums()
        }
    }

//    suspend fun searchAlbums(query: String) {
//        viewModelScope.launch {
//            _isLoading.postValue(true)
//            searchAlbum(query)
//        }
//    }

    suspend fun searchAlbums(query: String): List<AlbumsEntity> {
        return albumsRepository.searchAlbum(query)
    }

//    private suspend fun searchAlbum(query: String) {
//        when (val albumSearchResult = albumsRepository.searchAlbum(query)) {
//            is Resource.Success -> {
//                _isLoading.postValue(false)
//                _filteredAlbumList.postValue(albumSearchResult.data)
//            }
//            is Resource.Error -> {
//                _isLoading.postValue(false)
//                _errorAlbumsListResponse.postValue(albumSearchResult.errorMessage)
//            }
//        }
//    }

    private suspend fun getFavouriteAlbums() {
        when (val favouriteAlbumsResult = albumsRepository.getFavouriteAlbums()) {
            is Resource.Success -> {
                _isLoading.postValue(false)
                _favouriteAlbumList.postValue(favouriteAlbumsResult.data)
            }
            is Resource.Error -> {
                _isLoading.postValue(false)
                _errorAlbumsListResponse.postValue(favouriteAlbumsResult.errorMessage)
            }
        }
    }

    suspend fun updateAlbum(albumsEntity: AlbumsEntity) {
        albumsRepository.updateAlbum(albumsEntity)
    }
}