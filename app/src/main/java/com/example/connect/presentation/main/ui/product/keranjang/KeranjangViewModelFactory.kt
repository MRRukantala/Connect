package com.example.connect.presentation.main.ui.product.keranjang

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.connect.data.database.SavedProductDAO
import java.lang.IllegalArgumentException

class KeranjangViewModelFactory(
    private val idUser: Int,
    private val dataSource: SavedProductDAO,
    private val application: Application
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(KeranjangViewModel::class.java)){
            return KeranjangViewModel(idUser, dataSource, application) as T
        }
        throw IllegalArgumentException("ViewModel dari mana nih")
    }
}