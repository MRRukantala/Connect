package com.example.connect.presentation.main.ui.product.tabLayout.myproduct.add

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.connect.domain.entity.SementaraEntity
import com.example.connect.domain.usecase.UseCase
import com.example.connect.presentation.main.ui.product.keranjang.KeranjangViewState
import com.example.connect.utilites.base.Result
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

class AddMyProductViewModelTerbaru @Inject constructor(
    private val useCase: UseCase
):ViewModel() {

    private val _state = MutableStateFlow<AddMyProductState>(AddMyProductState.Init)
    val state get() = _state

    private val _data = MutableStateFlow<Any>("")
    val data get() = _data

    private fun loading() {
        _state.value = AddMyProductState.Loading()
    }

    private fun success(detailProductEntity: SementaraEntity){
        _state.value = AddMyProductState.Success(detailProductEntity)
        _data.value = detailProductEntity
    }

    private fun error(detailProductEntity: SementaraEntity){
        _state.value = AddMyProductState.Error(detailProductEntity)
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

sealed class AddMyProductState {
    object Init : AddMyProductState()

    data class Loading(val loading: Boolean = true) : AddMyProductState()
    data class Success(val addMyProductEntity: SementaraEntity) : AddMyProductState()
    data class Error(val response: SementaraEntity) : AddMyProductState()
}

