package com.example.connect.main.ui.home.agenda.detail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.connect.main.ui.home.agenda.model.Agenda

class DetailAgendaViewModel(
    agenda: Agenda,
    app: Application
) : AndroidViewModel(app) {
    // TODO: Implement the ViewModel
    private val _selectedAgenda = MutableLiveData<Agenda>()
    val selectedAgenda: LiveData<Agenda>
        get() = _selectedAgenda

    init {
        _selectedAgenda.value = agenda
    }
}