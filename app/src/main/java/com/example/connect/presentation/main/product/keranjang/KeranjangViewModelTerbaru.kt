package com.example.connect.presentation.main.product.keranjang

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.connect.data.database.SaveProductDataEntity
import com.example.connect.domain.entity.SementaraEntity
import com.example.connect.domain.usecase.KeranjangUseCase
import com.example.connect.utilites.base.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class KeranjangViewModelTerbaru @Inject constructor(
    private val case: KeranjangUseCase
) : ViewModel() {
    private val _state = MutableStateFlow<KeranjangViewState>(KeranjangViewState.Init)
    val state get() = _state

    private val _data = MutableStateFlow<List<SaveProductDataEntity>>(mutableListOf())
    val data get() = _data

    private fun loading() {
        _state.value = KeranjangViewState.Loading()
    }

    private fun success(data: List<SaveProductDataEntity>) {
        _state.value = KeranjangViewState.Success(data)
        _data.value = data
    }

    private fun error(data: List<SaveProductDataEntity>) {
        _state.value = KeranjangViewState.Error(data)
    }

    fun getDataById(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            case.getDataKeranjangByIdUser(id).onStart {
            }.catch {
                loading()
            }.collect {
                    result ->
                when(result){
                    is Result.Success -> {
                        success(result.data)
                    }
                    is Result.Error -> { }
                }
            }

        }
    }
}

sealed class KeranjangViewState {
    object Init : KeranjangViewState()

    data class Loading(val loading: Boolean = true) : KeranjangViewState()
    data class Success(val keranjangEntity: List<SaveProductDataEntity>) : KeranjangViewState()
    data class Error(val response: List<SaveProductDataEntity>) : KeranjangViewState()
}