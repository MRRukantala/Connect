package com.example.connect.presentation.main.product.tabLayout.myproduct

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.connect.domain.entity.ProductEntity
import com.example.connect.utilites.base.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class MyProductViewModel @Inject constructor(
    private val useCase: UseCase
):ViewModel() {
    private val _state = MutableStateFlow<MyProductState>(MyProductState.Init)
    val state get() = _state

    private val _data = MutableStateFlow<List<ProductEntity>>(mutableListOf())
    val data get() = _data

    private fun loading() {
        _state.value = MyProductState.Loading()
    }

    private fun success(myProductEntity: List<ProductEntity>){
        _state.value = MyProductState.Success(myProductEntity)
        _data.value = myProductEntity
    }

    private fun error(myProductEntity: List<ProductEntity>){
        _state.value = MyProductState.Error(myProductEntity)
    }

    fun getAllProductByUser(id:Int){
        viewModelScope.launch {
            useCase.getAllProductByUser(id)
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

sealed class MyProductState {
    object Init : MyProductState()

    data class Loading(val loading: Boolean = true) : MyProductState()
    data class Success(val myProductEntity: List<ProductEntity>) : MyProductState()
    data class Error(val response: List<ProductEntity>) : MyProductState()
}