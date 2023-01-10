package com.valentinerutto.music.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.valentinerutto.music.AlbumsViewmodel
import com.valentinerutto.music.R
import com.valentinerutto.music.data.local.AlbumsEntity
import com.valentinerutto.music.databinding.FragmentFavouriteBinding
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FavouriteFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FavouriteFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private var _binding: FragmentFavouriteBinding? = null
    private val albumsViewModel: AlbumsViewmodel by sharedViewModel()
    private lateinit var albumAdapter: AlbumsListRecyclerviewAdapter

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

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
                findNavController().navigate(R.id.action_FavouriteFragment_to_SecondFragment)

            }

            override fun onFavouriteAlbumSelected(album: AlbumsEntity) {
                TODO("Not yet implemented")
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

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FavouriteFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}