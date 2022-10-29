package com.example.connect.presentation.main.product.keranjang

import androidx.lifecycle.ViewModel
import com.example.connect.domain.entity.SementaraEntity
import com.example.connect.domain.usecase.ProductUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class KeranjangViewModelTerbaru @Inject constructor(
    private val useCase: ProductUseCase
):ViewModel() {
    private val _state = MutableStateFlow<KeranjangViewState>(KeranjangViewState.Init)
    val state get() = _state

    private val _data = MutableStateFlow<Any>("")
    val data get() = _data

    private fun loading() {
        _state.value = KeranjangViewState.Loading()
    }

    private fun success(detailProductEntity: SementaraEntity){
        _state.value = KeranjangViewState.Success(detailProductEntity)
        _data.value = detailProductEntity
    }

    private fun error(detailProductEntity: SementaraEntity){
        _state.value = KeranjangViewState.Error(detailProductEntity)
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

sealed class KeranjangViewState {
    object Init : KeranjangViewState()

    data class Loading(val loading: Boolean = true) : KeranjangViewState()
    data class Success(val keranjangEntity: SementaraEntity) : KeranjangViewState()
    data class Error(val response: SementaraEntity) : KeranjangViewState()
}