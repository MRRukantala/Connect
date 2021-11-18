package com.example.connect.main.ui.home.tablayout.news.mynews

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.connect.main.ui.home.tablayout.news.model.Post
import com.example.connect.utilites.MarkOIApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class MyNewsViewModel : ViewModel() {

    private val _status = MutableLiveData<String>()
    val status: LiveData<String>
        get() = _status

    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    private var _properties = MutableLiveData<List<Post>>()
    val properties: LiveData<List<Post>>
        get() = _properties

    private val _navigateToSelectedNews = MutableLiveData<Post?>()
    val navigatedToSelectedNews: LiveData<Post?>
        get() = _navigateToSelectedNews

    init {
        getPostProperties()
    }

    private fun getPostProperties() {
        coroutineScope.launch {
            var getPostDeferred = MarkOIApi.retrofitService.getAllKiriman()
            try {
                val result = getPostDeferred.await()
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

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    fun displayNewsDetails(postProperty: Post) {
        _navigateToSelectedNews.value = postProperty
    }

    fun displayNewsDetailsCompelete() {
        _navigateToSelectedNews.value = null
    }


}