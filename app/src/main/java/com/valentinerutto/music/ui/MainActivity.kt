package com.valentinerutto.music.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.valentinerutto.music.AlbumsViewmodel
import com.valentinerutto.music.data.local.AlbumsEntity
import com.valentinerutto.music.databinding.ActivityMainBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val albumsViewModel: AlbumsViewmodel by viewModel()
    private lateinit var albumAdapter: AlbumsListRecyclerviewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        albumsViewModel.fetchAlbumsList()

        albumAdapter = AlbumsListRecyclerviewAdapter(object : onAlbumClicked {
            override fun onAlbumClicked(id: Int, album: AlbumsEntity) {
            }

        })

        setUpObservables()
    }

    private fun setUpObservables() {
        albumsViewModel.isLoading.observe(this) { showLoading ->
            binding.albumsProgressBar.isVisible = showLoading
        }

        albumsViewModel.errorAlbumsListResponse.observe(this) { errorMsg ->
            binding.albumsErrorTextView.text = errorMsg
        }

        albumsViewModel.successfulAlbumListResponse.observe(this) {
            binding.albumsRecyclerView.adapter = albumAdapter.apply {
                submitList(it)
            }
        }

    }
}