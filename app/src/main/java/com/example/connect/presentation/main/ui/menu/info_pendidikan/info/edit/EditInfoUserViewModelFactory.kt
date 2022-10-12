package com.example.connect.presentation.main.ui.menu.info_pendidikan.info.edit

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.connect.presentation.main.ui.menu.info_pendidikan.MySubData

class EditInfoUserViewModelFactory(
    private val idUser: Int,
    private val token: String,
    private val application: Application,
    private val data : MySubData
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(EditInfoUserViewModel::class.java)) {
            return EditInfoUserViewModel(idUser, token, application, data) as T
        }
        throw  IllegalArgumentException("View Model dari mana nih")
    }
}
