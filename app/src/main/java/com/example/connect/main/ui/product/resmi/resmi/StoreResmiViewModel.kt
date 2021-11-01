package com.example.connect.main.ui.product.resmi.resmi

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.connect.main.ui.product.model.ProductModel
import com.example.connect.utilites.MarkOIApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class StoreResmiViewModel(token: String, application: Application) :
    AndroidViewModel(application) {

    private val _status = MutableLiveData<String>()
    val status: LiveData<String>
        get() = _status

    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    private var _properties = MutableLiveData<List<ProductModel>>()
    val properties: LiveData<List<ProductModel>>
        get() = _properties

    private val _navigateToSelectedNews = MutableLiveData<ProductModel?>()
    val navigatedToSelectedNews: LiveData<ProductModel?>
        get() = _navigateToSelectedNews

    init {
        getProductProperties(token)
    }

    private fun getProductProperties(token: String) {
        coroutineScope.launch {
            val getAdminProductDeferred = MarkOIApi.retrofitService
                .getAllProductByAdmin("Bearer " + token, 1)
            Log.v("hasil product", getAdminProductDeferred.toString())
            try {
                val result = getAdminProductDeferred.await()
//                Log.v("hasil product", result.data[0].nama_produk)
                when {
                    result.data.size > 0 -> {
                        _properties.value = result.data
                    }
                }
            } catch (e: Exception) {
                _status.value = e.toString()
                Log.v("Error produk", e.toString())
            }
        }
    }

    fun displayNewsDetails(productProperty: ProductModel) {
        _navigateToSelectedNews.value = productProperty
    }

    fun displayNewsDetailsCompelete() {
        _navigateToSelectedNews.value = null
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}