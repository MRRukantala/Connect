package com.example.connect.presentation.main.product.detail

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.connect.domain.entity.DetailProductEntity
import com.example.connect.domain.entity.SementaraEntity
import com.example.connect.domain.usecase.UseCase
import com.example.connect.utilites.base.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class DetailProductViewModel @Inject constructor(
    private val useCase: UseCase
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
        Log.v("DATA", "${_data}")
    }

    private fun error(detailProductEntity: SementaraEntity){
        _state.value = DetailProductState.Error(detailProductEntity)
    }

    fun detailProduct(){
        viewModelScope.launch {
            useCase.detailProduct(34)
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
sealed class DetailProductState {
    object Init : DetailProductState()

    data class Loading(val loading: Boolean = true) : DetailProductState()
    data class Success(val detailProductEntity: List<DetailProductEntity>) : DetailProductState()
    data class Error(val response: SementaraEntity) : DetailProductState()
}