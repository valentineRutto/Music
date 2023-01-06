package com.valentinerutto.music.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.valentinerutto.music.data.local.AlbumsEntity
import com.valentinerutto.music.databinding.RowAlbumListBinding

interface onAlbumClicked {
    fun onAlbumClicked(id: Int, album: AlbumsEntity)
}

class AlbumsListRecyclerviewAdapter(var itemClickListener: onAlbumClicked) :
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
        fun bind(album: AlbumsEntity, itemClickListener: onAlbumClicked) {

            binding.albumName.text = album.albumName
            binding.imgAlbumCover.load("${album.albumCover}")
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
            ): Boolean = oldItem.id == newItem.id
        }
    }

}