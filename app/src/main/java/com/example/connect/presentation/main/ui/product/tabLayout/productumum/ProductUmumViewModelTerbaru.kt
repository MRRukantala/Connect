package com.example.connect.presentation.main.ui.product.tabLayout.productumum

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.connect.domain.entity.SementaraEntity
import com.example.connect.domain.usecase.UseCase
import com.example.connect.presentation.main.ui.product.tabLayout.myproduct.MyProductState
import com.example.connect.utilites.base.Result
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

class ProductUmumViewModelTerbaru @Inject constructor(
    private val useCase: UseCase
):ViewModel() {
    private val _state = MutableStateFlow<ProductUmumState>(ProductUmumState.Init)
    val state get() = _state

    private val _data = MutableStateFlow<Any>("")
    val data get() = _data

    private fun loading() {
        _state.value = ProductUmumState.Loading()
    }

    private fun success(productUmumEntity: SementaraEntity){
        _state.value = ProductUmumState.Success(productUmumEntity)
        _data.value = productUmumEntity
    }

    private fun error(productUmumEntity: SementaraEntity){
        _state.value = ProductUmumState.Error(productUmumEntity)
    }

    fun register(){
        viewModelScope.launch {
            useCase.register()
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

sealed class ProductUmumState {
    object Init : ProductUmumState()

    data class Loading(val loading: Boolean = true) : ProductUmumState()
    data class Success(val productUmumEntity: SementaraEntity) : ProductUmumState()
    data class Error(val response: SementaraEntity) : ProductUmumState()
}