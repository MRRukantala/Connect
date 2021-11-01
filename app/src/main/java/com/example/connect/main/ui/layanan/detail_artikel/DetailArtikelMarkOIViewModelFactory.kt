package com.example.connect.main.ui.layanan.detail_artikel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.connect.main.ui.layanan.DataLayanan

class DetailArtikelMarkOIViewModelFactory(
    private val dataLayanan: DataLayanan,
    private val application: Application?
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailArtikelMarOIViewModel::class.java)) {
            return DetailArtikelMarOIViewModel(dataLayanan, application!!) as T
        }
        throw  IllegalArgumentException("View Model dari mana nih")
    }

}