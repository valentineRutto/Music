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

    private val _album = MutableLiveData<List<AlbumsEntity>?>()
    val album: LiveData<List<AlbumsEntity>?>
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

    fun searchAlbums(query: String) {
        viewModelScope.launch {
            _isLoading.postValue(true)
            searchAlbum(query)
        }
    }

    private suspend fun searchAlbum(query: String) {
        when (val albumSearchResult = albumsRepository.searchAlbum(query)) {
            is Resource.Success -> {
                _isLoading.postValue(false)
                _album.postValue(albumSearchResult.data)
            }
            is Resource.Error -> {
                _isLoading.postValue(false)
                _errorAlbumsListResponse.postValue(albumSearchResult.errorMessage)
            }
        }
    }


}