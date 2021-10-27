package com.example.connect.main.ui.dashboard.store

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.connect.main.ui.dashboard.DashboardViewModel

class DashbiardViewModelFactory(
    private val token: String,
    private val application: Application
) : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(DashboardViewModel::class.java)){
            return DashboardViewModel(token, application) as  T
        }
        throw  IllegalArgumentException("View Model dari mana nih")
    }
}