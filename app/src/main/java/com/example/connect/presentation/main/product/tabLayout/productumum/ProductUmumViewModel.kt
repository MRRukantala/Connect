package com.example.connect.presentation.main.product.tabLayout.productumum

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
class ProductUmumViewModel @Inject constructor(
    private val useCase: UseCase
):ViewModel() {
    private val _state = MutableStateFlow<ProductUmumState>(ProductUmumState.Init)
    val state get() = _state

    private val _data = MutableStateFlow<List<ProductEntity>>(mutableListOf())
    val data get() = _data

    private fun loading() {
        _state.value = ProductUmumState.Loading()
    }

    private fun success(productUmumEntity: List<ProductEntity>){
        _state.value = ProductUmumState.Success(productUmumEntity)
        _data.value = productUmumEntity
    }

    private fun error(productUmumEntity: List<ProductEntity>){
        _state.value = ProductUmumState.Error(productUmumEntity)
    }

    fun getAllProduct(){
        viewModelScope.launch {
            useCase.getAllProduct()
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
    data class Success(val productUmumEntity: List<ProductEntity>) : ProductUmumState()
    data class Error(val response: List<ProductEntity>) : ProductUmumState()
}