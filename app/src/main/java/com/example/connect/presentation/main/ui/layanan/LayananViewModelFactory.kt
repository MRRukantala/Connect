package com.example.connect.presentation.main.ui.layanan

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class LayananViewModelFactory(
    private val token: String,
    private val appliction: Application
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LayananViewModel::class.java)) {
            return LayananViewModel(token, appliction) as T
        }
        throw  IllegalArgumentException("View Model dari mana nih")
    }
}