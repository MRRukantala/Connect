package com.example.connect.presentation.main.ui.product.tabLayout.myproduct

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.connect.domain.entity.SementaraEntity
import com.example.connect.domain.usecase.UseCase
import com.example.connect.presentation.main.ui.product.tabLayout.myproduct.add.AddMyProductState
import com.example.connect.utilites.base.Result
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

class MyProductViewModelTerbaru @Inject constructor(
    private val useCase: UseCase
):ViewModel() {
    private val _state = MutableStateFlow<MyProductState>(MyProductState.Init)
    val state get() = _state

    private val _data = MutableStateFlow<Any>("")
    val data get() = _data

    private fun loading() {
        _state.value = MyProductState.Loading()
    }

    private fun success(myProductEntity: SementaraEntity){
        _state.value = MyProductState.Success(myProductEntity)
        _data.value = myProductEntity
    }

    private fun error(myProductEntity: SementaraEntity){
        _state.value = MyProductState.Error(myProductEntity)
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

sealed class MyProductState {
    object Init : MyProductState()

    data class Loading(val loading: Boolean = true) : MyProductState()
    data class Success(val myProductEntity: SementaraEntity) : MyProductState()
    data class Error(val response: SementaraEntity) : MyProductState()
}