package com.example.connect.main.ui.product.detail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.connect.main.ui.product.model.ProductModel

class DetailProductViewModel(
    productModel: ProductModel, application: Application
) : AndroidViewModel(application) {
    private val _selectedProductUmum = MutableLiveData<ProductModel>()
    val selectProductModel: LiveData<ProductModel>
        get() = _selectedProductUmum

    init {
        _selectedProductUmum.value = productModel
    }
}