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
import com.valentinerutto.music.databinding.FragmentFirstBinding
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class AlbumsFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null
    private val albumsViewModel: AlbumsViewmodel by sharedViewModel()
    private lateinit var albumAdapter: AlbumsListRecyclerviewAdapter

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
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
            binding.albumsRecyclerView.adapter = albumAdapter.apply {
                submitList(it)
            }
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        albumAdapter = AlbumsListRecyclerviewAdapter(object : onAlbumClicked {
            override fun onAlbumClicked(id: Int, album: AlbumsEntity) {
                findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)

            }

            override fun onFavouriteAlbumSelected(album: AlbumsEntity) {
                lifecycleScope.launch {
                    albumsViewModel.updateAlbum(album)
                }
            }

        })

        setUpObservables()

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}