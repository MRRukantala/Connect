package com.example.connect.main.ui.home.news.model

data class PostResponse(
    val data: List<Post>,
    val message: String? = null
)
