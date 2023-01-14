package com.valentinerutto.music.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import coil.load
import com.valentinerutto.music.AlbumsViewModel
import com.valentinerutto.music.databinding.FragmentSecondBinding
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class AlbumDetailsFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null
    private val albumsViewModel: AlbumsViewModel by sharedViewModel()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpObservables()
    }

    private fun setUpObservables() {
        albumsViewModel._isVisible.value = true

        albumsViewModel.album.observe(viewLifecycleOwner) { album ->
            binding.ivImage.load(album.albumCover)
            binding.txtAlbumName.text = album.albumName
            binding.txtGenre.text = album.genre
            binding.txtRealeseDate.text = album.releaseDate
            binding.txtLabelName.text = album.labelName

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}