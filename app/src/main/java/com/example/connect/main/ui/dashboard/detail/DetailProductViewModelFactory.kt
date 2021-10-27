package com.example.connect.main.ui.dashboard.detail

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.connect.main.ui.dashboard.modelproductumum.ProductUmum

class DetailProductViewModelFactory(
    private val productUmum: ProductUmum,
    private val application: Application
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailProductViewModel::class.java)) {
            return DetailProductViewModel(productUmum, application!!) as T
        }
        throw  IllegalArgumentException("View Model dari mana nih")
    }
}