package com.example.connect.main.ui.menu.info_pendidikan.info.edit

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.connect.main.ui.home.tablayout.news.model.Post
import com.example.connect.main.ui.menu.info_pendidikan.MySubData

class EditInfoUserViewModel(idUser: Int, token: String, application: Application, data: MySubData) : AndroidViewModel(application) {
    private val _selectedNews = MutableLiveData<MySubData>()
    val selectedNews: LiveData<MySubData>
        get() = _selectedNews

    init {
        _selectedNews.value = data
    }
}