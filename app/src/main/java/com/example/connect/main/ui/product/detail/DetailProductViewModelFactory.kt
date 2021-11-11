package com.example.connect.main.ui.product.detail

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.connect.database.SavedProductDAO
import com.example.connect.main.ui.product.model.ProductModel

class DetailProductViewModelFactory(
    private val id_user: Int,
    private val dataSource : SavedProductDAO,
    private val productModel: ProductModel,
    private val application: Application
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailProductViewModel::class.java)) {
            return DetailProductViewModel(id_user, dataSource ,productModel, application!!) as T
        }
        throw  IllegalArgumentException("View Model dari mana nih")
    }
}