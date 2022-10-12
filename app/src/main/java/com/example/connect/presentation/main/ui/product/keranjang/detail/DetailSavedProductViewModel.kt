package com.example.connect.presentation.main.ui.product.keranjang.detail

import androidx.lifecycle.*
import com.example.connect.data.database.SaveProductData
import com.example.connect.data.database.SavedProductDAO
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class DetailSavedProductViewModel(
    val idSaveProductData: Long = 0L,
    dataSource: SavedProductDAO
) : ViewModel() {

    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    val database = dataSource

    private val data = MediatorLiveData<SaveProductData>()
    fun getData() = data

    init {
        coroutineScope.launch {
            data.addSource(database.getProductById(idSaveProductData), data::setValue)
        }

    }

}