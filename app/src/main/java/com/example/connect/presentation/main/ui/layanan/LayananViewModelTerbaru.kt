package com.example.connect.presentation.main.ui.layanan

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.connect.domain.entity.SementaraEntity
import com.example.connect.domain.usecase.UseCase
import com.example.connect.presentation.RegisterActivityState
import com.example.connect.utilites.base.Result
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

class LayananViewModelTerbaru @Inject constructor(
    private val useCase: UseCase
):ViewModel() {
    private val _state = MutableStateFlow<LayananState>(LayananState.Init)
    val state get() = _state

    private val _data = MutableStateFlow<Any>("")
    val data get() = _data

    private fun loading() {
        _state.value = LayananState.Loading()
    }

    private fun success(layananEntity: SementaraEntity){
        _state.value = LayananState.Success(layananEntity)
        _data.value = layananEntity
    }

    private fun error(layananEntity: SementaraEntity){
        _state.value = LayananState.Error(layananEntity)
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

sealed class LayananState {
    object Init : LayananState()

    data class Loading(val loading: Boolean = true) : LayananState()
    data class Success(val layananEntity: SementaraEntity) : LayananState()
    data class Error(val response: SementaraEntity) : LayananState()
}