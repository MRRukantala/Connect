package com.example.connect.main.ui.home.tablayout.news.mynews

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.connect.R
import com.example.connect.databinding.ItemListNewsBinding
import com.example.connect.main.ui.home.HomeFragmentDirections
import com.example.connect.main.ui.home.tablayout.news.model.Post

class Adapter(private val onClickListener: OnClickListener?,
private val onClickMoreListener: OnClickMoreListener) :
    ListAdapter<Post, Adapter.ViewHolder>(DiffCallback) {

    object DiffCallback : DiffUtil.ItemCallback<Post>() {
        override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean {
            return oldItem.id == newItem.id
        }

    }


    class OnClickListener(val clickListener: (post: Post) -> Unit) {
        fun onClick(post: Post) = clickListener(post)
    }

    class ViewHolder(private var binding: ItemListNewsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val moreButton = binding.more

        fun bind(post: Post) {
            binding.post = post
            binding.executePendingBindings()
        }
    }

    class OnClickMoreListener(val clickListener: (post: Int) -> Unit) {
        fun onClick(post: Int) = clickListener(post)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemListNewsBinding.inflate(
            LayoutInflater.from(parent.context)
        ))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val postProperty = getItem(position)

        holder.moreButton.root.visibility = View.VISIBLE

        holder.itemView.setOnClickListener {
            onClickListener!!.onClick(postProperty)
        }


        holder.moreButton.menu.setOnClickListener {
            onClickMoreListener.clickListener(postProperty.id)
        }

        holder.bind(postProperty)
    }
}