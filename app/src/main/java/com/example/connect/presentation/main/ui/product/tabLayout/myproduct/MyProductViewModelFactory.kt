package com.example.connect.presentation.main.ui.product.tabLayout.myproduct

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class MyProductViewModelFactory(
    private val token : String,
    private val idUser: Int,
    private val appliction: Application
) : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(MyProductViewModel::class.java)){
            return MyProductViewModel(token, idUser, appliction) as  T
        }
        throw  IllegalArgumentException("View Model dari mana nih")
    }
}