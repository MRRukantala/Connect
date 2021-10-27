//package com.example.connect.main.ui.dashboard.store
//
//import android.util.Log
//import androidx.lifecycle.LiveData
//import androidx.lifecycle.MutableLiveData
//import androidx.lifecycle.ViewModel
//import com.example.connect.main.ui.dashboard.modelproductumum.ProductUmum
//import com.example.connect.main.ui.home.news.model.Post
//import com.example.connect.utilites.MarkOIApi
//import kotlinx.coroutines.CoroutineScope
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.Job
//import kotlinx.coroutines.launch
//
//class AdminStoreViewModel : ViewModel() {
//
//    private val _status = MutableLiveData<String>()
//    val status: LiveData<String>
//        get() = _status
//
//
//    private var viewModelJob = Job()
//    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)
//
//    private var _properties = MutableLiveData<List<ProductUmum>>()
//    val properties: LiveData<List<ProductUmum>>
//        get() = _properties
//
//    private val _navigateToSelectedNews = MutableLiveData<ProductUmum>()
//    val navigatedToSelectedNews: LiveData<ProductUmum?>
//        get() = _navigateToSelectedNews
//
//    init {
//        getProductProperties()
//    }
//
//    private fun getProductProperties() {
//        coroutineScope.launch {
//            val getAdminProductDeferred = MarkOIApi.retrofitService
//                .getAllProductMarkOI()
//
//            try {
//                val result = getAdminProductDeferred.await()
//                Log.v("hasil product", result.data[0].nama_produk)
//                when {
//                    result.data.size > 0 -> {
//                        _properties.value = result.data
//                    }
//                }
//            } catch (e: Exception) {
//                _status.value = e.toString()
//            }
//        }
//
//        fun displayNewsDetails(productProperty: ProductUmum) {
//            _navigateToSelectedNews.value = productProperty
//        }
//
//        fun displayNewsDetailsCompelete() {
//            _navigateToSelectedNews.value = null
//        }
//    }
//
//}