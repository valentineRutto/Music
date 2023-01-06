package com.valentinerutto.music.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.valentinerutto.music.AlbumsViewmodel
import com.valentinerutto.music.databinding.ActivityMainBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val albumsViewModel: AlbumsViewmodel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        albumsViewModel.fetchAlbumsList()

        albumsViewModel.successfulAlbumListResponse.observe(this) {
            binding.text.text = it?.map { it.albumName }.toString()
        }

        binding.text.setOnClickListener {

            albumsViewModel.searchAlbums("16")

            albumsViewModel.successfulAlbumListResponse.observe(this@MainActivity) {
                Toast.makeText(this@MainActivity, it.toString(), Toast.LENGTH_LONG).show()
            }

        }
    }
}