package com.example.connect.main.ui.home.agenda

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.connect.main.ui.home.agenda.model.Agenda
import com.example.connect.utilites.MarkOIApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class AgendaViewModel : ViewModel() {
    // TODO: Implement the ViewModel
    private val _status = MutableLiveData<String>()
    val status: LiveData<String>
        get() = _status

    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    private var _properties = MutableLiveData<List<Agenda>>()
    val properties: LiveData<List<Agenda>>
        get() = _properties

    private val _navigateToSelectedAgenda = MutableLiveData<Agenda>()
    val navigatedToSelectedAgenda: LiveData<Agenda?>
        get() = _navigateToSelectedAgenda


    init {
        getAgendaProperties()
    }

    private fun getAgendaProperties() {
        coroutineScope.launch {
            var getAgendaDeferred = MarkOIApi.retrofitService.getAllAgenda()
            try {
                val result = getAgendaDeferred.await()
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

    fun displayAgendaDetail(agenda: Agenda) {
        _navigateToSelectedAgenda.value = agenda
    }

    fun displayAgendaDetailComplete() {
        _navigateToSelectedAgenda.value = null
    }
}