package com.example.connect.main.ui.home.news.model

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.connect.databinding.ItemListNewsBinding

class PostsAdapter(private val onClickListener: OnClickListener) :
    androidx.recyclerview.widget.ListAdapter<Post, PostsAdapter.PostViewHolder>(DiffCallback) {

    object DiffCallback : DiffUtil.ItemCallback<Post>() {
        override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean {
            return oldItem.id == newItem.id
        }

    }

    class PostViewHolder(private var binding: ItemListNewsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(post: Post) {
            binding.post = post
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        return PostViewHolder(ItemListNewsBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val postProperty = getItem(position)

        holder.itemView.setOnClickListener {
            onClickListener.onClick(postProperty)
        }

        holder.bind(postProperty)
    }

    class OnClickListener(val clickListener: (newsProperty: Post) -> Unit){
        fun onClick(newsProperty: Post) = clickListener(newsProperty)
    }
}