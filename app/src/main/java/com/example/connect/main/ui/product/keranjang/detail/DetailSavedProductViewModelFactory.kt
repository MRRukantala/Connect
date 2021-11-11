package com.example.connect.main.ui.product.keranjang.detail

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.connect.database.SaveProductData
import com.example.connect.database.SavedProductDAO
import com.example.connect.database.SavedProductDatabase

class DetailSavedProductViewModelFactory(
    private val idSaveProductData: Long,
    private val dataSource: SavedProductDAO
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailSavedProductViewModel::class.java)) {
            return DetailSavedProductViewModel(
                idSaveProductData,
                dataSource
            ) as T
        }
        throw  IllegalArgumentException("View Model dari mana nih")
    }
}