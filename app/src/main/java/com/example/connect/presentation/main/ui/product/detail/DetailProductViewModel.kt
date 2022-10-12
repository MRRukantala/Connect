package com.example.connect.presentation.main.ui.product.detail

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.connect.data.database.SaveProductData
import com.example.connect.data.database.SavedProductDAO
import com.example.connect.presentation.main.ui.product.model.ProductModel
import kotlinx.coroutines.*

class DetailProductViewModel(
    val id_user: Int,
    val database: SavedProductDAO,
    val productModel: ProductModel, application: Application
) : AndroidViewModel(application) {

    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    private val _selectedProductUmum = MutableLiveData<ProductModel>()
    val selectProductModel: LiveData<ProductModel>
        get() = _selectedProductUmum

    val state = MutableLiveData<AddProdukState>()

    init {
        _selectedProductUmum.value = productModel
    }

    fun inputKeranjang() {
        coroutineScope.launch {

            val newChoosedProduct = SaveProductData(
                0,
                productModel.id,
                productModel.name,
                productModel.level,
                productModel.photo,
                productModel.gambar,
                productModel.harga,
                productModel.nama_produk,
                productModel.deskripsi,
                productModel.wa,
                id_user
            )

            try {
                insert(newChoosedProduct)
                state.postValue(AddProdukState.SUCCESS)
            } catch (e: Exception) {
                state.postValue(AddProdukState.ERROR)
                Log.v(
                    "DetailProductViewModel",
                    e.message.toString()
                )
            }
        }
    }

    private suspend fun insert(saveProductData: SaveProductData) {
        return withContext(Dispatchers.IO) {
            database.insert(saveProductData)
        }

    }
}

enum class AddProdukState { SUCCESS, LOADING, ERROR }