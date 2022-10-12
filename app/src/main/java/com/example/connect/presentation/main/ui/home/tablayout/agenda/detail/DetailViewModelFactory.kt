package com.example.connect.presentation.main.ui.home.tablayout.agenda.detail

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.connect.presentation.main.ui.home.tablayout.agenda.model.Agenda

class DetailViewModelFactory(
    private val agenda: Agenda,
    private val application: Application?
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailAgendaViewModel::class.java)) {
            return DetailAgendaViewModel(agenda, application!!) as T
        }
        throw  IllegalArgumentException("View Model dari mana nih")
    }

}