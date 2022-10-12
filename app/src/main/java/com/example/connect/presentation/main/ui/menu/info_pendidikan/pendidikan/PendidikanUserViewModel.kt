package com.example.connect.presentation.main.ui.menu.info_pendidikan.pendidikan

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.connect.presentation.main.ui.menu.info_pendidikan.MySubPendidikan
import com.example.connect.utilites.MarkOIApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class PendidikanUserViewModel(idUser: Int, token: String, application: Application) :
    AndroidViewModel(application) {

    private val _status = MutableLiveData<String>()
    val status: LiveData<String>
        get() = _status

    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    private var _properties = MutableLiveData<List<MySubPendidikan>>()
    val properties: LiveData<List<MySubPendidikan>>
        get() = _properties

    private val _navigateToSelectedNews = MutableLiveData<MySubPendidikan>()
    val navigatedToSelectedNews: LiveData<MySubPendidikan>
        get() = _navigateToSelectedNews


    init {
        getProductProperties(token, idUser)
    }

    private fun getProductProperties(token: String, idUser: Int) {
        coroutineScope.launch {
            val getAdminProductDeferred = MarkOIApi.retrofitService
                .getMyProfile("Bearer " + token, idUser)

            try {
                val result = getAdminProductDeferred.await()
                Log.v("BANYAK", result.data[0].pendidikan!!.size.toString())
                when {
                    result.data.size > 0 -> {
                        _properties.value = result.data[0].pendidikan!!
                    }
                }
            } catch (e: Exception) {
                _status.value = e.toString()
                Log.v("Error produk", e.message.toString())
            }
        }
    }

    fun displayNewsDetails(productProperty: MySubPendidikan) {
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