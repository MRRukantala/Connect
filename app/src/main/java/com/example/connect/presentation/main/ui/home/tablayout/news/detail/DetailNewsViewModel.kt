package com.example.connect.presentation.main.ui.home.tablayout.news.detail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.connect.presentation.main.ui.home.tablayout.news.model.Post

class DetailNewsViewModel(
    post: Post,
    app: Application
) : AndroidViewModel(app) {
    private val _selectedNews = MutableLiveData<Post>()
    val selectedNews: LiveData<Post>
        get() = _selectedNews
    init {
        _selectedNews.value = post
    }
}