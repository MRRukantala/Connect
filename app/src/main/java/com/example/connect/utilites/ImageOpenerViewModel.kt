package com.example.connect.utilites

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class ImageOpenerViewModel(
    link: String,
    app: Application
)  : AndroidViewModel(app){

    private val _link = MutableLiveData<String>()
    val link : LiveData<String>
    get() = _link

    init {
        _link.value = link
    }

}