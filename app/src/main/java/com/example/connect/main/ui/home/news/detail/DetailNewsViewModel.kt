package com.example.connect.main.ui.home.news.detail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.connect.main.ui.home.news.model.Post
import com.example.connect.utilites.MarkOIApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

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