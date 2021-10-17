package com.example.connect.utilites

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.connect.main.ui.home.agenda.model.Agenda
import com.example.connect.main.ui.home.agenda.model.AgendaAdapter
import com.example.connect.main.ui.home.news.model.Post
import com.example.connect.main.ui.home.news.model.PostsAdapter


@BindingAdapter("listPosts")
fun bindRecyclerViewListPosts(
    recyclerView: RecyclerView,
    data: List<Post>?
) {
    val adapter = recyclerView.adapter as PostsAdapter
    adapter.submitList(data)
}

@BindingAdapter("listAgendas")
fun bindRecyclerViewListAgenas(
    recyclerView: RecyclerView,
    data: List<Agenda>?
) {
    val adapter = recyclerView.adapter as AgendaAdapter
    adapter.submitList(data)
}