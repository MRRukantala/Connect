package com.example.connect.presentation.main.home.tablayout.news

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.connect.databinding.ItemListNewsBinding
import com.example.connect.domain.entity.KirimanEntity
import com.example.connect.utilites.enums.ConverterDate
import com.example.connect.utilites.ui.date.convertUTC2TimeTo2

class NewsAdapter(val onclickListener: OnclickListener) :
    ListAdapter<KirimanEntity, NewsAdapter.ViewHolder>(
        DiffCallback
    ) {

    inner class ViewHolder(
        private var binding: com.example.connect.databinding.ItemListNewsBinding,

        ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: KirimanEntity) {
            binding.post = data
            binding.datePost.text = data.tglUpdate.convertUTC2TimeTo2(ConverterDate.FULL_DATE)
            binding.executePendingBindings()
        }
    }

    object DiffCallback : DiffUtil.ItemCallback<KirimanEntity>() {
        override fun areItemsTheSame(oldItem: KirimanEntity, newItem: KirimanEntity) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: KirimanEntity, newItem: KirimanEntity) =
            oldItem.id == newItem.id
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemListNewsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val newsItem = getItem(position)
        holder.bind(newsItem)
        holder.itemView.setOnClickListener {
            onclickListener.onClick(newsItem)
        }
    }

    class OnclickListener(
        val clickListener: (entity: KirimanEntity) ->
        Unit
    ) {
        fun onClick(entity: KirimanEntity) = clickListener(entity)
    }
}