package com.example.connect.main.ui.menu.info_pendidikan.info

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.connect.main.ui.menu.info_pendidikan.MySubData
import com.example.connect.main.ui.menu.info_pendidikan.info.edit.EditInfoUserViewModel

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