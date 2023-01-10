package com.valentinerutto.music.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.valentinerutto.music.AlbumsViewmodel
import com.valentinerutto.music.R
import com.valentinerutto.music.data.local.AlbumsEntity
import com.valentinerutto.music.databinding.FragmentFavouriteBinding
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class FavouriteFragment : Fragment() {

    private var _binding: FragmentFavouriteBinding? = null
    private val albumsViewModel: AlbumsViewmodel by sharedViewModel()
    private lateinit var albumAdapter: AlbumsListRecyclerviewAdapter


    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentFavouriteBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        albumsViewModel.fetchFavouriteAlbumsList()

        albumAdapter = AlbumsListRecyclerviewAdapter(object : onAlbumClicked {
            override fun onAlbumClicked(id: Int, album: AlbumsEntity) {
                albumsViewModel._album.value = album
                findNavController().navigate(R.id.action_FavouriteFragment_to_SecondFragment)

            }

            override fun onFavouriteAlbumSelected(album: AlbumsEntity) {
                lifecycleScope.launch {
                    albumsViewModel.updateAlbum(album)


                }
            }

        })



        setUpObservables()

    }

    private fun setUpObservables() {

        albumsViewModel.isLoading.observe(viewLifecycleOwner) { showLoading ->
            binding.albumsProgressBar.isVisible = showLoading
        }

        albumsViewModel.errorAlbumsListResponse.observe(viewLifecycleOwner) { errorMsg ->
            binding.albumsErrorTextView.text = errorMsg
        }

        albumsViewModel.favouriteAlbumList.observe(viewLifecycleOwner) {
            binding.favouriteAlbumsRecyclerView.adapter = albumAdapter.apply {
                submitList(it)
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}