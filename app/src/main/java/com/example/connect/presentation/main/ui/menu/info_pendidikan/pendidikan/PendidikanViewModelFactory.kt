package com.example.connect.presentation.main.ui.menu.info_pendidikan.pendidikan

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class PendidikanViewModelFactory(
    private val idUser: Int,
    private val token: String,
    private val appliction: Application
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PendidikanUserViewModel::class.java)) {
            return PendidikanUserViewModel(idUser, token, appliction) as T
        }
        throw  IllegalArgumentException("View Model dari mana nih")
    }
}