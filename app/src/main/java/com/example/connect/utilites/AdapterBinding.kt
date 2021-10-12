package com.example.connect.utilites

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.connect.main.ui.home.news.model.Post
import com.example.connect.main.ui.home.news.model.PostsAdapter


@BindingAdapter("listPosts")
fun bindRecyclerViewListPosts(
    recyclerView: RecyclerView,
    data: List<Post>?
) {
//    val adapter = recyclerView.adapter as PostsAdapter
//    adapter.submitList(data)
}