package com.example.connect.presentation.main.product.keranjang.detail

import androidx.lifecycle.ViewModel
import com.example.connect.domain.entity.SementaraEntity
import com.example.connect.domain.usecase.UseCase
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

class DetailSavedProductViewModelTerbaru @Inject constructor(
    private val useCase: UseCase
):ViewModel()  {

    private val _state = MutableStateFlow<DetailSavedProductState>(DetailSavedProductState.Init)
    val state get() = _state

    private val _data = MutableStateFlow<Any>("")
    val data get() = _data

    private fun loading() {
        _state.value = DetailSavedProductState.Loading()
    }

    private fun success(detailProductEntity: SementaraEntity){
        _state.value = DetailSavedProductState.Success(detailProductEntity)
        _data.value = detailProductEntity
    }

    private fun error(detailProductEntity: SementaraEntity){
        _state.value = DetailSavedProductState.Error(detailProductEntity)
    }

//    fun register(){
//        viewModelScope.launch {
//            useCase.register()
//                .onStart { loading()
//
//                }.catch {
//
//                }.collect{ result ->
//                    when(result){
//                        is Result.Success -> success(result.data)
//                        is Result.Error -> { }
//                    }
//                }
//        }
//    }
}

sealed class DetailSavedProductState {
    object Init : DetailSavedProductState()

    data class Loading(val loading: Boolean = true) : DetailSavedProductState()
    data class Success(val detailProductEntity: SementaraEntity) : DetailSavedProductState()
    data class Error(val response: SementaraEntity) : DetailSavedProductState()
}