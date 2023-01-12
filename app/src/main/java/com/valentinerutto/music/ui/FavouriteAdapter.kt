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

interface OnFavouriteAlbumClicked {
    fun onAlbumClicked(album: AlbumsEntity)
    fun onRemoveFavouriteAlbum(album: AlbumsEntity)
}

class FavouriteAdapter(var itemClickListener: OnFavouriteAlbumClicked) :
    ListAdapter<AlbumsEntity, FavouriteAdapter.FavouriteAlbumsViewHolder>(
        diff
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavouriteAlbumsViewHolder {
        return from(parent)
    }

    override fun onBindViewHolder(holder: FavouriteAlbumsViewHolder, position: Int) {
        val album = getItem(position)
        holder.bind(album, itemClickListener)
    }

    class FavouriteAlbumsViewHolder(private val binding: RowAlbumListBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(album: AlbumsEntity, itemClickListener: OnFavouriteAlbumClicked) {

            var newAlbum = album

            binding.itemView.setOnClickListener {
                itemClickListener.onAlbumClicked(album)
            }

            binding.imgFav.setImageResource(R.drawable.ic_remove)

            binding.albumName.text = album.albumName

            binding.imgAlbumCover.load(album.albumCover)

            binding.icFavourite.setOnClickListener {
                newAlbum = album.copy(isFavorite = false)
                itemClickListener.onRemoveFavouriteAlbum(newAlbum)
            }

        }

    }

    companion object {

        fun from(parent: ViewGroup): FavouriteAlbumsViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = RowAlbumListBinding.inflate(
                layoutInflater,
                parent, false
            )
            return FavouriteAlbumsViewHolder(binding)
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
