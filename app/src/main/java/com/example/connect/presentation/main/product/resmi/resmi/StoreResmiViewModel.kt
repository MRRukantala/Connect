package com.example.connect.presentation.main.product.resmi.resmi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.connect.domain.entity.ProductEntity
import com.example.connect.domain.usecase.UseCase
import com.example.connect.utilites.base.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StoreResmiViewModel @Inject constructor(
    private val useCase: UseCase
):ViewModel() {

    private val _state = MutableStateFlow<StoreResmiState>(StoreResmiState.Init)
    val state get() = _state

    private val _data = MutableStateFlow<List<ProductEntity>>(mutableListOf())
    val data get() = _data

    private fun loading() {
        _state.value = StoreResmiState.Loading()
    }

    private fun success(storeResmiEntity: List<ProductEntity>){
        _state.value = StoreResmiState.Success(storeResmiEntity)
        _data.value = storeResmiEntity
    }

    private fun error(storeResmiEntity: List<ProductEntity>){
        _state.value = StoreResmiState.Error(storeResmiEntity)
    }

    fun getAllProductByAdmin(){
        viewModelScope.launch {
            useCase.getProductByAdmin(2)
                .onStart { loading()

                }.catch {

                }.collect{ result ->
                    when(result){
                        is Result.Success -> success(result.data)
                        is Result.Error -> { }
                    }
                }
        }
    }



}

sealed class StoreResmiState {
    object Init : StoreResmiState()

    data class Loading(val loading: Boolean = true) : StoreResmiState()
    data class Success(val storeResmiEntity: List<ProductEntity>) : StoreResmiState()
    data class Error(val storeResmiEntity: List<ProductEntity>) : StoreResmiState()
}

