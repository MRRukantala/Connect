package com.example.connect.main.ui.layanan

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.connect.utilites.MarkOIApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class LayananViewModel(token: String, application: Application) : AndroidViewModel(application) {

    private val _status = MutableLiveData<String>()
    val status: LiveData<String>
        get() = _status

    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    private var _properties =MutableLiveData<List<DataLayanan>>()
    val properties: LiveData<List<DataLayanan>>
    get() = _properties

    private val _navigateToSelectedNews = MutableLiveData<DataLayanan?>()
    val navigatedToSelectedNews: LiveData<DataLayanan?>
        get() = _navigateToSelectedNews

    init {
        getProductProperties(token)
    }

    private fun getProductProperties(token: String) {
        coroutineScope.launch {
            val getAdminProductDeferred = MarkOIApi.retrofitService
                .getAllLayanan("Bearer " + token)

            try {
                val result = getAdminProductDeferred.await()
                when {
                    result.data.size > 0 -> {
                        _properties.value = result.data
                    }
                }
            } catch (e: Exception) {
                _status.value = e.toString()
            }
        }
    }

    fun displayNewsDetails(productProperty: DataLayanan) {
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