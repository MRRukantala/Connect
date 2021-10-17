package com.example.connect.main.ui.home.news.detail

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.connect.main.ui.home.news.model.Post

class DetailViewModelFactory(
    private val post: Post,
    private val application: Application?
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailNewsViewModel::class.java)) {
            return DetailNewsViewModel(post, application!!) as T
        }
        throw  IllegalArgumentException("View Model dari mana nih")
    }

}