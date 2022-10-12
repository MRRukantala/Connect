package com.example.connect.presentation.main.ui.layanan.detail_artikel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.connect.presentation.main.ui.layanan.DataLayanan

class DetailArtikelMarOIViewModel(
    dataLayanan: DataLayanan,
    app: Application
) : AndroidViewModel(app) {

    private val _selectedLayanan = MutableLiveData<DataLayanan>()
    val selectedLayanan: LiveData<DataLayanan>
        get() = _selectedLayanan

    init {
        _selectedLayanan.value = dataLayanan
    }

}