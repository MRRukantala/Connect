package com.example.connect.main.ui.product.keranjang

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.connect.database.SaveProductData
import com.example.connect.database.SavedProductDAO
import kotlinx.coroutines.*

class KeranjangViewModel(
    val idUser: Int,
    val datasource: SavedProductDAO,
    application: Application
) : AndroidViewModel(application) {

    private var viewModelJob = Job()
    private var scope = CoroutineScope(viewModelJob + Dispatchers.Main)

    var properties: LiveData<List<SaveProductData>> = datasource.getByIdUser(idUser)

    private val _navigateToDetail = MutableLiveData<Long>()
    val navigateToDetail: LiveData<Long>
        get() = _navigateToDetail

    fun displayToDetail(id: Long){
        _navigateToDetail.value = id
    }

    fun delete(id: Long){
        viewModelScope.launch {
            deleteItem(id)
        }
    }

    suspend fun deleteItem(id: Long) {
        withContext(Dispatchers.IO){
            datasource.deleteItem(id)
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}