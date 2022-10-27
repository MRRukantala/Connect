package com.example.connect.presentation.main.layanan

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.connect.databinding.ItemPlaylistELearningBinding
import com.example.connect.domain.entity.elearning.PlaylistELearningEntity

class PlaylistAdapter(val onclickListener: OnclickListener) :
    ListAdapter<PlaylistELearningEntity, PlaylistAdapter.ViewHolder>(
        DiffCallback
    ) {

    inner class ViewHolder(
        private var binding: ItemPlaylistELearningBinding,

        ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: PlaylistELearningEntity) {
            binding.item = data
            binding.executePendingBindings()
        }
    }

    object DiffCallback : DiffUtil.ItemCallback<PlaylistELearningEntity>() {
        override fun areItemsTheSame(
            oldItem: PlaylistELearningEntity,
            newItem: PlaylistELearningEntity
        ) =
            oldItem.idPlaylist == newItem.idPlaylist

        override fun areContentsTheSame(
            oldItem: PlaylistELearningEntity,
            newItem: PlaylistELearningEntity
        ) =
            oldItem.idPlaylist == newItem.idPlaylist
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemPlaylistELearningBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val layananItem = getItem(position)
        holder.bind(layananItem)
        holder.itemView.setOnClickListener {
            onclickListener.onClick(layananItem)
        }
    }

    class OnclickListener(
        val clickListener: (entity: PlaylistELearningEntity) ->
        Unit
    ) {
        fun onClick(entity: PlaylistELearningEntity) = clickListener(entity)
    }
}