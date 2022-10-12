package com.example.connect.presentation.main.ui.product.resmi.resmi

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider


class StoreResmiViewModelFactory (
    private val token: String,
    private val application: Application
) : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(StoreResmiViewModel::class.java)){
            return StoreResmiViewModel(token, application) as  T
        }
        throw  IllegalArgumentException("View Model dari mana nih")
    }
}