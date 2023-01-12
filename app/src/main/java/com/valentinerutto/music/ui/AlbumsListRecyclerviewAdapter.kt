package com.valentinerutto.music.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.valentinerutto.music.R
import com.valentinerutto.music.data.local.AlbumsEntity
import com.valentinerutto.music.databinding.RowAlbumListBinding

interface OnAlbumClicked {
    fun onAlbumClicked(album: AlbumsEntity)
    fun onFavouriteAlbumSelected(album: AlbumsEntity)
}

class AlbumsListRecyclerviewAdapter(var itemClickListener: OnAlbumClicked) :
    ListAdapter<AlbumsEntity, AlbumsListRecyclerviewAdapter.AlbumsViewHolder>(diff) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumsViewHolder {
        return from(parent)
    }

    override fun onBindViewHolder(holder: AlbumsViewHolder, position: Int) {
        val album = getItem(position)
        holder.bind(album, itemClickListener)
    }

    class AlbumsViewHolder(private val binding: RowAlbumListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(album: AlbumsEntity, itemClickListener: OnAlbumClicked) {

            var newAlbum = album

            if (album.isFavorite) {
                binding.imgFav.setImageResource(R.drawable.ic_favorited)

            } else {
                binding.imgFav.setImageResource(R.drawable.ic_select_favorite)
            }

            binding.itemView.setOnClickListener {
                itemClickListener.onAlbumClicked(
                    album
                )
            }

            binding.albumName.text = album.albumName
            binding.imgAlbumCover.load(album.albumCover)

            binding.icFavourite.setOnClickListener {

                if (album.isFavorite) {
                    newAlbum = album.copy(isFavorite = false)
                    binding.imgFav.setImageResource(R.drawable.ic_favorited)

                } else {
                    newAlbum = album.copy(isFavorite = true)
                    binding.imgFav.setImageResource(R.drawable.ic_select_favorite)
                }

                itemClickListener.onFavouriteAlbumSelected(newAlbum)
            }

        }

    }

    companion object {

        fun from(parent: ViewGroup): AlbumsViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = RowAlbumListBinding.inflate(layoutInflater, parent, false)
            return AlbumsViewHolder(binding)
        }

        val diff = object : DiffUtil.ItemCallback<AlbumsEntity>() {
            override fun areItemsTheSame(
                oldItem: AlbumsEntity,
                newItem: AlbumsEntity
            ): Boolean = oldItem == newItem

            override fun areContentsTheSame(
                oldItem: AlbumsEntity,
                newItem: AlbumsEntity
            ): Boolean = oldItem.albumTitle == newItem.albumTitle
        }
    }

}