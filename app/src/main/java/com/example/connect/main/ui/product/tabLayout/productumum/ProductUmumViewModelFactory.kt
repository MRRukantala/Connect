package com.example.connect.main.ui.product.tabLayout.productumum

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ProductUmumViewModelFactory(
    private val token: String,
    private val application: Application
) : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(ProductUmumViewModel::class.java)){
            return ProductUmumViewModel(token, application) as  T
        }
        throw  IllegalArgumentException("View Model dari mana nih")
    }
}