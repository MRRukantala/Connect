package com.example.connect.presentation.main.product.detail

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.connect.data.database.SaveProductDataEntity
import com.example.connect.data.repository.DatabaseRepository
import com.example.connect.domain.entity.DetailProductEntity
import com.example.connect.domain.entity.SementaraEntity
import com.example.connect.domain.usecase.ProductUseCase
import com.example.connect.utilites.base.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class DetailProductViewModel @Inject constructor(
    private val useCase: ProductUseCase,
    private val repository: DatabaseRepository
):ViewModel() {

    private val _state = MutableStateFlow<DetailProductState>(DetailProductState.Init)
    val state get() = _state

    private val _data = MutableStateFlow<List<DetailProductEntity>>(mutableListOf())
    val data get() = _data


    private fun loading() {
        _state.value = DetailProductState.Loading()
    }

    private fun success(detailProductEntity: List<DetailProductEntity>){
        _state.value = DetailProductState.Success(detailProductEntity)
        _data.value = detailProductEntity
    }

    private fun error(detailProductEntity: SementaraEntity){
        _state.value = DetailProductState.Error(detailProductEntity)
    }

    fun detailProduct(id: Int){
        viewModelScope.launch {
            useCase.detailProduct(id)
                .onStart { loading()

                }.catch {

                }.collect{ result ->
                    when(result){
                        is Result.Success -> {
                            success(result.data)
                            Log.v("VIEWMODEL", _data.value.toString())
                        }
                        is Result.Error -> { }
                    }
                }
        }
    }
    val stateAddProduct = MutableLiveData<AddProdukState>()
    fun inputKeranjang(){
        val saveProductDataEntity = SaveProductDataEntity(
            0,
            _data.value[0].id,
            "",
            "",
            "",
            _data.value[0].gambar,
            _data.value[0].harga,
            _data.value[0].nama,
            _data.value[0].deskripsi,
            "",
            0

        )
        viewModelScope.launch(Dispatchers.IO) {

            try {
                repository.insert(saveProductDataEntity)
                stateAddProduct.postValue(AddProdukState.SUCCESS)
            } catch (e: Exception) {
                stateAddProduct.postValue(AddProdukState.ERROR)
                Log.v(
                    "DetailProductViewModel",
                    e.message.toString()
                )
            }


        }
    }

}
sealed class DetailProductState {
    object Init : DetailProductState()

    data class Loading(val loading: Boolean = true) : DetailProductState()
    data class Success(val detailProductEntity: List<DetailProductEntity>) : DetailProductState()
    data class Error(val response: SementaraEntity) : DetailProductState()
}

enum class AddProdukState { SUCCESS, LOADING, ERROR }