package com.example.connect.presentation.main.ui.menu.info_pendidikan.info

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class InfoUserViewModelFactory(
    private val idUser: Int,
    private val token: String,
    private val application: Application
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(InfoUserViewModel::class.java)) {
            return InfoUserViewModel(idUser, token, application) as T
        }
        throw  IllegalArgumentException("View Model dari mana nih")
    }
}