package com.example.connect.main.ui.menu.info_pendidikan.info

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.connect.main.ui.menu.info_pendidikan.MyData
import com.example.connect.utilites.MarkOIApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class InfoUserViewModel(idUser: Int, token: String, application: Application) : AndroidViewModel(application) {

    private val _status = MutableLiveData<String>()
    val status: LiveData<String>
        get() = _status

    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    private var _properties = MutableLiveData<MyData>()
    val properties: LiveData<MyData>
        get() = _properties

    init {
        getProductProperties(token, idUser)
    }

    private fun getProductProperties(token: String, idUser: Int) {
        coroutineScope.launch {
            val getAdminProductDeferred = MarkOIApi.retrofitService
                .getMyProfile("Bearer "+ token ,idUser)

            try {
                val result = getAdminProductDeferred.await()
                Log.v("MYDATA", result.data.size.toString())
                when {
                    result.data!!.size > 0 -> {
//                        Log.v("MYDATA", result.data[0].name.toString())
                        _properties.value = result
                    }
                }
            } catch (e: Exception) {
                _status.value = e.toString()
                Log.v("Error produk", e.message.toString())
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

}