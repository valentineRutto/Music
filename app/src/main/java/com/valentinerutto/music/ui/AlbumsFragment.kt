package com.valentinerutto.music.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.valentinerutto.music.AlbumsViewmodel
import com.valentinerutto.music.R
import com.valentinerutto.music.data.local.AlbumsEntity
import com.valentinerutto.music.databinding.FragmentAlbumsBinding
import com.valentinerutto.music.util.hide
import com.valentinerutto.music.util.show
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


class AlbumsFragment : Fragment() {

    private var _binding: FragmentAlbumsBinding? = null
    private val albumsViewModel: AlbumsViewmodel by sharedViewModel()
    private lateinit var albumAdapter: AlbumsListRecyclerviewAdapter

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentAlbumsBinding.inflate(inflater, container, false)
        return binding.root


    }

    private fun setUpObservables() {
        albumsViewModel.isLoading.observe(viewLifecycleOwner) { showLoading ->
            binding.albumsProgressBar.isVisible = showLoading
        }

        albumsViewModel.errorAlbumsListResponse.observe(viewLifecycleOwner) { errorMsg ->
            binding.albumsErrorTextView.text = errorMsg
        }

        albumsViewModel.successfulAlbumListResponse.observe(viewLifecycleOwner) {

            setUpViews(it)
        }
        albumsViewModel.filteredAlbumList.observe(viewLifecycleOwner) {
            setUpViews(it, search = true)
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        albumAdapter = AlbumsListRecyclerviewAdapter(object : onAlbumClicked {
            override fun onAlbumClicked(id: Int, album: AlbumsEntity) {
                albumsViewModel._album.value = album
                findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)

            }

            override fun onFavouriteAlbumSelected(album: AlbumsEntity) {
                lifecycleScope.launch {
                    albumsViewModel.updateAlbum(album)
                }
            }
        })

        binding.searchView.clearFocus()
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {

                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                filteredList(newText)

                return true
            }

        })

        setUpObservables()

    }

    fun filteredList(query: String) {
        lifecycleScope.launch {
            val filteredList = albumsViewModel.searchAlbums(query)

            setUpViews(filteredList, query, true)
        }

    }

    private fun setUpViews(
        albums: List<AlbumsEntity>?,
        searchInput: String = "",
        search: Boolean = false,
    ) {

        binding.searchView.show()

        if (albums.isNullOrEmpty()) {
            binding.albumsRecyclerView.hide()
            binding.albumsErrorTextView.show()
            if (search && searchInput.isNotBlank()) {
                binding.albumsErrorTextView.text = "No Albums Found"
            }

        } else {

            binding.albumsRecyclerView.show()
            binding.albumsErrorTextView.hide()

            if (search) {
                binding.albumsRecyclerView.smoothScrollToPosition(0)
            }

            binding.albumsRecyclerView.adapter = albumAdapter.apply {
                submitList(albums)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}