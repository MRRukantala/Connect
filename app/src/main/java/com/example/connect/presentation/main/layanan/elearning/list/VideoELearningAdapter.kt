package com.example.connect.presentation.main.layanan.elearning.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.connect.databinding.ItemVideoEElarningBinding
import com.example.connect.domain.entity.elearning.VideoELearningByIdEntity

class VideoELearningAdapter(val onclickListener: OnclickListener) :
    ListAdapter<VideoELearningByIdEntity, VideoELearningAdapter.ViewHolder>(
        DiffCallback
    ) {

    inner class ViewHolder(
        private var binding: ItemVideoEElarningBinding,

        ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: VideoELearningByIdEntity) {
            binding.data = data
            binding.executePendingBindings()
        }
    }

    object DiffCallback : DiffUtil.ItemCallback<VideoELearningByIdEntity>() {
        override fun areItemsTheSame(oldItem: VideoELearningByIdEntity, newItem: VideoELearningByIdEntity) =
            oldItem.idVideo == newItem.idVideo

        override fun areContentsTheSame(
            oldItem: VideoELearningByIdEntity,
            newItem: VideoELearningByIdEntity
        ) =
            oldItem.idVideo == newItem.idVideo
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemVideoEElarningBinding.inflate(
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
        val clickListener: (entity: VideoELearningByIdEntity) ->
        Unit
    ) {
        fun onClick(entity: VideoELearningByIdEntity) = clickListener(entity)
    }
}