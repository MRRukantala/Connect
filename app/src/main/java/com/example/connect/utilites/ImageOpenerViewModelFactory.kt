package com.example.connect.utilites

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ImageOpenerViewModelFactory(
    private val link: String,
    private val application: Application?
) : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ImageOpenerViewModel::class.java)) {
            return ImageOpenerViewModel(link, application!!) as T
        }
        throw  IllegalArgumentException("View Model dari mana nih")
    }
}