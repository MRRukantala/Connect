package com.example.connect.main.ui.dashboard.detail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.connect.main.ui.dashboard.modelproductumum.ProductUmum

class DetailProductViewModel(
    productUmum: ProductUmum, application: Application
) : AndroidViewModel(application) {
    private val _selectedProductUmum = MutableLiveData<ProductUmum>()
    val selectProductUmum: LiveData<ProductUmum>
        get() = _selectedProductUmum

    init {
        _selectedProductUmum.value = productUmum
    }
}